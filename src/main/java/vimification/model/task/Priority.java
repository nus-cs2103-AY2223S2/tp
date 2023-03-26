package vimification.model.task;

public enum Priority {
    VERY_URGENT(1), URGENT(2), NOT_URGENT(1), UNKNOWN(0);

    private int level;

    private Priority(int level) {
        this.level = level;
    }

    public int level() {
        return level;
    }
}
