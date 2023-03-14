package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD;

    public static final String MESSAGE_VIEW_PERSON_SUCCESS = "Viewed Person: %1$s";
    private final Index index;
    /**
     * @param index of the person in the filtered person list to view
     */
    public ViewCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person viewedPerson = lastShownList.get(index.getZeroBased());
        model.updateViewedPerson(x -> x.isSamePerson(viewedPerson));
        return new CommandResult(String.format(MESSAGE_VIEW_PERSON_SUCCESS, viewedPerson));
    }

}
