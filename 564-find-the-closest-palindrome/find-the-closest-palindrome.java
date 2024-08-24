class Solution {

    private long getPalindrom(long left, boolean flag) {
        long res = left;
        
        // edge case
        if (!flag) {
            left /= 10;
        }

        while (left > 0) {
            res = (res * 10) + (left % 10);
            left /= 10;
        }
        return res;
    }

    public String nearestPalindromic(String n) {
        int idx = n.length() % 2 == 0 ? ((n.length() / 2) - 1) : (n.length() / 2);
        long left = Long.parseLong(n.substring(0, idx + 1));

        List<Long> list = new ArrayList<>();
        list.add(getPalindrom(left, n.length() % 2 == 0));
        list.add(getPalindrom(left + 1, n.length() % 2 == 0));
        list.add(getPalindrom(left - 1, n.length() % 2 == 0));
        list.add((long) Math.pow(10, n.length()) + 1);
        list.add((long) Math.pow(10, n.length() - 1) - 1);

        long diff = Long.MAX_VALUE;
        long res = 0, new_long = Long.parseLong(n);
        for (long l : list) {
            if (l == new_long) {
                continue;
            }

            if (Math.abs(l - new_long) < diff) {
                diff = Math.abs(l - new_long);
                res = l;
            } else if (Math.abs(l - new_long) == diff) {
                res = Math.min(res, l);
            }
        }
        return String.valueOf(res);
    }
}