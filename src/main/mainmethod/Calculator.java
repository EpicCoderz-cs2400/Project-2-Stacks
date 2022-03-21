package main.mainmethod;
import java.util.Scanner;

import main.implementations.LinkedStack;
import main.implementations.ResizeableArrayStack;
import main.interfaces.StackInterface;


public class Calculator {
    

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


    // Converts an infix expression to an equivalent postfix expression.
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
                        }//endwhile
                        operatorStack.push(character);
                        break;
                    case '(' : 
                        operatorStack.push(character);
                        break;
                    case ')' : // Stack is not empty if infix expression is valid
                        topOperator = operatorStack.pop();
                        while (topOperator != '(')
                        {
                            postfix = postfix + topOperator;
                            topOperator = operatorStack.pop();
                        }//end while
                        break;
                    default:
                        break; // Ignore unexpected characters
                }//end switch
            }//end else 
        }

        while (!operatorStack.isEmpty())
        {
            topOperator = operatorStack.pop();
            postfix = postfix + topOperator;
        }
        
        //return completed postfix
        return postfix;
  
    }

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

    public static double evaluatePostFix(String postfix){
        // Evaluates a postfix expression.
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
                for (int i = (int) operandTwo; i != 0; --i) {
                    result *= operandOne;
                  }
                break;
        }
        return result;
    }

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
