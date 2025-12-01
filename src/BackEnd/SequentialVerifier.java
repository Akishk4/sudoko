/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd;

/**
 *
 * @author Bluray
 */

public class SequentialVerifier extends SudokuVerifier {
    public SequentialVerifier(int[][] board) { super(board); }
    public void verify() {
        for (int i = 0; i < 9; i++) {
            checkArray(getRow(i), "ROW", i);
        }
        for (int i = 0; i < 9; i++) {
            checkArray(getCol(i), "COL", i);
        }
        for (int i = 0; i < 9; i++) {
            checkArray(getBox(i), "BOX", i);
        }
        result.printResults();
    }
}