package seedu.address.logic.commands;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.parser.editpersoncommandsparser.PersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.person.fields.Address;
import seedu.address.model.person.fields.CommunicationChannel;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Faculty;
import seedu.address.model.person.fields.Favorite;
import seedu.address.model.person.fields.Field;
import seedu.address.model.person.fields.Gender;
import seedu.address.model.person.fields.Major;
import seedu.address.model.person.fields.Modules;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Race;
import seedu.address.model.person.fields.SuperField;
import seedu.address.model.person.fields.Tags;

/**
 * Represents a command that edits a person object. For all commands that edit a person to inherit from.
 */
public abstract class EditPersonCommand extends Command {

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    protected static Person createEditedPerson(Person personToEdit, PersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Gender updatedGender = editPersonDescriptor.getGender().orElse(personToEdit.getGender());
        Major updatedMajor = editPersonDescriptor.getMajor().orElse(personToEdit.getMajor());
        Race updatedRace = editPersonDescriptor.getRace().orElse(personToEdit.getRace());
        CommunicationChannel updatedComms = editPersonDescriptor.getComms().orElse(personToEdit.getComms());
        Favorite currentFavorite = personToEdit.getFavorite();
        Faculty updatedFaculty = editPersonDescriptor.getFaculty().orElse(personToEdit.getFaculty());

        Tags updatedTags = editPersonDescriptor.getTags()
                //Creates the edited tags.
                .map(tags -> createEditedTags(personToEdit.getTags(), tags))
                //If it was empty, then just get original tags.
                .orElseGet(personToEdit::getTags);

        Modules updatedModules = editPersonDescriptor.getModules()
                //Creates the edited modules.
                .map(modules -> createEditedModules(personToEdit.getModules(), modules))
                //If it was empty, then just get original modules.
                .orElseGet(personToEdit::getModules);

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedGender, updatedMajor, updatedModules, updatedRace, updatedTags,
                updatedComms, currentFavorite, updatedFaculty);
    }

    /**
     * Creates a set of Field that removes all entries that were already in the oldFields Set and adds the new ones.
     */
    public static <T extends Field> Set<T> createdEditedFields(SuperField<T> oldFields,
                                                               SuperField<T> optionalNewFields) {
        Set<T> unmodifiableOldFields = oldFields.getValues();
        Set<T> oldFieldSet = new HashSet<>(unmodifiableOldFields);
        Set<T> finalFieldSet = new HashSet<>();

        Set<T> newFields = optionalNewFields.getValues();
        for (T field : newFields) {
            if (oldFieldSet.contains(field)) {
                oldFieldSet.remove(field);
            } else {
                finalFieldSet.add(field);
            }
        }

        finalFieldSet.addAll(oldFieldSet);
        return finalFieldSet;
    }

    /**
     * Creates and returns a {@code Module} with the updated Details of Modules Taken.
     * If the same NusMods appears in both Old Modules and New Modules, we count that as removal.
     * Otherwise,it is an addition.
     */
    public static Modules createEditedModules(Modules oldModules, Modules optionalNewModules) {
        return new Modules(createdEditedFields(oldModules, optionalNewModules));
    }

    /**
     * Creates and returns a {@code Set<Tag>} with the Updated set of Tags.
     * If the same Tag appears in both Old Set and New Set, we count that as removal.
     * Otherwise,it is an addition.
     */
    public static Tags createEditedTags(Tags oldTags, Tags optionalNewTags) {
        return new Tags(createdEditedFields(oldTags, optionalNewTags));
    }
}
