package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAY_RATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class GroupAddCommand extends EditCommand {

    public static final String COMMAND_WORD = "groupadd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a person of index i to a group specified. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "varsity";
    public static final String GROUP_ADD_PERSON_SUCCESS = "Added Person: %1$s to Groups: %1$s";
    /**
     * @param index                of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public GroupAddCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        super(index, editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        super.execute(model);
        EditPersonDescriptor epd = getEditPersonDescriptor();
        return new CommandResult(String.format(GROUP_ADD_PERSON_SUCCESS, epd.getName(), epd.getTags()));

    }
}
