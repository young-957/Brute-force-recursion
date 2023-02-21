package 动态规划.背包问题.从尝试开始;

public class HorseJump {
    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(method1(x, y, step));
        System.out.println(jump2( step, x, y));
    }


    public static int method1(int a, int b, int k){
        return jump1(0, 0, k, a, b);
    }

    //当前的位置是（x, y）
    //还剩下rest步
    //返回 调完rest步，正好调到 (a ,b)的方法数
    public static int jump1(int x, int y, int rest, int a, int b){
        //有棋盘范围10 * 9
        if(x < 0 || x > 9 || y < 0 || y > 8){
            return 0;
        }        
        //base_case
        if(rest == 0){
            return (x == a && y == b) ? 1 : 0;
        }

        // 八种跳法
        int ways = jump1(x + 2,y + 1,rest - 1,a, b);
        ways += jump1(x + 1,y + 2,rest - 1, a, b);
        ways += jump1(x - 1,y + 2,rest - 1, a, b);
        ways += jump1(x - 1,y - 2,rest - 1, a, b);
        ways += jump1(x + 1,y - 2,rest - 1, a, b);
        ways += jump1(x + 2,y - 1,rest - 1, a, b);
        ways += jump1(x - 2,y + 1,rest - 1, a, b);
        ways += jump1(x - 2,y - 1,rest - 1, a, b);
        
        // ways += jump1(x + 2,y - 2,rest - 1, a, b);
        
        return ways;

    }

    //可变参数为x,y,rest.所以建立三维数组dp
    public static int jump2(int k, int a, int b){
        int[][][] dp = new int[10][9][k + 1];  //每一步都记录，k + 1 个空间
        //初始化dp：k=0； (x == a && y == b) ? 1 : 0;
        dp[a][b][0] = 1;
        
    
        for(int rest = 1; rest <= k; rest++){
            for(int x = 0; x < 10; x++){
                for(int y = 0; y < 9; y++){
                    int ways = pick(dp,x + 2,y + 1,rest - 1);
                        ways += pick(dp,x + 1,y + 2,rest - 1);
                        ways += pick(dp,x - 1,y + 2,rest - 1);
                        ways += pick(dp,x - 1,y - 2,rest - 1);
                        ways += pick(dp,x + 1,y - 2,rest - 1);
                        ways += pick(dp,x + 2,y - 1,rest - 1);
                        ways += pick(dp,x - 2,y + 1,rest - 1);
                        ways += pick(dp,x - 2,y - 1,rest - 1);
                        dp[x][y][rest] = ways;
                }
            }
        }
        return dp[0][0][k]; 
    }

    public static int pick(int[][][] dp, int x, int y, int rest){
        if(x < 0 || x > 9 || y < 0 || y > 8){
            return 0;
        }  
        return dp[x][y][rest];
    }
}
