package seedu.address.ui;

import javafx.beans.property.SimpleStringProperty;

public class HelpCommand {

    private final SimpleStringProperty command;
    private final SimpleStringProperty format;


    HelpCommand(String command, String format) {
        this.command = new SimpleStringProperty(command);
        this.format = new SimpleStringProperty(format);
    }

    public String getCommand() {
        return command.get();
    }

    public void setCommand(String commandString) {
        command.set(commandString);
    }

    public String getFormat() {
        return format.get();
    }

    public void setFormat(String formatString) {
        command.set(formatString);
    }

}
