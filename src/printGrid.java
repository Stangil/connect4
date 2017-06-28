/**
 * Created by Stan Gilstrap on 4/2/2017.
 */
public class printGrid
{
    public static void displayGrid(char[][] grid)
    {
        for(int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
                System.out.print("|" + grid[i][j]);
            System.out.println("|");
        }
        System.out.print("|");
        for(int i = 0; i < connect4.columns; i++)
        {
            System.out.print((i+1)+"|");
        }
        System.out.println();
    }
}
