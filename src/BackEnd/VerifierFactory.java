package BackEnd;

public class VerifierFactory {
    public static SudokuVerifier getVerifier(int mode, int[][] board) {
        switch (mode) {
            case 0:
                return new SequentialVerifier(board);
            case 3:
                return new ThreeThreadVerifier(board);
            case 27:
                return new TwentySevenThreadVerifier(board);
            default:
                return new SequentialVerifier(board);
        }
    }
}