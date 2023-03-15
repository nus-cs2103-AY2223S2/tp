package seedu.address.logic.injector;

import seedu.address.model.Model;

/**
 * Represents an input injector that parses raw command text from the user and returns a modified command text.
 */
public abstract class Injector {

    /**
     * Parses and modifies user input.
     *
     * @param commandText full user input string
     * @return modified user input string
     */
    public abstract String inject(String commandText, Model model);
}
