package seedu.dengue.logic.parser;

import static seedu.dengue.commons.core.Messages.MESSAGE_INDICATE_POSITIVE;
import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.dengue.logic.parser.exceptions.ParseException;

abstract class UndoRedoCommandParser {
    protected void requirePositive(int number) throws ParseException {
        if (number <= 0) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT + MESSAGE_INDICATE_POSITIVE));
        }
    }
}
