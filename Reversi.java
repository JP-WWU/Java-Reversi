/**
 * CPTR 241
 * Jeff P.
 *  
 * Reversi.java
 * Create a program to Write an application to manage the board of Reversi (also called Othello). 
 * The simplest version of your application should allow selection of moves by two players, 
 *   identify legal moves, and show the effect on a board. 
 * A more sophisticated version could simulate one of the players. 
 * I expect that the UI will be text-based (console/Transcript).
 */

import java.util.*;

class Reversi
{
    public static void main(String[] args)
    {
        Console console = new Console();
        
        while (true) {
            String[] playerNames = console.playerNames();         // Request Player Names
            int currentPlayerIndex = 0;
            int otherPlayerIndex = 1;

            Board board = new Board();                              
            board.initializeBoard();                                //Create Board
            board.printBoard();
            
            int [] rowColumn = new int[2];                          

            while(true) {                                                                   //implement error checking
            
                console.getMove(rowColumn, currentPlayerIndex);                             //get move

                if (board.validateMove(rowColumn[0], rowColumn[1], currentPlayerIndex, otherPlayerIndex)) {       //Error check move
                    board.updateBoard(rowColumn[0], rowColumn[1], currentPlayerIndex, otherPlayerIndex);          //Update Board
                    board.printBoard();
                }
                else {
                    System.out.println("Error, no valid position found.");
                    continue;
                }
                
                if (board.hasValidMove(otherPlayerIndex,currentPlayerIndex) == true) {      //If other player has valid move, switch players & continue
                    currentPlayerIndex = (currentPlayerIndex + 1) % 2;
                    otherPlayerIndex = (otherPlayerIndex +1) % 2;
                    continue;
                }
                else if (board.hasValidMove(currentPlayerIndex, otherPlayerIndex) == true) {    //If current player has valid move
                    System.out.println("No valid move for " + playerNames[otherPlayerIndex] + "; turn is skipped.");
                    continue;
                }
                else {
                    System.out.println("No remaining moves. Game Over.");
                    break;
                }
            }
            board.gameOver(playerNames);

            if (!console.playAgain()) {
                break;
            }
        }
        console.close();
    }       
}

class Board {

    char grid[][] = new char[8][8];             // = {1, 2, 3, 4, 5, 6, 7, 8};
    static char playerSymbol[] = {'X','O'};     //symbols for player 1, player 2

