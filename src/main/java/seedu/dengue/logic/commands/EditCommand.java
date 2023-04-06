package seedu.dengue.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_VARIANT;
import static seedu.dengue.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.dengue.commons.core.Messages;
import seedu.dengue.commons.core.index.Index;
import seedu.dengue.commons.util.CollectionUtil;
import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.model.Model;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.Postal;
import seedu.dengue.model.variant.Variant;

/**
 * Edits the details of an existing person in the Dengue Hotspot Tracker.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer less than or equal to 2147483647) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_POSTAL + "POSTAL] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_AGE + "AGE] "
            + "[" + PREFIX_VARIANT + "VARIANT]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_POSTAL + "598765 "
            + PREFIX_DATE + "2023-01-05";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the Dengue Hotspot Tracker.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Postal updatedPostal = editPersonDescriptor.getPostal().orElse(personToEdit.getPostal());
        Date updatedDate = editPersonDescriptor.getDate().orElse(personToEdit.getDate());
        Age updatedAge = editPersonDescriptor.getAge().orElse(personToEdit.getAge());
        Set<Variant> updatedVariants = editPersonDescriptor.getVariants().orElse(personToEdit.getVariants());

        return new Person(updatedName, updatedPostal, updatedDate, updatedAge, updatedVariants);
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
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Postal postal;
        private Date date;
        private Age age;
        private Set<Variant> variants;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code variants} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPostal(toCopy.postal);
            setDate(toCopy.date);
            setAge(toCopy.age);
            setVariants(toCopy.variants);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, postal, date, age, variants);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPostal(Postal postal) {
            this.postal = postal;
        }

        public Optional<Postal> getPostal() {
            return Optional.ofNullable(postal);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public void setAge(Age age) {
            this.age = age;
        }

        public Optional<Age> getAge() {
            return Optional.ofNullable(age);
        }

        /**
         * Sets {@code variants} to this object's {@code variants}.
         * A defensive copy of {@code variants} is used internally.
         */
        public void setVariants(Set<Variant> variants) {
            this.variants = (variants != null) ? new HashSet<>(variants) : null;
        }

        /**
         * Returns an unmodifiable variant set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code variants} is null.
         */
        public Optional<Set<Variant>> getVariants() {
            return (variants != null) ? Optional.of(Collections.unmodifiableSet(variants)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPostal().equals(e.getPostal())
                    && getDate().equals(e.getDate())
                    && getAge().equals(e.getAge())
                    && getVariants().equals(e.getVariants());
        }
    }
}
