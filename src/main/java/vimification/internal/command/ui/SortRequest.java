package vimification.internal.command.ui;

public class SortRequest {

    public static enum Mode {
        DEADLINE, PRIORITY, STATUS
    }

    private Mode mode;

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

}
