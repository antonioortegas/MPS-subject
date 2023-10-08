package org.mps.plateau;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Tag("LineCoverage")
class PlateauLineCoverageTest {

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
    @DisplayName("Returns the correct pair when array is valid and there is only one possible plateau")
    void shouldFunctionReturnTheCorrectPairWhenGivenAValidArrayWithASinglePlateau(){
        array = new int[] {1,2,3,3,3,2,1};

        Plateau.Pair pair = plateau.longestPlateau(array);

        assertEquals(2, pair.position());
        assertEquals(3, pair.length());
    }
}