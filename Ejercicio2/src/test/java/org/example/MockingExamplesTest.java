package org.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/*
  @author: Antonio Ortega Santaolalla
  ======================
  Inspirado en un proyecto anterior en el que se me encargó la creación de los tests para un sudoku y sus métodos,
  pero mis compañeros no habían realizado aún la implementación. Al no usar mockito, me limité a comunicarme con ellos
  para conocer cómo se iba a implementar.
  Usando mockito podria haber simplificado mucho el proceso, ya que podría programar los tests por adelantado por
  en solitario y mis compañeros también habrían contado de antemano con una clase de test

  Para ello, he creado dos clases fake: SudokuFake y SudokuChecker
  La clase SudokuFake tiene una clase heredada DummySudokuFake para dar un ejemplo usando dummies
  Usando mock, spy y stubbing, probamos estas clases, sin tener una implementacion real de un Sudoku
  ======================
 */
public class MockingExamplesTest {

  private class ExampleClass1 {
  }

  @Test
  @DisplayName("Test")
  public void test() {
    SudokuChecker checker = Mockito.mock(SudokuChecker.class);
    SudokuFake validSudoku = Mockito.mock(SudokuFake.class) ;
    SudokuFake invalidSudoku = Mockito.mock(SudokuFake.class);


    //STUBBING
    when(checker.checkSudoku(validSudoku)).thenReturn(true);
    when(checker.checkSudoku(invalidSudoku)).thenReturn(false);

    //SPY
    SudokuFake spy = spy(validSudoku); //Create a spy object for sudoku
    when(spy.size()).thenReturn(9); //Overwrite size function so it returns 9

    //DUMMY
    DummySudokuFake dummy = new DummySudokuFake();


    //ASSERTS
    assertEquals(true, checker.checkSudoku(validSudoku));
    assertEquals(false, checker.checkSudoku(invalidSudoku));

    assertEquals(spy.size(), 9); //Returns value overwritten by the spy
    assertEquals(spy.getValue(0,0),0); //Returns the actual value of the spied object

    assertTrue(checker.checkEqualDifficulty(validSudoku, "normal", dummy, "normal")); //Check function with dummy
    //                  ^Esta funcion no tiene sentido como esta implementada, el atributo difficultad deberia
    // partenecer al objecto Sudoku, pero no se me ocurria ningún caso en el que pudiera usarlo



  }
}
