class Solution {
    public String getHappyString(int n, int k) {
        List<String> list = new ArrayList<>();
        generateHappyStrings(n, "", list);
        if (k > list.size()) return "";
        return list.get(k - 1);
    }
    
    private void generateHappyStrings(int n, String current, List<String> list) {
        if (current.length() == n) {
            list.add(current);
            return;
        }
        for (char c : new char[]{'a', 'b', 'c'}) {
            if (current.isEmpty() || current.charAt(current.length() - 1) != c) {
                generateHappyStrings(n, current + c, list);
            }
        }
    }
}