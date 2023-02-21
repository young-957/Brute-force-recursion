package 动态规划.背包问题.从尝试开始;

import javax.print.DocFlavor.READER;

// 给定一个整型数组arr，代表数值不同的纸牌排成一条线，
// 玩家A和玩家B依次拿走每张纸牌，规定玩家A先拿，玩家B后拿，
// 但是每个玩家每次只能拿走最左和最右的纸牌，玩家A和玩家B绝顶聪明。
// 请返回最后的获胜者的分数。
public class 纸牌博弈 {
    public static void main(String[] args) {
        int[] arr = new int[]{1,100,109};
        System.out.println(纸牌博弈.win1(arr));
        System.out.println(纸牌博弈.win2(arr));
        System.out.println(纸牌博弈.win3(arr));
        }


    //暴力递归  

    //根据规则，返回获胜者的分数
    public static int win1(int[] arr){
        if(arr == null || arr.length == 0) return 0;
        int first = f1(arr, 0, arr.length - 1);
        int seccond = g1(arr, 0, arr.length - 1);
        return Math.max(first, seccond);
    }

    //先手函数，arr[L...R]返回先手可以获得的最好分数
    public static int f1(int[] arr, int L, int R){
        if(L == R){
            return arr[L];
        }
        int p1 = arr[L] + g1(arr, L + 1, R);
        int p2 = arr[R] + g1(arr, L, R - 1);
        return Math.max(p1, p2);
    }

    //后手函数，返回后手可以获得的最好分数
    public static int g1(int[] arr, int L, int R){
        if(L == R){
            //对方先手，拿走最后一张牌
            return 0;
        }
        //对方拿走arr[R]
        int p1 = f1(arr, L , R - 1);
        //对方拿走arr[L]
        int p2 = f1(arr, L + 1, R);
        //后手只能拿走最小的数
        return Math.min(p1, p2);
        
    }

    //优化 缓存法
    //观察依赖

    //根据规则，返回获胜者的分数
    public static int win2(int[] arr){
        int N = arr.length;
        if(arr == null || N == 0) return 0;
        
        //两张dp表
        //表长度
        //表的含义：返回的L R 的最好分数
        //先手表 
        int[][] fmap = new int[N][N];
        //后手表
        int[][] gmap = new int[N][N];
        //arr值大于零
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                    fmap[i][j] = -1;
                    gmap[i][j] = -1;
            }
        }

        int first = f2(arr, 0, arr.length - 1, fmap, gmap);
        int seccond = g2(arr, 0, arr.length - 1, fmap, gmap);
        return Math.max(first, seccond);
    }

    //先手函数，arr[L...R]返回先手可以获得的最好分数
    public static int f2(int[] arr, int L, int R, int[][] fmap, int[][] gmap){
        if(fmap[L][R] != -1) return fmap[L][R];

        int ans = 0;
        if(L == R){
            //拿走最后一张牌
            ans = arr[L];
            
        } else {
            int p1 = arr[L] + g2(arr, L + 1, R, fmap, gmap);
            int p2 = arr[R] + g2(arr, L, R - 1, fmap, gmap); 
            ans = Math.max(p1, p2);
        }
        
        fmap[L][R] = ans;

        return ans;
    }

    //后手函数，返回后手可以获得的最好分数
    public static int g2(int[] arr, int L, int R,  int[][] fmap, int[][] gmap){
        if(gmap[L][R] != -1) return gmap[L][R];

        int ans = 0;
        if(L == R){
            //对方先手，拿走最后一张牌
            ans = 0;
        }else {
            //对方拿走arr[R]
            int p1 = f2(arr, L , R - 1, fmap, gmap);
            //对方拿走arr[L]
            int p2 = f2(arr, L + 1, R, fmap, gmap);
            ans = Math.min(p1, p2);
        }
        gmap[L][R] = ans;
        //后手只能拿走最小的数
        return ans;
        
    }



    //动态规划返回获胜者的分数
    public static int win3(int[] arr){
        int N = arr.length;
        if(arr == null || N == 0) return 0;
        
        //两张dp表
        //表长度
        //表的含义：返回的L至R 的最好分数 
        //返回结果 0 ~ N - 1;
        //先手表 
        int[][] fmap = new int[N][N];
        //后手表
        int[][] gmap = new int[N][N];
        //数组初始化
        //初始化对角线元素
        for(int i = 0, j = 0; i < N && j < N; i++, j++){
                fmap[i][j] = arr[i];
                gmap[i][j] = 0;
        }
        //数组左下角不会使用到 因为L <= R.
        //沿对角线填充数组
        for(int startCol = 1; startCol < N; startCol++){
            int L = 0;
            int R = startCol; //以列为边界
            while(R < N){
                fmap[L][R] = gmap[L  +1][R] + gmap[L][R - 1];
                
                int p1 = arr[L] + gmap[L + 1][R];
                int p2 = arr[R] + gmap[L][R - 1];

                fmap[L][R] = Math.max(arr[L] + gmap[L + 1][R], arr[R] + gmap[L][R - 1]);

                gmap[L][R] = Math.min(fmap[L + 1][R], fmap[L][R - 1]);

                L++;
                R++;
            }
            
        }

        return Math.max(fmap[0][N - 1],gmap[0][N - 1]);
    }





}
