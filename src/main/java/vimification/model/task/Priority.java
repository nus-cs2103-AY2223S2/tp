package vimification.model.task;

public enum Priority {
    VERY_URGENT(1), URGENT(2), NOT_URGENT(1), UNKNOWN(0);

    private int level;

    Priority(int level) {
        this.level = level;
    }

    public static Priority fromInt(int level) {
        switch (level) {
        case 1:
            return VERY_URGENT;
        case 2:
            return URGENT;
        case 3:
            return NOT_URGENT;
        default:
            return UNKNOWN;
        }
    }

    public int level() {
        return level;
    }

    @Override
    public String toString() {
        return name().toLowerCase().replace('_', ' ');
    }
}
