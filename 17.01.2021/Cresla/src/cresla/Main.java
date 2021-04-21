package cresla;

import cresla.core.*;

import cresla.interfaces.InputReader;
import cresla.interfaces.Manager;
import cresla.interfaces.OutputWriter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final InputReader reader = new InputReaderImpl();
    private static final OutputWriter writer = new OutputWriterImpl();
    private static final Manager manager = new ManagerImpl();

    public static void main(String[] args) {

        while (true) {

            String[] command = reader.readLine().split("\\s+");

            List<String> arguments = Arrays.stream(command).skip(1).collect(Collectors.toList());

            switch (command[0]) {

                case "Reactor":
                    writer.write(manager.reactorCommand(arguments));
                    break;
                case "Module":
                    writer.write(manager.moduleCommand(arguments));
                    break;
                case "Report":
                    writer.write(manager.reportCommand(arguments));
                    break;
                case "Exit":
                    writer.write(manager.exitCommand(arguments));
                    return;
            }
        }
    }
}


