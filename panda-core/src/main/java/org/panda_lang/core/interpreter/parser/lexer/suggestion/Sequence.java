package org.panda_lang.core.interpreter.parser.lexer.suggestion;

import org.panda_lang.core.interpreter.parser.lexer.Token;
import org.panda_lang.core.interpreter.parser.lexer.TokenType;

public class Sequence implements Token {

    private final String sequenceStart;
    private final String sequenceEnd;

    public Sequence(char sequence) {
        this(Character.toString(sequence));
    }

    public Sequence(String sequence) {
        this(sequence, sequence);
    }

    public Sequence(String sequenceStart, String sequenceEnd) {
        this.sequenceStart = sequenceStart;
        this.sequenceEnd = sequenceEnd;
    }

    public String getSequenceStart() {
        return sequenceStart;
    }

    public String getSequenceEnd() {
        return sequenceEnd;
    }

    @Override
    public String getToken() {
        return getSequenceStart() + getSequenceEnd();
    }

    @Override
    public TokenType getType() {
        return TokenType.SEQUENCE;
    }

}