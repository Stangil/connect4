import java.util.Scanner;

/**
 * Created by Stan Gilstrap on 3/19/2017.
 */
public class connect4
{
    public static int columns;
    public static int rows;
    public static int winNum;
    public static boolean columnFull = false;
    private static boolean singlePlayer = false;
    private static String single = new String();
    private static boolean playerGoesFirst = true;
    private static String playerFirst = new String();
    public static int moveNumber = 0;
    public static char chipColor;
    private static int columnPosition;
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter how many rows");
        rows = input.nextInt();
        while(rows <= 2 || rows > 12)
        {
            System.out.println("Must be between 3 and 12 rows");
            rows = input.nextInt();
        }
        System.out.println("Enter how many columns");
        columns = input.nextInt();
        while(columns < 5 || columns > 14)
        {
            System.out.println("Must be between 5 and 14 columns");
            columns = input.nextInt();
        }
        System.out.println("Enter how many in a row to win");
        winNum = input.nextInt();
        while(winNum >= rows || winNum >= columns||winNum < 2)
        {
            System.out.println("Win number must be less than number of columns or rows and greater than 1");
            winNum = input.nextInt();
        }
        while(!single.equals("y") && !single.equals("Y") && !single.equals("n") && !single.equals("N")) {
            System.out.println("Single player, y or n: ");
            single = input.next();
            if (single.equals("y") || single.equals("Y")) {
                singlePlayer = true;
                System.out.println("Set to single player");
            } else if (single.equals("n") || single.equals("N")) {
                singlePlayer = false;
                System.out.println("Set to two player");
            } else
            {
                System.out.println("Please enter y or n");
            }
        }
    if(singlePlayer) {
        while (!playerFirst.equals("y") && !playerFirst.equals("Y") && !playerFirst.equals("n") && !playerFirst.equals("N")) {
            System.out.println("player goes first?, y or n: ");
            playerFirst = input.next();
            if (playerFirst.equals("y") || playerFirst.equals("Y")) {
                playerGoesFirst = true;
                System.out.println("Set to player goes first");
            } else if (playerFirst.equals("n") || playerFirst.equals("N")) {
                playerGoesFirst = false;
                System.out.println("Set to computer goes first");
            } else {
                System.out.println("Please enter y or n");
            }
        }
    }
        char[][] connectFourGrid = new char[rows][columns];
        for(int i = 0; i < connectFourGrid.length; i++)
        {
            for (int j = 0; j < connectFourGrid[i].length; j++)
                connectFourGrid[i][j] = ' ';
        }
        printGrid.displayGrid(connectFourGrid);
        playConnectFour(connectFourGrid);
    }

    private static void playConnectFour(char[][] connectFourGrid)
    {
        Scanner input = new Scanner(System.in);
        Boolean gameOver = false;
        Boolean playersTurn = true;
        char chipColor;
        while(!gameOver)
        {
            if(playersTurn)//pick column
            {
                if(playerGoesFirst || !singlePlayer) {
                    System.out.print("Place Player disk in column 1 - " + (columns) + ": ");
                    columnPosition = (input.nextInt() - 1);
                }
                else if (!playerGoesFirst && singlePlayer)
                {
                    columnPosition = AI.pickColumn(connectFourGrid,'A');
                }
                chipColor = 'A';
            }
            else
            {//pick column
                if(!playerGoesFirst || !singlePlayer) {
                    System.out.print("Place Player disk in column 1 - " + (columns) + ": ");
                    columnPosition = (input.nextInt() - 1);
                }
                else if (playerGoesFirst || singlePlayer)
                {
                    columnPosition = AI.pickColumn(connectFourGrid,'B');
                }
                chipColor = 'B';
            }
            //check column is in range
            while (columnPosition < 0 || columnPosition > (columns - 1))
            {
                System.out.println("Column position must be between 1 and " + (columns));
                columnPosition = (input.nextInt() - 1);
            }
            playersTurn = !playersTurn;

            if(dropChip(connectFourGrid, columnPosition, chipColor))
                playersTurn = !playersTurn;
            else
            {
                printGrid.displayGrid(connectFourGrid);
                if(gameStatus(connectFourGrid, columnPosition, chipColor))
                {
                    gameOver = true;
                    System.out.print(chipColor + " won! Game over.");
                }
                else if (checkWin.checkTie(connectFourGrid))
                {
                    gameOver = true;
                    System.out.print("Its a Tie!");
                }
            }
            moveNumber++;
        }
        input.close();
    }

    private static boolean gameStatus(char[][] connectFourGrid, int columnPosition, char chipColor)
    {
        boolean win;
        int rowPosition = 0;
        for(int i = 0; i < connectFourGrid.length; i++)
        {
            if(connectFourGrid[i][columnPosition] != ' ')
            {
                rowPosition = i;
                break;
            }
        }
        win = (checkWin.checkWin(connectFourGrid, columnPosition, chipColor, rowPosition));
        return win;
    }

    private static boolean dropChip(char[][] connectFourGrid, int columnPosition, char chipColor)
    {
        columnFull = false;
        for(int i = connectFourGrid.length - 1; i >= 0; i--)
            if (connectFourGrid[i][columnPosition] == ' ')
            {
                connectFourGrid[i][columnPosition] = chipColor;
                return false;
            }
        System.out.println("Column full choose another column");
        columnFull = true;
        return true;
    }
}
