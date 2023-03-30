package seedu.connectus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.connectus.commons.core.Messages.MESSAGE_PERSON_TOO_MANY_MAJORS;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.connectus.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.connectus.commons.core.Messages;
import seedu.connectus.commons.core.index.Index;
import seedu.connectus.logic.commands.exceptions.CommandException;
import seedu.connectus.model.Model;
import seedu.connectus.model.person.Person;
import seedu.connectus.model.tag.Cca;
import seedu.connectus.model.tag.Major;
import seedu.connectus.model.tag.Module;
import seedu.connectus.model.tag.Remark;

/**
 * Adds a tag to a person identified using its displayed index from ConnectUS.
 */
public class AddTagToPersonCommand extends Command {
    public static final String COMMAND_WORD = "add-t";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tag to the person identified "
        + "by the index number used in the displayed person list. \n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_MODULE + "MODULE]... "
        + "[" + PREFIX_CCA + "CCA#CCA_POSITION]... "
        + "[" + PREFIX_MAJOR + "MAJOR]... "
        + "[" + PREFIX_REMARK + "REMARK]...\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_MODULE + "CS2103T "
        + PREFIX_MODULE + "CS2101 "
        + PREFIX_CCA + "NES "
        + PREFIX_CCA + "ICS#Director"
        + PREFIX_MAJOR + "Computer Science "
        + PREFIX_MAJOR + "BBA "
        + PREFIX_REMARK + "friends "
        + PREFIX_REMARK + "owesMoney";

    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added tag to Person: %1$s";

    private final Index index;
    private final AddTagDescriptor addTagDescriptor;

    /**
     * @param index                of the person in the filtered person list to edit
     * @param addTagDescriptor details to edit the person with
     */
    public AddTagToPersonCommand(Index index, AddTagDescriptor addTagDescriptor) {
        requireNonNull(index);
        requireNonNull(addTagDescriptor);

        this.index = index;
        this.addTagDescriptor = new AddTagDescriptor(addTagDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        var lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        var personToEdit = lastShownList.get(index.getZeroBased());
        var editedPerson = createEditedPerson(personToEdit, addTagDescriptor);
        if (editedPerson.getMajors().size() > Major.MAX_MAJOR_COUNT) {
            throw new CommandException(MESSAGE_PERSON_TOO_MANY_MAJORS);
        }
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS, editedPerson));
    }

    private Person createEditedPerson(Person personToEdit, AddTagDescriptor addTagDescriptor) {
        var modules = new HashSet<>(personToEdit.getModules());
        var ccas = new HashSet<>(personToEdit.getCcas());
        var majors = new HashSet<>(personToEdit.getMajors());
        var remarks = new HashSet<>(personToEdit.getRemarks());

        modules.addAll(addTagDescriptor.modules);
        ccas.addAll(addTagDescriptor.ccas);
        majors.addAll(addTagDescriptor.majors);
        remarks.addAll(addTagDescriptor.remarks);

        return new Person(personToEdit, remarks, modules, ccas, majors);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTagToPersonCommand)) {
            return false;
        }

        // state check
        var e = (AddTagToPersonCommand) other;
        return index.equals(e.index)
            && addTagDescriptor.equals(e.addTagDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will
     * replace the
     * corresponding field value of the person.
     */
    public static class AddTagDescriptor {
        protected final Set<Module> modules;
        protected final Set<Cca> ccas;
        protected final Set<Major> majors;
        protected final Set<Remark> remarks;


        /**
         * Constructor.
         */
        public AddTagDescriptor(Set<Remark> remarks, Set<Module> modules,
                                Set<Cca> ccas, Set<Major> majors) {
            this.modules = modules;
            this.ccas = ccas;
            this.majors = majors;
            this.remarks = remarks;
        }

        /**
         * Copy constructor.
         */
        public AddTagDescriptor(AddTagDescriptor addTagDescriptor) {
            modules = addTagDescriptor.modules;
            ccas = addTagDescriptor.ccas;
            majors = addTagDescriptor.majors;
            remarks = addTagDescriptor.remarks;
        }

        /**
         * Returns an unmodifiable Remark set, which throws
         * {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code remarks} is null.
         */
        public Optional<Set<Remark>> getRemarks() {
            return (remarks != null) ? Optional.of(Collections.unmodifiableSet(remarks)) : Optional.empty();
        }

        /**
         * Returns an unmodifiable Module set, which throws
         * {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code modules} is null.
         */
        public Optional<Set<Module>> getModules() {
            return (modules != null) ? Optional.of(Collections.unmodifiableSet(modules)) : Optional.empty();
        }

        /**
         * Returns an unmodifiable CCA set, which throws
         * {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code ccas} is null.
         */
        public Optional<Set<Cca>> getCcas() {
            return (ccas != null) ? Optional.of(Collections.unmodifiableSet(ccas)) : Optional.empty();
        }

        /**
         * Returns an unmodifiable CCA set, which throws
         * {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code majors} is null.
         */
        public Optional<Set<Major>> getMajors() {
            return (majors != null) ? Optional.of(Collections.unmodifiableSet(majors)) : Optional.empty();
        }

        public boolean isEmpty() {
            return (remarks == null || remarks.isEmpty()) && (modules == null || modules.isEmpty())
                && (ccas == null || ccas.isEmpty()) && (majors == null || majors.isEmpty());
        }

        public int getMajorCount() {
            return getMajors().get().size();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof AddTagDescriptor)) {
                return false;
            }

            // state check
            var e = (AddTagDescriptor) other;

            return getRemarks().equals(e.getRemarks()) && getModules().equals(e.getModules())
                && getCcas().equals(e.getCcas()) && getMajors().equals(e.getMajors());
        }
    }
}
