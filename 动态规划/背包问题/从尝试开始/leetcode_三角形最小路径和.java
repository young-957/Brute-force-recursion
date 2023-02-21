package 动态规划.背包问题.从尝试开始;
import java.util.ArrayList;
import java.util.List;;
public class leetcode_三角形最小路径和 {
    public static void main(String[] args) {
        
    }
    public int minimumTotal(List<List<Integer>> triangle) {
        //存出结果的List res
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>(triangle.get(0)));
        //自下而上  从上到下太慢了 
        for(int i  = triangle.size() - 2; i >= 0; i--){
            for(int j = 0; j < triangle.get(i).size(); j++){
                triangle.get(i).set(j, triangle.get(i).get(j) + 
                 Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
            }
        }

        return triangle.get(0).get(0);
    }

    public static int minimumTotal1(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] f = new int[n];
        f[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i] = f[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; --j) {
                f[j] = Math.min(f[j - 1], f[j]) + triangle.get(i).get(j);
            }
            f[0] += triangle.get(i).get(0);
        }
        int minTotal = f[0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[i]);
        }
        return minTotal;
    }

// 作者：LeetCode-Solution
// 链接：https://leetcode.cn/problems/triangle/solution/san-jiao-xing-zui-xiao-lu-jing-he-by-leetcode-solu/
// 来源：力扣（LeetCode）
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
