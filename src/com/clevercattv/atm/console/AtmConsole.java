package com.clevercattv.atm.console;

public interface AtmConsole {

    void printScreenName(String name);

    void print(String s);

    void println(String s);

    void refreshScanner();

    void printlnError(String s);

    int readInt();

    int readInt(String text);

    String readString();

}
