/**
 * CPTR 241
 * Jeff P.
 * Nov 20
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
        //welcome, get Players
        //List <String> players = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        String[] players = {"Player1", "Player2"};

        System.out.println("\nWelcome to a game of Reversi/Othello. Please enter player names.");
        System.out.print("Player 1: ");
        players[0] = scanner.nextLine();
        System.out.print("Player 2: ");
        players[1] = scanner.nextLine();
        System.out.println();
        // System.out.println("Players: " + players[0] + ", " + players[1] + "\n");             // Test players output
    //}

    //public static void printBoard(char rows[][]) {
        
        // Initialize board setup
        char rows[][] = new char[8][8]; // = {1, 2, 3, 4, 5, 6, 7, 8};
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                rows[i][j] = '-';
            }
        }
        
        rows[3][3] = rows [4][4] = 'X';
        rows[3][4] = rows [4][3] = 'O';
        
        // Print out Board
        System.out.println("  a b c d e f g h  ");

        for (int i = 0; i < 8; i++) {
            System.out.print((i+1) + " ");
            
            for (int j = 0; j < 8; j++) {
                System.out.print(rows[(i)][j] + " ");
            }
            
            System.out.println(i+1);
            
        }

        System.out.println("  a b c d e f g h  \n");
    }
    //scanner.close(); 
}