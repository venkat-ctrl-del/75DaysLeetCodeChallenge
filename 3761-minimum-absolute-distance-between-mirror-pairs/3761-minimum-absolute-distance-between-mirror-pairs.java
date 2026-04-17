import java.util.*;

class Solution {
    public int minMirrorPairDistance(int[] nums) {
        int n = nums.length;
        int minDistance = Integer.MAX_VALUE;
        // Map stores: Key = value needed to complete a pair, Value = last index where it appeared
        Map<Integer, Integer> lastSeenIndex = new HashMap<>();

        for (int i = 0; i < n; i++) {
            // If the current number is the "mirror" of a previous number
            if (lastSeenIndex.containsKey(nums[i])) {
                minDistance = Math.min(minDistance, i - lastSeenIndex.get(nums[i]));
            }

            // Calculate the reverse of the current number to store for future pairs
            int mirroredVal = reverse(nums[i]);
            // Always update with the latest index to minimize future distances (i - j)
            lastSeenIndex.put(mirroredVal, i);
        }

        return (minDistance == Integer.MAX_VALUE) ? -1 : minDistance;
    }

    private int reverse(int x) {
        int reversed = 0;
        while (x > 0) {
            reversed = reversed * 10 + (x % 10);
            x /= 10;
        }
        return reversed;
    }
}
