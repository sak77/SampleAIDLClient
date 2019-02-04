// IMathAidlInterface.aidl
package com.saket.sampleaidl;

// Declare any non-default types here with import statements

interface IMathAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    //Adds 2 numbers
    int addNumbers(int num1, int num2);
    //Subtracts 2 numbers
    int subtractNumbers(int num1, int num2);
    //Divides 2 numbers
    int divideNumbers(int num1, int num2);
    //Multiplies 2 numbers
    int multiplyNumbers(int num1, int num2);
}
