package calcultator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Write:");
        String input = reader.readLine();
        Lexer lexer = new Lexer(new StringReader(input));
        Parser parser = new Parser(lexer);
        int result = parser.statement();
        System.out.println("Result is: " + result);
        System.exit(0);
    }
}

