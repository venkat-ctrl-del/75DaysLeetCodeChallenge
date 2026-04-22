import java.util.*;

class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>();
        
        for (String query : queries) {
            for (String word : dictionary) {
                if (canMatch(query, word)) {
                    result.add(query);
                    break; // Move to the next query once a match is found
                }
            }
        }
        return result;
    }

    private boolean canMatch(String s1, String s2) {
        int diffCount = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffCount++;
            }
            // Early exit if more than 2 edits are needed
            if (diffCount > 2) return false;
        }
        return true;
    }
}
