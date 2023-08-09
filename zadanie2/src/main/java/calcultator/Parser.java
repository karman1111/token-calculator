package calcultator;

public class Parser {
    private final Lexer lexer;
    private Token symbol;
    public Parser(Lexer lexer) {
        this.lexer = lexer;
        consume();
    }
    public int statement() {
        int result = T();
        if (symbol != Token.EOF) {
            throw new CalculatorException("Unexpected symbol: " + symbol);
        }
        return result;
    }
    private int T() {
        int left = F();
        while (symbol == Token.PLUS || symbol == Token.MINUS) {
            if (symbol == Token.PLUS) {
                consume();
                left += F();
            } else {
                consume();
                left -= F();
            }
        }
        return left;
    }

    private int F() {
        int left = P();
        while (symbol == Token.MUL) {
            consume();
            left *= P();
        }
        return left;
    }

    private int P() {
        int left = S();
        while (symbol == Token.DIV) {
            consume();
            int right = S();
            if(right == 0){
                throw new CalculatorException("Divinity by zero");
            }
            left /= right;
        }
        return left;
    }

    private int S() {
        if (symbol == Token.MINUS) {
            consume();
            return -A();
        }
        return A();
    }

    private int A() {
        int left = B();
        if (symbol == Token.POWER) {
            consume();
            int right = A();
            left = (int) Math.pow(left, right);
        }
        return left;
    }

    private int B() {
        if (symbol == Token.NUMBER) {
            int value = lexer.getValue();
            consume();
            return value;
        } else if (symbol == Token.LPAR) {
            consume();
            int value = T();
            if (symbol != Token.RPAR) {
                throw new CalculatorException("Ather parent not found");
            }
            consume();
            return value;
        } else {
            throw new CalculatorException("Unexpected symbol: " + symbol);
        }
    }

    private void consume() {
        symbol = lexer.nextToken();
    }
}
