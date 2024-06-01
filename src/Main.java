import java.util.ArrayList;
import java.util.List;

public class Main {

    static int triangle[][] = {{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}};

    static int r[][]= new int[4][4];

    static int selected[][] = new int[5][5];


    public static void main(String[] args) {

        int n = triangle.length;

        System.out.println("Min Sum Path is : ");
        System.out.println(MinimumSumPath(triangle, n,0,0));


        System.out.println();
        System.out.println("The selected path");
        PrintSelected(selected, 0 ,0);
        System.out.println();


    }

    private static void PrintSelected(int[][] selected, int i, int j) {
        if(selected[i][j] == -1){
            System.out.println(triangle[i][j]);
            PrintSelected(selected,i+1,j);
        }
        if (selected[i][j] == 1){
            System.out.println(triangle[i][j]);
            PrintSelected(selected, i+1, j+1);
        }
        if (selected[i][j]==10){
            System.out.println(triangle[i][j]);
            return;
        }

    }

    public static int MinimumSumPath(int[][] triangle, int n,int i, int j){
            if (i == n-1)
            {
                selected[i][j]=10;
                return triangle[i][j];
            }
            if(selected[i][j]!=0)
            {

                return r[i][j];
            }
            int q = MinimumSumPath(triangle,n,i+1,j) ;
            int p = MinimumSumPath(triangle,n,i+1,j+1);
            if (p>q)
            {
                r[i][j]=q + triangle[i][j] ;
                 selected[i][j]=-1;
                 return r[i][j];
            }
            else {
                r[i][j] =p + triangle[i][j];
                selected[i][j]=1;
                return r[i][j];
            }

        }



    }
