package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.ViewCommandResult;
import seedu.address.logic.parser.IndexHandler;
import seedu.address.logic.parser.TagType;
import seedu.address.model.Model;
import seedu.address.model.commitment.Lesson;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;
import seedu.address.model.person.User;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;
import seedu.address.model.tag.Tag;
import seedu.address.model.time.TimePeriod;
import seedu.address.model.time.util.TimeUtil;

/**
 * Adds a ModuleTag to a person.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a ModuleTag or GroupTag to a person. \n"
            + "tag <index> m/<module> : Adds modules to the person of given index. \n"
            + "tag m/<module> : Adds modules to your own profile instead. \n"
            + "tag <index> g/<group> : Adds groups to the person of given index \n"
            + "tag g/<group> : Adds groups to your own profile instead. \n";

    public static final String MESSAGE_MODULE_TAG_PERSON_SUCCESS = "Module(s) tagged to Person! \n";
    public static final String MESSAGE_MODULE_TAG_USER_SUCCESS = "Module(s) tagged to User! \n";
    public static final String MESSAGE_GROUP_TAG_PERSON_SUCCESS = "Group(s) tagged to Person! \n";
    public static final String MESSAGE_GROUP_TAG_USER_SUCCESS = "Group(s) tagged to User! \n";
    public static final String MESSAGE_NO_TAGS = "At least one Module/Group must be provided.\n";
    public static final String MESSAGE_BOTH_TAGS_INPUTTED = "You can only tag groups or modules in the same command.\n";

    private final ContactIndex index;
    private final Set<GroupTag> groupTags = new HashSet<>();
    private final Set<ModuleTag> moduleTags = new HashSet<>();
    private final Set<Lesson> lessons = new HashSet<>();
    private final TagType tagType;

    /**
     * @param index of the person in the filtered person list to add modules.
     * @param tagsToAdd set of tags to be added
     */
    public TagCommand(ContactIndex index, Set<? extends Tag> tagsToAdd, TagType tagType) {
        requireNonNull(tagsToAdd);

        this.index = index;
        this.tagType = tagType;
        switch (tagType) {
        case MODULE:
            initializeModules(tagsToAdd);
            break;
        case GROUP:
            initializeGroups(tagsToAdd);
            break;
        default:
            break;
        }
    }

    private void initializeModules(Set<? extends Tag> tagsToAdd) {
        for (Tag tag : tagsToAdd) {
            assert(tag instanceof ModuleTag);
            this.moduleTags.add((ModuleTag) tag);
        }
        this.lessons.addAll(moduleTags.stream()
                .map(ModuleTag::getImmutableLessons)
                .flatMap(Set::stream)
                .collect(Collectors.toSet()));
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
            personToEdit.addGroupTags(this.groupTags);
            if (personToEdit instanceof User) {
                return new ViewCommandResult(String.format(MESSAGE_GROUP_TAG_USER_SUCCESS
                            + "Name: " + personToEdit.getName().toString() + '\n'
                            + "Groups: " + personToEdit.getImmutableGroupTags().toString()), personToEdit);
            }
            return new ViewCommandResult(String.format(MESSAGE_GROUP_TAG_PERSON_SUCCESS
                        + "Name: " + personToEdit.getName().toString() + '\n'
                        + "Groups: " + personToEdit.getImmutableGroupTags().toString()), personToEdit);
        }

        List<TimePeriod> timePeriods = lessons.stream().map(Lesson::getTimePeriod).collect(Collectors.toList());

        if (!personToEdit.canAddCommitments(lessons) || TimeUtil.hasAnyClash(timePeriods)) {
            throw new CommandException("There is a clash in commitments!");
        }

        personToEdit.addModuleTags(moduleTags);

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
     * Add tags to person at given index.
     * @return feedback message of the operation result for display
     */
    public ViewCommandResult setPersonCommonModuleTags(Model model, Person personToEdit) {
        Set<ModuleTag> userModuleTags = model.getUser().getImmutableModuleTags();

        // caches the common modules in each ModuleTagSet as running set
        // intersection is expensive if we only use it in the compareTo method
        personToEdit.setCommonModules(userModuleTags);

        return new ViewCommandResult(String.format(MESSAGE_MODULE_TAG_PERSON_SUCCESS
                + "Name: " + personToEdit.getName().toString() + '\n'
                + "Modules: " + personToEdit.getImmutableModuleTags().toString() + '\n'
                + "Module(s) in common: " + personToEdit.getImmutableCommonModuleTags().toString() + '\n'
                + "Lessons: " + personToEdit.getLessonsAsStr()), personToEdit);
    }

    /**
     * Adds modules to user.
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     */
    public ViewCommandResult setUserCommonModuleTags(Model model, User editedUser) {
        model.getObservablePersonList().forEach(person ->
                person.setCommonModules(editedUser.getImmutableModuleTags()));

        return new ViewCommandResult(String.format(MESSAGE_MODULE_TAG_USER_SUCCESS
                + "Name: " + editedUser.getName().toString() + '\n'
                + "Modules: " + editedUser.getImmutableModuleTags().toString() + '\n'
                + "Lessons: " + editedUser.getLessonsAsStr()), editedUser);

    }

    public ContactIndex getIndex() {
        return this.index;
    }

    public Set<ModuleTag> getModules() {
        return this.moduleTags;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof TagCommand) {
            TagCommand otherCommand = (TagCommand) other;
            return otherCommand.getIndex().equals(getIndex())
                    && otherCommand.getModules().equals(getModules());
        }

        return false;
    }
}
