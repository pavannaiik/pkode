/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
class Graph {
    public List<Node> roots;
}
*/

// If the question is to clone disconnected graphs

class Solution {
    public Graph cloneGraph(Graph graph) {
        if (graph == null) return null;

        Graph ans = new Graph();
        ans.roots = new ArrayList<>();

        for (Node curRoot : graph.roots) {
            HashMap<Integer, Node> map = new HashMap<>();
            ans.roots.add(dfs(curRoot, map));
        }

        return ans;
    }

    private Node dfs(Node head, HashMap<Integer, Node> map) {
        if (head == null) return null;

        if (map.containsKey(head.val)) {
            return map.get(head.val);
        }

        Node clone = new Node(head.val);
        map.put(head.val, clone);

        for (Node neighbor : head.neighbors) {
            clone.neighbors.add(dfs(neighbor, map));
        }

        return clone;
    }
}
