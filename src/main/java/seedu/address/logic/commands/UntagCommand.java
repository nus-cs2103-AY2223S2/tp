package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

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
            + "untag <index> m/<module> : Removes modules from the person of given index in displayed list. \n"
            + "untag m/<module> : Removes modules from your own profile instead. \n"
            + "untag <index> g/<group> : Remove groups from the person of given index. \n"
            + "untag g/<group> : Remove groups from your own profile instead. \n";

    public static final String MESSAGE_MODULE_UNTAG_PERSON_SUCCESS = "Module(s) untagged to Person! \n";
    public static final String MESSAGE_MODULE_UNTAG_USER_SUCCESS = "Module(s) untagged to User! \n";
    public static final String MESSAGE_GROUP_UNTAG_PERSON_SUCCESS = "Group(s) untagged to Person! \n";
    public static final String MESSAGE_GROUP_UNTAG_USER_SUCCESS = "Group(s) untagged to User! \n";
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
        Person personToEdit = getPersonToEdit(model);

        if (this.tagType == TagType.GROUP) {
            personToEdit.removeGroupTags(this.groupTags);
            if (personToEdit instanceof User) {
                return new ViewCommandResult(String.format(MESSAGE_GROUP_UNTAG_USER_SUCCESS
                            + "Name: " + personToEdit.getName().toString() + '\n'
                            + "Groups: " + personToEdit.getImmutableGroupTags().toString()), personToEdit);
            }
            return new ViewCommandResult(String.format(MESSAGE_GROUP_UNTAG_PERSON_SUCCESS
                        + "Name: " + personToEdit.getName().toString() + '\n'
                        + "Groups: " + personToEdit.getImmutableGroupTags().toString()), personToEdit);
        }

        personToEdit.removeModuleTags(moduleTags);

        if (personToEdit instanceof User) {
            return setUserCommonModuleTags(model, (User) personToEdit);
        }

        return setPersonCommonModuleTags(model, personToEdit);
    }

    private Person getPersonToEdit(Model model) throws CommandException {
        if (index == null) {
            return model.getUser();
        }

        IndexHandler indexHandler = new IndexHandler(model);
        return indexHandler.getPersonByIndex(index).orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));
    }

    /**
     * Removes tags from person at given index.
     * @param model {@code Model} which the command should operate on.
     * @param personToEdit {@code Person} which has been edited.
     * @return feedback message of the operation result for display.
     */
    public ViewCommandResult setPersonCommonModuleTags(Model model, Person personToEdit) {
        Set<ModuleTag> userModuleTags = model.getUser().getImmutableModuleTags();

        personToEdit.setCommonModules(userModuleTags);

        return new ViewCommandResult(String.format(MESSAGE_MODULE_UNTAG_PERSON_SUCCESS
                + "Name: " + personToEdit.getName().toString() + '\n'
                + "Modules: " + personToEdit.getImmutableModuleTags().toString() + '\n'
                + "Module(s) in common: " + personToEdit.getImmutableCommonModuleTags().toString()),
                personToEdit);
    }

    /**
     * Removes tags from the user.
     * @param model {@code Model} which the command should operate on
     * @param editedUser {@code User} which has been edited.
     * @return feedback message of the operation result for display.
     */
    public ViewCommandResult setUserCommonModuleTags(Model model, User editedUser) {
        model.getObservablePersonList().forEach(person ->
                person.setCommonModules(editedUser.getImmutableModuleTags()));

        return new ViewCommandResult(String.format(MESSAGE_MODULE_UNTAG_USER_SUCCESS
                + "Name: " + editedUser.getName().toString() + '\n'
                + "Modules: " + editedUser.getImmutableModuleTags().toString()), editedUser);
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
        }
        return false;

    }
}
