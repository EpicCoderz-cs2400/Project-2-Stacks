/**
 * Class used to test additional methods added.
 * Uses JUnit 5 1.7.0
 * @author Adrian
 */

package main.test;
import static org.junit.Assert.assertEquals;
import main.mainmethod.Calculator;
import org.junit.Test;

public class CalculatorTest{
    
    /**
     * Method that checks if calculated postfix expression is equal to actual. 
     * Assumes that all variables are single digit and operators are typed in the correct order.
     */
    @Test
    public void TestInfixToPostFix(){
        assertEquals("ab*ca-/de*+", Calculator.convertToPostfix("a*b/(c-a)+d*e")); //inital project problem
        assertEquals("ab/cde-+*", Calculator.convertToPostfix("a/b*(c+(d-e))")); //inital given textbook problem
        assertEquals("ba*ea-+cc^+", Calculator.convertToPostfix("b*a+(e-a)+c^c")); //testing equation that includes exponent
        assertEquals("ab+cd-/", Calculator.convertToPostfix("(a+b)/(c-d)")); //problem 1 given by the textbook
        assertEquals("abc-/d*", Calculator.convertToPostfix("a/(b-c)*d")); //problem 2 given by the textbook 
        assertEquals("abcd-/e*d+e^-", Calculator.convertToPostfix("a-(b/(c-d)*e+d)^e")); //problem 3 given by the textbook
        assertEquals("abc*-def^*g*h+/", Calculator.convertToPostfix("(a-b*c)/(d*e^f*g+h)")); //problem 4 given by the textbook
        assertEquals("abc+*c-d+e*", Calculator.convertToPostfix("((((a*(b+c))-c)+d)*e)")); //more complex expreesion filled with paranthesis
        assertEquals("ab+c+d+", Calculator.convertToPostfix("a+b+c+d")); //only adding
        assertEquals("ab-c+dea*+*", Calculator.convertToPostfix("(a-b+c)*(d+e*a)")); //
    }

    /**
     * Method that checks if the calculated evalueation of a postfix equation is equal to actual.
     * Uses already tested expressions from testInfixToPostFix.
     * 
     */
    @Test
    public void TestEvaluatePostfix(){
        assertEquals(33, Calculator.evaluatePostFix("ab*ca-/de*+"), 0.0001); //inital project problem
        assertEquals(2, Calculator.evaluatePostFix("ab/cde-+*"), 0.0001); //inital given textbook problem
        assertEquals(266, Calculator.evaluatePostFix("ba*ea-+cc^+"), 0.001); //testing equation that includes exponent
        assertEquals(-5, Calculator.evaluatePostFix("ab+cd-/"), 0.001); //problem 1 given by the textbook (negative answer)
        assertEquals(-10, Calculator.evaluatePostFix("abc-/d*"), 0.001); //problem 2 given by the textbook
        assertEquals(-4826807, Calculator.evaluatePostFix("abcd-/e*d+e^-"), 0.001); //problem 3 given by the textbook
    }

    /**
     * Method that checks if the result given by getResult method is correct. 
     */
    @Test
    public void TestGetResult(){
        assertEquals(256, Calculator.getResult('^', 4, 4), 0.001); //test exponenet operand
        assertEquals(8, Calculator.getResult('+', 6, 2), 0.001); //test addition operand with positive values
        assertEquals(-4, Calculator.getResult('+', -6, 2), 0.001); //test addition operand with negative values
        assertEquals(-3, Calculator.getResult('-', 5, 2), 0.001); //test subtraction operand
        assertEquals(18, Calculator.getResult('*', 6, 3), 0.001); //test multiplication operand 
        assertEquals(0.5, Calculator.getResult('/', 4, 2), 0.001); //test division operand 
    }

}
