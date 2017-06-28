
/**
 * Created by Stan Gilstrap on 3/20/2017.
 */
public class AI {
    private static double highScore;
    private static double score;
    private static final int RECURSIONDEPTH = 10;
    private static int depthCount = 0;
    private static int recursioncount = 0;
    private static int n = 0;
    private static char chipColorTest;

    public static int pickColumn(char[][] connectFourTestGrid, char chipColor) {
        System.out.println("Computer is chip: " + chipColor);
        chipColorTest = chipColor;
        highScore = -100.0;
        score = 0.0;
        char[][] testGrid = new char[connect4.rows][connect4.columns];
        for (int i = 0; i < connectFourTestGrid.length; i++) {//copy array to test
            for (int j = 0; j < connectFourTestGrid[i].length; j++) {
                testGrid[i][j] = connectFourTestGrid[i][j];
            }
        }
        if (connect4.moveNumber == 0)//move if computer goes first
        {
            n = connect4.columns / 2;
        } else if (connect4.columnFull) {
            n = (int) (Math.random() * connect4.columns);
        } else {
            scanColumns(testGrid, chipColor);
        }
        System.out.println("Computer picks row: " + (n + 1));
        depthCount = 0;
        System.out.println("high score: " + highScore + " score: " + score);
        return n;
    }

    public static void scanColumns(char testGrid[][], char chipColor) {
        int i;
        int j;
        char[][] testGrid2 = new char[connect4.rows][connect4.columns];
        for (i = 0; i < connect4.columns; i++) {// iterates across columns
            if (testGrid[0][i] == ' ')//checking for not full column
            {
                for (j = connect4.rows - 1; j >= 0; j--)//iterates up rows till depth count
                {
                    if (depthCount + 2 == RECURSIONDEPTH) {
                        for (int k = 0; k < testGrid.length; k++) {//copy array to test
                            for (int l = 0; l < testGrid[i].length; l++) {
                                testGrid2[k][l] = testGrid[k][l];
                            }
                        }
                    }
                    if (testGrid[j][i] == ' ' && depthCount < RECURSIONDEPTH) {
                        testGrid[j][i] = chipColor;
                        if (checkWin.checkWin(testGrid, i, chipColor, j)) {
                            if (chipColor == chipColorTest) {
                                score = score + 1.0;
                            }
                            else {
                                score = score - 1.0;
                            }
                            if (score > highScore) {
                                highScore = score;
                                n = i;
                            }
                        }
                        else if(checkToBlock.checkToBlock(testGrid, i, chipColor, j))
                        {
                            if (chipColor == chipColorTest) {
                                score = score + 0.8;
                            }
                            else {
                                score = score - 0.8;
                            }
                            if (score > highScore) {
                                highScore = score;
                                n = i;
                            }
                        }
                        else {
                            score = score - 0.01;
                        }
                        if (chipColor == 'A') chipColor = 'B';
                        else chipColor = 'A';
                        depthCount++;
                    } else if (depthCount >= RECURSIONDEPTH) {
                        testGrid[j][i] = ' ';
                        if (chipColor == 'A') chipColor = 'B';
                        else chipColor = 'A';
                        depthCount--;
                        break;//breaks out of current column
                    }
                }
            }
        }
        recursioncount++;
        if (recursioncount < depthCount) {
            depthCount--;
            if (chipColor == 'A') chipColor = 'B';
            else chipColor = 'A';
            scanColumns(testGrid2, chipColor);
        }
    }
}
