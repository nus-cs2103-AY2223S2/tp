package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.MatchNamePredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Views a person's contact details
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": views the contact details of the person identified "
            + "by the index number used in the last person listing. "
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 ";

    private final List<Index> index;

    /**
     * Constructor for viewCommand
     * @param index index of the person in the list to display their contacts
     */
    public ViewCommand(List<Index> index) {
        requireNonNull(index);
        this.index = index;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();


        Optional<Index> maxIndex = this.index.stream().max(Comparator.naturalOrder());
        int max = maxIndex.get().getOneBased();

        if (max > lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_VIEW_INVALID_PERSON_CONTACT_DETAILS);
        }

        List<Name> nameList = index.stream().filter(x -> validateIndex(x, lastShownList))
                .map(x -> lastShownList.get(x.getZeroBased()).getName()).collect(Collectors.toList());
        MatchNamePredicate predicate = new MatchNamePredicate(nameList);
        model.updateFilteredPersonList(predicate);
        StringBuilder sb = new StringBuilder();
        nameList.stream().forEach(x -> sb.append(
                String.format(Messages.MESSAGE_VIEW_PERSON_CONTACT_DETAILS, x) + "\n"));
        return new CommandResult(sb.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewCommand)) {
            return false;
        }

        // state check
        ViewCommand v = (ViewCommand) other;
        return index.equals(v.index);
    }

    private boolean validateIndex(Index index, List<Person> lastShownList) {
        return index.getZeroBased() >= lastShownList.size() ? false : true;
    }
}
