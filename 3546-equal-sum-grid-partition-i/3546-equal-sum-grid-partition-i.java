class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long total = 0;
        for (int[] row : grid)
            for (int v : row)
                total += v;
        
        if (total % 2 != 0) return false;
        long half = total / 2;
        
        long rowSum = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int v : grid[i]) rowSum += v;
            if (rowSum == half) return true;
        }
        
        long colSum = 0;
        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++) colSum += grid[i][j];
            if (colSum == half) return true;
        }
        
        return false;
    }
}