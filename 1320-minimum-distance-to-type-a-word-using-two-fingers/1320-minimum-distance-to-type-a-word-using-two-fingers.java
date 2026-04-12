class Solution {
    public int minimumDistance(String word) {
        int n = word.length();
        int[][] dp = new int[n][26];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE / 2);

        // dp[i][j] = min cost to type word[i..n-1] with one finger at word[i-1], other at j
        // Base: after typing first char, one finger at word[0], other anywhere (cost 0 so far)
        for (int j = 0; j < 26; j++) dp[0][j] = 0;

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int cur = word.charAt(i) - 'A';
            int prev = word.charAt(i - 1) - 'A';
            int d = dist(prev, cur);
            for (int j = 0; j < 26; j++) {
                if (dp[i - 1][j] == Integer.MAX_VALUE / 2) continue;
                // Move prev finger to cur
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + d);
                // Move j finger to cur
                dp[i][prev] = Math.min(dp[i][prev], dp[i - 1][j] + dist(j, cur));
            }
        }

        int last = word.charAt(n - 1) - 'A';
        for (int j = 0; j < 26; j++)
            ans = Math.min(ans, dp[n - 1][j]);

        return ans;
    }

    private int dist(int a, int b) {
        int r1 = a / 6, c1 = a % 6;
        int r2 = b / 6, c2 = b % 6;
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}