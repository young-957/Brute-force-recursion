package 动态规划.背包问题;

public class zeroOne背包 {
    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7};
        int[] values = {5, 6, 3, 19};
        int bag = 11;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }

    //w为货物重量
    //v货物价值
    //bag不超重
    //返回不超重能获得的最大价值


    public static int maxValue(int[] w, int[] v, int bag){
        if(w == null || v == null || w.length != v.length || w.length == 0){
            return 0;
        }

        return process(w, v, 0, bag);
    }


    //index 当前选择货物的下标
    //返回最大价值
    public static int process(int[] w, int[] v, int index, int bag){
        //递归停止条件
        if  (bag < 0) return -1;
        if(index == w.length){
            return 0;
        }

        //不要当前位置
        int p1 = process(w, v, index + 1, bag);

        //要当前位置, 返回加上现在的货物价值
        int p2 = 0;
        //考虑当前背包容量为6，装入了重量为10的货物，不能多放货物
        int next = process(w, v, index + 1, bag - w[index]);
        if(next != -1){ // 放入当前货物有效
            p2 = v[index] + next;
        }
        

        return Math.max(p1, p2);

    }

    //有重复计算过程
    //index 当前选择货物的下标  0 ~ N
    //bag 负数 ~ Bag
    //可变参数为2
    public static int dp(int[] w, int[] v, int bag){
        if(w == null || v == null || w.length != v.length || w.length == 0){
            return 0;
        }
        //可变参数为2，记录数值。 dp数组含义  dp[i][j] = 0~i货物，j容量能存放的最大价值
        int[][] dp = new int[w.length + 1][bag + 1];
        //dp数组初始化
        //最后一行全是零 ，观察子函数得到 
        // if(index == w.length) return 0;
        //默认初始化为零
        for(int i = 0; i <= bag; i++){
            dp[w.length][i] = 0;
        }
        //填dp
        for (int index = w.length - 1; index >= 0; index--){
            for(int rest = 0; rest <= bag; rest++){
                //确定状态转移方程
                //不拿当前货物
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                //拿当前货物
                if(next != -1){
                    p2 = v[index] + next;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }

        //返回右上角的值，货物全部取完，而且bag容量最大
        //暴力递归需要[0，bag]的值
        return dp[0][bag];
    }


    
    public static int process1(int[] w, int[] v, int index, int bag){
        //递归停止条件
        if  (bag < 0) return -1;
        if(index == w.length){
            return 0;
        }

        //不要当前位置
        int p1 = process(w, v, index + 1, bag);

        //要当前位置, 返回加上现在的货物价值
        int p2 = 0;
        //考虑当前背包容量为6，装入了重量为10的货物，不能多放货物
        int next = process(w, v, index + 1, bag - w[index]);
        if(next != -1){ // 放入当前货物有效
            p2 = v[index] + next;
        }
        

        return Math.max(p1, p2);

    }
}

