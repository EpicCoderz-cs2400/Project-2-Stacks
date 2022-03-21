package main.testing;
/*

    NEEDS unit testing

*/

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {
    public static void main(String[] args){
        int total = newTotal(5, 10);
        System.out.println(total);

    }

    public static int newTotal(int first, int second){
        int newTotal = first+second;
        return newTotal;
    }

    @Test
    public void testNewTotal(){
        assertEquals(15, newTotal(5, 10));
    }
}