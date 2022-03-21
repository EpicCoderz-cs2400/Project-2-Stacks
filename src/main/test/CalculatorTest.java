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
     * Method that checks for the postfix of given equations 
     * Assumes that 
     */
    @Test
    public void TestInfixToPostFix(){
        assertEquals("ab*ca-/de*+", Calculator.convertToPostfix("a*b/(c-a)+d*e")); //inital project problem
        assertEquals("ab/cde-+*", Calculator.convertToPostfix("a/b*(c+(d-e))")); //given textbook problem
        assertEquals("ba*ea-+cc^+", Calculator.convertToPostfix("b*a+(e-a)+c^c")); //testing equation that includes exponenet
        assertEquals("ab+c+d+c+", Calculator.convertToPostfix("a+b+c+d+c")); //testing equation that only adds
    }

}
