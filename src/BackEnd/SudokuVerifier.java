package BackEnd;

import java.util.ArrayList;
import java.util.List;

public abstract class SudokuVerifier {
    protected int[][] board;
    protected ValidationResult result;

    public SudokuVerifier(int[][] board) {
        this.board = board;
        this.result = new ValidationResult();
    }

    public abstract void verify();

    protected void checkArray(int[] data, String regionType, int index) {
        int[] counts = new int[10]; 
        boolean hasDuplicates = false;
        List<Integer> duplicatesList = new ArrayList<>();

        for (int num : data) {
            if (num >= 1 && num <= 9) {
                counts[num]++;
            }
        }

        for (int i = 1; i <= 9; i++) {
            if (counts[i] > 1) {
                duplicatesList.add(i); 
                hasDuplicates = true;
            }
        }

        if (hasDuplicates) {
            StringBuilder sb = new StringBuilder();
            sb.append(regionType).append(" ").append(index + 1);
            sb.append(", #1, ");
            sb.append(duplicatesList.toString()); 
            result.addError(regionType, sb.toString());
        }
    }

    protected int[] getBox(int boxIndex) {
        int[] data = new int[9];
        int startRow = (boxIndex / 3) * 3;
        int startCol = (boxIndex % 3) * 3;
        int k = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                data[k++] = board[startRow + r][startCol + c];
            }
        }
        return data;
    }
    
    protected int[] getCol(int colIndex) {
        int[] data = new int[9];
        for(int r = 0; r < 9; r++) {
            data[r] = board[r][colIndex];
        }
        return data;
    }

    protected int[] getRow(int rowIndex) {
        return board[rowIndex];
    }
}