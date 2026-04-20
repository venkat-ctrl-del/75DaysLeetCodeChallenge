class Solution {
    public int maxDistance(int[] colors) {
        int n = colors.length;
        int maxDist = 0;

        for (int i = 0; i < n; i++) {
            // Check current house against the first house
            if (colors[i] != colors[0]) {
                maxDist = Math.max(maxDist, i);
            }
            // Check current house against the last house
            if (colors[i] != colors[n - 1]) {
                maxDist = Math.max(maxDist, n - 1 - i);
            }
        }
        return maxDist;
    }
}
