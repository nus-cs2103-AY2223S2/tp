package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLATOON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RANK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIT;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Copies the details of an existing person in the address book to the user's
 * clipboard.
 */
public class CopyCommand extends Command {
    public static final String COMMAND_WORD = "copy";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Copies the information of the person identified by "
            + "the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "[" + PREFIX_RANK + "] "
            + "[" + PREFIX_NAME + "] "
            + "[" + PREFIX_UNIT + "] "
            + "[" + PREFIX_COMPANY + "] "
            + "[" + PREFIX_PLATOON + "] "
            + "[" + PREFIX_PHONE + "] "
            + "[" + PREFIX_EMAIL + "] "
            + "[" + PREFIX_ADDRESS + "] "
            + "[" + PREFIX_TAG + "]...\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_NAME + " " + PREFIX_RANK;

    public static final String MESSAGE_COPY_SUCCESS = "Successfully copied information to clipboard!";

    public static final String MESSAGE_NO_CLIPBOARD_FOUND = "Clipboard does not exist in your environment! "
            + "Displaying information for you:\n\n";

    private final Index targetIndex;
    private final CopyInformationSelector copyInformationSelector;

    /**
     * @param targetIndex Index of the person in the filtered person list to copy
     * @param copyInformationSelector selects which information to copy
     */
    public CopyCommand(Index targetIndex, CopyInformationSelector copyInformationSelector) {
        this.targetIndex = targetIndex;
        this.copyInformationSelector = copyInformationSelector;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToCopy = lastShownList.get(targetIndex.getZeroBased());
        String informationToCopy = getInformation(personToCopy, copyInformationSelector);
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

    public String getInformation(Person personToCopy, CopyInformationSelector copyInformationSelector) {
        final StringBuilder infoBuilder = new StringBuilder();
        if (copyInformationSelector.toCopyRank()) {
            infoBuilder.append("Rank: " + personToCopy.getRank() + "\n");
        }
        if (copyInformationSelector.toCopyName()) {
            infoBuilder.append("Name: " + personToCopy.getName() + "\n");
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
        if (copyInformationSelector.toCopyPhone()) {
            infoBuilder.append("Phone: " + personToCopy.getPhone() + "\n");
        }
        if (copyInformationSelector.toCopyEmail()) {
            infoBuilder.append("Email: " + personToCopy.getEmail() + "\n");
        }
        if (copyInformationSelector.toCopyAddress()) {
            infoBuilder.append("Address: " + personToCopy.getAddress() + "\n");
        }
        if (copyInformationSelector.toCopyTags()) {
            infoBuilder.append("Tags: ");
            personToCopy.getTags().forEach(infoBuilder::append);
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

    /**
     * Stores which field is selected to be copied.
     */
    public static class CopyInformationSelector {
        private boolean rank;
        private boolean name;
        private boolean unit;
        private boolean company;
        private boolean platoon;
        private boolean phone;
        private boolean email;
        private boolean address;
        private boolean tags;

        public boolean isAnyFieldSelected() {
            return CollectionUtil.isAnyTrue(rank, name, unit, company, platoon, phone, email, address, tags);
        }

        /**
         * All fields are selected to be copied.
         */
        public void copyAllInformation(boolean toCopy) {
            this.rank = toCopy;
            this.name = toCopy;
            this.unit = toCopy;
            this.company = toCopy;
            this.platoon = toCopy;
            this.phone = toCopy;
            this.email = toCopy;
            this.address = toCopy;
            this.tags = toCopy;
        }

        public void copyRank(boolean toCopy) {
            this.rank = toCopy;
        }

        public boolean toCopyRank() {
            return this.rank;
        }

        public void copyName(boolean toCopy) {
            this.name = toCopy;
        }

        public boolean toCopyName() {
            return this.name;
        }

        public void copyUnit(boolean toCopy) {
            this.unit = toCopy;
        }

        public boolean toCopyUnit() {
            return this.unit;
        }

        public void copyCompany(boolean toCopy) {
            this.company = toCopy;
        }

        public boolean toCopyCompany() {
            return this.company;
        }

        public void copyPlatoon(boolean toCopy) {
            this.platoon = toCopy;
        }

        public boolean toCopyPlatoon() {
            return this.platoon;
        }

        public void copyPhone(boolean toCopy) {
            this.phone = toCopy;
        }

        public boolean toCopyPhone() {
            return this.phone;
        }

        public void copyEmail(boolean toCopy) {
            this.email = toCopy;
        }

        public boolean toCopyEmail() {
            return this.email;
        }

        public void copyAddress(boolean toCopy) {
            this.address = toCopy;
        }

        public boolean toCopyAddress() {
            return this.address;
        }

        public void copyTags(boolean toCopy) {
            this.tags = toCopy;
        }

        public boolean toCopyTags() {
            return this.tags;
        }
    }
}
