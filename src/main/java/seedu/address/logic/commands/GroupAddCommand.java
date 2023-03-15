package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TAGS;

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



public class GroupAddCommand extends Command {

    public static final String COMMAND_WORD = "groupadd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a person of index i to a group specified. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "varsity";
    public static final String GROUP_ADD_PERSON_SUCCESS = "Added Person: %1$s to Groups: %2$s";
    public static final String GROUP_NOT_FOUND_FAILURE = "Groups: %1$s cannot be found. Here are the list of existing groups: %2$s";
    public static final String STUDENT_ALREADY_ADDED_FAILURE = "Student already belongs to %1$s";

    private Set<Tag> groupsToAdd;
    private Index index;

    /**
     * @param index                of the person in the filtered person list to edit
     * @param groupsToAdd details to edit the person with
     */
    public GroupAddCommand(Index index, Set<Tag> groupsToAdd) {
        this.index = index;
        this.groupsToAdd = groupsToAdd;
    }

    public Person createModifiedPerson(Person person, Set<Tag> groups) {
        Name updatedName = person.getName();
        Phone updatedPhone = person.getPhone();
        Address updatedAddress = person.getAddress();
        PayRate updatedPayRate = person.getPayRate();
        Set<Tag> updatedTags = new HashSet<>();
        updatedTags.addAll(groups);
        return new Person(updatedName, updatedPhone, updatedAddress, updatedPayRate, updatedTags);
    }

    public Set<Tag> addToModifiedGroup(Model model, Person personToModify) throws CommandException {
        Set<Tag> existingGroup = personToModify.getTags();
        Set<Tag> modifiedGroup = new HashSet<>();
        modifiedGroup.addAll(existingGroup);
        for (Tag group : groupsToAdd) {
            if (model.hasTag(group)) {
                if (modifiedGroup.contains(group)) {
                    throw new CommandException(String.format(STUDENT_ALREADY_ADDED_FAILURE, group));
                }
                modifiedGroup.add(group);
            } else {
                model.updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
                String results = model.getAddressBook().getTagList().toString();
                throw new CommandException(String.format(GROUP_NOT_FOUND_FAILURE, group, results));
            }
        }
        return modifiedGroup;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();


        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToGroupAdd = lastShownList.get(index.getZeroBased());
        Set<Tag> groupsAdded = addToModifiedGroup(model, personToGroupAdd);
        Person modifiedPerson = createModifiedPerson(personToGroupAdd, groupsAdded);
        model.setPerson(personToGroupAdd, modifiedPerson);
        return new CommandResult(String.format(GROUP_ADD_PERSON_SUCCESS, personToGroupAdd.getName(), groupsToAdd));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof GroupAddCommand)) {
            return false;
        }
        GroupAddCommand e = (GroupAddCommand) other;
        return index.equals(e.index) && groupsToAdd.equals(e.groupsToAdd);
    }



}
