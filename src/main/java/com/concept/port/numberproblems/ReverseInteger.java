package com.concept.port.numberproblems;


public class ReverseInteger {

    // 1. Mathematical - using modulo and division (handles sign and overflow check)
    public static int reverseUsingMath(int num) {
        int reversed = 0;
        int original = num;
        while (num != 0) {
            int digit = num % 10;
            // overflow check before multiplying
            if (reversed > Integer.MAX_VALUE / 10 || reversed < Integer.MIN_VALUE / 10) {
                System.out.println("Overflow detected for: " + original);
                return 0;
            }
            reversed = reversed * 10 + digit;
            num /= 10;
        }
        return reversed;
        // Time: O(d) && Space: O(1)
        // -> d = number of digits, constant extra variables
    }

    // 2. String-based - convert to string, reverse, handle sign
    public static int reverseUsingString(int num) {
        boolean negative = num < 0;
        String str = String.valueOf(Math.abs(num));
        String reversedStr = new StringBuilder(str).reverse().toString();
        try {
            long result = java.lang.Long.parseLong(reversedStr);
            if (result > Integer.MAX_VALUE) return 0;
            return negative ? (int) -result : (int) result;
        } catch (NumberFormatException e) {
            return 0;
        }
        // Time: O(d) && Space: O(d)
        // -> string allocations for conversion and reversal
    }

    // 3. Using StringBuilder directly with sign handling
    public static int reverseUsingStringBuilder(int num) {
        StringBuilder sb = new StringBuilder();
        boolean negative = num < 0;
        sb.append(Math.abs((long) num));
        sb.reverse();
        try {
            long result = java.lang.Long.parseLong(sb.toString());
            if (result > Integer.MAX_VALUE) return 0;
            return negative ? (int) -result : (int) result;
        } catch (NumberFormatException e) {
            return 0;
        }
        // Time: O(d) && Space: O(d)
        // -> StringBuilder holds reversed digit sequence
    }

    // 4. Recursive
    public static int reverseUsingRecursion(int num) {
        return reverseHelper(Math.abs((long) num), 0) * (num < 0 ? -1 : 1);
        // Time: O(d) && Space: O(d)
        // -> recursion call stack depth equal to digit count
    }

    private static int reverseHelper(long num, long reversed) {
        if (num == 0) {
            if (reversed > Integer.MAX_VALUE) return 0;
            return (int) reversed;
        }
        reversed = reversed * 10 + num % 10;
        return reverseHelper(num / 10, reversed);
    }

    // 5. Using Long to avoid overflow, cast back at the end
    public static int reverseUsingLong(int num) {
        long reversed = 0;
        long n = num;
        while (n != 0) {
            reversed = reversed * 10 + n % 10;
            n /= 10;
        }
        if (reversed > Integer.MAX_VALUE || reversed < Integer.MIN_VALUE) return 0;
        return (int) reversed;
        // Time: O(d) && Space: O(1)
        // -> long accumulator, no digit-by-digit overflow check needed mid-loop
    }

    public static void main(String[] args) {
       // int num = 1534236469; // deliberately chosen to test overflow
        int num = 3467; // deliberately chosen to test overflow

        System.out.println("Math (with pre-check): " + reverseUsingMath(num));
        System.out.println("String-based: " + reverseUsingString(num));
        System.out.println("StringBuilder: " + reverseUsingStringBuilder(num));
        System.out.println("Recursive: " + reverseUsingRecursion(num));
        System.out.println("Using Long: " + reverseUsingLong(num));
    }

   /* (d = number of digits in the integer)

    Which is best:

    Using Long (accumulate in long, cast at end) — BEST. O(d) time, O(1) space, simplest overflow handling — just check bounds once at the end instead of before every multiplication, cleanest and least error-prone approach.
    Math (int with pre-check) — equally optimal in complexity, and useful to know for contexts where long isn't available/allowed (e.g., strict primitive-only constraints), but requires a trickier overflow check before each digit is added.
    String-based / StringBuilder — intuitive and easy to write correctly, but O(d) extra space for string allocations is unnecessary when a purely numeric approach solves it in O(1) space; acceptable in production code for readability, not optimal for interviews focused on complexity.
    Recursive (AVOID) — same O(d) time as iterative approaches but adds O(d) stack space for no benefit; only useful to demonstrate recursive digit-extraction technique if explicitly asked.

    Practical/interview default: Using Long accumulator — optimal time and space, and handles overflow with a single clean check at the end (this is the standard solution for LeetCode's "Reverse Integer").*/
}
