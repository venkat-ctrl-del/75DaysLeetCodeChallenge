class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length, MOD = 12345;
        int total = m * n;
        int[][] ans = new int[m][n];
        
        long[] flat = new long[total];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                flat[i * n + j] = grid[i][j];
        
        long[] prefix = new long[total];
        long[] suffix = new long[total];
        
        prefix[0] = 1;
        for (int i = 1; i < total; i++)
            prefix[i] = prefix[i-1] * flat[i-1] % MOD;
        
        suffix[total-1] = 1;
        for (int i = total - 2; i >= 0; i--)
            suffix[i] = suffix[i+1] * flat[i+1] % MOD;
        
        for (int i = 0; i < total; i++)
            ans[i / n][i % n] = (int)(prefix[i] * suffix[i] % MOD);
        
        return ans;
    }
}