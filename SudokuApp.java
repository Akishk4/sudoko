package FrontEnd;

import BackEnd.SudokuVerifier;
import BackEnd.VerifierFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SudokuApp {
    public static void main(String[] args) {
        String filePath = "valid.csv";
        int mode = 0;

        if (args.length >= 2) {
            filePath = args[0];
            try {
                mode = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Mode must be a number");
                return;
            }
        } else {
            Scanner scanner = new Scanner(System.in);
            
            while (true) {
                System.out.print("Enter Mode (0, 3, 27): ");
                if (scanner.hasNextInt()) {
                    mode = scanner.nextInt();
                    if (mode == 0 || mode == 3 || mode == 27) {
                        break;
                    }
                } else {
                    scanner.next();
                }
            }
        }

        int[][] board = loadBoard(filePath);

        if (board == null) {
            System.out.println("Error reading file");
            return;
        }

        SudokuVerifier verifier = VerifierFactory.getVerifier(mode, board);
        verifier.verify();
    }

    private static int[][] loadBoard(String filePath) {
        int[][] board = new int[9][9];
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                if (row >= 9) break;
                String[] values = line.split(","); 
                for (int col = 0; col < 9; col++) {
                    if (col < values.length) {
                        board[row][col] = Integer.parseInt(values[col].trim());
                    }
                }
                row++;
            }
            return board;
        } catch (IOException | NumberFormatException e) {
            return null;
        }
    }
}