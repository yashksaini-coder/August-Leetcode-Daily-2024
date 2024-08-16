class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int minVal = arrays.get(0).get(0);
        int maxVal = arrays.get(0).get(arrays.get(0).size() - 1);
        int maxDistance = 0;

        for (int i = 1; i < arrays.size(); i++) {
            List<Integer> currentArray = arrays.get(i);

            maxDistance = Math.max(maxDistance, Math.max(
                Math.abs(currentArray.get(currentArray.size() - 1) - minVal),
                Math.abs(maxVal - currentArray.get(0))
            ));

            minVal = Math.min(minVal, currentArray.get(0));
            maxVal = Math.max(maxVal, currentArray.get(currentArray.size() - 1));
        }

        return maxDistance;
    }
}