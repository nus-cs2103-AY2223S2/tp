package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SERVICES;

import seedu.address.model.Model;

/**
 * Lists all services in the AutoM8 system to the user.
 */
public class ListServicesCommand extends Command {

    public static final String COMMAND_WORD = "listservices";

    public static final String MESSAGE_SUCCESS = "Listed all services.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredServiceList(PREDICATE_SHOW_ALL_SERVICES);
        return new CommandResult(MESSAGE_SUCCESS, ResultType.LISTED_SERVICES);
    }
}










