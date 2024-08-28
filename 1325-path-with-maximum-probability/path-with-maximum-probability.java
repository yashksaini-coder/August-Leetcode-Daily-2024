class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        // Initialize the distances with 0.0, and the start node with 1.0
        double[] dist = new double[n];
        Arrays.fill(dist, 0.0);
        dist[start_node] = 1.0;
        
        // Perform Bellman-Ford relaxation up to (n-1) times
        for (int i = 0; i < n - 1; i++) {
            boolean updated = false;
            for (int j = 0; j < edges.length; j++) {
                int u = edges[j][0];
                int v = edges[j][1];
                double prob = succProb[j];
                
                // Relaxation for edge u -> v
                if (dist[u] * prob > dist[v]) {
                    dist[v] = dist[u] * prob;
                    updated = true;
                }
                
                // Relaxation for edge v -> u (because the graph is undirected)
                if (dist[v] * prob > dist[u]) {
                    dist[u] = dist[v] * prob;
                    updated = true;
                }
            }
            // Early termination if no update was made
            if (!updated) break;
        }
        
        // Return the maximum probability to reach the end node
        return dist[end_node];
    }
}