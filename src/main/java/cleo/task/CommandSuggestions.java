package cleo.task;

import java.util.Arrays;
import java.util.List;

/**
 * Provides functionality to suggest the closest valid command
 * based on user input by using the Levenshtein Distance algorithm.
 * It is used to correct minor typos in command inputs and offer suggestions.
 */

public class CommandSuggestions {
    /**
     * A list of valid commands that the chatbot recognizes.
     * These are the only commands that will be suggested when the user mistypes a command.
     */

    private static final List<String> validCommands = Arrays.asList(
            "todo", "deadline", "event", "list", "mark", "unmark", "delete", "find", "priority", "bye", "hi", "commands"
    );
    /**
     * Finds and returns the closest valid command based on the input string using the Levenshtein Distance algorithm.
     * The method returns a suggestion if the typo is within a threshold of 3 character edits (insertions, deletions,
     * or substitutions).
     *
     * @param input The user input that may contain a typo.
     * @return The closest command if a match is found within the allowed distance; otherwise, {@code null}.
     */

    public static String getClosestCommand(String input) {
        int minDistance = Integer.MAX_VALUE;
        String closestCommand = null;
        for (String command : validCommands) {
            int distance = levenshteinDistance(input, command);
            if (distance < minDistance) {
                minDistance = distance;
                closestCommand = command;
            }
        }
        // Only suggest if the typo is not too far off (e.g., 1 or 2 character difference)
        if (minDistance <= 3) {
            return closestCommand;
        }
        return null; // Return null if no close match is found
    }
    /**
     * Implements the Levenshtein Distance algorithm to calculate the minimum number of single-character edits
     * (insertions, deletions, or substitutions) required to change one string into another.
     *
     * @param a The first string to compare.
     * @param b The second string to compare.
     * @return The Levenshtein distance between the two strings, which represents the number of edits required.
     */
    private static int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1),
                            Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }
        return dp[a.length()][b.length()];
    }
}
