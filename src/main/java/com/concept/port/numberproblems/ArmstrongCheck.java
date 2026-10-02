package com.concept.port.numberproblems;

public class ArmstrongCheck {

    // 1. Iterative - fixed for 3-digit numbers
    public static boolean isArmstrong3Digit(int num) {
        int original = num, sum = 0;
        while (num != 0) {
            int digit = num % 10;
            sum += digit * digit * digit;
            num /= 10;
        }
        return sum == original;
    } // Time: O(1) && Space: O(1) -> fixed 3 digits


    // 2. Iterative - generalized for any number of digits
    public static boolean isArmstrongGeneral(int num) {
        int original = num;
        int digitCount = String.valueOf(num).length();
        int sum = 0, n = num;
        while (n != 0) {
            int digit = n % 10;
            int power = 1;
            for (int i = 0; i < digitCount; i++) {
                power *= digit;
            }
            sum += power;
            n /= 10;
        }
        return sum == original;
    } // Time: O(d²) && Space: O(1) -> where d = digit count

    // 3. Using Math.pow (concise, but involves double precision)

    public static boolean isArmstrongMathPow(int num) {
        int original = num, digitCount = String.valueOf(num).length();
        int sum = 0, n = num;
        while (n != 0) {
            int digit = n % 10;
            sum += Math.pow(digit, digitCount);
            n /= 10;
        }
        return sum == original;
    } // Time: O(d log d) && Space: O(1)
    // -> pow is faster than manual loop, plus double overhead


    // 4. Recursive
    public static boolean isArmstrongRecursive(int num) {
        int digitCount = String.valueOf(num).length();
        return num == armstrongSum(num, digitCount);
    } //Time: O(d²) && Space: O(d) -> recursion stack

    private static int armstrongSum(int n, int digitCount) {
        if (n == 0) return 0;
        int digit = n % 10;
        int power = 1;
        for (int i = 0; i < digitCount; i++) power *= digit;
        return power + armstrongSum(n / 10, digitCount);
    }

    // 5. String-based (convert digits via char arithmetic)
    public static boolean isArmstrongString(int num) {
        String str = String.valueOf(num);
        int digitCount = str.length();
        int sum = 0;
        for (char c : str.toCharArray()) {
            int digit = c - '0';
            int power = 1;
            for (int i = 0; i < digitCount; i++) power *= digit;
            sum += power;
        }
        return sum == num;
    } //Time: O(d²) && Space: O(d) -> string allocation


    public static void main(String[] args) {
        int num = 153;       // 3-digit Armstrong: 1^3 + 5^3 + 3^3 = 153
        int num2 = 9474;     // 4-digit Armstrong: 9^4 + 4^4 + 7^4 + 4^4 = 9474

        System.out.println("3-digit fixed: " + isArmstrong3Digit(num));
        System.out.println("General (any digits): " + isArmstrongGeneral(num2));
        System.out.println("Math.pow version: " + isArmstrongMathPow(num2));
        System.out.println("Recursive: " + isArmstrongRecursive(num2));
        System.out.println("String-based: " + isArmstrongString(num2));
    }

}
