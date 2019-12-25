package com.clevercattv.atm.consoles;

import com.clevercattv.atm.screens.AtmScreen;

public interface AtmConsole {

    void printNewOperation();

    void print(String s);

    void println(String s);

    void printlnError(String s);

    int readInt();

    int readInt(String text);

    String readString();

    String readString(String text);

}
