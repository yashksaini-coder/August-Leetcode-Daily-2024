class Solution {
    public int removeStones(int[][] stones) {
        int n = stones.length;
        UnionFind ufObj = new UnionFind(20002); 
        for (var stone : stones) {
            var row = stone[0];
            var col = stone[1] + 10001; 
            ufObj.union(row, col);
        }
        Set<Integer> uniqueRoots = new HashSet<>();
        for (int[] stone : stones) {
            uniqueRoots.add(ufObj.find(stone[0]));
        }
        return n - uniqueRoots.size();
    }

    private class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int node = 0; node < size; node++) {
                parent[node] = node;
                rank[node] = 1; 
            }
        }

        public int find(int node) {
            if (parent[node] != node) {
                parent[node] = find(parent[node]);
            }
            return parent[node];
        }

        public void union(int node1, int node2) {
            int grpHead1 = find(node1);
            int grpHead2 = find(node2);
            if (grpHead1 != grpHead2) {
                if (rank[grpHead1] > rank[grpHead2]) {
                    parent[grpHead2] = grpHead1;
                } else if (rank[grpHead1] < rank[grpHead2]) {
                    parent[grpHead1] = grpHead2;
                } else {
                    parent[grpHead2] = grpHead1;
                    rank[grpHead1]++;
                }
            }
        }
    }
}