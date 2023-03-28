package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2040S_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS3245_F;
import static seedu.address.testutil.TypicalPersons.ISAAC;
import static seedu.address.testutil.TypicalPersons.getContactIndexOfPerson;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.IndexHandler;
import seedu.address.logic.parser.TagType;
import seedu.address.model.EduMateHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

public class TagCommandTest {
    private final Model model = new ModelManager(getTypicalEduMate(), new UserPrefs(), new EduMateHistory());
    private final IndexHandler indexHandler = new IndexHandler(model);

    @Test
    public void execute_addNewNonClashingModuleTags_success() throws CommandException {
        Set<ModuleTag> modulesToAdd = new HashSet<>();
        modulesToAdd.add(CS2040S_F);
        modulesToAdd.add(CS3245_F);

        ContactIndex indexIsaac = getContactIndexOfPerson(ISAAC);

        Person isaacToUpdate = indexHandler.getPersonByIndex(indexIsaac).orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));

        Set<ModuleTag> isaacModuleTags = new HashSet<>(isaacToUpdate.getImmutableModuleTags());

        assertFalse(isaacModuleTags.contains(CS2040S_F));
        assertFalse(isaacModuleTags.contains(CS3245_F));

        isaacModuleTags.add(CS2040S_F);
        isaacModuleTags.add(CS3245_F);

        TagCommand tagIsaac = new TagCommand(indexIsaac, modulesToAdd, TagType.MODULE);
        tagIsaac.execute(model);

        Person updatedIsaac = indexHandler.getPersonByIndex(indexIsaac).orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));

        assertEquals(updatedIsaac.getImmutableModuleTags(), isaacModuleTags);
    }

    @Test
    public void execute_addNewClashingModuleTags_success() throws CommandException {
        Set<ModuleTag> modulesToAdd = new HashSet<>();
        modulesToAdd.add(CS2040S_F);
        modulesToAdd.add(CS3245_F);

        ContactIndex indexIsaac = getContactIndexOfPerson(ISAAC);

        Person isaacToUpdate = indexHandler.getPersonByIndex(indexIsaac).orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));

        Set<ModuleTag> isaacModuleTags = isaacToUpdate.getImmutableModuleTags();

        assertFalse(isaacModuleTags.contains(CS2040S_F));
        assertFalse(isaacModuleTags.contains(CS3245_F));

        isaacModuleTags.add(CS2040S_F);
        isaacModuleTags.add(CS3245_F);

        TagCommand tagIsaac = new TagCommand(indexIsaac, modulesToAdd, TagType.MODULE);
        tagIsaac.execute(model);

        Person updatedIsaac = indexHandler.getPersonByIndex(indexIsaac).orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));

        assertEquals(updatedIsaac.getImmutableModuleTags(), isaacModuleTags);
    }

    @Test
    public void execute_addModulesToUser() throws CommandException {

        Set<ModuleTag> modulesToAdd = new HashSet<>();
        modulesToAdd.add(new ModuleTag("CS1234"));
        modulesToAdd.add(new ModuleTag("CS2345"));

        TagCommand userTest = new TagCommand(null, modulesToAdd, TagType.MODULE);
        userTest.execute(model);

        Set<ModuleTag> tags = new HashSet<>() {{
                add(new ModuleTag("CS2100"));
                add(new ModuleTag("CS2101"));
                add(new ModuleTag("CS2102"));
                add(new ModuleTag("CS2103"));
                add(new ModuleTag("CS2104"));
                add(new ModuleTag("CS2105"));
                add(new ModuleTag("CS2106"));
                add(new ModuleTag("CS1234"));
                add(new ModuleTag("CS2345"));
            }};

        Person userAct = model.getUser();

        assertEquals(userAct.getImmutableModuleTags(), tags);

        UntagCommand untag = new UntagCommand(null, modulesToAdd, TagType.MODULE);
        untag.execute(model);

    }

    @Test
    public void execute_addTwoGroupsToAng() throws CommandException {
        Set<GroupTag> groupsToAdd = new HashSet<>() {{
                add(new GroupTag("Enemy"));
                add(new GroupTag("EnemyOfEnemy"));
            }};

        ContactIndex index = new ContactIndex(2);

        TagCommand tag = new TagCommand(index, groupsToAdd, TagType.GROUP);
        tag.execute(model);
        Person personEdited = indexHandler.getPersonByIndex(index).orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));

        Set<GroupTag> groups = new HashSet<>() {{
                add(new GroupTag("Enemy"));
                add(new GroupTag("EnemyOfEnemy"));
                add(new GroupTag("Study"));
            }};

        //        assertEquals(personEdited.getImmutableGroupTags(), groups);

        UntagCommand untag = new UntagCommand(index, groupsToAdd, TagType.GROUP);
        untag.execute(model);
    }

    @Test
    public void execute_addOneGroupToAlber() throws CommandException {
        Set<GroupTag> groupsToAdd = new HashSet<>() {{
                add(new GroupTag("Sheep"));
            }};

        ContactIndex index = new ContactIndex(1);

        TagCommand tag = new TagCommand(index, groupsToAdd, TagType.GROUP);
        tag.execute(model);
        Person personEdited = indexHandler.getPersonByIndex(index).orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));

        Set<GroupTag> groups = new HashSet<>() {{
                add(new GroupTag("Sheep"));
                add(new GroupTag("TA"));
            }};

        //        assertEquals(personEdited.getImmutableGroupTags(), groups);

        UntagCommand untag = new UntagCommand(index, groupsToAdd, TagType.GROUP);
        untag.execute(model);
    }

    @Test
    public void execute_addGroupsToUser() throws CommandException {
        Set<GroupTag> groupsToAdd = new HashSet<>() {{
                add(new GroupTag("CCA"));
                add(new GroupTag("Friend"));
            }};

        TagCommand tagCommand = new TagCommand(null, groupsToAdd, TagType.GROUP);
        tagCommand.execute(model);

        Set<GroupTag> groups = new HashSet<>() {{
                add(new GroupTag("CCA"));
                add(new GroupTag("Friend"));
                add(new GroupTag("User"));
            }};

        Person user = model.getUser();

        //        assertEquals(user.getImmutableGroupTags(), groups);

        UntagCommand untag = new UntagCommand(null, groupsToAdd, TagType.GROUP);
        untag.execute(model);


    }

}
