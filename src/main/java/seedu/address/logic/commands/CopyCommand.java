package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Copies the details of an existing person in the address book to the user's clipboard.
 */
public class CopyCommand extends Command {
    public static final String COMMAND_WORD = "copy";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Copies the information of the person identified by "
            + "the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_COPY_SUCCESS = "Successfully copied information to clipboard!";

    public static final String MESSAGE_NO_CLIPBOARD_FOUND = "Clipboard does not exist in your environment! "
            + "Displaying information for you:\n\n";

    private final Index targetIndex;

    public CopyCommand(Index targetIndex) {
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
        try {
            copyToClipboard(informationToCopy);
            return new CommandResult(MESSAGE_COPY_SUCCESS);
        } catch (HeadlessException e) {
            return new CommandResult(MESSAGE_NO_CLIPBOARD_FOUND + informationToCopy);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CopyCommand // instanceof handles nulls
                && targetIndex.equals(((CopyCommand) other).targetIndex)); // state check
    }

    public String getInformation(Person personToCopy) {
        final StringBuilder infoBuilder = new StringBuilder();
        infoBuilder.append("Name: " + personToCopy.getName() + "\n")
                .append("Phone: " + personToCopy.getPhone() + "\n")
                .append("Email: " + personToCopy.getEmail() + "\n")
                .append("Address: " + personToCopy.getAddress() + "\n")
                .append("Rank: " + personToCopy.getRank() + "\n")
                .append("Unit: " + personToCopy.getUnit() + "\n")
                .append("Company: " + personToCopy.getCompany() + "\n")
                .append("Platoon: " + personToCopy.getPlatoon() + "\n");
        return infoBuilder.toString();
    }

    /**
     * Copies the information to user's clipboard
     *
     * @param information information to copy
     */
    public void copyToClipboard(String information) {
        StringSelection stringSelection = new StringSelection(information);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}
