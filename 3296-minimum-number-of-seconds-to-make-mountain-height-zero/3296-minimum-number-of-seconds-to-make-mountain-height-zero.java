class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long left = 1;
        long right = (long)1e18;
        long ans = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            if (canFinish(mid, mountainHeight, workerTimes)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private boolean canFinish(long time, int height, int[] workerTimes) {
        long total = 0;

        for (int t : workerTimes) {
            long val = (long)Math.sqrt((2.0 * time) / t);
            long k = (long)((-1 + Math.sqrt(1 + 8.0 * time / t)) / 2);
            total += k;

            if (total >= height) return true;
        }

        return total >= height;
    }
}