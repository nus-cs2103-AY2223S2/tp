package seedu.sprint.ui;

import javafx.beans.property.SimpleStringProperty;

/**
 * This class contains HelpCommand that is used to generate the helpTable
 * in the Help window.
 */
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
