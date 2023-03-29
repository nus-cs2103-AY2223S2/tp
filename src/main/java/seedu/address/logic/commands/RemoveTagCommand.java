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
 * Removes a person from a tag in the address book.
 */
public class RemoveTagCommand extends Command {

    public static final String COMMAND_WORD = "remove-tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Remove a person of index i to a tag specified. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "varsity";
    public static final String REMOVE_TAG_PERSON_SUCCESS = "Removed Person: %1$s from tags: %2$s";
    public static final String TAG_NOT_FOUND_FAILURE = "Tags not found: %1$s";

    private final Set<Tag> tagsToRemove;
    private final Index index;

    /**
     * command constructor
     * @param index of the person in the filtered person list to edit
     * @param tagsToRemove details to edit the person with
     */
    public RemoveTagCommand(Index index, Set<Tag> tagsToRemove) {
        this.index = index;
        this.tagsToRemove = tagsToRemove;
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
     * @return set of tags
     * @throws CommandException
     */
    public Set<Tag> removeFromModifiedTag(Model model, Person personToModify) throws CommandException {
        Set<Tag> existingTag = personToModify.getTags();
        Set<Tag> modifiedTag = new HashSet<>();
        modifiedTag.addAll(existingTag);
        for (Tag tag : tagsToRemove) {
            if (!personToModify.getTags().contains(tag)) {
                throw new CommandException(String.format(TAG_NOT_FOUND_FAILURE,
                        tag));
            }
            modifiedTag.remove(tag);
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

        Person personToRemoveTag = lastShownList.get(index.getZeroBased());
        Set<Tag> tagsRemoved = removeFromModifiedTag(model, personToRemoveTag);
        Person modifiedPerson = createModifiedPerson(personToRemoveTag, tagsRemoved);
        model.setPerson(personToRemoveTag, modifiedPerson);
        model.commitAddressBook();
        return new CommandResult(String.format(REMOVE_TAG_PERSON_SUCCESS, personToRemoveTag.getName(), tagsToRemove));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof RemoveTagCommand)) {
            return false;
        }
        RemoveTagCommand e = (RemoveTagCommand) other;
        return index.equals(e.index) && tagsToRemove.equals(e.tagsToRemove);
    }
}
