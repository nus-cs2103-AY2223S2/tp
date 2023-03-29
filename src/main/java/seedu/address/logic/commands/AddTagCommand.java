package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.PayRate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;


/**
 * Adds a person to a tag to in the address book.
 */
public class AddTagCommand extends Command {

    public static final String COMMAND_WORD = "add-tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a person of index i to a tag specified. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "varsity";
    public static final String ADD_TAG_PERSON_SUCCESS = "Added Person: %1$s to tags: %2$s";
    public static final String STUDENT_ALREADY_ADDED_FAILURE = "%1$s already has tag: %2$s";

    private final Set<Tag> tagsToAdd;
    private final Index index;

    /**
     * command constructor
     * @param index of the person in the filtered person list to edit
     * @param tagsToAdd details to edit the person with
     */
    public AddTagCommand(Index index, Set<Tag> tagsToAdd) {
        this.index = index;
        this.tagsToAdd = tagsToAdd;
    }

    /**
     * creates a modified person
     * @param person
     * @param tags
     * @return
     */
    public Person createModifiedPerson(Person person, Set<Tag> tags) {
        Name updatedName = person.getName();
        Phone updatedPhone = person.getPhone();
        Address updatedAddress = person.getAddress();
        PayRate updatedPayRate = person.getPayRate();
        Set<Tag> updatedTags = new HashSet<>();
        updatedTags.addAll(tags);
        return new Person(updatedName, updatedPhone, updatedAddress, updatedPayRate, updatedTags);
    }

    /**
     * Create modified tag
     * @param model
     * @param personToModify
     * @return Set of tags
     * @throws CommandException
     */
    public Set<Tag> addToModifiedTag(Model model, Person personToModify) throws CommandException {
        Set<Tag> existingTag = personToModify.getTags();
        Set<Tag> modifiedTag = new HashSet<>();
        modifiedTag.addAll(existingTag);
        for (Tag tag : tagsToAdd) {
            if (personToModify.getTags().contains(tag)) {
                throw new CommandException(String.format(
                        STUDENT_ALREADY_ADDED_FAILURE,
                        personToModify.getName().fullName,
                        tag));
            }
            modifiedTag.add(tag);
        }
        return modifiedTag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();


        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddTag = lastShownList.get(index.getZeroBased());
        Set<Tag> tagsAdded = addToModifiedTag(model, personToAddTag);
        Person modifiedPerson = createModifiedPerson(personToAddTag, tagsAdded);
        model.setPerson(personToAddTag, modifiedPerson);
        model.commitAddressBook();
        return new CommandResult(String.format(ADD_TAG_PERSON_SUCCESS, personToAddTag.getName(), tagsToAdd));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof AddTagCommand)) {
            return false;
        }
        AddTagCommand e = (AddTagCommand) other;
        return index.equals(e.index) && tagsToAdd.equals(e.tagsToAdd);
    }
}
