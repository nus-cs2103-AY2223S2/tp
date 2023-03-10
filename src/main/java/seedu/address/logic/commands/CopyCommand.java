package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class CopyCommand extends Command {
    public static final String COMMAND_WORD = "copy";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Copies the information of the person identified by the index number used in the displayed person list.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_COPY_SUCCESS = "Successfully copied information to clipboard!";

    private final Index targetIndex;

    public CopyCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToCopy = lastShownList.get(targetIndex.getZeroBased());
        String informationToCopy = getInformation(personToCopy);
        copyToClipboard(informationToCopy);
        return new CommandResult(MESSAGE_COPY_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof CopyCommand // instanceof handles nulls
            && targetIndex.equals(((CopyCommand) other).targetIndex)); // state check
    }

    public String getInformation(Person personToCopy) {
        String information = "";
        information += String.format("Name: %s\n", personToCopy.getName());
        information += String.format("Phone: %s\n", personToCopy.getPhone());
        information += String.format("Email: %s\n", personToCopy.getEmail());
        information += String.format("Address: %s\n", personToCopy.getAddress());
        return information;
    }

    public void copyToClipboard(String information) {
        StringSelection stringSelection = new StringSelection(information);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}
