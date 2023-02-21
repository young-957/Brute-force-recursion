package 动态规划.背包问题.从尝试开始;
// 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
// 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/longest-palindromic-subsequence
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// 示例 1：

// 输入：s = "bbbab"
// 输出：4
// 解释：一个可能的最长回文子序列为 "bbbb" 
public class leetcode_516 {
    public static void main(String[] args) {
        //串和自己逆序串的最长公共子序列就是回文子序列
        String test = "bbbab";
        System.out.println(longestPalindromeSubseq1(test));
        System.out.println(longestPalindromeSubseq2(test));

    }


    //尝试
    public static int longestPalindromeSubseq1(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        char[] str = s.toCharArray();
        return process1(str, 0, str.length - 1);
    }


    //返回str[L...R]上的最长回文子序列
    public static int process1(char[] str, int L, int R){
        //base-case
        if(L == R){
            return 1;
        }
        if(L == R - 1){
            return str[L] == str[R] ? 2 : 1;
        }
        //普遍情况
        //以L开头，以R结尾
        //使用if不方便
        int p4 = str[L] == str[R] ? process1(str, L + 1, R - 1) + 2 : 0;
        //以L开头，不以R结尾
        int p3 = process1(str, L, R - 1);

        //不以L开头，以R结尾
        int p2 = process1(str, L + 1, R);

        //不以L开头，不以R结尾
        int p1 = process1(str, L + 1, R - 1);

        return Math.max(Math.max(p2, p1), Math.max(p3, p4));

    }

     //尝试
     public static int longestPalindromeSubseq2(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        char[] str = s.toCharArray();

        int N = str.length;
        int[][] dp = new int[N][N];
        //dp为L 到 R 上的最长回文子序列的长度
        //初始化dp数组
        //最好画一个图，清晰明了
        dp[N -1][N - 1] = 1;
        for(int i = 0; i < N - 1; i++){
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        // for(int i = 0; i < N - 1; i++){
        //     dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        // }

        //填充dp数组
        //寻找依赖位置
        //对角线右下方无意义
        //从底往上，从左往右 
        for(int L = N - 3; L >= 0; L--){ //L
            for(int R = L + 2; R < N; R++){ //R
                //以L开头，以R结尾
                // int p4 = str[L] == str[R] ? dp[L + 1][R - 1]  + 2 : 0;
                //以L开头，不以R结尾
                int p3 = dp[L][R - 1];

                //不以L开头，以R结尾
                int p2 = dp[L + 1][R];

                //不以L开头，不以R结尾
                // int p1 = dp[L + 1][R - 1];  //左下；抛弃

                dp[L][R] = Math.max(p2, p3);
                if(str[L] == str[R]){
                    dp[L][R] = Math.max(dp[L][R], dp[L + 1][R - 1]  + 2);
                }
                
            }
        }
        
        

        return dp[0][N - 1];
    }

    


}
