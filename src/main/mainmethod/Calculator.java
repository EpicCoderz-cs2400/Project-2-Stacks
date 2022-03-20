package main.mainmethod;
import java.util.Scanner;

import main.implementations.LinkedStack;
import main.interfaces.StackInterface;

public class Calculator {
    

    public static void main(String[] args){
        Scanner scnr = new Scanner(System.in);
        String postfix;
        //get eqation from user
        System.out.println("Type in expression");
        String infix = scnr.nextLine();

        //converting to postfix 
        postfix = convertToPostfix(infix);
        System.out.println(postfix);
        
        //evaluating
        
        scnr.close(); //close scanner

    } //end main method

    // Converts an infix expression to an equivalent postfix expression.
    public static String convertToPostfix(String infix){

        StackInterface<Character> operatorStack = new LinkedStack<>(); //new empty stack
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

}
