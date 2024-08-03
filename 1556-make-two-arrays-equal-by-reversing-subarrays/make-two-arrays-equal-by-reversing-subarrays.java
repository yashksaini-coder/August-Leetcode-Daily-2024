class Solution {
    public boolean canBeEqual(int[] targetArray, int[] currentArray) {
        int n=targetArray.length;
        if (n!=currentArray.length) {
            return false;
        }
        int[] e=new int[1001];
        for (int i=0; i<n; i++) {
            e[targetArray[i]]++;
            e[currentArray[i]]--;
        }
        for (int c:e) {
            if (c!=0) {
                return false;
            }
        }
        return true;
    }
}