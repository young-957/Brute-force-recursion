package 动态规划.背包问题.从尝试开始;
// 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
// 子数组 是数组中的一个连续部分。
public class leetcode_53最大子数组和 {
    public static void main(String[] args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {
        //          该算法更为简便之处是忽略了对子序列的寻找比较,而是根据规律直接找出最佳答案.
        // 对于含有正数的序列而言,最大子序列肯定是正数,所以头尾肯定都是正数.我们可以从第一个正数开始算起,每往后加一个数便更新一次和的最大值;当当前和成为负数时,则表明此前序列无法为后面提供最大子序列和,因此必须重新确定序列首项.
                int res = nums[0];
                int sum = 0;
                for (int i = 0; i < nums.length; i++){
                    //有负数有正数
                    if(sum > 0){
                        //sum对后面的数有好处
                        sum += nums[i];
                    }else sum = nums[i]; //没好处 当前值为sum
        
                    res = Math.max(res, sum);
                }
                return res;
            }
}
