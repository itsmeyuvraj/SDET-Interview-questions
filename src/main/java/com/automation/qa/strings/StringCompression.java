package com.automation.qa.strings;

/**
 * ============================================================================
 * INTERVIEW QUESTION: String Compression & Decompression (Run-Length Encoding)
 * ============================================================================
 * Problem 1: Compress string based on consecutive character counts
 *            Input:  "aaabbcccdee"
 *            Output: "a3b2c3d1e2"
 *
 * Problem 2 (Follow-up): Decompress the compressed string
 *            Input:  "a3b2c3d1e2"
 *            Output: "aaabbcccdee"
 *
 * WHY ASKED IN SDET INTERVIEWS:
 * - Checks single-pass tracking of running counts.
 * - Tests off-by-one boundary handling at the end of the string.
 * - Tests string manipulation and parsing.
 */
public class StringCompression {

    /**
     * Compresses consecutive duplicate characters into character + count.
     */
    public static String compressString(String input) {
        if (input == null || input.isEmpty()) return input;

        StringBuilder compressed = new StringBuilder();
        int count = 1;

        for (int i = 0; i < input.length(); i++) {
            // Check if current char matches the next char
            if (i + 1 < input.length() && input.charAt(i) == input.charAt(i + 1)) {
                count++;
            } else {
                // Char sequence ended, append char and count
                compressed.append(input.charAt(i));
                compressed.append(count);
                count = 1; // Reset count for next sequence
            }
        }

        return compressed.toString();
    }

    /**
     * Follow-Up: Decompresses "a3b2c1" back into "aaabbc"
     */
    public static String decompressString(String input) {
        if (input == null || input.isEmpty()) return input;

        StringBuilder decompressed = new StringBuilder();
        int i = 0;

        while (i < input.length()) {
            char ch = input.charAt(i++);
            StringBuilder countDigits = new StringBuilder();

            // Handle multi-digit numbers (e.g. a12b3)
            while (i < input.length() && Character.isDigit(input.charAt(i))) {
                countDigits.append(input.charAt(i++));
            }

            int repeatCount = countDigits.length() > 0 ? Integer.parseInt(countDigits.toString()) : 1;
            decompressed.append(String.valueOf(ch).repeat(repeatCount));
        }

        return decompressed.toString();
    }

    public static void main(String[] args) {
        String original = "aaabbcccdee";
        String compressed = compressString(original);
        String decompressed = decompressString(compressed);

        System.out.println("Original String:      " + original);
        System.out.println("Compressed Result:    " + compressed);
        System.out.println("Decompressed Back:    " + decompressed);

        // Verification
        System.out.println("Matches Original:     " + original.equals(decompressed));
    }
}
