package 动态规划.背包问题.从尝试开始;

public class leetcode_5最长回文子串 {
    public static void main(String[] args) {
        String test = "cbba";
        System.out.println(process(test));
        System.out.println(processDp(test));
    }


    
    // dp
    public static String processDp(String s){
        int n = s.length();
        if(n < 2){
            return s;
        }
        //dp[i][j]: i到j的位置上的子串是否是回文串
        //dp[i][j] 考虑 i + 1 和 j - 1的位置
        boolean[][] dp = new boolean[n][n];
        //行为L; 列为R
        //单个字串肯定是回文
        for(int i = 0; i < n; i++){
            dp[i][i] = true;
        }
        char[] str = s.toCharArray();
        int maxlen = 1;
        int begin = 0;
        //不确定当前i 对应哪一个j
        for(int increments = 1; increments < n; increments++){
            for(int i = 0; i < n; i++){
                //从上到下，从左到右
                int j = i + increments;
                if(j >= n){
                    break; //本行轮换结束
                }
                //二者不相同默认为false
                if(str[i] == str[j]){
                    if(j - i == 1){
                        dp[i][j] = true;
                    }
                    else {
                        dp[i][j] = dp[i + 1][j - 1]; //正确状态转移
                    }
                }

                //当前状态为true，比 maxlen 大 
                if(dp[i][j] && increments + 1 > maxlen){
                    maxlen = increments + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxlen);
    }



    //暴力解法
    public static String process(String s){
        int maxlen = 1; //只有一个字符，就是自身
        int start = 0;
        char[] str = s.toCharArray();
        int n = str.length;
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if( j - i + 1 > maxlen && isRight(str, i, j)){
                    maxlen = j - i + 1;
                    start = i;
                } 
            }
        }

        return s.substring(start, start + maxlen);
    }
    public static boolean isRight(char[] str, int begin, int end){
        while(begin < end){
            if(str[begin] != str[end]){
                return false;
            }
            begin++;
            end--;
        }


        return true;
    }
}
