class Solution {
    public int mirrorDistance(int n) {
        int reversed = reverseNumber(n);
        return Math.abs(n - reversed);
    }

    private int reverseNumber(int n) {
        int rev = 0;
        while (n > 0) {
            rev = rev * 10 + (n % 10);
            n /= 10;
        }
        return rev;
    }
}
