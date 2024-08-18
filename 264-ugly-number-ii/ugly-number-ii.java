class Solution {
    public int nthUglyNumber(int n) {
        int[] primes = {2, 3, 5};
        PriorityQueue<Long> uglyHeap = new PriorityQueue<>();
        HashSet<Long> visited = new HashSet<>();
        
        uglyHeap.add(1L);
        visited.add(1L);
        
        long curr = 1L;
        for (int i = 0; i < n; i++) {
            curr = uglyHeap.poll();
            for (int prime : primes) {
                long new_ugly = curr * prime;
                if (!visited.contains(new_ugly)) {
                    uglyHeap.add(new_ugly);
                    visited.add(new_ugly);
                }
            }
        }
        return (int)curr;
    }
}