    public void initializeBoard() {
        // Board board setup
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = '-';
            }
        }
        
        // Default Placement
        grid[3][3] = grid [4][4] = 'X';
        grid[3][4] = grid [4][3] = 'O';

        //For Testing Only, uncomment or edit as needed
        //grid[5][2] = grid [2][5] = 'O';     
        //grid[4][5] = grid[4][6] = 'O';
        //grid[6][1] = grid[1][1] = grid[6][6] = grid[1][6] = 'O';
        //grid[3][4] = grid [6][6] = 'O';     
    }

    public void printBoard() {  
        System.out.println();
        System.out.println("  a b c d e f g h  ");

        for (int i = 0; i < 8; i++) {
            System.out.print((i+1) + " ");
            
            for (int j = 0; j < 8; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println(i+1);
        }
        System.out.println("  a b c d e f g h  \n");
    } 

    public boolean validateMove(int row, int column, int currentPlayerIndex, int otherPlayerIndex) {

        char currentPlayer = playerSymbol[currentPlayerIndex];
        char otherPlayer = playerSymbol[otherPlayerIndex];

        if (grid[row][column] != '-' ) {
            System.out.println("Invalid placement.  Pieces may only be placed on empty locations.");
            return false;
        }

        //Down (South)
        if ((row+1 < 7) && (grid[row+1][column] == otherPlayer)) {  

            for (int i = 1; (grid[row+i][column] == otherPlayer) && (row+i <= 6);) {
                ++i;
                if(grid[row+i][column]== currentPlayer) {
                    return true;
                }
            }          
        }
        //Up (North)
        if ((row-1 > 0) && (grid[row-1][column] == otherPlayer)) {  

            for (int i = 1; (grid[row-i][column] == otherPlayer) && (row-i >= 1); ) {
                ++i;
                if(grid[row-i][column]== currentPlayer) {
                    return true;
                }
            }            
        }
        //Right (East)
        if ((column+1 < 7) && (grid[row][column+1] == otherPlayer)) {  

            for (int i = 1; (grid[row][column+i] == otherPlayer) && (column+i <= 6); ) {
                ++i;
                if(grid[row][column+i]== currentPlayer) {
                    return true;
                }
            }            
        }
        //Left (West)
        if ((column-1 > 0) && (grid[row][column-1] == otherPlayer)) {  

            for (int i = 1; (grid[row][column-i] == otherPlayer) && (column-i >= 1); ) {
                ++i;
                if(grid[row][column-i]== currentPlayer) {
                    return true;
                }
            }            
        }
        // (South-East)
        if ((row+1 < 7) && (column+1 < 7) && (grid[row+1][column+1] == otherPlayer)) {  

            for (int i = 1; (grid[row+i][column+i] == otherPlayer) && (row+i <= 6) && (column+i <= 6); ) {
                ++i;
                if(grid[row+i][column+i]== currentPlayer) {
                    return true;
                }
            }            
        }
        // (North-West)
        if ((row-1 > 0) && (column-1 > 0) && (grid[row-1][column-1] == otherPlayer)) {  

            for (int i = 1; (grid[row-i][column-i] == otherPlayer) && (row-i > 0) && (column-i > 0); ) {
                ++i;
                if(grid[row-i][column-i]== currentPlayer) {
                    return true;
                }
            }            
        }
        // (South-West)
        if ((row+1 < 7) && (column-1 > 0) && (grid[row+1][column-1] == otherPlayer)) {  

            for (int i = 1; (grid[row+i][column-i] == otherPlayer) && (row+i <= 6) && (column-i >= 1); ) {
                ++i;
                if(grid[row+i][column-i]== currentPlayer) {
                    return true;
                }
            }            
        }
        // (North-East)
        if ((row-1 > 0) && (column+1 < 7) && (grid[row-1][column+1] == otherPlayer)) {  
            
            for (int i = 1; (grid[row-i][column+i] == otherPlayer) && (row-i >= 1) && (column+i <= 6); ) {
                ++i;
                if(grid[row-i][column+i]== currentPlayer) {
                    return true;
                }
            }            
        }        
        return false;
    }

    public void updateBoard(int row, int column, int currentPlayerIndex, int otherPlayerIndex) {
        char currentPlayer = playerSymbol[currentPlayerIndex];
        char otherPlayer = playerSymbol[otherPlayerIndex];
        
        if((row < 0) && (row > 7) && (column < 0) && (column > 7)) {
            System.out.println("Error in updating the board.");
            return;
        }

        grid[row][column] = currentPlayer;
        
        //Down (South)
        if ((row+1 < 7) && (grid[row+1][column] == otherPlayer)) {  

            for (int i = 1; (grid[row+i][column] == otherPlayer) && (row+i <= 6);) {
                ++i;
                if(grid[row+i][column]== currentPlayer) {
                    for (int j = 1; (grid[row+j][column] == otherPlayer) && (row+j <= 6); j++) {
                        grid[row+j][column] = currentPlayer;    
                    }
                    break;
                }
            }          
        }
        //Up (North)
        if ((row-1 > 0) && (grid[row-1][column] == otherPlayer)) {  

            for (int i = 1; (grid[row-i][column] == otherPlayer) && (row-i >= 1); ) {
                ++i;
                if(grid[row-i][column]== currentPlayer) {
                    for (int j = 1; (grid[row-j][column] == otherPlayer) && (row-j >= 1); j++) {
                        grid[row-j][column] = currentPlayer;
                    }
                    break;
                }
            }            
        }
        //Right (East)
        if ((column+1 < 7) && (grid[row][column+1] == otherPlayer)) {  

            for (int i = 1; (grid[row][column+i] == otherPlayer) && (column+i <= 6); ) {
                ++i;
                if(grid[row][column+i]== currentPlayer) {
                    for (int j = 1; (grid[row][column+j] == otherPlayer) && (column+j <= 6); j++) {
                        grid[row][column+j] = currentPlayer;
                    }
                    break;
                }
            }            
        }
        //Left (West)
        if ((column-1 > 0) && (grid[row][column-1] == otherPlayer)) {  

            for (int i = 1; (grid[row][column-i] == otherPlayer) && (column-i >= 1); ) {
                ++i;
                if(grid[row][column-i]== currentPlayer) {
                    for (int j = 1; (grid[row][column-j] == otherPlayer) && (column-j >= 1); j++) {
                        grid[row][column-j] = currentPlayer;
                    }
                    break;
                }
            }            
        }
        // (South-East)
        if ((row+1 < 7) && (column+1 < 7) && (grid[row+1][column+1] == otherPlayer)) {  

            for (int i = 1; (grid[row+i][column+i] == otherPlayer) && (row+i <= 6) && (column+i <= 6); ) {
                ++i;
                if(grid[row+i][column+i]== currentPlayer) {
                    for (int j = 1; (grid[row+j][column+j] == otherPlayer) && (row+j <= 6) && (column+j <= 6); j++) {
                        grid[row+j][column+j] = currentPlayer;
                    }
                    break;
                }
            }            
        }
        // (North-West)
        if ((row-1 > 0) && (column-1 > 0) && (grid[row-1][column-1] == otherPlayer)) {  

            for (int i = 1; (grid[row-i][column-i] == otherPlayer) && (row-i > 0) && (column-i > 0); ) {
                ++i;
                if(grid[row-i][column-i]== currentPlayer) {
                    for (int j = 1; (grid[row-j][column-j] == otherPlayer) && (row-j <= 6) && (column-j <= 6); j++) {
                        grid[row-j][column-j] = currentPlayer;
                    }
                    break;
                }
            }            
        }
        // (South-West)
        if ((row+1 < 7) && (column-1 > 0) && (grid[row+1][column-1] == otherPlayer)) {  

            for (int i = 1; (grid[row+i][column-i] == otherPlayer) && (row+i <= 6) && (column-i >= 1); ) {
                ++i;
                if(grid[row+i][column-i]== currentPlayer) {
                    for (int j = 1; (grid[row+j][column-j] == otherPlayer) && (row+j <= 6) && (column-j >= 1); j++) {
                        grid[row+j][column-j] = currentPlayer;
                    }
                    break;
                }
            }            
        }
        // (North-East)
        if ((row-1 > 0) && (column+1 < 7) && (grid[row-1][column+1] == otherPlayer)) {  
            
            for (int i = 1; (grid[row-i][column+i] == otherPlayer) && (row-i >= 1) && (column+i <= 6); ) {
                ++i;
                if(grid[row-i][column+i]== currentPlayer) {
                    for (int j = 1; (grid[row-j][column+j] == otherPlayer) && (row-j >= 1) && (column+j <= 6); j++) {
                        grid[row-j][column+j] = currentPlayer;
                    }
                    break;
                }
            }            
        }        
    }

    public boolean hasValidMove(int currentPlayerIndex, int otherPlayerIndex) {
        for (int i = 0; i < 8; i++) {
            
            for (int j = 0; j < 8; j++) {
                if (grid[i][j] == '-') {
                    if (validateMove(i, j, currentPlayerIndex, otherPlayerIndex)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void gameOver(String[] playerNames) {
        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid[i][j] == 'X') {
                    count1 ++;                    
                }
                if (grid[i][j] == 'O') {
                    count2 ++;                    
                }
            }
        }

        if(count1 < count2) {
            System.out.println(playerNames[0] + " wins.");
        }
        if(count1 > count2) {
            System.out.println(playerNames[1] + " wins.");
        }
        if(count1 == count2) {
            System.out.println("The game is a tie.");
        }
        System.out.println(playerNames[0] + " has " + count1 + " pieces.");
        System.out.println(playerNames[1] + " has " + count2 + " pieces.");
    }
}

class Console {

    Scanner scanner = new Scanner(System.in);
    String[] players = {"Player1", "Player2"};

    public String[] playerNames() {        
        // Output board, and retreive playerNames names.
        System.out.println("\nWelcome to a game of Reversi/Othello. Please enter player names.");
        System.out.print("Player 1: ");
        players[0] = scanner.nextLine();
        System.out.print("Player 2: ");
        players[1] = scanner.nextLine();
        
        System.out.print(players[0] + " is X.\n");
        System.out.print(players[1] + " is O.");

        System.out.println();
        return players;               
    }
        
    private boolean isValidNumber(char number) {
        return number >= '1' && number <= '8';
    }

    private boolean isValidCharacter(char letter) {
        letter = Character.toLowerCase(letter);
        return letter >= 'a' && letter <= 'h';
    }

    public void getMove(int[] rowColumn, int currentPlayerIndex) {
        String stringInput = new String();
             
        int row = 0;
        int column = 0;                        

        while(true) {
            System.out.println(players[currentPlayerIndex] + "'s turn. Enter a board position:"); //" Represented as a Letter/Number combination.");
            stringInput = scanner.nextLine();

            if(stringInput.length() != 2) {               // Check for approprate length
                stringInput = "00";                       // Force invalid answer, divert to error message
            }

            char a = stringInput.charAt(0);
            char b = stringInput.charAt(1); 

            if ((isValidNumber(a) && isValidCharacter(b))  || (isValidCharacter(a) && isValidNumber(b))) {
                if(isValidCharacter(b)) {
                    row = a - '1';
                    column = Character.toLowerCase(b) - 'a';
                    break;
                }
                else if(isValidCharacter(a)) {
                    row = b - '1';
                    column = Character.toLowerCase(a) -'a';
                    break;
                }
                else{
                    System.out.println("Error, getMove failed.");
                    break;
                }
            }
            else {
                System.out.println("Invalid input.  Enter a single letter (A-H) and number (1-8) combination.");
                System.out.println("For example: 1A.");           
            }            
        }
        rowColumn[0] = row;
        rowColumn[1] = column;     
    }

    public boolean playAgain() {
        System.out.print("Would you like to play again? If so, type 'yes': \n");
        String response = scanner.nextLine().toLowerCase();

        return response.equals("yes");
    }

    public void close() {
        scanner.close();
    }    
}

