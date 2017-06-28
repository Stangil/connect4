/**
 * Created by Stan Gilstrap on 4/6/2017.
 */
public class checkToBlock {
    private static int blockNumber = (connect4.winNum - 1);
        public static boolean checkToBlock ( char[][] connectFourGrid, int columnPosition, char chipColor,
        int rowPosition)
        {
            if (checkColumn(connectFourGrid, columnPosition, chipColor, rowPosition))
                return true;
            if (checkRow(connectFourGrid, columnPosition, chipColor, rowPosition))
                return true;
            if (checkMajorDiag(connectFourGrid, columnPosition, chipColor, rowPosition))
                return true;
            if (checkMinorDiag(connectFourGrid, columnPosition, chipColor, rowPosition))
                return true;
            return false;
        }

    public static boolean checkMinorDiag(char[][] connectFourGrid, int columnPosition, char chipColor, int rowPosition) {
        int chipCounter = 1;
        for (int i = rowPosition + 1, j = columnPosition - 1; i < connectFourGrid.length && j >= 0; i++, j--)
            if (chipColor == connectFourGrid[i][j])
                chipCounter++;
            else
                break;
        if (chipCounter >= blockNumber)
            return true;
        for (int i = rowPosition - 1, j = columnPosition + 1; i >= 0 && j < connectFourGrid[0].length; i--, j++)
            if (chipColor == connectFourGrid[i][j])
                chipCounter++;
            else break;
        if (chipCounter >= blockNumber)
            return true;
        return false;
    }

    public static boolean checkMajorDiag(char[][] connectFourGrid, int columnPosition, char chipColor, int rowPosition) {
        int chipCounter = 1;
        for (int i = rowPosition - 1, j = columnPosition - 1; i >= 0 && j >= 0; i--, j--)
            if (chipColor == connectFourGrid[i][j])
                chipCounter++;
            else
                break;
        if (chipCounter >= blockNumber)
            return true;
        for (int i = rowPosition + 1, j = columnPosition + 1; i < connectFourGrid.length && j < connectFourGrid[0].length; i++, j++)
            if (chipColor == connectFourGrid[i][j])
                chipCounter++;
            else break;
        if (chipCounter >= blockNumber)
            return true;
        return false;
    }

    public static boolean checkRow(char[][] connectFourGrid, int columnPosition, char chipColor, int rowPosition) {
        int chipCounter = 1;
        for (int i = columnPosition - 1; i >= 0; i--) {
            if (chipColor == connectFourGrid[rowPosition][i])
                chipCounter++;
            else
                break;
        }
        if (chipCounter >= blockNumber)
            return true;

        for (int i = columnPosition + 1; i < connectFourGrid[0].length; i++) {
            if (chipColor == connectFourGrid[rowPosition][i])
                chipCounter++;
            else
                break;
        }
        if (chipCounter >= blockNumber)
            return true;
        return false;
    }

    public static boolean checkColumn(char[][] connectFourGrid, int columnPosition, char chipColor, int rowPosition) {
        int chipCounter = 1;
        if ((rowPosition + blockNumber) <= connect4.rows)//rowPosition starts at number of rows - 1 this is at the bottom
            for (int i = rowPosition + 1; i <= (rowPosition + (blockNumber - 1)); i++)
                if (chipColor == connectFourGrid[i][columnPosition])
                    chipCounter++;
                else
                    break;
        if (chipCounter >= blockNumber)
            return true;
        return false;
    }
}
