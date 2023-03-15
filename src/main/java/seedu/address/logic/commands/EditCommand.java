package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_FED_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_FISHES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.fish.Address;
import seedu.address.model.fish.Fish;
import seedu.address.model.fish.LastFedDate;
import seedu.address.model.fish.Name;
import seedu.address.model.fish.Species;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing fish in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the fish identified "
            + "by the index number used in the displayed fish list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_LAST_FED_DATE + "LAST FED DATE] "
            + "[" + PREFIX_SPECIES + "SPECIES] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_LAST_FED_DATE + "91234567 "
            + PREFIX_SPECIES + "Guppy ";

    public static final String MESSAGE_EDIT_FISH_SUCCESS = "Edited Fish: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_FISH = "This fish already exists in the address book.";

    private final Index index;
    private final EditFishDescriptor editFishDescriptor;

    /**
     * @param index of the fish in the filtered fish list to edit
     * @param editFishDescriptor details to edit the fish with
     */
    public EditCommand(Index index, EditFishDescriptor editFishDescriptor) {
        requireNonNull(index);
        requireNonNull(editFishDescriptor);

        this.index = index;
        this.editFishDescriptor = new EditFishDescriptor(editFishDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Fish> lastShownList = model.getFilteredFishList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FISH_DISPLAYED_INDEX);
        }

        Fish fishToEdit = lastShownList.get(index.getZeroBased());
        Fish editedFish = createEditedFish(fishToEdit, editFishDescriptor);

        if (!fishToEdit.isSameFish(editedFish) && model.hasFish(editedFish)) {
            throw new CommandException(MESSAGE_DUPLICATE_FISH);
        }

        model.setFish(fishToEdit, editedFish);
        model.updateFilteredFishList(PREDICATE_SHOW_ALL_FISHES);
        return new CommandResult(String.format(MESSAGE_EDIT_FISH_SUCCESS, editedFish));
    }

    /**
     * Creates and returns a {@code Fish} with the details of {@code fishToEdit}
     * edited with {@code editFishDescriptor}.
     */
    private static Fish createEditedFish(Fish fishToEdit, EditFishDescriptor editFishDescriptor) {
        assert fishToEdit != null;

        Name updatedName = editFishDescriptor.getName().orElse(fishToEdit.getName());
        LastFedDate updatedLastFedDate = editFishDescriptor.getLastFedDate().orElse(fishToEdit.getLastFedDate());
        Species updatedSpecies = editFishDescriptor.getSpecies().orElse(fishToEdit.getSpecies());
        Address updatedAddress = editFishDescriptor.getAddress().orElse(fishToEdit.getAddress());
        Set<Tag> updatedTags = editFishDescriptor.getTags().orElse(fishToEdit.getTags());

        return new Fish(updatedName, updatedLastFedDate, updatedSpecies, updatedAddress, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editFishDescriptor.equals(e.editFishDescriptor);
    }

    /**
     * Stores the details to edit the fish with. Each non-empty field value will replace the
     * corresponding field value of the fish.
     */
    public static class EditFishDescriptor {
        private Name name;
        private LastFedDate lastFedDate;
        private Species species;
        private Address address;
        private Set<Tag> tags;

        public EditFishDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditFishDescriptor(EditFishDescriptor toCopy) {
            setName(toCopy.name);
            setLastFedDate(toCopy.lastFedDate);
            setSpecies(toCopy.species);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, lastFedDate, species, address, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setLastFedDate(LastFedDate lastFedDate) {
            this.lastFedDate = lastFedDate;
        }

        public Optional<LastFedDate> getLastFedDate() {
            return Optional.ofNullable(lastFedDate);
        }

        public void setSpecies(Species species) {
            this.species = species;
        }

        public Optional<Species> getSpecies() {
            return Optional.ofNullable(species);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditFishDescriptor)) {
                return false;
            }

            // state check
            EditFishDescriptor e = (EditFishDescriptor) other;

            return getName().equals(e.getName())
                    && getLastFedDate().equals(e.getLastFedDate())
                    && getSpecies().equals(e.getSpecies())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
