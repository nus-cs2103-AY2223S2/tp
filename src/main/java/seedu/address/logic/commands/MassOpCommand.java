package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Performs a single-target sub-operation on every person currently filtered for display.
 */
public class MassOpCommand extends Command {

    //CHECKSTYLE.OFF: VisibilityModifier
    public static List<String> commandWords = new ArrayList<String>(Arrays.asList("mass", "m"));
    //CHECKSTYLE.ON: VisibilityModifier

    public static final String MESSAGE_USAGE = commandWords
            + ": Applies a single-target operation to all people being displayed.\n"
            + "Parameters: [edit | tag | delete_tag | delete] [FURTHER_ARGS (must exclude index)] \n"
            + "Example: " + commandWords.get(0) + " tag marked";

    public static final String MESSAGE_SUCCESS = "Command executed on %1$d / %2$d persons";

    private static final Logger logger = LogsCenter.getLogger(MassOpCommand.class);

    private final String subcommandInput;

    /**
     * @param subcommandInput the parsable String containing the command word and arguments of the subcommand,
     *                        with the Index argument omitted
     */
    public MassOpCommand(String subcommandInput) {
        this.subcommandInput = subcommandInput;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        int successes = 0;
        int initialSize = lastShownList.size();
        int end = lastShownList.size() - 1;
        // lastShownList gets updated every iteration
        for (int i = end; i >= 0; i--) {
            try {
                Command subcommand = AddressBookParser.parseCommandWithIndex(subcommandInput, Index.fromZeroBased(i));
                subcommand.execute(model);
                successes++;
            } catch (ParseException pe) {
                assert false : "Subcommand could not be parsed during mass execution";
            } catch (CommandException ce) {
                logger.warning("Subcommand failed on index " + i + " : " + ce.getMessage());
            }
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, successes, initialSize), true, true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MassOpCommand)) {
            return false;
        }

        // state check
        if (subcommandInput.equals(((MassOpCommand) other).subcommandInput)) {
            return true;
        }
        try {
            Command thisSubcommand =
                    AddressBookParser.parseCommandWithIndex(
                            subcommandInput, Index.fromZeroBased(0));
            Command otherSubcommand =
                    AddressBookParser.parseCommandWithIndex((
                            (MassOpCommand) other).subcommandInput, Index.fromZeroBased(0));
            return thisSubcommand.equals(otherSubcommand);
        } catch (ParseException ex) {
            return false;
        }
    }

}
