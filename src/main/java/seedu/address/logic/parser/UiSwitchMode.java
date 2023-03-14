package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Enum of possible state transitions from a parsed command.
 */
public enum UiSwitchMode {
    NONE,
    VIEW,
    LIST,
    TOGGLE
}
