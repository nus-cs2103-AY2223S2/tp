package seedu.address.logic.commands.results;

import java.util.Objects;
import java.util.Optional;

import seedu.address.model.person.Person;

/**
 * CommandResult to be shown when executing a ViewCommand.
 */
public class ViewCommandResult extends CommandResult {

    private final Person displayPerson;

    /**
     * Constructs a {@code ViewCommandResult} with the specified fields.
     */
    public ViewCommandResult(String feedbackToUser, Person displayPerson) {
        super(feedbackToUser);
        this.displayPerson = displayPerson;
    }

    @Override
    public boolean isToShowNewPerson() {
        return true;
    }

    @Override
    public Optional<Person> getDisplayPerson() {
        return Optional.of(displayPerson);
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other)
                && other instanceof ViewCommandResult
                && displayPerson.equals(((ViewCommandResult) other).displayPerson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), displayPerson);
    }

}
