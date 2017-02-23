/*
 * Copyright (c) 2015-2017 Dzikoysk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.panda_lang.panda.language.structure.prototype.parser;

import org.panda_lang.framework.interpreter.lexer.token.TokenType;
import org.panda_lang.framework.interpreter.lexer.token.TokenizedSource;
import org.panda_lang.framework.interpreter.lexer.token.distributor.SourceStream;
import org.panda_lang.framework.interpreter.lexer.token.extractor.Extractor;
import org.panda_lang.framework.interpreter.lexer.token.reader.TokenReader;
import org.panda_lang.framework.interpreter.parser.ParserInfo;
import org.panda_lang.framework.interpreter.parser.UnifiedParser;
import org.panda_lang.framework.interpreter.parser.generation.ParserGeneration;
import org.panda_lang.framework.interpreter.parser.util.Components;
import org.panda_lang.framework.structure.Statement;
import org.panda_lang.panda.implementation.interpreter.lexer.token.extractor.TokenPattern;
import org.panda_lang.panda.implementation.interpreter.parser.PandaParserException;
import org.panda_lang.panda.implementation.interpreter.parser.ParserRegistration;
import org.panda_lang.panda.language.structure.prototype.ClassPrototype;

import java.util.List;

@ParserRegistration(parserClass = ClassPrototypeParser.class, handlerClass = ClassPrototypeParserHandler.class)
public class ClassPrototypeParser implements UnifiedParser {

    protected static final TokenPattern PATTERN = TokenPattern.builder()
            .unit(TokenType.KEYWORD, "class")
            .gap()
            .unit(TokenType.SEPARATOR, "{")
            .gap()
            .unit(TokenType.SEPARATOR, "}")
            .build();

    @Override
    public Statement parse(ParserInfo parserInfo) {
        SourceStream source = parserInfo.getComponent(Components.SOURCE_STREAM);
        ParserGeneration generation = parserInfo.getComponent(Components.GENERATION);

        Extractor extractor = PATTERN.extractor();
        TokenReader reader = source.toTokenReader();
        List<TokenizedSource> gaps = extractor.extract(reader);

        if (gaps == null) {
            throw new PandaParserException("Mismatched parser to the specified source");
        }

        source.readDifference(reader);

        String className = gaps.get(0).getToken(0).getTokenValue();
        ClassPrototype classPrototype = new ClassPrototype(className);

        return new ClassPrototypeReference(classPrototype);
    }

}
