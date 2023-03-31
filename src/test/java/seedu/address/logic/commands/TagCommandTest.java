package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.tag.util.TypicalModuleTag.CFG1002_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2040S_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2101_KE;
import static seedu.address.model.tag.util.TypicalModuleTag.CS3245_F;
import static seedu.address.model.tag.util.TypicalModuleTag.ES2660_RU_ALT_2;
import static seedu.address.model.tag.util.TypicalModuleTag.GEN2050_F;
import static seedu.address.model.tag.util.TypicalModuleTag.LAJ1201_F;
import static seedu.address.model.timetable.util.TypicalLesson.CS2101_MON_8AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2101_THU_8AM_2HR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALBERT;
import static seedu.address.testutil.TypicalPersons.ISAAC;
import static seedu.address.testutil.TypicalPersons.OWEN;
import static seedu.address.testutil.TypicalPersons.getContactIndexOfPerson;
import static seedu.address.testutil.TypicalPersons.getPersonFromIndexHandler;
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
import seedu.address.model.person.User;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

public class TagCommandTest {
    private final Model model = new ModelManager(getTypicalEduMate(), new UserPrefs(), new EduMateHistory());
    private final IndexHandler indexHandler = new IndexHandler(model);
    private final TagCommand tagCommand =
            new TagCommand(new ContactIndex(1), Set.of(CS2040S_F), TagType.MODULE);

    @Test
    public void execute_addNewNonClashingModuleTags_success() {
        Set<ModuleTag> modulesToAdd = new HashSet<>();
        modulesToAdd.add(CS2040S_F);
        modulesToAdd.add(CS3245_F);
        modulesToAdd.add(GEN2050_F);

        Person isaacToUpdate = getPersonFromIndexHandler(indexHandler, ISAAC);
        ContactIndex indexIsaac = getContactIndexOfPerson(ISAAC);

        Set<ModuleTag> isaacModuleTags = new HashSet<>(isaacToUpdate.getImmutableModuleTags());

        assertFalse(isaacModuleTags.contains(CS2040S_F));
        assertFalse(isaacModuleTags.contains(CS3245_F));
        assertFalse(isaacModuleTags.contains(GEN2050_F));

        isaacModuleTags.add(CS2040S_F);
        isaacModuleTags.add(CS3245_F);
        isaacModuleTags.add(GEN2050_F);

        TagCommand tagIsaac = new TagCommand(indexIsaac, modulesToAdd, TagType.MODULE);
        assertDoesNotThrow(() -> tagIsaac.execute(model));

        Person updatedIsaac = getPersonFromIndexHandler(indexHandler, ISAAC);

        assertEquals(updatedIsaac.getImmutableModuleTags(), isaacModuleTags);
    }

    @Test
    public void execute_addNewNonClashingModuleTagsToUser_success() {
        Set<ModuleTag> modulesToAdd = new HashSet<>();
        modulesToAdd.add(ES2660_RU_ALT_2);

        User userToUpdate = model.getUser();

        Set<ModuleTag> userModuleTags = new HashSet<>(userToUpdate.getImmutableModuleTags());

        assertFalse(userModuleTags.contains(ES2660_RU_ALT_2));

        userModuleTags.add(ES2660_RU_ALT_2);

        TagCommand tagUser = new TagCommand(null, modulesToAdd, TagType.MODULE);
        assertDoesNotThrow(() -> tagUser.execute(model));

        User updatedUser = model.getUser();

        assertEquals(updatedUser.getImmutableModuleTags(), userModuleTags);
    }

    @Test
    void execute_addOldClashingModuleTags_throwsCommandException() {
        Set<ModuleTag> modulesToAdd = new HashSet<>();
        modulesToAdd.add(LAJ1201_F);

        Person isaacToUpdate = getPersonFromIndexHandler(indexHandler, ISAAC);
        ContactIndex indexIsaac = getContactIndexOfPerson(ISAAC);

        Set<ModuleTag> isaacModuleTags = new HashSet<>(isaacToUpdate.getImmutableModuleTags());
        assertTrue(isaacModuleTags.contains(LAJ1201_F));

        isaacModuleTags.add(LAJ1201_F);

        TagCommand tagIsaac = new TagCommand(indexIsaac, modulesToAdd, TagType.MODULE);
        assertThrows(CommandException.class, () -> tagIsaac.execute(model));
    }

