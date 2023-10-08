package org.example;

public class SudokuChecker {

    public SudokuChecker(){

    }
    public boolean checkSudoku(SudokuFake sudoku){
        //Not implemented
        return true;
    }

    public boolean checkEqualDifficulty(SudokuFake s1, String c1 , SudokuFake s2, String c2){
        Boolean result = false;
        if(c1 == c2){
            result = true;
        }
        return result;
    }
}
