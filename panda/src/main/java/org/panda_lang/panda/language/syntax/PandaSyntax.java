/*
 * Copyright (c) 2015-2018 Dzikoysk
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

package org.panda_lang.panda.language.syntax;

import org.panda_lang.panda.framework.language.interpreter.lexer.Syntax;
import org.panda_lang.panda.framework.language.interpreter.token.Token;
import org.panda_lang.panda.framework.language.interpreter.token.defaults.*;
import org.panda_lang.panda.language.syntax.tokens.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PandaSyntax implements Syntax {

    private static final PandaSyntax INSTANCE = new PandaSyntax();

    private final List<Keyword> keywords;
    private final List<Literal> literals;
    private final List<Separator> separators;
    private final List<Operator> operators;
    private final List<Sequence> sequences;
    private char[] specialCharacters;

    private PandaSyntax() {
        this.keywords = new ArrayList<>();
        this.literals = new ArrayList<>();
        this.separators = new ArrayList<>();
        this.operators = new ArrayList<>();
        this.sequences = new ArrayList<>();
        this.specialCharacters = Characters.getSpecialCharacters();

        this.initialize();
    }

    private void initialize() {
        Collections.addAll(keywords, Keywords.values());
        Collections.addAll(literals, Literals.values());
        Collections.addAll(separators, Separators.values());
        Collections.addAll(operators, Operators.values());
        Collections.addAll(sequences, Sequences.values());

        Comparator<Token> tokenComparator = new Comparator<Token>() {
            @Override
            public int compare(Token x, Token y) {
                return Integer.compare(y.getTokenValue().length(), x.getTokenValue().length());
            }
        };

        keywords.sort(tokenComparator);
        literals.sort(tokenComparator);
        separators.sort(tokenComparator);
        operators.sort(tokenComparator);
        sequences.sort(tokenComparator);
    }

    public void setSpecialCharacters(char[] specialCharacters) {
        this.specialCharacters = specialCharacters;
    }

    @Override
    public char[] getSpecialCharacters() {
        return specialCharacters;
    }

    @Override
    public List<Sequence> getSequences() {
        return sequences;
    }

    @Override
    public List<Operator> getOperators() {
        return operators;
    }

    @Override
    public List<Separator> getSeparators() {
        return separators;
    }

    @Override
    public List<Literal> getLiterals() {
        return literals;
    }

    @Override
    public List<Keyword> getKeywords() {
        return keywords;
    }

    public static PandaSyntax getInstance() {
        return INSTANCE;
    }

}
