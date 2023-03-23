package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.editpersoncommandsparser.PersonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.fields.Address;
import seedu.address.model.person.fields.CommunicationChannel;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Faculty;
import seedu.address.model.person.fields.Favorite;
import seedu.address.model.person.fields.Gender;
import seedu.address.model.person.fields.Major;
import seedu.address.model.person.fields.Modules;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Race;
import seedu.address.model.person.fields.subfields.NusMod;
import seedu.address.model.person.fields.subfields.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditContactCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final PersonDescriptor editPersonDescriptor;
    private final Index index;

    /**
     * @param editPersonDescriptor details to edit the person with
     */
    public EditContactCommand(PersonDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);

        this.index = editPersonDescriptor.getIndex().get();
        this.editPersonDescriptor = new PersonDescriptor(editPersonDescriptor);
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
    private static Person createEditedPerson(Person personToEdit, PersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Gender updatedGender = editPersonDescriptor.getGender().orElse(personToEdit.getGender());
        Major updatedMajor = editPersonDescriptor.getMajor().orElse(personToEdit.getMajor());
        Modules updatedModules = createEditedModules(personToEdit.getModules(), editPersonDescriptor.getModules());
        Race updatedRace = editPersonDescriptor.getRace().orElse(personToEdit.getRace());
        CommunicationChannel updatedComms = editPersonDescriptor.getComms().orElse(personToEdit.getComms());
        Favorite currentFavorite = personToEdit.getIsFavorite();
        Faculty updatedFaculty = editPersonDescriptor.getFaculty().orElse(personToEdit.getFaculty());
        Set<Tag> updatedTags = createEditedTags(personToEdit.getTags(), editPersonDescriptor.getTags());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedGender, updatedMajor, updatedModules, updatedRace, updatedTags,
                updatedComms, currentFavorite, updatedFaculty);
    }

    public PersonDescriptor getEditPersonDescriptor() {
        return this.editPersonDescriptor;
    }

    /**
     * Creates and returns a {@code Module} with the updated Details of Modules Taken.
     * If the same NusMods appears in both Old Modules and New Modules, we count that as removal.
     * Otherwise,it is an addition.
     *
     * @param oldModules
     * @param optionalNewModules
     */
    public static Modules createEditedModules(Modules oldModules, Optional<Modules> optionalNewModules) {
        Set<NusMod> unmodifiableOldNusMods = oldModules.getMods();
        Set<NusMod> oldNusMods = new HashSet<>(unmodifiableOldNusMods);
        Set<NusMod> finalNusMods = new HashSet<>();

        if (optionalNewModules.isEmpty()) {
            return oldModules;
        }

        Modules newModules = optionalNewModules.get();
        Set<NusMod> newNusMods = newModules.getMods();
        for (NusMod nusMod : newNusMods) {
            if (oldNusMods.contains(nusMod)) {
                oldNusMods.remove(nusMod);
            } else {
                finalNusMods.add(nusMod);
            }
        }

        finalNusMods.addAll(oldNusMods);
        return new Modules(finalNusMods);
    }

    /**
     * Creates and returns a {@code Set<Tag>} with the Updated set of Tags.
     * If the same Tag appears in both Old Set and New Set, we count that as removal.
     * Otherwise,it is an addition.
     *
     * @param unmodifiableOldTags
     * @param optionalNewTags
     */
    public static Set<Tag> createEditedTags(Set<Tag> unmodifiableOldTags, Optional<Set<Tag>> optionalNewTags) {
        Set<Tag> finalSet = new HashSet<>();
        Set<Tag> oldTags = new HashSet<>(unmodifiableOldTags);
        if (optionalNewTags.isEmpty()) {
            return oldTags;
        }

        Set<Tag> newTags = optionalNewTags.get();
        for (Tag tags : newTags) {
            if (oldTags.contains(tags)) {
                oldTags.remove(tags);
            } else {
                finalSet.add(tags);
            }
        }

        finalSet.addAll(oldTags);
        return finalSet;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditContactCommand)) {
            return false;
        }

        // state check
        EditContactCommand e = (EditContactCommand) other;
        return index.equals(e.index)
                && this.getEditPersonDescriptor().equals(e.getEditPersonDescriptor());
    }
}
