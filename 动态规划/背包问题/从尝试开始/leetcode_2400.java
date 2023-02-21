package 动态规划.背包问题.从尝试开始;
class leetcode_2400{
    public static void main(String[] args) {
        // System.out.println(leetcode_2400.ways1(4, 2,  4, 10));

        System.out.println(leetcode_2400.ways2(4, 2,  4, 20));
        System.out.println(leetcode_2400.ways3(4, 2,  4, 20));

    }


    //纯粹的暴力递归，可能会超时
    public static int ways1(int N, int start, int aim, int K){
        return process1(N, start, aim, K);
    }
    /**
     * 
     * @param cur  当前位置为cur
     * @param rest  还有rest步走
     * @param aim  目标位置
     * @param N  有哪些位置
     * @return
     */
    public static int process1(int N, int cur, int aim, int rest){
        //递归停止条件
        if (rest == 0){
            return cur == aim ? 1 : 0;
        }
        //rest > 0 继续走
        if (cur == 1){
            return process1(N, cur + 1, aim, rest - 1);
        }
        if (cur == N){
            return process1(N , cur - 1, aim , rest - 1);
        }

        //处于中间位置
        return process1( N , cur - 1, aim , rest - 1) + process1(N , cur + 1, aim , rest - 1);
    }

    //优化 缓存法
    //记忆化搜索，从顶向下的动态规划; 和递归形式结合
    public static int ways2(int N, int start, int aim, int K){
        int[][] dp = new int[N + 1][K + 1];  //有足够的表空间放下返回值; 数组dp[cur][rest]含义为当前cur 和 rest 下 有多少种可行方法
        //dp数组初始化.dp数组作为缓存表
        for(int i = 0; i <= N; i++){
            for(int j = 0; j<= K; j++){
                dp[i][j] = -1;
            }
        }
        //如果dp[cur][rest] == -1  ,这个值之前没算过！
        //如果dp[cur][rest] ！= -1  ,这个值之前算过！返回dp[cur][rest]！
        return process2(N, start, aim, K, dp);
    }
    /**
     * 
     * @param cur  当前位置为cur  范围 1~N
     * @param rest  还有rest步走  范围 0~K
     * @param aim  目标位置
     * @param N  有哪些位置
     * @return
     */
    public static int process2(int N, int cur, int aim, int rest, int[][] dp){

        if(dp[cur][rest] != -1){
            return dp[cur][rest];
        }

        //之前没算过
        int ans = 0;
        //递归停止条件
        if (rest == 0){
            ans = cur == aim ? 1 : 0;
        }
        //rest > 0 继续走
        else if (cur == 1){
            ans = process2(N, cur + 1, aim, rest - 1, dp);
        }
        else if (cur == N){
            ans = process2(N , cur - 1, aim , rest - 1, dp);
        }
        //处于中间位置
        else{
            ans = process2( N , cur - 1, aim , rest - 1, dp) + process2(N , cur + 1, aim , rest - 1, dp);
        }
        
        dp[cur][rest] = ans;

        return ans;
        
    }   


    
    //动态规划
    public static int ways3(int N, int start, int aim, int K){
        int[][] dp = new int[N + 1][K + 1];  //有足够的表空间放下返回值; 数组dp[cur][rest]含义为当前cur 和 rest 下 有多少种可行方法
        //dp数组初始化.dp数组作为缓存表
        for(int i = 0; i <= N; i++){
            for(int j = 0; j<= K; j++){
                dp[i][j] = -1;
            }
        }
        //如果dp[cur][rest] == -1  ,这个值之前没算过！
        //如果dp[cur][rest] ！= -1  ,这个值之前算过！返回dp[cur][rest]！
        return process3(N, start, aim, K);
    }
    /**
     * 
     * @param cur  当前位置为cur  范围 1~N
     * @param rest  还有rest步走  范围 0~K
     * @param aim  目标位置
     * @param N  有哪些位置
     * @return
     */
    public static int process3(int N, int cur, int aim, int rest){

        int[][] dp = new int[N + 1][rest + 1];  //有足够的表空间放下返回值; 数组dp[cur][rest]含义为当前cur 和 rest 下 有多少种可行方法
        //dp数组初始化
        dp[aim][0] = 1; //重点位置到达自身位置方法为1种
        //当cur等于1， 值依赖于左下角
        //cur = N， 值依赖于左上角
        //cur 为中间位置， 值依赖于左上角 + 左下角
        //当前矩阵应该一列一列的填充
        for(int j = 1; j<= rest; j++){  // rest
            dp[1][j] = dp[2][j - 1];
            for(int i = 2; i < N; i++){ //cur 
                             
                dp[i][j] = dp[i - 1][j - 1] + dp[i + 1][j - 1];
                
            }
            dp[N][j] = dp[N -1][j - 1];
            
        }

       
        return dp[cur][rest];
    }   


}