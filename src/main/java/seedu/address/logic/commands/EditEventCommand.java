package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONSULTATIONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_LABS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTORIALS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Consultation;
import seedu.address.model.event.Event;
import seedu.address.model.event.Lab;
import seedu.address.model.event.Tutorial;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditEventCommand extends Command {

    public static final String COMMAND_WORD = "editEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the event identified "
            + "by the index number used in the displayed event list. ";

    public static final String MESSAGE_EDIT_EVENT_SUCCESS = "Edited Event: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the address book.";

    private final Index index;
    private Event editEvent;
    private final boolean isTutorial;
    private final boolean isLab;
    private final boolean isConsultation;

    /**
     * Has parameters to pass data on whether the event is a tutorial, a lab or consultation for type casting
     * @param index
     * @param editEvent
     * @param isTutorial
     * @param isLab
     * @param isConsultation
     */
    public EditEventCommand(Index index, Event editEvent, boolean isTutorial,
                            boolean isLab, boolean isConsultation) {
        requireNonNull(index);
        requireNonNull(editEvent);

        this.index = index;
        this.isTutorial = isTutorial;
        this.isLab = isLab;
        this.isConsultation = isConsultation;
        this.editEvent = editEvent;
    }

    /**
     * Executes model by typccasing depending on whether it is a tutorial, lab or consultation
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (isTutorial) {
            return executeTutorial(model);
        } else if (isLab) {
            return executeLab(model);
        } else {
            return executeConsultation(model);
        }
    }

    /**
     * Executes the set tutorial with model
     * @param model
     * @return CommandResult
     * @throws CommandException
     */
    public CommandResult executeTutorial(Model model) throws CommandException {
        List<Tutorial> lastShownList = model.getFilteredTutorialList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Tutorial tutorialToEdit = lastShownList.get(index.getZeroBased());
        Tutorial editedTutorial = (Tutorial) editEvent;

        if (!tutorialToEdit.isSameTutorial(editedTutorial) && model.hasTutorial(editedTutorial)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.setTutorial(tutorialToEdit, editedTutorial);
        model.updateFilteredTutorialList(PREDICATE_SHOW_ALL_TUTORIALS);
        return new CommandResult(String.format(MESSAGE_EDIT_EVENT_SUCCESS, editedTutorial));
    }

    /**
     * Executes the set lab with model
     * @param model
     * @return CommandResult
     * @throws CommandException
     */
    public CommandResult executeLab(Model model) throws CommandException {
        List<Lab> lastShownList = model.getFilteredLabList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Lab tutorialToEdit = lastShownList.get(index.getZeroBased());
        Lab editedLab = (Lab) editEvent;

        if (!tutorialToEdit.isSameLab(editedLab) && model.hasLab(editedLab)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.setLab(tutorialToEdit, editedLab);
        model.updateFilteredLabList(PREDICATE_SHOW_ALL_LABS);
        return new CommandResult(String.format(MESSAGE_EDIT_EVENT_SUCCESS, editedLab));
    }

    /**
     * Executes the set consultation with model
     * @param model
     * @return CommandResult
     * @throws CommandException
     */
    public CommandResult executeConsultation(Model model) throws CommandException {
        List<Consultation> lastShownList = model.getFilteredConsultationList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Consultation tutorialToEdit = lastShownList.get(index.getZeroBased());
        Consultation editedConsultation = (Consultation) editEvent;

        if (!tutorialToEdit.isSameConsultation(editedConsultation) && model.hasConsultation(editedConsultation)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.setConsultation(tutorialToEdit, editedConsultation);
        model.updateFilteredConsultationList(PREDICATE_SHOW_ALL_CONSULTATIONS);
        return new CommandResult(String.format(MESSAGE_EDIT_EVENT_SUCCESS, editedConsultation));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditEventCommand)) {
            return false;
        }

        // state check
        EditEventCommand e = (EditEventCommand) other;
        return index.equals(e.index)
                && editEvent.equals(e.editEvent);
    }
}