    @Test
    void execute_addNewClashingModuleTags_throwsCommandException() {
        Set<ModuleTag> modulesToAdd = new HashSet<>();
        modulesToAdd.add(CS2101_KE);

        Person owenToUpdate = getPersonFromIndexHandler(indexHandler, OWEN);
        ContactIndex indexOwen = getContactIndexOfPerson(OWEN);

        Set<ModuleTag> owenModuleTags = new HashSet<>(owenToUpdate.getImmutableModuleTags());

        assertFalse(owenModuleTags.contains(CS2101_KE));

        owenModuleTags.add(CS2101_KE);

        TagCommand tagOwen = new TagCommand(indexOwen, modulesToAdd, TagType.MODULE);
        assertThrows(CommandException.class, () -> tagOwen.execute(model));

        Person updatedOwen = getPersonFromIndexHandler(indexHandler, OWEN);
        assertFalse(updatedOwen.getImmutableModuleTags().contains(CS2101_KE));
    }

    @Test
    void execute_someClashing_throwsCommandException() {
        Set<ModuleTag> modulesToAdd = new HashSet<>();
        modulesToAdd.add(CS2101_KE);
        modulesToAdd.add(CFG1002_F);

        Person owenToUpdate = getPersonFromIndexHandler(indexHandler, OWEN);
        ContactIndex indexOwen = getContactIndexOfPerson(OWEN);

        Set<ModuleTag> owenModuleTags = new HashSet<>(owenToUpdate.getImmutableModuleTags());

        assertFalse(owenModuleTags.contains(CS2101_KE));
        assertFalse(owenModuleTags.contains(CFG1002_F));

        owenModuleTags.add(CS2101_KE);
        owenModuleTags.add(CFG1002_F);

        TagCommand tagOwen = new TagCommand(indexOwen, modulesToAdd, TagType.MODULE);
        assertThrows(CommandException.class, () -> tagOwen.execute(model));

        Person updatedOwen = getPersonFromIndexHandler(indexHandler, OWEN);
        assertFalse(updatedOwen.getImmutableModuleTags().contains(CS2101_KE));
        assertFalse(updatedOwen.getImmutableModuleTags().contains(CFG1002_F));
    }

    @Test
    void execute_nameClashesButLessonsDont_success() {
        Set<ModuleTag> modulesToAdd = new HashSet<>();
        modulesToAdd.add(CFG1002_F);

        Person isaacToUpdate = getPersonFromIndexHandler(indexHandler, ISAAC);
        ContactIndex indexIsaac = getContactIndexOfPerson(ISAAC);

        Set<ModuleTag> isaacModuleTags = new HashSet<>(isaacToUpdate.getImmutableModuleTags());

        assertFalse(isaacModuleTags.contains(CFG1002_F));

        isaacModuleTags.add(CFG1002_F);

        TagCommand tagIsaac = new TagCommand(indexIsaac, modulesToAdd, TagType.MODULE);
        assertDoesNotThrow(() -> tagIsaac.execute(model));
        Person updatedIsaac = getPersonFromIndexHandler(indexHandler, ISAAC);
        assertTrue(updatedIsaac.getImmutableModuleTags().contains(CFG1002_F));

        TagCommand anotherTagIsaac = new TagCommand(indexIsaac, modulesToAdd, TagType.MODULE);
        assertDoesNotThrow(() -> anotherTagIsaac.execute(model));
        updatedIsaac = getPersonFromIndexHandler(indexHandler, ISAAC);
        assertTrue(updatedIsaac.getImmutableModuleTags().contains(CFG1002_F));

        assertEquals(updatedIsaac.getImmutableModuleTags(), isaacModuleTags);
    }

