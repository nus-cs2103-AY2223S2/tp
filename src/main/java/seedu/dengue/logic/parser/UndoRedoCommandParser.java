package seedu.dengue.logic.parser;

import seedu.dengue.logic.parser.exceptions.ParseException;

/**
 * A class that handles common methods between {@link UndoCommandParser} and {@link RedoCommandParser}.
 */
abstract class UndoRedoCommandParser {

    /**
     * Checks that an integer is positive.
     * @param number An integer.
     * @throws ParseException If the number is not positive.
     */
    abstract void requirePositive(int number) throws ParseException;
}
