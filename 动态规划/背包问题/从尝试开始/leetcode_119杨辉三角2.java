package 动态规划.背包问题.从尝试开始;

import java.util.ArrayList;
import java.util.List;;
public class leetcode_119杨辉三角2 {
    public static void main(String[] args) {
        
    }

    public static List<Integer> getRow(int rowIndex) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> p1 = new ArrayList<>();
        p1.add(1);
        res.add(p1);
        for(int i = 1; i <= rowIndex; i++){
            List<Integer> temp = res.get(0);
            ArrayList<Integer> p2 = new ArrayList<>();
            p2.add(1);
            for(int j = 0; j < temp.size() - 1; j++){
                p2.add(temp.get(j) + temp.get(j + 1));
            }
            p2.add(1);
            res.add(new ArrayList<>(p2));
            res.remove(0);
        }

        return res.get(0);
    }
}
