package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.parser.IndexHandler;
import seedu.address.model.Model;
import seedu.address.model.commitment.Lesson;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;
import seedu.address.model.person.User;
import seedu.address.model.tag.ModuleTag;
import seedu.address.model.time.TimePeriod;
import seedu.address.model.time.util.TimeUtil;

/**
 * Adds a ModuleTag to a person.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a ModuleTag to a person. \n"
            + "tag <index> m/<module> : Adds tags from the person of given index in displayed list. \n"
            + "tag m/<module> : Adds tags to your own profile instead.";

    public static final String MESSAGE_TAG_PERSON_SUCCESS = "Module(s) tagged to Person! \n";
    public static final String MESSAGE_TAG_USER_SUCCESS = "Module(s) tagged to User! \n";
    public static final String MESSAGE_NO_TAGS = "At least one Module must be provided.";
    public static final String MESSAGE_INCORRECT_INPUT_FOR_LESSON = "The wrong types of arguments has been provided.";

    private final ContactIndex index;
    private final Set<ModuleTag> moduleTags;
    private final Set<Lesson> lessons;

    /**
     * @param index of the person in the filtered person list to add modules.
     * @param modulesToAdd modules to add to the person
     */
    public TagCommand(ContactIndex index, Set<ModuleTag> modulesToAdd) {
        requireNonNull(modulesToAdd);

        this.index = index;
        this.moduleTags = modulesToAdd;
        lessons = moduleTags.stream()
                .map(ModuleTag::getImmutableLessons)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person personToEdit = getPersonToEdit(model);

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
    public CommandResult setPersonCommonModuleTags(Model model, Person personToEdit) {
        Set<ModuleTag> userModuleTags = model.getUser().getImmutableModuleTags();

        // caches the common modules in each ModuleTagSet as running set
        // intersection is expensive if we only use it in the compareTo method
        personToEdit.setCommonModules(userModuleTags);

        return new CommandResult(String.format(MESSAGE_TAG_PERSON_SUCCESS
                + "Name: " + personToEdit.getName().toString() + '\n'
                + "Modules: " + personToEdit.getImmutableModuleTags().toString() + '\n'
                + "Module(s) in common: " + personToEdit.getImmutableCommonModuleTags().toString() + '\n'
                + "Lessons: " + personToEdit.getLessonsAsStr()));
    }

    /**
     * Adds modules to user.
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     */
    public CommandResult setUserCommonModuleTags(Model model, User editedUser) {
        model.getObservablePersonList().forEach(person ->
                person.setCommonModules(editedUser.getImmutableModuleTags()));

        return new CommandResult(String.format(MESSAGE_TAG_USER_SUCCESS
                + "Name: " + editedUser.getName().toString() + '\n'
                + "Modules: " + editedUser.getImmutableModuleTags().toString() + '\n'
                + "Lessons: " + editedUser.getLessonsAsStr()));

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
