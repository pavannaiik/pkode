// Variant One -> If interviwer ask us to print Path not the length, I have generalized the code for first non variant, so this code can be used for both questions.


class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int[][] dir = {{0,1},{1,0},{0,-1},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
        int n = grid.length, m = grid[0].length;

        if(grid[0][0] != 0 || grid[n-1][m-1] != 0) return -1;

        Queue<Node> queue = new LinkedList<>();
        List<int[]> startPath = new ArrayList<>();
        startPath.add(new int[]{0, 0});
        queue.add(new Node(0, 0, startPath));
        grid[0][0] = -1;

        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            int r = cur.row, c = cur.col;
            List<int[]> path = cur.path;

            // Check if we've reached the bottom-right cell
            if(r == n - 1 && c == m - 1) {
                System.out.println("Shortest path length: " + path.size());
                System.out.println("Path:");
                for (int[] p : path) {
                    System.out.println(Arrays.toString(p));
                }
                return path.size();
            }

            for(int i = 0; i < 8; i++) {
                int x = r + dir[i][0];
                int y = c + dir[i][1];
                if(x < 0 || y < 0 || x >= n || y >= m || grid[x][y] != 0) continue;

                grid[x][y] = -1; // Mark as visited
                List<int[]> newPath = new ArrayList<>(path);
                newPath.add(new int[]{x, y});
                queue.add(new Node(x, y, newPath));
            }
        }

        return -1;
    }

    static class Node {
        int row, col;
        List<int[]> path;
        Node(int row, int col, List<int[]> path) {
            this.row = row;
            this.col = col;
            this.path = path;
        }
    }
}
