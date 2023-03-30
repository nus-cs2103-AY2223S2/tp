package vimification.model.task;

public enum Status {
    NOT_DONE, IN_PROGRESS, COMPLETED, OVERDUE;

    public static Status fromInt(int level) {
        switch (level) {
        case 0:
            return NOT_DONE;
        case 1:
            return IN_PROGRESS;
        case 2:
            return COMPLETED;
        case 3:
            return OVERDUE;
        default:
            return NOT_DONE;
        }
    }

    @Override
    public String toString() {
        return name().toLowerCase().replace('_', ' ');
    }
}

