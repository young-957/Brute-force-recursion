package 动态规划.背包问题.从尝试开始;

// 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
// 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
// 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/longest-common-subsequence
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class leetcode_1143 {
    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace" ;
        System.out.println(longestCommonSubsequence1(text1, text2));
        System.out.println(longestCommonSubsequence2(text1, text2));
    }




    public static int longestCommonSubsequence1(String text1, String text2) {
        if(text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0){
            return 0;
        }
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        return process1(str1, str2, str1.length - 1, str2.length - 1);
    }
    //求公共最长子序列，那么从最后的字母开始尝试，逐一对比，两个字符数组异步扫描
    //str1[0...i] 和 str2[0...j]的最长公共子序列
    // i,j 从左到右遍历
    public static int process1(char[] str1, char[] str2, int i, int j){
        if( i == 0 && j == 0){
            return str1[i] == str2[j] ? 1 : 0;
        }else if (i == 0){
            if( str2[j] == str1[i]){
                return 1;
            }else{
                return process1(str1, str2, i, j - 1);
            }
        } else if(j == 0){
            if( str1[i] == str2[j]){
                return 1;
            }else{
                return process1(str1, str2, i - 1, j);
            }
        } else {
            //i != 0 && j != 0
            int p1 = process1(str1, str2, i, j - 1);
            int p2 = process1(str1, str2, i - 1, j);
            int p3 = str1[i] == str2[j] ? (1 + process1(str1, str2, i - 1, j - 1)) : 0;
            return Math.max(p1, Math.max(p2, p3)); 
        }
        
    }


    public static int longestCommonSubsequence2(String text1, String text2) {
        if(text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0){
            return 0;
        }
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        int n1 = str1.length;
        int n2 = str2.length;
        int[][] dp = new int[str1.length][str2.length];
        //dp数组初始化
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        //填充0行
        for(int  j = 1; j < n2; j++){
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        //填充0列
        for(int  j = 1; j < n1; j++){
            dp[j][0] = str1[j] == str2[0] ? 1 : dp[j - 1][0];
        }
        for(int i = 1; i < n1; i++){
            for(int j = 1; j < n2; j++){
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = str1[i] == str2[j] ? (1 + dp[i -1][j -1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }

        return dp[n1 - 1][n2 - 1];
    }


}
