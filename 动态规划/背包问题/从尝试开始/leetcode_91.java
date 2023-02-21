package 动态规划.背包问题.从尝试开始;

public class leetcode_91 {
    public static void main(String[] args) {
        System.out.println(number("124312124"));
        System.out.println(number1("220"));
    }


    public static int number(String str){
        if(str == null || str.length() == 0){
            return 0;
        }

        return process1(str.toCharArray(), 0);
    }


    //str[...i - 1] 转化有多少种方法
    //递归返回 str[i....]种方法
    public static int process1(char[] str, int i){
        //递归停止条件: 不好想
        if(i == str.length){
            return 1; //所有字符全部被转化完，有一种方法
        }

        //其实确定一位两位
        // i 还有字符
        if(str[i] == '0') {
            return 0;  //说明上一步的转化有问题
        }

        //先转i位置，再转 i+1

        //1
        //i位置字符单转
        int ways = process1(str,  i + 1);

        // 如果i + 1 < N, 可以
        if(i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27){
            ways += process1(str,  i + 2);
        }
        return ways;
    }


    //一维表
    //最终的结果返回dp[0];
    public static int number1(String str){
        char[] s = str.toCharArray();
        int N = s.length;
        //dp只和i有关， 所以dp数组是一维的
        int[] dp = new int[N + 1];
        //dp数组的含义 dp[i]: i位置可以有的转发方法数
        //递归顺序，从右往左填
        //初始化dp数组
        dp[N] = 1;

        for(int  i = N - 1; i >= 0; i--){
            //填充数组
            if(s[i] !='0'){
                dp[i] += dp[i + 1];
                if(i + 1 < s.length && (s[i] - '0') * 10 + s[i + 1] - '0' < 27)
                    dp[i] += dp[i + 2];
                
            }

        }
        

        return dp[0];
    }
}
