package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Search for persons having specified appointment Date
 */
public class SearchAppointmentCommand extends MakeAppointmentCommand {

    public static final String COMMAND_WORD = "searchDate";
    public static final String MESSAGE_SUCCESS = "Listed all persons having appointment on this date";

    private final LocalDate toSearch;

    /**
     * Creates an SearchAppointmentCommand to search for appointment clash
     */
    public SearchAppointmentCommand(LocalDate toSearch) {
        requireNonNull(toSearch);
        this.toSearch = toSearch;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // change in modelManager
        Predicate<Person> predicate = Person -> Person.isOnSearchDate(toSearch);
        model.updateSearchAppointmentDate(predicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
