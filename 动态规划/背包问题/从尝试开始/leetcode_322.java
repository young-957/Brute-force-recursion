package 动态规划.背包问题.从尝试开始;

public class leetcode_322 {
    
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 5};
        int amount = 11;
        coinChange(arr, amount);
    }

    public static int coinChange(int[] coins, int amount) {
        if(coins == null ||coins.length == 0 || amount < 0 ){
            return -1;
        }
        int N = coins.length;
        int[][] dp = new int[N + 1][amount + 1];
        //最后一排初始化为除了dp[N][0], 其余为 -1
        for(int col = 1; col <= amount; col++){
            dp[N][col] = -1;
        }

        //从底向上，从左到右遍历
        for(int i = N - 1; i >= 0; i--){//行
            for(int rest = 0; rest <= amount; rest++){//从左到右
                dp[i][rest] = -1; //初始化为无效
                if(dp[i + 1][rest] != -1){//下面的值有效
                    dp[i][rest] = dp[i + 1][rest];
                }
                //下来看 dp[i + 1][rest - coin[i]]
                if(rest - coins[i] >= 0 && dp[i][rest - coins[i]] != -1){  //判断数值有效且不越界
                //上面条件判断顺序不能交换，防止数组越界
                // rest - coins[i] >= 0  判断数组是否越界
                    //数组不越界，而且值有效
                    if(dp[i][rest] == -1){
                        //之前下面的值无效
                        dp[i][rest] = dp[i][rest - coins[i]] + 1;  // 已使用一枚硬币
                    } else{
                        //说明下面的值有效
                        dp[i][rest] = Math.min(dp[i][rest], dp[i][rest - coins[i]] + 1);
                    }
                }
                for(int row = 0; row <= N; row++){
                    for(int col = 0; col <= amount; col++){
                        System.out.print(dp[row][col] + ", ");
                    }
                    System.out.print("\n");
                }
                System.out.println("");
            }


            // for(int row = 0; row <= N; row++){
            //     for(int col = 0; col <= amount; col++){
            //         System.out.print(dp[row][col] + ", ");
            //     }
            //     System.out.print("\n");
            // }
            
        }
        // return process1(coins, 0, amount);
        return dp[0][amount];

    }
}
