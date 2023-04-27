package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.Desktop;
import java.io.IOException;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.Lab;
import seedu.address.model.event.Tutorial;

/**
 * Deletes an event identified using it's displayed index from the address book.
 */
public class OpenFileCommand extends Command {

    public static final String COMMAND_WORD = "openFile";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens a file for the tutorial or lab identified by the index number used"
            + " in the displayed event list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " Tutorial/1";

    public static final String MESSAGE_OPEN_FILE_SUCCESS = "Opened file for Event: %1$s";
    public static final String CONSULTATION_OPEN_FILE_FAILURE = "Consultation events do not have attachments!";

    private final Index targetIndex;

    private boolean isTutorial;
    private boolean isLab;
    private boolean isConsultation;

    /**
     * Sets all the boolean flags (isTutorial, isLab, and isConsultation) to false.
     *
     * @param targetIndex   the index of the event to open the file from.
     */
    public OpenFileCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        isTutorial = false;
        isLab = false;
        isConsultation = false;
    }

    /**
     * Marks the tutorial as true and the rest as false.
     */
    public void markTutorial() {
        isTutorial = true;
        isLab = false;
        isConsultation = false;
    }

    /**
     * Marks the lab as true and the rest as false.
     */
    public void markLab() {
        isLab = true;
        //ensures no mishandling of cases
        isTutorial = false;
        isConsultation = false;
    }

    /**
     * Marks the consultation as true and the rest as false.
     */
    public void markConsultation() {
        isConsultation = true;
        //ensures no mishandling of cases
        isLab = false;
        isTutorial = false;
    }

    /**
     * Executes the open file based on whether it is a tutorial, lab or a consultation.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult.
     * @throws CommandException if the command is invalid.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //Ensures open consultation file cannot be executed since it does not exist in the first place
        if (isConsultation) {
            executeConsultation(model);
        }
        if (isTutorial) {
            return executeTutorial(model);
        } else {
            return executeLab(model);
        }
    }

    /**
     * Opens the file at the index from the list of most recent filtered tutorial list.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult.
     * @throws CommandException if the command is invalid.
     */
    public CommandResult executeTutorial(Model model) throws CommandException {

        List<Tutorial> lastShownList = model.getFilteredTutorialList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Tutorial tutorialFileToOpen = lastShownList.get(targetIndex.getZeroBased());

        handleAttachmentClick(tutorialFileToOpen);

        return new CommandResult(String.format(MESSAGE_OPEN_FILE_SUCCESS, tutorialFileToOpen),
                false, false, false, true);
    }

    /**
     * Opens the file at the index from the list of most recent filtered lab list.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult.
     * @throws CommandException if the command is invalid.
     */
    public CommandResult executeLab(Model model) throws CommandException {

        List<Lab> lastShownList = model.getFilteredLabList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Lab labFileToOpen = lastShownList.get(targetIndex.getZeroBased());

        handleAttachmentClick(labFileToOpen);

        return new CommandResult(String.format(MESSAGE_OPEN_FILE_SUCCESS, labFileToOpen),
                false, false, false, true);
    }

    /**
     * Throws error if user tries to open file for consultation event since consultation has no attachments.
     *
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException if the command is invalid.
     */
    public void executeConsultation(Model model) throws CommandException {
        throw new CommandException(CONSULTATION_OPEN_FILE_FAILURE);
    }

    private void handleAttachmentClick(Event event) throws CommandException {
        if (event.getAttachments().size() > 0 && event.getAttachments().get(0).exists()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(event.getAttachments().get(0));
            } catch (IOException e) {
                throw new CommandException(Messages.MESSAGE_INVALID_FILE);
            }
        } else {
            throw new CommandException(Messages.MESSAGE_NO_FILE);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OpenFileCommand // instanceof handles nulls
                && targetIndex.equals(((OpenFileCommand) other).targetIndex)); // state check
    }
}
