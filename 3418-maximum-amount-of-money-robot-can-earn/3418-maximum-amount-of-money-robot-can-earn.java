class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length, n = coins[0].length;
        int[][][] dp = new int[m][n][3];

        for (int[][] layer : dp)
            for (int[] row : layer)
                Arrays.fill(row, Integer.MIN_VALUE);

        dp[0][0][0] = coins[0][0];
        if (coins[0][0] < 0) dp[0][0][1] = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    if (dp[i][j][k] == Integer.MIN_VALUE) continue;
                    int val = dp[i][j][k];

                    if (i + 1 < m) {
                        dp[i+1][j][k] = Math.max(dp[i+1][j][k], val + coins[i+1][j]);
                        if (k < 2 && coins[i+1][j] < 0)
                            dp[i+1][j][k+1] = Math.max(dp[i+1][j][k+1], val);
                    }
                    if (j + 1 < n) {
                        dp[i][j+1][k] = Math.max(dp[i][j+1][k], val + coins[i][j+1]);
                        if (k < 2 && coins[i][j+1] < 0)
                            dp[i][j+1][k+1] = Math.max(dp[i][j+1][k+1], val);
                    }
                }
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int k = 0; k < 3; k++)
            ans = Math.max(ans, dp[m-1][n-1][k]);
        return ans;
    }
}