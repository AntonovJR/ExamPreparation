package cresla.core;

import cresla.interfaces.InputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReaderImpl implements InputReader {
    private BufferedReader reader;

    public InputReaderImpl() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String readLine() {
        try {
            return this.reader.readLine().trim();
        } catch (IOException exception) {
            return null;
        }
    }
}
