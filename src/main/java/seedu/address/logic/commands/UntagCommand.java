package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.ViewCommandResult;
import seedu.address.logic.parser.IndexHandler;
import seedu.address.logic.parser.TagType;
import seedu.address.model.Model;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;
import seedu.address.model.person.User;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;
import seedu.address.model.tag.Tag;

/**
 * Removes modules from an existing person in the address book.
 */
public class UntagCommand extends Command {

    public static final String COMMAND_WORD = "untag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a ModuleTag to a person. \n"
            + "untag INDEX m/MODULE : Removes modules from the person of given index in displayed list. \n"
            + "untag m/MODULE : Removes modules from your own profile instead. \n"
            + "untag INDEX g/GROUP : Remove groups from the person of given index. \n"
            + "untag g/GROUP : Remove groups from your own profile instead. \n";

    public static final String MESSAGE_MODULE_UNTAG_PERSON_SUCCESS = "Module(s) untagged from Person! \n";
    public static final String MESSAGE_MODULE_UNTAG_USER_SUCCESS = "Module(s) untagged from User! \n";
    public static final String MESSAGE_GROUP_UNTAG_PERSON_SUCCESS = "Group(s) untagged from Person! \n";
    public static final String MESSAGE_GROUP_UNTAG_USER_SUCCESS = "Group(s) untagged from User! \n";
    public static final String MESSAGE_NO_TAGS = "At least one Module/Group must be provided. \n";
    public static final String MESSAGE_BOTH_TAGS_INPUTTED = "You can only tag groups or modules in the same command.\n";

    private final ContactIndex index;
    private final Set<ModuleTag> moduleTags = new HashSet<>();
    private final Set<GroupTag> groupTags = new HashSet<>();
    private final TagType tagType;

    /**
     * @param index of the person in the filtered person list to edit.
     * @param tagsToRemove set of tags to be removed.
     */
    public UntagCommand(ContactIndex index, Set<? extends Tag> tagsToRemove, TagType tagType) {
        requireNonNull(tagsToRemove);

        this.index = index;
        this.tagType = tagType;
        switch (tagType) {
        case MODULE:
            initializeModules(tagsToRemove);
            break;
        case GROUP:
            initializeGroups(tagsToRemove);
            break;
        default:
            break;
        }
    }

    private void initializeModules(Set<? extends Tag> tagsToRemove) {
        for (Tag tag : tagsToRemove) {
            assert(tag instanceof ModuleTag);
            this.moduleTags.add((ModuleTag) tag);
        }
    }

    private void initializeGroups(Set<? extends Tag> tagsToAdd) {
        for (Tag tag: tagsToAdd) {
            assert(tag instanceof GroupTag);
            this.groupTags.add((GroupTag) tag);
        }
    }

    @Override
    public ViewCommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (tagType == TagType.GROUP) {
            return untagGroups(model);
        }

        if (index == null) {
            return untagUserModules(model);
        }

        return untagPersonModules(model);

    }

    private ViewCommandResult untagPersonModules(Model model) throws CommandException {
        IndexHandler indexHandler = new IndexHandler(model);
        Person personToEdit = indexHandler.getPersonByIndex(index).orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));
        Person editedPerson = personToEdit.copy();

        Set<ModuleTag> removedModuleTags = editedPerson.removeModuleTags(moduleTags);

        // caches the common modules in each ModuleTagSet as running set
        // intersection is expensive if we only use it in the compareTo method
        Set<ModuleTag> userModuleTags = model.getUser().getImmutableModuleTags();
        editedPerson.setCommonModules(userModuleTags);

        model.setPerson(personToEdit, editedPerson);
        model.updateObservablePersonList();

        String message = MESSAGE_MODULE_UNTAG_PERSON_SUCCESS
                + removedModuleTags.stream()
                .map(ModuleTag::toString)
                .collect(Collectors.joining("\n"));

        return new ViewCommandResult(message, editedPerson);
    }

    private ViewCommandResult untagGroups(Model model) throws CommandException {
        Person personToEdit;
        if (index == null) {
            personToEdit = model.getUser();
        } else {
            IndexHandler indexHandler = new IndexHandler(model);
            personToEdit = indexHandler.getPersonByIndex(index).orElseThrow(() ->
                    new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));
        }

        Set<GroupTag> removedGroupTags = personToEdit.removeGroupTags(this.groupTags);

        model.updateObservablePersonList(Model.COMPARATOR_CONTACT_INDEX_PERSON.reversed());
        model.updateObservablePersonList(Model.COMPARATOR_CONTACT_INDEX_PERSON);

        String message = removedGroupTags.stream()
                .map(GroupTag::toString)
                .collect(Collectors.joining("\n"));

        return (personToEdit instanceof User)
                ? new ViewCommandResult(MESSAGE_GROUP_UNTAG_USER_SUCCESS + message, personToEdit)
                : new ViewCommandResult(MESSAGE_GROUP_UNTAG_PERSON_SUCCESS + message, personToEdit);
    }
    private ViewCommandResult untagUserModules(Model model) throws CommandException {
        User userToEdit = model.getUser();
        User editedUser = userToEdit.copy();

        Set<ModuleTag> removedModuleTags = editedUser.removeModuleTags(moduleTags);
        model.setUser(editedUser);

        Set<ModuleTag> userModuleTags = model.getUser().getImmutableModuleTags();

        model.getObservablePersonList().forEach(person ->
                person.setCommonModules(userModuleTags));
        model.updateObservablePersonList(Model.COMPARATOR_CONTACT_INDEX_PERSON.reversed());
        model.updateObservablePersonList(Model.COMPARATOR_CONTACT_INDEX_PERSON);

        String message = MESSAGE_MODULE_UNTAG_USER_SUCCESS
                + removedModuleTags.stream()
                .map(ModuleTag::toString)
                .collect(Collectors.joining("\n"));

        return new ViewCommandResult(message, editedUser);
    }

    public ContactIndex getIndex() {
        return this.index;
    }

    public Set<ModuleTag> getModules() {
        return this.moduleTags;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof UntagCommand) {
            UntagCommand otherCommand = (UntagCommand) other;
            return otherCommand.getIndex().equals(getIndex())
                    && otherCommand.getModules().equals(getModules());
            // Currently wrong due to presence of Lessons in ModuleTag.
        }
        return false;

    }
}
