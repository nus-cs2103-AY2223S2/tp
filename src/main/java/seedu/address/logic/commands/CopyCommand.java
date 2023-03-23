package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.util.Optional;

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
    private final Optional<CopyInformationSelector> copyInformationSelector;

    public CopyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.copyInformationSelector = Optional.empty();
    }

    public CopyCommand(Index targetIndex, CopyInformationSelector copyInformationSelector) {
        this.targetIndex = targetIndex;
        this.copyInformationSelector = Optional.ofNullable(copyInformationSelector);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToCopy = lastShownList.get(targetIndex.getZeroBased());
        String informationToCopy = copyInformationSelector.isEmpty()
                 ? getInformation(personToCopy) : getInformation(personToCopy, copyInformationSelector.get());
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
                .append("Address: " + personToCopy.getAddress() + "\n")
                .append("Email: " + personToCopy.getEmail() + "\n")
                .append("Rank: " + personToCopy.getRank() + "\n")
                .append("Unit: " + personToCopy.getUnit() + "\n")
                .append("Company: " + personToCopy.getCompany() + "\n")
                .append("Platoon: " + personToCopy.getPlatoon() + "\n")
                .append("Tags: ");
        personToCopy.getTags().forEach(tag -> infoBuilder.append(tag));
        return infoBuilder.toString();
    }

    public String getInformation(Person personToCopy, CopyInformationSelector copyInformationSelector) {
        final StringBuilder infoBuilder = new StringBuilder();
        if (copyInformationSelector.toCopyName()) {
            infoBuilder.append("Name: " + personToCopy.getName() + "\n");
        }
        if (copyInformationSelector.toCopyPhone()) {
            infoBuilder.append("Phone: " + personToCopy.getPhone() + "\n");
        }
        if (copyInformationSelector.toCopyEmail()) {
            infoBuilder.append("Email: " + personToCopy.getEmail() + "\n");
        }
        if (copyInformationSelector.toCopyAddress()) {
            infoBuilder.append("Address: " + personToCopy.getAddress() + "\n");
        }
        if (copyInformationSelector.toCopyRank()) {
            infoBuilder.append("Rank: " + personToCopy.getRank() + "\n");
        }
        if (copyInformationSelector.toCopyUnit()) {
            infoBuilder.append("Unit: " + personToCopy.getUnit() + "\n");
        }
        if (copyInformationSelector.toCopyCompany()) {
            infoBuilder.append("Company: " + personToCopy.getCompany() + "\n");
        }
        if (copyInformationSelector.toCopyPlatoon()) {
            infoBuilder.append("Platoon: " + personToCopy.getPlatoon() + "\n");
        }
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

    public static class CopyInformationSelector {
        private boolean name = false;
        private boolean phone = false;
        private boolean email = false;
        private boolean address = false;
        private boolean rank = false;
        private boolean unit = false;
        private boolean company = false;
        private boolean platoon = false;

        public CopyInformationSelector() {
        }

        public CopyInformationSelector(Person personToCopy, CopyInformationSelector copyInformationSelector) {
            this.name = copyInformationSelector.name;
            this.phone = copyInformationSelector.phone;
            this.email = copyInformationSelector.email;
            this.address = copyInformationSelector.address;
            this.rank = copyInformationSelector.rank;
            this.unit = copyInformationSelector.unit;
            this.company = copyInformationSelector.company;
            this.platoon = copyInformationSelector.platoon;
        }

        public void copyName() {
            this.name = true;
        }

        public boolean toCopyName() {
            return this.name;
        }

        public void copyPhone() {
            this.phone = true;
        }

        public boolean toCopyPhone() {
            return this.phone;
        }

        public void copyEmail() {
            this.email = true;
        }

        public boolean toCopyEmail() {
            return this.email;
        }

        public void copyAddress() {
            this.address = true;
        }

        public boolean toCopyAddress() {
            return this.address;
        }

        public void copyRank() {
            this.rank = true;
        }

        public boolean toCopyRank() {
            return this.rank;
        }

        public void copyUnit() {
            this.unit = true;
        }

        public boolean toCopyUnit() {
            return this.unit;
        }

        public void copyCompany() {
            this.company = true;
        }

        public boolean toCopyCompany() {
            return this.company;
        }

        public void copyPlatoon() {
            this.platoon = true;
        }

        public boolean toCopyPlatoon() {
            return this.platoon;
        }
    }
}
