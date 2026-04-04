class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int cols = encodedText.length() / rows;
        StringBuilder sb = new StringBuilder();

        for (int start = 0; start < cols; start++) {
            for (int r = 0, c = start; r < rows && c < cols; r++, c++) {
                sb.append(encodedText.charAt(r * cols + c));
            }
        }

        int end = sb.length() - 1;
        while (end >= 0 && sb.charAt(end) == ' ') end--;
        return sb.substring(0, end + 1);
    }
}