
package sudokuplayer;

import java.util.*;

/**
 *
 * @author greg geary
 */
public class Sudoku {

    //declaring out new sudoku
    char[][] newSudoku;

    public Sudoku() {
        //this loop populates the sudoku board with blank spaces
        newSudoku = new char[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                newSudoku[row][col] = ' ';
            }
        }
    }

    public Sudoku(String starting_configuration) {
        // this changes out blank config. to the starting config we need to start with
        newSudoku = new char[9][9];
        int i = 0;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                newSudoku[row][col] = starting_configuration.charAt(i);
                i++;
            }
            i++;
        }
    }

    public char getSquare(int row, int col) {
        // this retrieves a index from the sudoku matrix
        return newSudoku[row][col];
    }

    public void setSquare(int row, int col, char value) {
        // this will pass in a value to the designated array index
        newSudoku[row][col] = value;
    }

    public boolean isValid() {
        //the returns true or false based upon the values from the rows, columns and subsquares
        return (isRowValid() && isColValid() && isSubValid());
    }

    private boolean hasDouble(char[] anArray) {
        int oneCount = 0;
        int twoCount = 0;
        int threeCount = 0;
        int fourCount = 0;
        int fiveCount = 0;
        int sixCount = 0;
        int sevenCount = 0;
        int eightCount = 0;
        int nineCount = 0;
        for (int i = 0; i < anArray.length; i++) {
            if (anArray[i] == 1) {
                oneCount++;
            }
            if (anArray[i] == 2) {
                twoCount++;
            }
            if (anArray[i] == 3) {
                threeCount++;
            }
            if (anArray[i] == 4) {
                fourCount++;
            }
            if (anArray[i] == 5) {
                fiveCount++;
            }
            if (anArray[i] == 6) {
                sixCount++;
            }
            if (anArray[i] == 7) {
                sevenCount++;
            }
            if (anArray[i] == 8) {
                eightCount++;
            }
            if (anArray[i] == 9) {
                nineCount++;
            }
        }
        return (oneCount >= 2 && twoCount >= 2 && threeCount >= 2 && fourCount >= 2 && fiveCount >= 2 && sixCount >= 2 && sevenCount >= 2 && eightCount >= 2 && nineCount >= 2);
    }

    public boolean isRowValid() {
        //this loop retrieves the sudoku rows
        for (int row = 0; row < 9; row++) {
            // this sets up an empty set
            char[] arrayCheck = new char[9];
            for (int col = 0; col < 9; col++) {
                //this populates the array with the contents of the row
                arrayCheck[col] = (newSudoku[row][col]);
            }
            if (hasDouble(arrayCheck) == false) {
                return false;
            }
        }
        //returns true if the set is a full set 1 through 9
        return true;
    }

    public boolean isColValid() {
        // all of this is the same as above, but rows and columns are swapped
        for (int col = 0; col < 9; col++) {
            char[] arrayCheck = new char[9];
            for (int row = 0; row < 9; row++) {
                arrayCheck[col] = (newSudoku[row][col]);
            }
            if (hasDouble(arrayCheck) == false) {
                return false;
            }
        }
        return true;
    }

    public boolean isSubValid() {

        //this first loop moves the row by three indexes each time
        for (int rowSkip = 0; rowSkip < 9; rowSkip += 3) {
            // this loop moves the col by three
            for (int colSkip = 0; colSkip < 9; colSkip += 3) {
                //we create our empty set
                char[] arrayCheck = new char[9];
                //we loop by a 3x3 here
                for (int row = 0; row < 3; row++) {
                    int count = 0;
                    for (int col = 0; col < 3; col++) {
                        //here we pass each 3x3 index into a set
                        arrayCheck[count] = (newSudoku[row + rowSkip][col + colSkip]);
                        count++;
                    }
                    if (hasDouble(arrayCheck) == false) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isSolved() {
        if (isValid()== false) {
            return false;
        }
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (newSudoku[row][col] >= 1 && newSudoku[row][col] <= 9) {
                    return true;
                }
            }
        }
        return false;
    }
}
