package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.*;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

/**
 * Adds a ModuleTag to a person.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    /**
     * tag n/NAME m/MODULE_TAG
     */

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a ModuleTag to a person. "
            + "Parameters: ";

    public static final String MESSAGE_TAG_PERSON_SUCCESS = "Module(s) tagged to Person:";
    public static final String MESSAGE_TAG_USER_SUCCESS = "Module(s) tagged to User:";
    public static final String MESSAGE_NO_TAGS = "At least one Module must be provided.";

    private final Index index;
    private final Set<ModuleTag> moduleTags;

    public TagCommand(Index index, Set<ModuleTag> modulesToAdd) {
        requireNonNull(modulesToAdd);

        this.index = index;
        this.moduleTags = modulesToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (index == null) {
            return addUserTags(model);
        }

        return addPersonTags(model);
    }

    public CommandResult addPersonTags(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        ModuleTagSet oldModules = personToEdit.getModuleTags();

        oldModules.addAll(this.moduleTags);

        Set<ModuleTag> userModuleTags = model.getUser().getImmutableModuleTags();

        // caches the common modules in each ModuleTagSet as running set
        // intersection is expensive if we only use it in the compareTo method
        personToEdit.setCommonModules(userModuleTags);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_TAG_PERSON_SUCCESS, personToEdit));
    }

    public CommandResult addUserTags(Model model) throws CommandException {
        User editedUser = model.getUser();

        ModuleTagSet userModuleTags = model.getUser().getModuleTags();

        userModuleTags.addAll(this.moduleTags);

        model.getFilteredPersonList().forEach(person ->
                person.setCommonModules(editedUser.getImmutableModuleTags()));

        return new CommandResult(String.format(MESSAGE_TAG_USER_SUCCESS, editedUser));
    }

}
