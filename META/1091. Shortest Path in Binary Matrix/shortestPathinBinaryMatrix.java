class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
         int[][] dir = {{0,1},{1,0},{0,-1},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
        int n = grid.length, m = grid[0].length;
        if(grid[0][0] != 0 || grid[n-1][m-1] != 0) return -1;
        Queue<int[]>queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0});
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int r = cur[0], c =cur[1], val = cur[2];
            if(r==n-1 && c==m-1 ) return val+1;
            for(int i=0;i<8;i++){
                int x = dir[i][0]+r;
                int y = dir[i][1]+c;
                if(x <0 || y <0 || x > grid.length-1 || y >grid[0].length-1||grid[x][y]!=0) continue;
                grid[x][y]=-1;
                queue.add(new int[]{x,y,val+1});
            }
        }
        return -1;
    }
}
