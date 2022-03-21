/**
 * Class used to test additional methods added.
 * Uses JUnit 5 1.7.0
 * 
 * @author Adrian
 */

package main.test;
import static org.junit.Assert.assertEquals;
import main.mainmethod.Calculator;
import org.junit.Test;

public class CalculatorTest{
    
    @Test
    public void addsTwoNumbers(){ //
        assertEquals(10, Calculator.getResult('+', 5, 5), 0.001); //two positive numbers 
        assertEquals(6, Calculator.getResult('+', 10, -4), 0.001); //one negative and one positive     
    }

    /**
     * Method that checks for the postfix of given equations. 
     * Assumes that all variables are single digit and in the correct order.
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
    }

    /**
     * Method that evalueates a postfix equation.
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

    @Test
    public void TestEponent(){
        assertEquals(256, Calculator.getResult('^', 4, 4), 0.001);
    }
}
