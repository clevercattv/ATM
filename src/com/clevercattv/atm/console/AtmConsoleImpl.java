package com.clevercattv.atm.console;

import java.util.Scanner;

public class AtmConsoleImpl implements AtmConsole {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void refreshScanner() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void printScreenName(String name) {
        System.out.println("\n" + String.format("********%s********", name));
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
        return scanner.nextInt();
    }

    @Override
    public int readInt(String text) {
        print(text);
        return readInt();
    }

    @Override
    public String readString() {
        return scanner.nextLine();
    }

}
