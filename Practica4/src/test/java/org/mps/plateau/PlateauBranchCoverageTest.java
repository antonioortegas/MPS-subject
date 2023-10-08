package org.mps.plateau;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Tag("BranchCoverage")
class PlateauBranchCoverageTest {

    Plateau plateau = new Plateau();
    int[] array;

    @BeforeEach
    void setUp() {
        array = null;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Throws an Exception when array is null")
    void shouldFunctionRiseAnExceptionWhenArrayIsNull(){
        assertThrows(IllegalArgumentException.class, () -> Plateau.longestPlateau(array));
    }

    @Test
    @DisplayName("Throws an Exception when array is null")
    void shouldFunctionRiseAnExceptionWhenArrayLengthIsLessThanThree(){
        array = new int[1];

        assertThrows(IllegalArgumentException.class, () -> Plateau.longestPlateau(array));
    }


    @Test
    @DisplayName("Returns the correct pair when array is valid and there is only one possible plateau")
    void shouldFunctionReturnTheCorrectPairWhenGivenAValidArrayWithASinglePlateau(){
        array = new int[] {1,2,3,3,3,2,1};

        Plateau.Pair pair = plateau.longestPlateau(array);

        assertEquals(2, pair.position());
        assertEquals(3, pair.length());
    }

    @Test
    @DisplayName("Returns the correct pair when array is valid and there is only one possible plateau")
    void shouldFunctionReturnTheCorrectPairWhenGivenAValidArrayWithTwoPlateaus(){
        array = new int[] {1,2,3,3,3,2,3,3,3,2,1};

        Plateau.Pair pair = plateau.longestPlateau(array);

        assertEquals(2, pair.position());
        assertEquals(3, pair.length());
    }



    @Test
    @DisplayName("Returns the correct pair when array is valid and there is only one possible plateau")
    void shouldFunctionReturnTheCorrectPairWhenGivenAValidArrayWithAPossiblePlateauAtTheEnd(){
        array = new int[] {2,1,2,1,3,3,3};

        Plateau.Pair pair = plateau.longestPlateau(array);

        assertEquals(2, pair.position());
        assertEquals(1, pair.length());
    }
}