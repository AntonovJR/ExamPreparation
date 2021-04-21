package hell.entities.miscellaneous;

import hell.interfaces.OutputWriter;

public class OutputWriterImpl implements OutputWriter {
    @Override
    public void writeLine(String output) {
        System.out.println(output);
    }

    @Override
    public void writeLine(String format, Object... params) {
        System.out.printf((format),params);
    }
}
