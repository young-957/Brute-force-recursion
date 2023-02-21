package 动态规划.背包问题.从尝试开始;

public class leetcode_64最小路径和 {
    public static void main(String[] args) {
        int[][] grid = new int[][]{{1,3,1},
                                    {1,5,1},
                                    {4,2,1}};

        System.out.println(minPathSum(grid));
    }

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        //填充行
        int sum = grid[0][0];
        dp[0][0] = grid[0][0];
        for(int i = 1; i < n; i++){
            sum += grid[0][i];
            dp[0][i] += sum;
            // dp[0][i] += grid[0][i];
        }
        sum = grid[0][0];
        for(int i = 1; i < m; i++){
            sum += grid[i][0];
            dp[i][0] += sum;
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }

        }
        return dp[m - 1][n - 1];
    }
}
