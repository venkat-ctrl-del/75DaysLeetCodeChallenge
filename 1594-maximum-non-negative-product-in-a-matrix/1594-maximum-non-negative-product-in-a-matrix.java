class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length, MOD = 1_000_000_007;
        long[][] dpMax = new long[m][n];
        long[][] dpMin = new long[m][n];

        dpMax[0][0] = dpMin[0][0] = grid[0][0];

        for (int i = 1; i < m; i++)
            dpMax[i][0] = dpMin[i][0] = dpMax[i-1][0] * grid[i][0];
        for (int j = 1; j < n; j++)
            dpMax[0][j] = dpMin[0][j] = dpMax[0][j-1] * grid[0][j];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long v = grid[i][j];
                if (v >= 0) {
                    dpMax[i][j] = Math.max(dpMax[i-1][j], dpMax[i][j-1]) * v;
                    dpMin[i][j] = Math.min(dpMin[i-1][j], dpMin[i][j-1]) * v;
                } else {
                    dpMax[i][j] = Math.min(dpMin[i-1][j], dpMin[i][j-1]) * v;
                    dpMin[i][j] = Math.max(dpMax[i-1][j], dpMax[i][j-1]) * v;
                }
            }
        }

        long res = dpMax[m-1][n-1];
        return res < 0 ? -1 : (int)(res % MOD);
    }
}