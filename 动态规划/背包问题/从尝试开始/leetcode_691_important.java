package 动态规划.背包问题.从尝试开始;
// 我们有 n 种不同的贴纸。每个贴纸上都有一个小写的英文单词。
// 您想要拼写出给定的字符串 target ，方法是从收集的贴纸中切割单个字母并重新排列它们。如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。
// 返回你需要拼出 target 的最小贴纸数量。如果任务不可能，则返回 -1 。
// 注意：在所有的测试用例中，所有的单词都是从 1000 个最常见的美国英语单词中随机选择的，并且 target 被选择为两个随机单词的连接。
//  
// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/stickers-to-spell-word
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.HashMap;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.text.TabExpander;

//完全背包
public class leetcode_691_important {
    public static void main(String[] args) {
        String[] stickers = new String[]{"with","example","science"};
        String target = "thehat";

        System.out.println(minStickers1(stickers, target));
        System.out.println(minStickers2(stickers, target));
        System.out.println(minStickers3(stickers, target));
    }


    //暴力递归，会超时
    public static int minStickers1(String[] stickers, String target){
        int res = process1(stickers, target);
        return res == Integer.MAX_VALUE ? -1 : res;
    }


    //返回最小min贴纸数
    //dp数组是一维的
    public static int process1(String[] stickers, String target){
            if(target.length() == 0){
                return 0;
            }

            //当做完全背包，对字符进行尝试；每张字符都当做第一张
            int min =  Integer.MAX_VALUE;
            for(String first : stickers){  //但是也可以不用第一张啊
                //尝试first字符，返回剩余字符
                String rest = minus(first, target);
                //判断是否拼凑
                if(rest.length() != target.length()){ //使用了一张
                    min = Math.min(min, process1(stickers, rest));
                }
            }
            
            return min + (min == Integer.MAX_VALUE ? 0 : 1); //如果有min被修改过，说明本层递归有一张贴纸被使用
    }

    public static String minus(String first, String target){
        char[] s1 = first.toCharArray();
        char[] s2 = target.toCharArray();
        int[] count1 = new int[26];
        // int[] count2 = new int[26];
        //小写字母减97
        //统计两个字符串中字符出现的次数
        for(char cha : s2){
            count1[cha - 'a']++;
        }
        for(char cha : s1){
            count1[cha - 'a']--;  //会有位置为负数
        }

        // for(int i = 0; i < 26; i++){
        //     count2[i] -= count1[i];
        // }

        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("构建剩余字符");
        for(int i = 0; i < 26; i++){
            if(count1[i] > 0){
                for(int j = 0; j < Math.abs(count1[i]); j++){
                    stringBuilder.append((char) (i + 'a'));
                }
                
            }
            // if(count[i] < 0){
            //     for(int j = 0; i > count[i]; j--){
            //         stringBuilder.append((char) (i + 'a'));
            //     }
            //     stringBuilder.append((char) (i + 'a'));
            // }
        }
        System.out.println(stringBuilder);

        //拼凑字符串




        return stringBuilder.toString();
    }


    //字符串相减本质是字符频率相减
    public static int minStickers2(String[] stickers, String target){
        //统计每个字符中不同字母出现的频率\
        //词频统计
        int[][] tmp = new int[stickers.length][26];
        for(int i = 0; i < stickers.length; i++){
            char[] charArray = stickers[i].toCharArray();
            for(char cha : charArray){
                tmp[i][cha - 'a']++;
            }
        }

        int res = process2(tmp, target);

        return res == Integer.MAX_VALUE ? -1 : res;
    }


    public static int process2(int[][] stickers, String target){
        //base_case
        if(target.length() == 0){
            return 0; //不需要使用贴纸了
        }
        //统计target的词频
        int[] tcounts = new int[26];
        char[] t = target.toCharArray();
        for(char c : t){
            tcounts[c - 'a']++;
        }

        //统计结束，对冲
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        //开始递归；每张字符都有可能使用
        //需要剪枝优化
        for(int i = 0; i < N; i++){
            int[] sticker = stickers[i]; //取出第i个字符
            //剪枝：只含有第一个字符的贴纸先试。剪枝位置任选
            if(sticker[t[0] - 'a'] > 0){
                StringBuilder stringBuilder = new  StringBuilder();
                //尝试字符
                for(int j = 0; j < 26; j++){
                    //有26个字符从0位置开始统计复原字符
                    //从target有这个字符的位置开始计算
                    if(tcounts[j] > 0){
                        int nums = tcounts[j] - sticker[j];
                        for(int k = 0; k < nums; k++){ //差值为负数的不会进入for
                            stringBuilder.append((char)(j + 'a'));
                        }
                    }
                                                               
                }

                String rest = stringBuilder.toString();
                min = Math.min(min, process2(stickers, rest)); //如果没有结果则一直返回 Integer.MAX_VALUE
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    //字符串相减本质是字符频率相减
    //字符的变化太大，所以不太适合做矩阵
    //字符串的可变参数，不做严格表结构，记忆化搜索即可
    //字符的下标没有特殊含义
    //使用map，傻缓存
    public static int minStickers3(String[] stickers, String target){
        //统计每个字符中不同字母出现的频率\
        //词频统计
        int[][] tmp = new int[stickers.length][26];
        for(int i = 0; i < stickers.length; i++){
            char[] charArray = stickers[i].toCharArray();
            for(char cha : charArray){
                tmp[i][cha - 'a']++;
            }
        }
        HashMap<String,Integer> dp = new HashMap<String, Integer>();
        dp.put("", 0);
        int res = process3(tmp, target, dp);

        return res == Integer.MAX_VALUE ? -1 : res;
    }


    //dp 剩余字符和所使用的牌数
    public static int process3(int[][] stickers, String target, HashMap<String, Integer> dp){
        //若有记录，则返回
        if(dp.containsKey(target)){
            return dp.get(target);
        }
        //统计target的词频
        int[] tcounts = new int[26];
        char[] t = target.toCharArray();
        for(char c : t){
            tcounts[c - 'a']++;
        }

        //统计结束，对冲
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        //开始递归；每张字符都有可能使用
        //需要剪枝优化
        for(int i = 0; i < N; i++){
            int[] sticker = stickers[i]; //取出第i个字符
            //剪枝：只含有第一个字符的贴纸先试。剪枝位置任选
            if(sticker[t[0] - 'a'] > 0){
                StringBuilder stringBuilder = new  StringBuilder();
                //尝试字符
                for(int j = 0; j < 26; j++){
                    //有26个字符从0位置开始统计复原字符
                    //从target有这个字符的位置开始计算
                    if(tcounts[j] > 0){
                        int nums = tcounts[j] - sticker[j];
                        for(int k = 0; k < nums; k++){ //差值为负数的不会进入for
                            stringBuilder.append((char)(j + 'a'));
                        }
                    }
                                                               
                }

                String rest = stringBuilder.toString();
                min = Math.min(min, process3(stickers, rest, dp)); //如果没有结果则一直返回 Integer.MAX_VALUE
            }
        }

        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(target, ans);
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

}
