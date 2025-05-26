import java.util.*;

public class playTicTacToeGame {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("------------------------- A GAME OF TIC-TAC-TOE!!! --------------------------");
        System.out.println();
        System.out.println("Welcome!");
        System.out.println("Will you play a game of tic-tac-toe? : ");
        String willPlayGame = scan.nextLine().toLowerCase();

        String previousPlayerName = null;
        String previousPlayerMark = null;

        while (willPlayGame.equals("yes") || willPlayGame.equals("y")) {
            TicTacToeJava newGame = new TicTacToeJava();
            String[][] playersAndMarks = new String[2][2];

            System.out.println();
            String playerName;
            String playerMark;

            if (previousPlayerName != null) {
                System.out.println("Will the same player continue? (yes/no): ");
                String samePlayer = scan.nextLine().toLowerCase();
                if (samePlayer.equals("yes") || samePlayer.equals("y")) {
                    playerName = previousPlayerName;
                    playerMark = previousPlayerMark;
                    System.out.println("Welcome back, " + playerName + "! You're still playing as '" + playerMark + "'.");
                } else {
                    System.out.println("Great! Before we start, can I get the player's name? : ");
                    playerName = scan.nextLine();

                    System.out.println("Sounds good! Do you want 'X' mark or 'O' mark? : ");
                    playerMark = scan.nextLine().toUpperCase();
                    while (!playerMark.equals("X") && !playerMark.equals("O")) {
                        System.out.println("Invalid input. Please enter either 'X' or 'O': ");
                        playerMark = scan.nextLine().toUpperCase();
                    }

                    previousPlayerName = playerName;
                    previousPlayerMark = playerMark;
                }
            } else {
                System.out.println("Great! Before we start, can I get the player's name? : ");
                playerName = scan.nextLine();

                System.out.println("Sounds good! Do you want 'X' mark or 'O' mark? : ");
                playerMark = scan.nextLine().toUpperCase();
                while (!playerMark.equals("X") && !playerMark.equals("O")) {
                    System.out.println("Invalid input. Please enter either 'X' or 'O': ");
                    playerMark = scan.nextLine().toUpperCase();
                }

                previousPlayerName = playerName;
                previousPlayerMark = playerMark;
            }

            playersAndMarks[0][0] = playerName;
            playersAndMarks[0][1] = playerMark;

            System.out.println("Awesome! (1) Will you be the only player or (2) do you have a rival? [Choose 1 or 2]: ");
            int isSecondPlayer = scan.nextInt();
            scan.nextLine(); // consume newline

            String opponentPlayerName;
            if (isSecondPlayer == 2) {
                System.out.println("Cool! Can I also get your rival's name? : ");
                opponentPlayerName = scan.nextLine();
            } else {
                System.out.println("No problem! You will be competing against TicTacPRO, who claims to be unbeatable! Let's see whether you can break the record!");
                opponentPlayerName = "TicTacPRO";
            }

            playersAndMarks[1][0] = opponentPlayerName;
            String opponentPlayerMark = playerMark.equals("X") ? "O" : "X";
            playersAndMarks[1][1] = opponentPlayerMark;

            System.out.println();
            System.out.println("Wonderful! So these are the players of our game now:- ");
            System.out.println(playerName.toUpperCase() + "  x  " + opponentPlayerName.toUpperCase());
            System.out.println();
            System.out.println(playerName + " => " + playerMark);
            System.out.println(opponentPlayerName + " => " + opponentPlayerMark);
            System.out.println();
            System.out.println("Okay!! Let's start!! Ready, Set, GO!!!!!");
            System.out.println();
            System.out.println("ROUND " + newGame.getNumRoundsPlayed() + ":- ");
            System.out.println();

            String currentPlayer = playerName;
            String currentMark = playerMark;

            while (!newGame.isFull() && newGame.solve().equals("N")) {
                System.out.println("Current board: ");
                System.out.println(newGame);
                System.out.println();

                int playerRow = -1, playerCol = -1;
                boolean validMove = false;

                if (currentPlayer.equals("TicTacPRO") && opponentPlayerName.equals("TicTacPRO")) {
                    // AI's turn
                    int[] aiMove = TicTacPRO.getBestMove(newGame, opponentPlayerMark, playerMark);
                    playerRow = aiMove[0];
                    playerCol = aiMove[1];
                    System.out.println("TicTacPRO places '" + currentMark + "' at row " + (playerRow + 1) + ", column " + (playerCol + 1));
                    newGame.placeMark(playerRow, playerCol, currentMark);
                } else {
                    // Human player's turn
                    do {
                        System.out.println("Place your '" + currentMark + "' mark on any of the empty grids:-");
                        System.out.println("Your row (1-3): ");
                        if (!scan.hasNextInt()) {
                            System.out.println("Please enter a valid number.");
                            scan.next();
                            continue;
                        }
                        playerRow = scan.nextInt() - 1;

                        System.out.println("Your column (1-3): ");
                        if (!scan.hasNextInt()) {
                            System.out.println("Please enter a valid number.");
                            scan.next();
                            continue;
                        }
                        playerCol = scan.nextInt() - 1;
                        scan.nextLine(); // consume newline

                        if (playerRow < 0 || playerRow > 2 || playerCol < 0 || playerCol > 2) {
                            System.out.println("Invalid row/column number. Try again.");
                        } else if (!newGame.placeMark(playerRow, playerCol, currentMark)) {
                            System.out.println("That grid is already filled. Try another one.");
                        } else {
                            validMove = true;
                        }
                    } while (!validMove);
                }

                // Check for win
                if (!newGame.solve().equals("N")) {
                    System.out.println(newGame);
                    if (currentMark.equals(playerMark)) {
                        System.out.println(playerName + " has won the game!!! Congrats!! WooHoo!!!");
                        System.out.println("Uh oh, " + opponentPlayerName + " couldn't make it this time! Maybe next time!");
                    } else {
                        if (!opponentPlayerName.equals("TicTacPRO")) {
                            System.out.println(opponentPlayerName + " has won the game!!! Congrats!! WooHoo!!!");
                            System.out.println("Uh oh, " + playerName + " couldn't make it this time! Maybe next time!");
                        } else {
                            System.out.println("TicTacPRO has once again been unbeatable!");
                            System.out.println("Uh oh, " + playerName + " couldn't make it this time! Maybe next time!");
                        }
                    }
                    break;
                }

                // Switch player
                currentPlayer = currentPlayer.equals(playerName) ? opponentPlayerName : playerName;
                currentMark = currentMark.equals(playerMark) ? opponentPlayerMark : playerMark;
            }

            // If board is full and no winner
            if (newGame.isFull() && newGame.solve().equals("N")) {
                System.out.println(newGame);
                System.out.println("The board is full. It's a draw!");
            }

            System.out.println("Will you play a game of tic-tac-toe? : ");
            willPlayGame = scan.nextLine().toLowerCase();
        }

        scan.close();
    }
}
