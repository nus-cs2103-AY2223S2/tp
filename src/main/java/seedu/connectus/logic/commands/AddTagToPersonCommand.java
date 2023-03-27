package seedu.connectus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.connectus.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.connectus.commons.core.Messages;
import seedu.connectus.commons.core.index.Index;
import seedu.connectus.logic.commands.exceptions.CommandException;
import seedu.connectus.model.Model;
import seedu.connectus.model.person.Person;
import seedu.connectus.model.tag.Module;
import seedu.connectus.model.tag.Tag;

/**
 * Adds a tag (or module) to a person identified using its displayed index from ConnectUS.
 */
public class AddTagToPersonCommand extends Command {
    public static final String COMMAND_WORD = "addt";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tag (or module) to the person identified "
        + "by the index number used in the displayed person list. \n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_TAG + "TAG] "
        + "[" + PREFIX_MODULE + "MODULE]"
        + "\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_TAG + "friend "
        + PREFIX_MODULE + "CS1231";

    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added %2$s to Person: %1$s";

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

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS, editedPerson, getTagSuccessDetailsMessage()));
    }

    private String getTagSuccessDetailsMessage() {
        StringBuilder sb = new StringBuilder(MESSAGE_ADD_TAG_SUCCESS.length() * 2);
        if (!addTagDescriptor.tags.isEmpty()) {
            sb.append("tag");
            sb.append(addTagDescriptor.tags.size() > 1 ? "s " : " ");
            sb.append(addTagDescriptor.tags.stream().map(tag -> tag.tagName)
                .collect(Collectors.joining(", ")));
        }

        if (!addTagDescriptor.modules.isEmpty()) {
            sb.append(sb.length() == 0 ? "" : " and ");
            sb.append("module");
            sb.append(addTagDescriptor.modules.size() > 1 ? "s " : " ");
            sb.append(addTagDescriptor.modules.stream().map(module -> module.moduleName)
                .collect(Collectors.joining(", ")));
        }

        return sb.toString();
    }

    private Person createEditedPerson(Person personToEdit, AddTagDescriptor addTagDescriptor) {
        var tags = new HashSet<>(personToEdit.getTags());
        var modules = new HashSet<>(personToEdit.getModules());

        tags.addAll(addTagDescriptor.tags);
        modules.addAll(addTagDescriptor.modules);

        return new Person(personToEdit, tags, modules);
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
        private final Set<Tag> tags;
        private final Set<Module> modules;


        /**
         * Constructor.
         */
        public AddTagDescriptor(Set<Tag> tags, Set<Module> modules) {
            this.tags = tags;
            this.modules = modules;
        }

        /**
         * Copy constructor.
         */
        public AddTagDescriptor(AddTagDescriptor addTagDescriptor) {
            tags = addTagDescriptor.tags;
            modules = addTagDescriptor.modules;
        }

        /**
         * Returns an unmodifiable tag set, which throws
         * {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Returns an unmodifiable modules set, which throws
         * {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code modules} is null.
         */
        public Optional<Set<Module>> getModules() {
            return (modules != null) ? Optional.of(Collections.unmodifiableSet(modules)) : Optional.empty();
        }

        public boolean isEmpty() {
            return (tags == null || tags.isEmpty()) && (modules == null || modules.isEmpty());
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

            return getTags().equals(e.getTags()) && getModules().equals(e.getModules());
        }
    }
}
