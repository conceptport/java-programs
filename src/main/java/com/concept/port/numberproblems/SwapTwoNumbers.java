package com.concept.port.numberproblems;

public class SwapTwoNumbers {

    // 1. With a third (temporary) variable
    public static void swapWithTemp(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
        System.out.println("a = " + a + ", b = " + b);
    } // Time: O(1) && Space: O(1)


    // 2. Without third variable — arithmetic (+/-)
    public static void swapWithArithmetic(int a, int b) {
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("a = " + a + ", b = " + b);
    } // Time: O(1) && Space: O(1)


    // 3. Without third variable — XOR
    public static void swapWithXOR(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a = " + a + ", b = " + b);
    } // Time: O(1) && Space: O(1)


    public static void main(String[] args) {
        swapWithTemp(5, 10);
        swapWithArithmetic(5, 10);
        swapWithXOR(5, 10);
    }

}
