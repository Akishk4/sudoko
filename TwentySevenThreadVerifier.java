package BackEnd;

public class TwentySevenThreadVerifier extends SudokuVerifier {
    public TwentySevenThreadVerifier(int[][] board) { super(board); }

    @Override
    public void verify() {
        Thread[] threads = new Thread[27];
        int tIndex = 0;

        for (int i = 0; i < 9; i++) {
            final int idx = i;
            threads[tIndex++] = new Thread(() -> checkArray(getRow(idx), "ROW", idx));
        }

        for (int i = 0; i < 9; i++) {
            final int idx = i;
            threads[tIndex++] = new Thread(() -> checkArray(getCol(idx), "COL", idx));
        }

        for (int i = 0; i < 9; i++) {
            final int idx = i;
            threads[tIndex++] = new Thread(() -> checkArray(getBox(idx), "BOX", idx));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result.printResults();
    }
}