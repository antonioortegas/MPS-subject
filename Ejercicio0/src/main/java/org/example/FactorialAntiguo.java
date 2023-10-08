package org.example;
/*
Class providing factorial method
Autor:Antonio
 */
public class FactorialAntiguo {
    public int compute(int value) {
        int result;
        if(value < 0){
           throw new NegativeValueException("Numero negativo");
        } else if(value==0){
            result = 1;
        } else {
            result = value * compute (value-1);
        }
        return result;
    }
}
