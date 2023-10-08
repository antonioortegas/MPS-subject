package org.example;

/*
La implementacion del sudoku y su checker no son validas, son fakes
 */
public class SudokuFake {
    private int[][] matrix;
    public SudokuFake(){
        this.matrix = new int[9][9];
    }

    public void printSudoku(){
        System.out.println("Sudoku");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(this.matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int size(){
        return this.size();
    }

    public int getValue(int i, int j){
        return this.matrix[i][j];
    }

}
