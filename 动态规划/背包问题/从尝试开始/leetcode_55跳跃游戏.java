package 动态规划.背包问题.从尝试开始;

public class leetcode_55跳跃游戏 {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 1, 4};
        System.out.println(method1(nums));
    }



    public static boolean method1(int[] nums){
        int n = nums.length;
        if(n == 1){
            return true; //一开始就在最后位置
        }
        if(nums[0] == 0){
            return false;
        }
        
        int energy = nums[0];//当前可以往前走的步数
        int cur = 1;//默认数组长度大于0了
        for(cur = 1; energy != 0 &&cur < n; cur++){
            energy--;//走了一步
            if(nums[cur] >= energy){ //当前位置可走的步数比energy（原可走的步数大）
                energy = nums[cur];
            }
        } 

        return cur == n;
    } 
}
