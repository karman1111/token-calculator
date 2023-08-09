package tests;

import calcultator.CalculatorException;
import calcultator.Lexer;
import calcultator.Parser;
import org.testng.annotations.Test;

import java.io.StringReader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class Testing {

    @Test
    public void check_simple_addition_and_subtraction() {
        Lexer lexer = new Lexer(new StringReader("2 + 2 - 4 ⊕ 5"));
        Parser parser = new Parser(lexer);
        assertEquals(parser.statement(), 5);
    }

    @Test
    public void check_simple_multiplication() {
        Lexer lexer = new Lexer(new StringReader("2 * 12"));
        Parser parser = new Parser(lexer);
        assertEquals(parser.statement(), 24);
    }

    @Test
    public void check_simple_division() {
        Lexer lexer = new Lexer(new StringReader("12 / 4"));
        Parser parser = new Parser(lexer);
        assertEquals(parser.statement(), 3);
    }

    @Test
    public void check_the_priority_of_multiplication_and_division() {
        Lexer lexer = new Lexer(new StringReader("2 * 12 / 3"));
        Parser parser = new Parser(lexer);
        assertEquals(parser.statement(), 8);
    }

    @Test
    public void check_the_priority_in_brackets() {
        Lexer lexer = new Lexer(new StringReader("(2 + 3) * 4"));
        Parser parser = new Parser(lexer);
        assertEquals(parser.statement(), 20);
    }

    @Test
    public void check_with_a_minus_next_to_the_number() {
        Lexer lexer = new Lexer(new StringReader("-15-(-5)"));
        Parser parser = new Parser(lexer);
        assertEquals(parser.statement(), -10);
    }

    @Test
    public void check_degree() {
        Lexer lexer = new Lexer(new StringReader("-10 * 4^(4-2)"));
        Parser parser = new Parser(lexer);
        assertEquals(parser.statement(), -160);
    }

    @Test
    public void check_all_together() {
        Lexer lexer = new Lexer(new StringReader("8^2 / (22 - 6) - 12"));
        Parser parser = new Parser(lexer);
        assertEquals(parser.statement(), -8);
    }

    @Test
    public void check_big() {
        Lexer lexer = new Lexer(new StringReader("((4 + 3) * 2 - 5 * (-2 / 2))"));
        Parser parser = new Parser(lexer);
        assertEquals(parser.statement(), 19);
    }

    @Test
    public void check_exception_1() {
        Lexer lexer = new Lexer(new StringReader("((4 + 3) * 2 - 5 * (-2 / 2)"));
        Parser parser = new Parser(lexer);
        assertThrows(CalculatorException.class, () -> parser.statement());
    }

    @Test
    public void check_exception_2() {
        Lexer lexer = new Lexer(new StringReader("--5"));
        Parser parser = new Parser(lexer);
        assertThrows(CalculatorException.class, () -> parser.statement());
    }

    @Test
    public void divinity_by_zero() {
        Lexer lexer = new Lexer(new StringReader("20 / 0"));
        Parser parser = new Parser(lexer);
        assertThrows(CalculatorException.class, () -> parser.statement());
    }

    @Test
    public void test() {
        Lexer lexer = new Lexer(new StringReader("(((20 * 3) / 6) + 15) / 5"));
        Parser parser = new Parser(lexer);
        assertEquals(parser.statement(), 5);
    }
}
