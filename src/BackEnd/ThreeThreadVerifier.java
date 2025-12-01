/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd;

/**
 *
 * @author Bluray
 */
public class ThreeThreadVerifier extends SudokuVerifier {
    public ThreeThreadVerifier(int[][] board) { super(board); }
    public void verify() {
        Thread rows = new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                checkArray(getRow(i), "ROW", i);
            }
        });

        Thread cols = new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                checkArray(getCol(i), "COL", i);
            }
        });

        Thread boxes = new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                checkArray(getBox(i), "BOX", i);
            }
        });

        rows.start();
        cols.start();
        boxes.start();

        try {
            rows.join();
            cols.join();
            boxes.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result.printResults();
    }
}