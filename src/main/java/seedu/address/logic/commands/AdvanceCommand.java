package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NamePhoneNumberPredicate;
import seedu.address.model.person.Person;

/**
 * Advances an applicant in HMHero.
 */
public class AdvanceCommand extends Command {
    public static final String COMMAND_WORD = "advance";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Advance an applicant in HMHero.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 ";

    private final NamePhoneNumberPredicate predicate;

    /**
     * Creates an AdvanceCommand to advance the specified {@code Person}
     */
    public AdvanceCommand(NamePhoneNumberPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);

        List<Person> personList = model.getFilteredPersonList();
        assert personList.size() <= 1;

        if (personList.isEmpty()) {
            return new CommandResult(Messages.MESSAGE_NO_PERSON_WITH_NAME_AND_PHONE);
        }

        Person personToAdvance = personList.get(0);
        if (model.advancePerson(personToAdvance)) {
            model.refreshListWithPredicate(predicate);
            return new CommandResult("Successfully advanced " + personToAdvance.getName().fullName);
        } else {
            return new CommandResult(personToAdvance.getName().fullName + " cannot be advanced!");
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AdvanceCommand // instanceof handles nulls
                && predicate.equals(((AdvanceCommand) other).predicate)); // state check
    }
}
