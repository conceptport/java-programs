package com.concept.port.stringproblems;

public class PalindromeCheck {

    // 1. String - two-pointer (no extra string built)
    public static boolean isPalindromeTwoPointer(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    } // Time: O(n) && Space: O(1)


    // 2. String - reverse and compare
    public static boolean isPalindromeReverse(String s) {
        String reversed = new StringBuilder(s).reverse().toString();
        return s.equals(reversed);
    } // Time: O(n) && Space: O(n)


    // 3. String - recursion
    public static boolean isPalindromeRecursive(String s) {
        if (s.length() <= 1) return true;
        if (s.charAt(0) != s.charAt(s.length() - 1)) return false;
        return isPalindromeRecursive(s.substring(1, s.length() - 1));
    } // Time: O(n) && Space: O(n)


    // 4. Number - reverse digits mathematically (no string conversion)
    public static boolean isPalindromeNumber(int num) {
        if (num < 0) return false; // negatives aren't palindromes
        int original = num, reversed = 0;
        while (num != 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }
        return original == reversed;
    } // Time: O(log n) && Space: O(1)


    // 5. Number - convert to string, reuse two-pointer
    public static boolean isPalindromeNumberAsString(int num) {
        if (num < 0) return false;
        return isPalindromeTwoPointer(String.valueOf(num));
    } // Time: O(log n) && Space: O(log n)


    public static void main(String[] args) {
        String str = "radar";
        int num = 12321;

        System.out.println("String reverse: " + isPalindromeReverse(str));
        System.out.println("String two-pointer: " + isPalindromeTwoPointer(str));
        System.out.println("String recursive: " + isPalindromeRecursive(str));
        System.out.println("Number math: " + isPalindromeNumber(num));
        System.out.println("Number as string: " + isPalindromeNumberAsString(num));
    }


}
