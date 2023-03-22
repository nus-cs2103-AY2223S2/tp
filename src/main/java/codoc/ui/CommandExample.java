package codoc.ui;

/**
 * The class used in help window for each command and example.
 */
public class CommandExample {
    private String command;
    private String example;

    /**
     * Constructor for CommandExample
     * @param command String for the command type
     * @param example String for the example of the command
     */
    public CommandExample(String command, String example) {
        this.command = command;
        this.example = example;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
