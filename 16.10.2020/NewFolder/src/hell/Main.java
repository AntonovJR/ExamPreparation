package hell;

import hell.entities.miscellaneous.InputReaderImpl;
import hell.entities.miscellaneous.ManagerImpl;
import hell.entities.miscellaneous.OutputWriterImpl;
import hell.interfaces.InputReader;
import hell.interfaces.Manager;
import hell.interfaces.OutputWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        InputReader reader = new InputReaderImpl();
        OutputWriter writer = new OutputWriterImpl();
        Manager manager = new ManagerImpl();

        while (true) {
            String[] command = reader.readLine().split("\\s+");

            List<String> arguments = Arrays.stream(command).skip(1).collect(Collectors.toList());

            switch (command[0]) {
                case "Hero":
                    writer.writeLine(manager.addHero(arguments));
                    break;
                case "Item":
                    writer.writeLine(manager.addItem(arguments));
                    break;
                case "Recipe":
                    writer.writeLine(manager.addRecipe(arguments));
                    break;
                case "Inspect":
                    writer.writeLine(manager.inspect(arguments));
                    break;
                case "Quit":
                    writer.writeLine(manager.quit());
                    return;
            }
        }
    }
}