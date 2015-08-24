package net.dzikoysk.panda.core.parser;

import net.dzikoysk.panda.PandaScript;
import net.dzikoysk.panda.core.GlobalVariables;
import net.dzikoysk.panda.core.parser.util.CodePatcher;
import net.dzikoysk.panda.core.parser.util.Error;
import net.dzikoysk.panda.core.syntax.Block;
import net.dzikoysk.panda.core.syntax.Variable;
import net.dzikoysk.panda.core.syntax.block.ScriptBlock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

public class PandaParser {

	private static PandaParser currentParser;
	private final String[] source;
	private final Recognizer recognizer;
	private final Collection<Block> blocks;
	private PandaScript script;
	private BlockParser parser;
	private Block latest;
	private int i, currentLine;

	public PandaParser(String source){
		this.source = CodePatcher.patch(source);
		this.recognizer = new Recognizer();
		this.blocks = new ArrayList<>();
		currentParser = this;
	}

	public PandaScript parse(){
		script = new PandaScript();
		ScriptBlock me = new ScriptBlock();
		me.setVariableMap(GlobalVariables.VARIABLES);
		try {
			Stack<Character> characters = new Stack<>();
			StringBuilder node = new StringBuilder();
			for (i = 0; i < source.length; i++) {
				String line = source[i];

				SyntaxIndication indi = recognizer.recognize(line);
				if (indi == null) {
					String info = recognizer.getLineIndication(line).toLowerCase();
					if(info.equals("else")) {
						indi = SyntaxIndication.SECTION;
					} else {
						System.out.println("Error at line " + i);
						net.dzikoysk.panda.core.parser.util.Error error = new Error("[SyntaxIndication] Not detected: " + line);
						error.print();
						break;
					}
				}

				if(indi == SyntaxIndication.COMMENT){
					continue;
				}
				else if(indi == SyntaxIndication.SECTION){
					if(characters.size() == 0) currentLine = i;
					characters.push('{');
				}
				else if(indi == SyntaxIndication.CLOSE){
					if(characters.size() != 0) characters.pop();
					if(characters.size() == 0) {
						String sectionSource = node.toString();
						node.setLength(0);
						parser = new BlockParser(script, recognizer, sectionSource);
						parser.setParent(me);
						parser.setLatest(latest);
						Block block = parser.parse(this);
						latest = block;
						if(block == null){
							System.out.println("S: " + sectionSource);
							System.out.println("[" + i + ":~" + parser.getCurrentLine() + "] Something went wrong...");
							return null;
						}
						blocks.add(block);
						continue;
					}
				}
				else if(indi == SyntaxIndication.VARIABLE && characters.size() == 0 && node.length() != 0){
					VariableParser parser = new VariableParser(me, node.toString());
					Variable variable = parser.parse();
					me.addExecutable(variable);
					node.setLength(0);
					continue;
				}

				node.append(line);
				node.append(System.lineSeparator());

			}
		} catch (Exception e){
			int l = 0;
			if(parser != null) l = parser.getCurrentLine();
			System.out.println("Error at line " + (i + l));
			e.printStackTrace();
		}

		for(Block block : blocks) script.addSection(block);
		return script.name(null).author(null).version(null);
	}


	protected void addSection(Block block){
		this.blocks.add(block);
	}

	protected void setCurrentLine(int i){
		this.currentLine = i;
	}

	public PandaScript getScript(){
		return script;
	}

	public Collection<Block> getBlocks() {
		return blocks;
	}

	public String[] getSource() {
		return source;
	}

	public static PandaParser getCurrentInstance(){
		return currentParser;
	}

}