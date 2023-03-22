package codoc.ui;

public class CommandExample {
    private String command;
    private String example;

    public CommandExample() {
        this.command = "";
        this.example = "";
    }
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
