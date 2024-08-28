class Solution {
    public List<Integer> postorder(Node root) {
        // If the root is null, return an empty list
        if (root == null) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();

        // Start DFS from the root
        dfs(root, res);

        // Return the result list containing node values in post-order
        return res;
    }

    private void dfs(Node root, List<Integer> res) {
        // Recursively call dfs for each child of the current node
        for (Node child : root.children) {
            dfs(child, res);
        }
        // Append the value of the current node to the result list
        res.add(root.val);
    }
}