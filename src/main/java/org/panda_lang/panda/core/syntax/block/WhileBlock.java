package org.panda_lang.panda.core.syntax.block;

import org.panda_lang.panda.core.ElementsBucket;
import org.panda_lang.panda.core.parser.improved.Atom;
import org.panda_lang.panda.core.parser.improved.essential.CustomParser;
import org.panda_lang.panda.core.parser.improved.essential.ParameterParser;
import org.panda_lang.panda.core.scheme.BlockScheme;
import org.panda_lang.panda.core.syntax.Block;
import org.panda_lang.panda.core.syntax.Parameter;
import org.panda_lang.panda.lang.PBoolean;
import org.panda_lang.panda.lang.PObject;

public class WhileBlock extends Block {

    static {
        ElementsBucket.registerBlock(new BlockScheme(WhileBlock.class, "while").parser(new CustomParser() {
            @Override
            public Block parse(Atom atom) {
                Block current = new WhileBlock();
                current.setParameters(new ParameterParser().parse(atom, atom.getBlockInfo().getParameters()));
                return current;
            }
        }));
    }

    public WhileBlock() {
        super.setName("WhileBlock");
    }

    @Override
    public PObject run(Parameter... vars) {
        while (parameters[0].getValue(PBoolean.class).isTrue()) {
            PObject o = super.run(vars);
            if (o != null) {
                return o;
            }
        }
        return null;
    }

}