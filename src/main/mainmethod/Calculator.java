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
     * Main method that takes expression from user and turns it into postfix, then evaluates it. 
     */
    public static void main(String[] args){
        Scanner scnr = new Scanner(System.in);
        String postfix;
        double result;
        
        //get eqation from user
        System.out.println("Type in expression");
        String infix = scnr.nextLine();

        //converting to postfix 
        postfix = convertToPostfix(infix);
        System.out.println(postfix);
        
        //evaluating and printing answer
        result = evaluatePostFix(postfix);
        System.out.println("The result is: " + result);

        scnr.close(); //close scanner

    } //end main method

    /**
     * Converts an infix expression to an equivalent postfix expression.
     * @param infix expression as a String.
     * @return The postfix expression as a String.
     */
    public static String convertToPostfix(String infix){

        StackInterface<Character> operatorStack = new LinkedStack<>(); //new empty Linked stack
        String postfix = ""; //new empty String
        char topOperator;

        for(int i = 0; i<infix.length(); ++i) //(curCar !=null)
        {
            //get current character
            char character = infix.charAt(i);
            
            //if character is a variable
            if(Character.isLetter(character))
                postfix = postfix + character;
            else{ //if not variable
                
                switch (character)
                {
                    //exponent
                    case '^' :
                        operatorStack.push(character);
                        break;
                    case '+' : case '-' : case '*' : case '/' :
                        while (!operatorStack.isEmpty())//and precedence of nextCharacter <= precedence of operatorStack.peek()
                        {
                            topOperator = operatorStack.peek();
                            if(getPrecedence(character) <= getPrecedence(topOperator)){
                                postfix = postfix + topOperator;
                                operatorStack.pop();
                            }
                            else{
                                break;
                            }
                        }//end while-loop
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
                }//end switch
            }//end else 
        }

        while (!operatorStack.isEmpty()) //adding to the postfix string
        {
            topOperator = operatorStack.pop();
            postfix = postfix + topOperator;
        }
        
        //return completed postfix
        return postfix;
  
    }

    /**
     * Evaluates a postfix expression.
     * @param infix expression as a String.
     * @return result of evaulation as type float.
     */
    public static double evaluatePostFix(String postfix){

        StackInterface<Double> valueStack = new ResizeableArrayStack<>(); //new empty Linked stack
        double result= 0;
        for (int i =0; i<postfix.length(); ++i)
        {
            char character = postfix.charAt(i);

            if(Character.isLetter(character))
                valueStack.push(getValue(character));
            else{
                switch (character)
                {
                    case '+' : case '-' : case '*' : case '/' : case '^' :
                        double operandTwo = valueStack.pop();
                        double operandOne = valueStack.pop();
                        result = getResult(character, operandTwo, operandOne);
                        valueStack.push(result);
                        break;
                    default: 
                        break; // Ignore unexpected characters
                }
            }
        }
        return valueStack.peek();
    }
    
    /**
     * Returns the priority of an operator.
     * @param operator an operator in the expression 
     * @return integer that shows prority. The Lower the more priority it has.
     */
    private static int getPrecedence (char operator)
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
    } // end getPrecedence

    /**
     * 
     * @param character The operator.
     * @param operandTwo The second operand.
     * @param operandOne The first operand.
     * @return the result of the operation between operandOne and Two using the operator.
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
     * 
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
