package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS_ASSIGN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS_FIND;


import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Assigns a person's lead status,
 * or finds people matching a lead status.
 */
public class StatusCommand extends Command {

    public static final String COMMAND_WORD = "status";

    public static final String MESSAGE_USAGE =  COMMAND_WORD + ": Handles lead statuses, "
            + "allows for setting of statuses or finding contacts matching the status.\n"
            + "Parameters (choose only one): \n"
            + "[INDEX " + PREFIX_STATUS_ASSIGN + " STATUS_TYPE] OR "
            + "[" + PREFIX_STATUS_FIND + "STATUS_TYPE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_STATUS_ASSIGN + "qualified\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STATUS_ASSIGN + "uncontacted";

    public static final String MESSAGE_STATUS_ASSIGN_PERSON_SUCCESS = "Assigned %1$s status of "
            + " %1$s";
    public static final String MESSAGE_STATUS_FIND_PERSON_SUCCESS = "Listed all contacts with "
            + "status %1$s";
    public static final String MESSAGE_STATUS_NOT_IMPLEMENTED = "status command not implemented";

//    private final Index index;
//    private final String status; //TODO replace with LeadStatus class
//
////    public StatusCommand(Index index, String status) { //TODO replace with LeadStatus class
//        requireNonNull(index);
//        requireNonNull(status);
//
//        this.index = index;
//        this.status = status;
//    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        throw new CommandException(MESSAGE_STATUS_NOT_IMPLEMENTED);
    }

    //TODO add equals method
}
