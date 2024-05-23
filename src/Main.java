import java.util.ArrayList;
import java.util.List;

public class Main {

    static int triangle[][] = {{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}};
    static int r[][]= new int[4][4];
    static int s[][]= new int[4][4];
    static int selected[][] = new int[8][8];


    public static void main(String[] args) {

        int n = triangle.length;

        System.out.println(MinimumSumPath(triangle, n,0,0));
        for (int i =0 ; i<n; i++){
            for (int j = 0; j < n; j++) {
                if(selected[i][j]!= 0){
                    System.out.println("index i: "+ i + "index j:"+ j);
                    System.out.println(selected[i][j]+ " ");
                }

            }

        }

    }
        public static int MinimumSumPath(int[][] triangle, int n,int i, int j){
            if (i == n-1)
            {
                return triangle[i][j];
            }
            if(r[i][j]>0)
            {
                System.out.println("Here returned");
                return r[i][j];
            }
            int q = MinimumSumPath(triangle,n,i+1,j) ;
            int p = MinimumSumPath(triangle,n,i+1,j+1);
            if (p>q)
            {
                r[i][j]=q + triangle[i][j] ;
                 s[i][j]=triangle[i][j];

                 selected[i][j]=triangle[i][j];
                 return r[i][j];
            }
            else {
                r[i][j] =p + triangle[i][j];
                s[i][j]=triangle[i][j];

                selected[i][j]= triangle[i][j];
                return r[i][j];
            }

        }



    }
