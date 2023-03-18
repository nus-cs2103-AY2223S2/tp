package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.LinkedHashMap;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.FriendlyLink;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final HashMap<Prefix, String> COMMAND_PROMPTS = new LinkedHashMap<>();
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFriendlyLink(new FriendlyLink());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
