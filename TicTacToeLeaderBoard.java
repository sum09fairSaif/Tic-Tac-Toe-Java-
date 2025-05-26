
import java.io.*;
import java.util.Arrays;

public class TicTacToeLeaderBoard {

    public static void csvReadFile() {
        String filePath = "C:\\Users\\saifs\\OneDrive\\Documents\\Tic-Tac-Toe in Java\\TicTacToeLeaderBoard.csv";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                System.out.println(Arrays.toString(row));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        csvReadFile();
    }
}
