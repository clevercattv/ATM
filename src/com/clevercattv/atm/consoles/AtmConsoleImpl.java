package com.clevercattv.atm.consoles;

import java.util.Scanner;

public class AtmConsoleImpl implements AtmConsole {

    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public void printNewOperation() {
        System.out.println("*******************");
        System.out.println("*******************");
        System.out.println("********ATM********");
    }

    @Override
    public void printlnError(String s) {
        System.err.println(s);
    }

    @Override
    public void print(String s) {
        System.out.print(s);
    }

    @Override
    public void println(String s) {
        System.out.println(s);
    }

    @Override
    public int readInt() {
        return SCANNER.nextInt();
    }

    @Override
    public int readInt(String text) {
        println(text);
        return readInt();
    }

    @Override
    public String readString(String text) {
        println(text);
        return readString();
    }

    @Override
    public String readString() {
        return SCANNER.nextLine();
    }

}
