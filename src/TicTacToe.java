import java.util.Scanner; // Import Scanner from Java
public class TicTacToe {
    private static final int ROW = 3; // Initialize variable for number of rows
    private static final int COL = 3; // Initialize variable for number of columns
    private static String board [][] = new String[ROW][COL]; // Initialize the board (two-dimensional array)
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); // Create a scanner to read user input
        boolean donePlaying = false; // Initialize boolean variable for whether the person is done playing, initially false
        while(!donePlaying) { // While the user is playing
            clearBoard(); // Clear board
            String player = "X"; // Initialize variable for which player's turn
            boolean gameDone = false; // Initialize boolean variable for whether the game is done, initially false
            while(!gameDone) { // While the game is running
                boolean validMove = false; // Initialize boolean variable for the do-while loop for whether the move is valid, initially false
                int rowMove; // Create variable for the row move the user enters
                int colMove; // Create variable for the column move the user enters
                display(); // Display the board
                do {
                    rowMove = SafeInput.getRangedInt(scan, "Player "+player+", enter your row move", 1, ROW); // Get a row move (ranged integer) for the player in the range 1 to ROW (user input)
                    colMove = SafeInput.getRangedInt(scan, "Player "+player+", enter your column move", 1, COL); // Get a column move (ranged integer) for the player in the range 1 to COL (user input)
                    if(isValidMove(rowMove, colMove)) { // If the move is valid
                        board[rowMove - 1][colMove - 1] = player; // Change the board by assigning the location given by the row and column to the player
                        validMove = true; // Set validMove to true to exit the while loop
                    }
                    else { // Otherwise
                        System.out.println("You entered an invalid move! Please enter a move that isn't occupied!"); // Asks user to enter a valid move that isn't occupied
                    }
                }
                while(!validMove); // While the user hasn't entered a valid move yet
                if(isWin("X")) { // If the player X has met one of the conditions to win
                    System.out.println("Player X wins!"); // Display that player X wins
                    gameDone = true; // Set gameDone to true to end the game
                }
                else if(isWin("O")) { // If the player O has met one of the conditions to win
                    System.out.println("Player O wins!"); // Display that player O wins
                    gameDone = true; // Set gameDone to true to end the game
                }
                else if(isTie()) { // If there is a tie
                    System.out.println("Players X and O tie!"); // Display that the players tie
                    gameDone = true; // Set gameDone to true to end the game
                }
                if(player == "X") { // If it's player X's turn
                    player = "O"; // Switch the turn to player O
                }
                else { // Otherwise (it must be player O's turn)
                    player = "X"; // Switch the turn to player X
                }
            }
            donePlaying = !(SafeInput.getYNConfirm(scan, "Would you like to play again? [Y/N]")); // After the game is done, ask user if they would like to play again (Y/N using the SafeInput method)
        }
    }
    private static void clearBoard() { // Method that clears the board
        for(int i = 0; i < ROW; i++) { // Loop through board rows
            for(int j = 0; j < COL; j++) { // Loop through board columns
                board[i][j] = " "; // Set the item in the two-dimensional array to a blank space
            }
        }
    }
    private static void display() { // Method that displays the board
        for(int i = 0; i < ROW; i++) { // Loop through board rows
            for(int j = 0; j < COL; j++) { // Loop through column rows
                if(j != COL - 1) { // If not on the last column
                    System.out.print(" " + board[i][j] + " |"); // Print the item and then a | to separate the items (or basically the columns) in the row
                }
                else { // Otherwise (last column)
                    System.out.print(" " + board[i][j] + " "); // Print the item
                }
            }
            if(i != ROW - 1) { // If not on the last row
                System.out.print("\n-----------\n"); // Print this to separate the rows
            }
        }
        System.out.print("\n"); // Print newline
    }
    private static boolean isValidMove(int row, int col) { // Method that checks whether the move is valid, given the row and column
        String spot = board[row - 1][col - 1]; // Create a variable spot that checks the board location specified by the row and column
        return spot.equals(" "); // Returns a boolean whether the location/spot is empty (meaning a valid move)
    }
    private static boolean isWin(String player) { // Method that checks whether a specific player wins
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player); // Returns a boolean for if the player has satisfied one of the following win conditions: row, column, and diagonal
    }
    private static boolean isColWin(String player) { // Method that checks for a column win
        if(board[0][0].equals(player) && board[1][0].equals(player) && board[2][0].equals(player)) { // Check for first column win
            return true; // Return true
        }
        else if(board[0][1].equals(player) && board[1][1].equals(player) && board[2][1].equals(player)) { // Check for second column win
            return true; // Return true
        }
        else if(board[0][2].equals(player) && board[1][2].equals(player) && board[2][2].equals(player)) { // Check for third column win
            return true; // Return true
        }
        return false; // Otherwise, return false
    }
    private static boolean isRowWin(String player) { // Method that checks for a row win
        if(board[0][0].equals(player) && board[0][1].equals(player) && board[0][2].equals(player)) { // Check for first row win
            return true; // Return true
        }
        else if(board[1][0].equals(player) && board[1][1].equals(player) && board[1][2].equals(player)) { // Check for second column win
            return true; // Return true
        }
        else if(board[2][0].equals(player) && board[2][1].equals(player) && board[2][2].equals(player)) { // Check for third column win
            return true; // Return true
        }
        return false; // Otherwise, return false
    }
    private static boolean isDiagonalWin(String player) { // Method that checks for a diagonal win
        if(board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) { // Check for the following diagonal win: first row first column, second row second column, third row third column
            return true; // Return true
        }
        else if(board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) { // Check for the following diagonal win: first row third column, second row second column, third row first column
            return true; // Return true
        }
        return false; // Otherwise, return false
    }
    private static boolean isTie() { // Method that checks whether the players have tied
        return isFilled() || isWinsBlocked(); // Returns a boolean for if the players have satisfied one of the following tie conditions: completely filled board and all 8 wins blocked
    }
    private static boolean isFilled() { // Method that checks whether the board is completely filled
        boolean filled = true; // Initialize a boolean variable filled for whether the board is completely filled, initially true
        for(int i = 0; i < ROW; i++) { // Loop through board rows
            for(int j = 0; j < COL; j++) { // Loop through board columns
                if(board[i][j].equals(" ")) { // If the board location has a blank space
                    filled = false; // Set filled to false (not completely filled board)
                }
            }
        }
        return filled; // Return boolean variable
    }
    private static boolean isWinsBlocked() { // Method that checks whether all 8 wins are blocked
        boolean blocked = true; // Initialize a boolean variable filled for whether all 8 wins are blocked
        if(!((board[0][0].equals("X") || board[0][1].equals("X") || board[0][2].equals("X")) && (board[0][0].equals("O") || board[0][1].equals("O") || board[0][2].equals("O")))) { // Checks whether first row has X and O
            blocked = false; // Set blocked to false (win not blocked)
        }
        else if(!((board[1][0].equals("X") || board[1][1].equals("X") || board[1][2].equals("X")) && (board[1][0].equals("O") || board[1][1].equals("O") || board[1][2].equals("O")))) { // Checks whether second row has X and O
            blocked = false; // Set blocked to false (win not blocked)
        }
        else if(!((board[2][0].equals("X") || board[2][1].equals("X") || board[2][2].equals("X")) && (board[2][0].equals("O") || board[2][1].equals("O") || board[2][2].equals("O")))) { // Checks whether third row has X and O
            blocked = false; // Set blocked to false (win not blocked)
        }
        else if(!((board[0][0].equals("X") || board[1][0].equals("X") || board[2][0].equals("X")) && (board[0][0].equals("O") || board[1][0].equals("O") || board[2][0].equals("O")))) { // Checks whether first column has X and O
            blocked = false; // Set blocked to false (win not blocked)
        }
        else if(!((board[0][1].equals("X") || board[1][1].equals("X") || board[2][1].equals("X")) && (board[0][1].equals("O") || board[1][1].equals("O") || board[2][1].equals("O")))) { // Checks whether second column has X and O
            blocked = false; // Set blocked to false (win not blocked)
        }
        else if(!((board[0][2].equals("X") || board[1][2].equals("X") || board[2][2].equals("X")) && (board[0][2].equals("O") || board[1][2].equals("O") || board[2][2].equals("O")))) { // Checks whether third column has X and O
            blocked = false; // Set blocked to false (win not blocked)
        }
        else if(!((board[0][0].equals("X") || board[1][1].equals("X") || board[2][2].equals("X")) && (board[0][0].equals("O") || board[1][1].equals("O") || board[2][2].equals("O")))) { // Checks whether diagonal first row first column, second row second column, third row third column has X and O
            blocked = false; // Set blocked to false (win not blocked)
        }
        else if(!((board[0][2].equals("X") || board[1][1].equals("X") || board[2][0].equals("X")) && (board[0][2].equals("O") || board[1][1].equals("O") || board[2][0].equals("O")))) { // Checks whether diagonal first row third column, second row second column, third row first column has X and O
            blocked = false; // Set blocked to false (win not blocked)
        }
        return blocked; // Return boolean variable
    }
}