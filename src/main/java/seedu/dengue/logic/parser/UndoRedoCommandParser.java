package seedu.dengue.logic.parser;

import seedu.dengue.logic.parser.exceptions.ParseException;

/**
 * A class that handles common methods between {@link UndoCommandParser} and {@link RedoCommandParser}.
 */
abstract class UndoRedoCommandParser {

    abstract void requirePositive(int number) throws ParseException;
}
