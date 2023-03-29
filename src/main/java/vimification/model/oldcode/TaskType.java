package vimification.model.oldcode;

import static java.util.Objects.requireNonNull;
import static vimification.commons.util.AppUtil.checkArgument;

public enum TaskType {
    TODO("Todo"), DEADLINE("Deadline"), EVENT("Event");

    private String type;

    TaskType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public static TaskType str2type(String type) {
        requireNonNull(type);
        switch (type) {
        case "Todo":
            return TODO;
        case "Deadline":
            return DEADLINE;
        case "Event":
            return EVENT;
        default:
            throw new IllegalArgumentException("Invalid task type");
        }
    }
}

