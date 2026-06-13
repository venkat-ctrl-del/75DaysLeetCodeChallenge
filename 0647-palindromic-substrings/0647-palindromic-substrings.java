class Solution {
    public int expand(String s, int st, int e)
    {
        int count=0;
        while(st >=0 && e < s.length() && s.charAt(st) == s.charAt(e))
        {
            count++;
            st--;
            e++;
        }
        return count;
    }
    public int countSubstrings(String s)
    {
        int count=0;
        for(int i=0;i<s.length();i++)
        {
            int odds=expand(s,i,i);
            int evens=expand(s,i,i+1);
            count+=odds+evens;
        }
        return count;
    }
}
