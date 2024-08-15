class Solution {
    public boolean lemonadeChange(int[] bills) {
        Map<Integer, Integer> track = new HashMap<>();

        for (int bill : bills) {
            int change = bill - 5;

            if (change == 5) {
                if (track.getOrDefault(5, 0) == 0) {
                    return false;
                } else {
                    track.put(5, track.get(5) - 1);
                }
            } else if (change == 15) {
                if (track.getOrDefault(5, 0) >= 1 && track.getOrDefault(10, 0) >= 1) {
                    track.put(5, track.get(5) - 1);
                    track.put(10, track.get(10) - 1);
                } else if (track.getOrDefault(5, 0) >= 3) {
                    track.put(5, track.get(5) - 3);
                } else {
                    return false;
                }
            }

            track.put(bill, track.getOrDefault(bill, 0) + 1);
        }

        return true;
    }
}