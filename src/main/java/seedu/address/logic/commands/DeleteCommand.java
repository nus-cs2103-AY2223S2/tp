package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Consultation;
import seedu.address.model.event.Lab;
import seedu.address.model.event.Tutorial;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "rm";

    public static final String MESSAGE_USAGE = "Delete syntax: rm INDEX";

    public static final String DELETE_EXAMPLE = "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        List<Tutorial> lastShownTutorial = model.getFilteredTutorialList();
        List<Lab> lastShownLab = model.getFilteredLabList();
        List<Consultation> lastShownConsultation = model.getFilteredConsultationList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);

        for (Tutorial tutorial: lastShownTutorial) {
            for (Person student: tutorial.getStudents()) {
                if (student.equals(personToDelete)) {
                    tutorial.removeStudent(student);
                    break;
                }
            }
        }

        for (Lab lab: lastShownLab) {
            for (Person student: lab.getStudents()) {
                if (student.equals(personToDelete)) {
                    lab.removeStudent(student);
                    break;
                }
            }
        }

        for (Consultation consultation: lastShownConsultation) {
            for (Person student: consultation.getStudents()) {
                if (student.equals(personToDelete)) {
                    consultation.removeStudent(student);
                    break;
                }
            }
        }

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
