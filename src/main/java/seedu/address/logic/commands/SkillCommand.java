package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in HMHero whose note contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class SkillCommand extends Command {

    public static final String COMMAND_WORD = "skill";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons with notes that"
            + "matches all the keywords input and displays them as a "
            + "list with index numbers.\n\n"
            + "Parameters: KEYWORDS (Space-separated, Case-insensitive)\n\n"
            + "Example: " + COMMAND_WORD + " java c++";

    private final Predicate<Person> findPredicate;

    public SkillCommand(Predicate<Person> findPredicate) {
        this.findPredicate = findPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(findPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    public Predicate<Person> getPredicate() {
        return findPredicate;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SkillCommand)) {
            return false;
        }

        SkillCommand otherCmd = (SkillCommand) other;
        return this.findPredicate.equals(otherCmd.findPredicate);
    }
}
