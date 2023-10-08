package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
    Test code
    1.- factorial de 0 es 1
    2.- factorial de 1 es 1
    3.- factorial de 2 es 2
    4.- factorial de 3 es 6
    5.- factorial de 5 es 120
    6.- factorial de un numero negativo ?
 */

class FactorialAntiguoTest {
    FactorialAntiguo factorialAntiguo;
    @BeforeEach
    void setup(){
        factorialAntiguo = new FactorialAntiguo();
    }

    @AfterEach
    void shutdown(){
        factorialAntiguo = null; //Es un ejemplo para ver el AfterEach, pero no es necesario en este caso
    }

    @Test
    void factorialOfZeroIsOne(){
        int obtainedValue = factorialAntiguo.compute(0);
        int expectedValue = 1;

        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    void factorialOfOneIsOne(){
        int obtainedValue = factorialAntiguo.compute(1);
        int expectedValue = 1;

        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    void factorialOfTwoIsTwo(){
        int obtainedValue = factorialAntiguo.compute(2);
        int expectedValue = 2;

        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    void factorialOfThreeIsSix(){
        int obtainedValue = factorialAntiguo.compute(3);
        int expectedValue = 6;

        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    void factorialOfFiveIs120(){
        int obtainedValue = factorialAntiguo.compute(5);
        int expectedValue = 120;

        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    void factorialOfMinusThrowsNegativeValueException(){
        assertThrows(NegativeValueException.class, () -> factorialAntiguo.compute(-1));
    }


}