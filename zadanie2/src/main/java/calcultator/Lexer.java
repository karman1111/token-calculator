package calcultator;

import java.io.IOException;
import java.io.Reader;

public class Lexer {
    private int current;
    private int value;
    private final Reader input;
    public Lexer(Reader reader) {
        this.input = reader;
        consume();
    }
    public Token nextToken() {
        while (Character.isWhitespace(current)) {
            consume();
        }

        if (Character.isDigit(current)) {
            return number();
        }

        switch (current) {
            case '+':
            case '⊕':
                consume();
                return Token.PLUS;
            case '-':
                consume();
                return Token.MINUS;
            case '*':
            case 'x':
                if(current == '*') {
                    consume();
                    return Token.MUL;
                }else{
                    consume();
                    if(current == 'x'){
                        consume();
                         if(current == 'x'){
                            consume();
                            return Token.MUL;
                        }
                    }
                }
            case '/':
                consume();
                return Token.DIV;
            case '^':
                consume();
                return Token.POWER;
            case '(':
                consume();
                return Token.LPAR;
            case ')':
                consume();
                return Token.RPAR;
            case -1:
                return Token.EOF;
            default:
                throw new CalculatorException("Unexpected character: " + (char) current);
        }
    }

    private Token number() {
        String buffer = "";

        while(Character.isDigit(current)){
            buffer += ((char)current);
            consume();
        }

        this.value = Integer.parseInt(buffer);

        return Token.NUMBER;
    }

    private void consume() {
        try {
            this.current = this.input.read();
        } catch (IOException ex) {
            this.current = -1;
        }
    }

    public int getValue() {
        return value;
    }
}
