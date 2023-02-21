package 动态规划.背包问题.从尝试开始;


import java.util.Comparator;
import java.util.PriorityQueue;

// 数组arr[i]代表第i个咖啡机做好咖啡的时间，一共有n个人
// 1台洗咖啡机，洗一个杯子需要a时间，杯子自己挥发干净需要b时间，洗咖啡机只能洗完一个杯子接着洗下一个杯子，串行工作，杯子挥发干可以并行挥发。
// 求让全部咖啡杯变干净的最早时间
public class coffeeMachinde {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 1, 1, 5, 5};
        System.out.println( method1(arr, 1, 3, 10));
        System.out.println( method2(arr, 1, 3, 10));
    }
    
    
    public static class MachineComparator implements Comparator<Drinktime> {
        @Override
		public int compare(Drinktime o1, Drinktime o2) {
			return (o1.orderTime + o1.workTime) - (o2.orderTime + o2.workTime);
		}
    }

    public static int method2(int[] arr, int n, int wash, int air){
        return drinkBestTime2(arr, n, wash, air);
    }
    public static int bestTimeDp(int[] drinks, int wash, int air){
        int n = drinks.length;
        int maxFree = 0;
        //需要求出最大
        for(int i = 0; i < drinks.length; i++){
            maxFree = Math.max(maxFree, drinks[i]) + wash;
        }
        //找到最大洗净free时间
        //创建dp数组
        int[][] dp = new int[n + 1][maxFree + 1];
        //填充dp数组
        // 每一行依赖下一行；最后一行全为零
        for(int index = n - 1; index >= 0; index--){
            for(int free = 0; free <= maxFree; free++){
                int selfClean1 = Math.max(drinks[index], free) + wash;
                if(selfClean1 > maxFree){
                    continue;  //不需要再进行计算，已超过最大时间
                }
                int restClean1 = dp[index + 1][selfClean1];
                //可以洗的时间和空闲时间不一致，选择max
                
                int p1 = Math.max(selfClean1, restClean1);

                //2.决定自然挥发
                int selfClean2 = drinks[index] + air;
                int restClean2 = dp[index + 1][free];
                int p2 = Math.max(selfClean2, restClean2);
                dp[index][free] = Math.min(p1, p2);
            }
        }

        return dp[0][0];

        


        

    }

    //获得每个人开始洗杯子的时间
    public static int drinkBestTime2(int[] arr, int n, int wash, int air){
        //生成优先级队列
        PriorityQueue<Drinktime> priorityQueue = new PriorityQueue<Drinktime>(new MachineComparator());
        for(int i = 0; i < arr.length; i++){
            // arr[i]代表第i个咖啡机做好咖啡的时间
            priorityQueue.add(new Drinktime(0, arr[i]));
        }

        //计算每个人喝完的时间，也就是每个杯子开始洗的时间
        int[] drinks = new int[n];
        for(int i = 0; i < n; i++){
            //使用n次咖啡机
            Drinktime cur = priorityQueue.poll();
            cur.orderTime += cur.workTime;
            drinks[i] = cur.orderTime;
            priorityQueue.add(cur);
        }
        return bestTimeDp(drinks, wash, air);
    }
    public static int  method1(int[] arr, int n, int wash, int air){
        return drinkBestTime(arr, n, wash, air);
    }

    public static int drinkBestTime(int[] arr, int n, int wash, int air){
        //生成优先级队列
        PriorityQueue<Drinktime> priorityQueue = new PriorityQueue<Drinktime>(new MachineComparator());
        for(int i = 0; i < arr.length; i++){
            // arr[i]代表第i个咖啡机做好咖啡的时间
            priorityQueue.add(new Drinktime(0, arr[i]));
        }

        //计算每个人喝完的时间，也就是每个杯子开始洗的时间
        int[] drinks = new int[n];
        for(int i = 0; i < n; i++){
            //使用n次咖啡机
            Drinktime cur = priorityQueue.poll();
            cur.orderTime += cur.workTime;
            drinks[i] = cur.orderTime;
            priorityQueue.add(cur);
        }
        return washBestTime(drinks, wash, air, 0, 0);
    }


    //drinks所有杯子可以开始洗的时间
    //wash 单杯洗干净的时间  （串行）
    //air 挥发干净的时间 （并行）
    //free 洗的机器何时可用的时间,从0开始计时。
    //返回drinks[index....] 都变干净的最短时间
    public static int washBestTime(int[] drinks, int wash, int air, int index, int free){
        //base_case
        if(index == drinks.length){
            return 0; //所有杯子都洗完了
        }
        //开始洗index号杯子，怎么洗呢
        //选并行和串行时间总的花费最短的方式
        //1.决定洗
        int selfClean1 = Math.max(drinks[index], free) + wash;  //可以洗的时间和空闲时间不一致，选择max
        int restClean1 = washBestTime(drinks, wash, air, index + 1, selfClean1);
        int p1 = Math.max(selfClean1, restClean1);

        //2.决定自然挥发
        int selfClean2 = drinks[index] + air;
        int restClean2 = washBestTime(drinks, wash, air, index + 1, free);
        int p2 = Math.max(selfClean2, restClean2);
        return Math.min(p1, p2);

    }
}
class Drinktime{
    int orderTime;
    int workTime;
    public Drinktime(int orderTime, int workTime){
        this.orderTime = orderTime;
        this.workTime = workTime;
    }
}