    @Test
    public void execute_addNewLessons_success() {
        Person albertToUpdate = getPersonFromIndexHandler(indexHandler, ALBERT);
        ContactIndex indexAlbert = getContactIndexOfPerson(ALBERT);

        Set<ModuleTag> albertModuleTags = new HashSet<>(albertToUpdate.getImmutableModuleTags());

        assertTrue(albertModuleTags.isEmpty());

        ModuleTag tagWithOneLesson = new ModuleTag("CS2101", CS2101_MON_8AM_2HR);
        TagCommand tagOneLesson = new TagCommand(indexAlbert,
                Set.of(tagWithOneLesson), TagType.MODULE);
        assertDoesNotThrow(() -> tagOneLesson.execute(model));
        Person updatedAlbert = getPersonFromIndexHandler(indexHandler, ALBERT);
        assertEquals(updatedAlbert.getImmutableModuleTags(), Set.of(tagWithOneLesson));

        ModuleTag anotherTagWithOneLesson = new ModuleTag("CS2101", CS2101_THU_8AM_2HR);
        TagCommand tagAnotherLesson = new TagCommand(indexAlbert,
                Set.of(anotherTagWithOneLesson), TagType.MODULE);
        assertDoesNotThrow(() -> tagAnotherLesson.execute(model));

        updatedAlbert = getPersonFromIndexHandler(indexHandler, ALBERT);

        ModuleTag mergedTag = tagWithOneLesson.mergeWith(anotherTagWithOneLesson);
        assertEquals(updatedAlbert.getImmutableModuleTags(), Set.of(mergedTag));
        assertNotEquals(updatedAlbert.getImmutableModuleTags(),
                Set.of(tagWithOneLesson, anotherTagWithOneLesson));
    }

    @Test
    void execute_addEmptyModuleTags_success() {
        Person albertToUpdate = getPersonFromIndexHandler(indexHandler, ALBERT);
        ContactIndex indexAlbert = getContactIndexOfPerson(ALBERT);

        Set<ModuleTag> albertModuleTags = new HashSet<>(albertToUpdate.getImmutableModuleTags());

        assertTrue(albertModuleTags.isEmpty());

        TagCommand tagNothing = new TagCommand(indexAlbert, Set.of(), TagType.MODULE);

        assertDoesNotThrow(() -> tagNothing.execute(model));

        Person updatedAlbert = getPersonFromIndexHandler(indexHandler, ALBERT);
        assertTrue(updatedAlbert.getImmutableModuleTags().isEmpty());
    }

    @Test
    void execute_addEmptyLesson_success() {
        Person albertToUpdate = getPersonFromIndexHandler(indexHandler, ALBERT);
        ContactIndex indexAlbert = getContactIndexOfPerson(ALBERT);

        Set<ModuleTag> albertModuleTags = new HashSet<>(albertToUpdate.getImmutableModuleTags());

        assertTrue(albertModuleTags.isEmpty());

        TagCommand tagNothing = new TagCommand(indexAlbert, Set.of(CFG1002_F), TagType.MODULE);

        assertDoesNotThrow(() -> tagNothing.execute(model));

        Person updatedAlbert = getPersonFromIndexHandler(indexHandler, ALBERT);
        assertTrue(updatedAlbert.getImmutableModuleTags().contains(CFG1002_F));
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

        assertEquals(personEdited.getImmutableGroupTags(), groups);

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

        assertEquals(personEdited.getImmutableGroupTags(), groups);

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

        assertEquals(user.getImmutableGroupTags(), groups);

        UntagCommand untag = new UntagCommand(null, groupsToAdd, TagType.GROUP);
        untag.execute(model);


    }

    @Test
    void tagGroups_invalidContactIndex_throwsCommandException() {
        TagCommand tagCommand = new TagCommand(new ContactIndex(1000),
                Set.of(), TagType.GROUP);
        assertThrows(CommandException.class, () -> tagCommand.execute(model));
    }

    @Test
    void tagPersonModules_invalidContactIndex_throwsCommandException() {
        TagCommand tagCommand = new TagCommand(new ContactIndex(1000),
                Set.of(), TagType.MODULE);
        assertThrows(CommandException.class, () -> tagCommand.execute(model));
    }

    @Test
    void equals_sameObject_true() {
        assertEquals(tagCommand, tagCommand);
    }

    @Test
    void equals_sameValues_true() {
        TagCommand other = new TagCommand(new ContactIndex(1),
                Set.of(CS2040S_F), TagType.MODULE);
        assertEquals(tagCommand, other);
    }

    @Test
    void equals_differentTypes_false() {
        assertNotEquals(tagCommand, 2);
    }

    @Test
    void getLessons() {
        assertEquals(tagCommand.getLessons(), CS2040S_F.getImmutableLessons());
    }
}
