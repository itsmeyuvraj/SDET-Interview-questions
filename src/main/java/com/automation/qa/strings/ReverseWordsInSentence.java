package com.automation.qa.strings;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Reverse Words in a Sentence
 * ============================================================================
 * Asked in nearly 70% of SDET / Automation Engineer interviews!
 *
 * Two main variations are asked:
 * Variation A: Reverse the ORDER of the words
 *              "Welcome to Test Automation" -> "Automation Test to Welcome"
 *
 * Variation B: Reverse EACH WORD in place
 *              "Welcome to Test Automation" -> "emocleW ot tseT noitamotuA"
 *
 * COMPLEXITY:
 * - Time: O(n) where n is total characters in string
 * - Space: O(n) to store split words and build result
 *
 * INTERVIEW TIPS:
 * - Ask the interviewer: "Are there multiple consecutive spaces? Should leading/trailing spaces be preserved or trimmed?"
 * - Using regex `\\s+` splits by one or more whitespace characters cleanly.
 */
public class ReverseWordsInSentence {

    /**
     * Variation A: Reverse the ORDER of words in the sentence.
     * Example: "Hello World Java" -> "Java World Hello"
     */
    public static String reverseWordOrder(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) {
            return sentence;
        }

        // Split by one or more whitespace characters
        String[] words = sentence.trim().split("\\s+");
        StringBuilder reversedSentence = new StringBuilder();

        // Iterate backwards through the word array
        for (int i = words.length - 1; i >= 0; i--) {
            reversedSentence.append(words[i]);
            if (i > 0) {
                reversedSentence.append(" ");
            }
        }

        return reversedSentence.toString();
    }

    /**
     * Variation B: Reverse EACH individual word while maintaining word order.
     * Example: "Hello World" -> "olleH dlroW"
     */
    public static String reverseEachWord(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return sentence;
        }

        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            // Reverse current word using StringBuilder
            String reversedWord = new StringBuilder(words[i]).reverse().toString();
            result.append(reversedWord);

            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String input = "Welcome to Test Automation";

        System.out.println("Original String:       \"" + input + "\"");
        System.out.println("Reversed Word Order:   \"" + reverseWordOrder(input) + "\"");
        System.out.println("Reversed Each Word:    \"" + reverseEachWord(input) + "\"");

        // Edge case: Multiple spaces
        String edgeCase = "   Java   Selenium   Cucumber   ";
        System.out.println("\nHandling Multiple Spaces:");
        System.out.println("Original:              \"" + edgeCase + "\"");
        System.out.println("Reversed Word Order:   \"" + reverseWordOrder(edgeCase) + "\"");
    }
}
