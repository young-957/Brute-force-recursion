package 动态规划.背包问题.从尝试开始;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class leetcode_22括号生成 {

    static List<String> res = new ArrayList<>();
    static List<String> res1 = new ArrayList<>();
    public static void main(String[] args) {
        String test = "(((())))";
        int n = 4;
        System.out.println(isValid(test));
        System.out.println(generateParenthesis(n));
        System.out.println(generateParenthesis1(n));
    }

    public  static List<String> generateParenthesis1(int n) {
        process1(n, 0, 0, new String());
       return res1;
   }
    //剪枝
    //L '('的数量; R ')'的数量
    public static void process1(int n, int L, int R, String str){
        //统计左右括号的频次，作为剪枝条件
        if(L > n || R > n || R > L){
            return;
        }
        if(L == n && R == n && !res1.contains(str)){
            res1.add(str);
        }
        process1(n, L + 1, R, str + '(');
        process1(n, L, R + 1, str + ')');
    }
   





    public  static List<String> generateParenthesis(int n) {
         char[] c =  new char[2 * n];
        for(int i = 0; i < 2 * n; i++){
            c[i] = i < n ? '(' : ')';
        }
        boolean[] isOk = new boolean[2 * n];
        //默认为false，代表未使用；true 代表使用过
        process(n, new String(), c, isOk);
        return res;
    }


//返回当前组成的str
    //res.add(str)
    public static void process(int n, String str, char[] c, boolean[] test){
        //basecase
        // System.out.print(str + "  ");
        //所有括号已经用完，并且str有效
        if(str.length() == 2 * n){
            // System.out.print(str);
            if(isValid(str)){
                // System.out.print(str);
                if(!res.contains(str)){
                    res.add(new String(str));
                }
            }
        }
        for(int i = 0; i < 2 * n; i++){
            if(str.indexOf(")") == 0){
                break;
            }
            if(!test[i]){
                str += c[i];
                // System.out.print(str +  "  " + str.length() + " ");
                test[i] = true; //标记为已使用
                process(n, str, c, test);
                test[i] = false;
                str = str.substring(0, str.length() - 1); //复原
            }
        }

    }

    //验证括号是否有效
    public static boolean isValid(String s){
        char[] c = s.toCharArray();
        Stack<Character> left = new Stack<>();
        for(char a : c){
            if(a == '('){
                left.push(a);
            }
            else if(!left.isEmpty() && left.peek() != a){
                left.pop();
            }
            else return false;
        }
        return left.isEmpty();
    }
}
