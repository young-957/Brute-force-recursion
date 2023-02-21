package 动态规划.背包问题.从尝试开始;
// 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
// 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
// 0 <= j <= nums[i] 
// i + j < n
// 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/jump-game-ii
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class leetcode_45跳跃游戏2 {
    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,1,4};
        System.out.println(jump(nums));
    }

    public static int jump(int[] nums) {
        int n = nums.length;
        if(n == 1){
            return 0; //一开始就在最后位置;不用跳
        }
        int minStep = 0;
        //测试用例必可以到达终点
        int curMaxNextIndex = nums[0];//0 位置可以去的最远的位置
        int reach = 0;//当前cur可遍历到的最远位置
        //贪心：寻找从当前位置开始能去的最远的位置
        for(int cur = 0; cur < n; cur++){
            curMaxNextIndex = Math.max(curMaxNextIndex, cur + nums[cur]);
            if(curMaxNextIndex >= n - 1) return minStep + 1;//这一步走完就能到达重点
            //遍历完当前cur的范围；步数++
            if(cur == reach){
                minStep++;
                reach = curMaxNextIndex;
            }
        } 
        return minStep;
    }
}

