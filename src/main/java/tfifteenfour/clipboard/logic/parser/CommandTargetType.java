package tfifteenfour.clipboard.logic.parser;

import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;

/**
 * Handles which entity a command is targeted at
 */
public enum CommandTargetType {
    MODULE("course"),
    GROUP("group"),
    SESSION("session"),
    TASK("task"),
    STUDENT("student");

    private String type;

    private CommandTargetType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    /**
     * Placeholder
     */
    public static CommandTargetType fromString(String type) throws ParseException {
        for (CommandTargetType sc : CommandTargetType.values()) {
            if (sc.getType().equalsIgnoreCase(type)) {
                return sc;
            }
        }

        throw new ParseException("Unrecognised type for command: " + type
                + "\nCurrent available types: course, group, student, session, task");
    }

    /**
     * Placeholder
     */
    public static boolean isValidAddType(String type) {
        for (CommandTargetType sc : CommandTargetType.values()) {
            if (sc.getType().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }
}
