/**
 * Class that contains main method that tests added methods.
 * @author Adrian 
 */

package main.mainmethod;
import java.util.Scanner;
import main.implementations.LinkedStack;
import main.implementations.ResizeableArrayStack;
import main.interfaces.StackInterface;


public class Calculator {
    /**
     * Main method that takes infix expression from user and turns it into equivilant postfix and then evaluates it. 
     */
    public static void main(String[] args){
        Scanner scnr = new Scanner(System.in);
        String postfix; //equivilant postfix equation
        double result; //the postfix expression evaluated
        
        //get infix equation from user
        System.out.println("Type in expression");
        String infix = scnr.nextLine();

        //converting to postfix 
        postfix = convertToPostfix(infix); //calls method to convert
        System.out.println(postfix);
        
        //evaluating and printing answer
        result = evaluatePostFix(postfix); //calls method to evaluate
        System.out.println("The result is: " + result);

        scnr.close();
    } //end main method

    /**
     * Converts an infix expression to an equivalent postfix expression.
     * @param infix The expression given by the user as a String.
     * @return equivilant postfix expression as a String.
     */
    public static String convertToPostfix(String infix){

        StackInterface<Character> operatorStack = new LinkedStack<>(); //new empty Linked stack
        String postfix = ""; //new empty String
        char topOperator;

        for(int i = 0; i<infix.length(); ++i) //traverses infix
        {
            //get current character
            char character = infix.charAt(i);
            
            //if character is a variable
            if(Character.isLetter(character))
                postfix = postfix + character; //adds to postfix
            else{ //if not variable
                
                switch (character)
                {
                    case '^' :
                        operatorStack.push(character);
                        break;
                    case '+' : case '-' : case '*' : case '/' :
                        while (!operatorStack.isEmpty())
                        {
                            topOperator = operatorStack.peek();
                            //if the operator has lower priority then top of the stack
                            if(getPriority(character) <= getPriority(topOperator)){
                                postfix = postfix + topOperator;
                                operatorStack.pop();
                            }
                            else{
                                break;
                            }
                        }
                        operatorStack.push(character);
                        break;
                    case '(' : 
                        operatorStack.push(character);
                        break;
                    case ')' : 
                        topOperator = operatorStack.pop();
                        while (topOperator != '(')
                        {
                            postfix = postfix + topOperator;
                            topOperator = operatorStack.pop();
                        }//end while
                        break;
                    default:
                        break; // If is blank or not an operator
                }
            }
        }

        while (!operatorStack.isEmpty()) //adding reamining to the postfix string
        {
            topOperator = operatorStack.pop();
            postfix = postfix + topOperator;
        }
        
        //return completed postfix
        return postfix;
  
    }

    /**
     * Evaluates a postfix expression.
     * @param postfix expression as a String.
     * @return float result of the evaulation.
     */
    public static double evaluatePostFix(String postfix){

        StackInterface<Double> valueStack = new ResizeableArrayStack<>(); //new empty ResizableArray stack
        double result= 0;
        
        for (int i =0; i<postfix.length(); ++i) 
        {
            char character = postfix.charAt(i);

            if(Character.isLetter(character))//if character is varaible get assigned value
                valueStack.push(getValue(character));
            else{
                switch (character)
                {
                    case '+' : case '-' : case '*' : case '/' : case '^' :
                        double operandTwo = valueStack.pop();
                        double operandOne = valueStack.pop();
                        result = getResult(character, operandTwo, operandOne); //perform opertion between two operands
                        valueStack.push(result);
                        break;
                    default: 
                        break; // Ignore unexpected characters
                }
            }
        }
        //return the result
        return valueStack.peek();
    }
    
    /**
     * Returns the mathematical priority of an operator.
     * @param operator in the expression. 
     * @return integer that assigns prority. The higher, the more higer precedence it has it has.
     */
    private static int getPriority (char operator)
    {
        switch (operator)
        {
            case '(':
            case ')':
                return 0;
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        } // end switch
        return -1;
    } // end

    /**
     * Gets the result of the operation between two operands using the operator.
     * @param character The operator.
     * @param operandTwo The second operand.
     * @param operandOne The first operand.
     * @return the result of the operation.
     */
    public static double getResult(char character, double operandTwo, double operandOne){
        double result = 0.0;
        switch(character)
        {
            case '+': 
                result = operandOne + operandTwo;
                break;
            case '-': 
                result = operandOne - operandTwo;
                break;
            case '*':
                result = operandOne * operandTwo;
                break;
            case '/': 
                result = operandOne / operandTwo;
                break;
            case '^':
                result = Math.pow(operandOne, operandTwo);
                break;
        }
        return result;
    }

    /**
     * Gets the value assigned to a variable. 
     * @param operator The current variable.
     * @return The value assigned to the variable.
     */
    private static double getValue(char operator){
        double value = 0;
        switch (operator){
            case 'a':
                value = 2;
                break;
            case 'b':
                value = 3;
                break;
            case 'c':
                value = 4;
                break;
            case 'd':
                value = 5;
                break;
            case 'e':
                value = 6;
                break;
        }
        return value;
    }


}
