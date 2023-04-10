package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2101_SE;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2103T_SE;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2108_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2109S_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.CS3245_F;
import static seedu.address.model.timetable.util.TypicalLesson.CS2101_MON_8AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2101_THU_8AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2105_THU_4PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2106_WED_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2104_FRI_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2104_TUE_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2104_WED_12PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA3252_FRI_7PM_3HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA3252_WED_10AM_1HR;
import static seedu.address.testutil.TypicalPersons.HALF;
import static seedu.address.testutil.TypicalPersons.JINYONG;
import static seedu.address.testutil.TypicalPersons.KAIMIN;
import static seedu.address.testutil.TypicalPersons.KEVIN;
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
import seedu.address.model.tag.util.ModuleTagBuilder;

public class UntagCommandTest {
    private final Model model = new ModelManager(getTypicalEduMate(), new UserPrefs(), new EduMateHistory());
    private final IndexHandler indexHandler = new IndexHandler(model);

    @Test
    void execute_removeNonExistent_success() {
        Set<ModuleTag> toRemove = Set.of(CS2108_HA);

        Person personToUpdate = getPersonFromIndexHandler(indexHandler, JINYONG);
        ContactIndex index = getContactIndexOfPerson(JINYONG);

        assertFalse(personToUpdate.getImmutableModuleTags().contains(CS2108_HA));

        UntagCommand untagCommand = new UntagCommand(index, toRemove, TagType.MODULE);
        assertDoesNotThrow(() -> untagCommand.execute(model));

        Person updatedPerson = getPersonFromIndexHandler(indexHandler, JINYONG);
        assertFalse(updatedPerson.getImmutableModuleTags().contains(CS2108_HA));

        assertEquals(updatedPerson, personToUpdate);
    }

    @Test
    void execute_removeNonExistentForUser_success() {
        Set<ModuleTag> toRemove = Set.of(CS3245_F);

        User userToUpdate = model.getUser();

        assertFalse(userToUpdate.getImmutableModuleTags().contains(CS3245_F));

        UntagCommand untagCommand = new UntagCommand(null, toRemove, TagType.MODULE);
        assertDoesNotThrow(() -> untagCommand.execute(model));

        User updatedUser = model.getUser();
        assertFalse(updatedUser.getImmutableModuleTags().contains(CS3245_F));

        assertEquals(updatedUser, userToUpdate);
    }

    @Test
    void execute_removeExistent_success() {
        Set<ModuleTag> toRemove = Set.of(CS2108_HA);

        Person personToUpdate = getPersonFromIndexHandler(indexHandler, HALF);
        ContactIndex index = getContactIndexOfPerson(HALF);

        assertTrue(personToUpdate.getImmutableModuleTags().contains(CS2108_HA));

        UntagCommand untagCommand = new UntagCommand(index, toRemove, TagType.MODULE);
        assertDoesNotThrow(() -> untagCommand.execute(model));

        Person updatedPerson = getPersonFromIndexHandler(indexHandler, HALF);
        assertFalse(updatedPerson.getImmutableModuleTags().contains(CS2108_HA));
    }

    @Test
    void execute_removeMultipleExistent_success() {
        Set<ModuleTag> toRemove = Set.of(CS2101_SE, CS2103T_SE);

        Person personToUpdate = getPersonFromIndexHandler(indexHandler, HALF);
        ContactIndex index = getContactIndexOfPerson(HALF);

        assertTrue(personToUpdate.getModuleTags().canRemove(CS2101_SE));
        assertTrue(personToUpdate.getImmutableModuleTags().contains(CS2101_SE));
        assertTrue(personToUpdate.getModuleTags().canRemove(CS2103T_SE));
        assertTrue(personToUpdate.getImmutableModuleTags().contains(CS2103T_SE));

        UntagCommand untagCommand = new UntagCommand(index, toRemove, TagType.MODULE);
        assertDoesNotThrow(() -> untagCommand.execute(model));

        Person updatedPerson = getPersonFromIndexHandler(indexHandler, HALF);
        assertFalse(updatedPerson.getModuleTags().canRemove(CS2101_SE));
        assertFalse(updatedPerson.getImmutableModuleTags().contains(CS2101_SE));
        assertFalse(updatedPerson.getModuleTags().canRemove(CS2103T_SE));
        assertFalse(updatedPerson.getImmutableModuleTags().contains(CS2103T_SE));
    }

    @Test
    void execute_removeLesson_success() {
        ModuleTag partialLesson = new ModuleTagBuilder()
                .withModuleCode("CS2101")
                .withLessons(CS2101_MON_8AM_2HR)
                .build();
        ModuleTag shouldRemain = new ModuleTagBuilder()
                .withModuleCode("CS2101")
                .withLessons(CS2101_THU_8AM_2HR)
                .build();

        Set<ModuleTag> toRemove = Set.of(partialLesson);

        Person personToUpdate = getPersonFromIndexHandler(indexHandler, KAIMIN);
        ContactIndex index = getContactIndexOfPerson(KAIMIN);

        assertTrue(personToUpdate.getModuleTags().canRemove(partialLesson));
        assertFalse(personToUpdate.getImmutableModuleTags().contains(partialLesson));
        assertTrue(personToUpdate.getModuleTags().canRemove(shouldRemain));
        assertFalse(personToUpdate.getImmutableModuleTags().contains(shouldRemain));

        UntagCommand untagCommand = new UntagCommand(index, toRemove, TagType.MODULE);
        assertDoesNotThrow(() -> untagCommand.execute(model));

        Person updatedPerson = getPersonFromIndexHandler(indexHandler, KAIMIN);
        assertFalse(updatedPerson.getModuleTags().canRemove(partialLesson));
        assertFalse(updatedPerson.getImmutableModuleTags().contains(partialLesson));
        assertTrue(updatedPerson.getModuleTags().canRemove(shouldRemain));
        assertTrue(updatedPerson.getModuleTags().containsKey("CS2101"));
    }

    @Test
    void execute_removeMultipleLessons_success() {
        ModuleTag partialLesson = new ModuleTagBuilder()
                .withModuleCode("CS2101")
                .withLessons(CS2101_MON_8AM_2HR)
                .build();
        ModuleTag shouldRemain = new ModuleTagBuilder()
                .withModuleCode("CS2101")
                .withLessons(CS2101_THU_8AM_2HR)
                .build();
        ModuleTag anotherPartialLesson = new ModuleTagBuilder()
                .withModuleCode("CS2106")
                .withLessons(CS2106_WED_10AM_2HR)
                .build();

        Set<ModuleTag> toRemove = Set.of(partialLesson, anotherPartialLesson);

        Person personToUpdate = getPersonFromIndexHandler(indexHandler, JINYONG);
        ContactIndex index = getContactIndexOfPerson(JINYONG);

        assertTrue(personToUpdate.getModuleTags().canRemove(partialLesson));
        assertFalse(personToUpdate.getImmutableModuleTags().contains(partialLesson));
        assertTrue(personToUpdate.getModuleTags().canRemove(shouldRemain));
        assertFalse(personToUpdate.getImmutableModuleTags().contains(shouldRemain));
        assertTrue(personToUpdate.getModuleTags().canRemove(anotherPartialLesson));
        assertFalse(personToUpdate.getImmutableModuleTags().contains(anotherPartialLesson));

        UntagCommand untagCommand = new UntagCommand(index, toRemove, TagType.MODULE);
        assertDoesNotThrow(() -> untagCommand.execute(model));

        Person updatedPerson = getPersonFromIndexHandler(indexHandler, JINYONG);

        assertFalse(updatedPerson.getModuleTags().canRemove(partialLesson));
        assertFalse(updatedPerson.getImmutableModuleTags().contains(partialLesson));
        assertTrue(updatedPerson.getModuleTags().containsKey("CS2106"));

        assertFalse(updatedPerson.getModuleTags().canRemove(anotherPartialLesson));
        assertFalse(updatedPerson.getImmutableModuleTags().contains(anotherPartialLesson));
        assertTrue(updatedPerson.getModuleTags().canRemove(shouldRemain));
        assertTrue(updatedPerson.getImmutableModuleTags().contains(shouldRemain));
        assertTrue(updatedPerson.getModuleTags().containsKey("CS2101"));
    }

    @Test
    void execute_removeBasicTag_success() {
        ModuleTag tagToRemove = new ModuleTagBuilder()
                .withModuleCode("CS2105")
                .withLessons()
                .build();

        Set<ModuleTag> toRemove = Set.of(tagToRemove);
        Person personToUpdate = getPersonFromIndexHandler(indexHandler, JINYONG);
        ContactIndex index = getContactIndexOfPerson(JINYONG);

        assertTrue(personToUpdate.getModuleTags().canRemove(tagToRemove));
        assertTrue(personToUpdate.getModuleTags().containsKey("CS2105"));
        assertFalse(personToUpdate.getImmutableModuleTags().contains(tagToRemove));

        UntagCommand untagCommand = new UntagCommand(index, toRemove, TagType.MODULE);
        assertDoesNotThrow(() -> untagCommand.execute(model));

        Person updatedPerson = getPersonFromIndexHandler(indexHandler, JINYONG);

        assertFalse(updatedPerson.getModuleTags().canRemove(tagToRemove));
        assertFalse(updatedPerson.getModuleTags().containsKey("CS2105"));
        assertFalse(updatedPerson.getImmutableModuleTags().contains(tagToRemove));
    }

    @Test
    void execute_removeBasicTagAndRegular_success() {
        ModuleTag tagToRemove = new ModuleTagBuilder()
                .withModuleCode("CS2105")
                .withLessons()
                .build();
        ModuleTag partialLessonRemove = new ModuleTagBuilder()
                .withModuleCode("CS2105")
                .withLessons(CS2105_THU_4PM_2HR)
                .build();

        Set<ModuleTag> toRemove = Set.of(tagToRemove, partialLessonRemove);
        Person personToUpdate = getPersonFromIndexHandler(indexHandler, JINYONG);
        ContactIndex index = getContactIndexOfPerson(JINYONG);

        assertTrue(personToUpdate.getModuleTags().canRemove(tagToRemove));
        assertTrue(personToUpdate.getModuleTags().canRemove(partialLessonRemove));
        assertTrue(personToUpdate.getModuleTags().containsKey("CS2105"));
        assertFalse(personToUpdate.getImmutableModuleTags().contains(tagToRemove));
        assertFalse(personToUpdate.getImmutableModuleTags().contains(partialLessonRemove));

        UntagCommand untagCommand = new UntagCommand(index, toRemove, TagType.MODULE);
        assertDoesNotThrow(() -> untagCommand.execute(model));

        Person updatedPerson = getPersonFromIndexHandler(indexHandler, JINYONG);

        assertFalse(updatedPerson.getModuleTags().canRemove(tagToRemove));
        assertFalse(updatedPerson.getModuleTags().containsKey("CS2105"));
        assertFalse(updatedPerson.getModuleTags().canRemove(partialLessonRemove));
        assertFalse(updatedPerson.getImmutableModuleTags().contains(tagToRemove));
    }

    @Test
    void execute_removeStandardModuleTags_success() {
        ModuleTag removeOneLesson = new ModuleTagBuilder()
                .withModuleCode("MA3252")
                .withLessons(MA3252_WED_10AM_1HR)
                .build();
        ModuleTag shouldRemainOne = new ModuleTagBuilder()
                .withModuleCode("MA3252")
                .withLessons(MA3252_FRI_7PM_3HR)
                .build();

        ModuleTag removeMultipleLessons = new ModuleTagBuilder()
                .withModuleCode("MA2104")
                .withLessons(MA2104_FRI_12PM_2HR, MA2104_TUE_12PM_2HR)
                .build();
        ModuleTag shouldRemainMultiple = new ModuleTagBuilder()
                .withModuleCode("MA2104")
                .withLessons(MA2104_WED_12PM_1HR)
                .build();

        ModuleTag removeBasic = new ModuleTagBuilder()
                .withModuleCode("CFG1002")
                .withLessons()
                .build();

        Set<ModuleTag> toRemove = Set.of(removeOneLesson,
                removeMultipleLessons, removeBasic, CS2109S_HA);
        Person personToUpdate = getPersonFromIndexHandler(indexHandler, HALF);
        ContactIndex index = getContactIndexOfPerson(HALF);

        assertTrue(personToUpdate.getModuleTags().containsKey("MA2104"));
        assertTrue(personToUpdate.getModuleTags().containsKey("MA3252"));
        assertTrue(personToUpdate.getModuleTags().containsKey("CFG1002"));
        assertTrue(personToUpdate.getModuleTags().containsKey("CS2109S"));

        assertTrue(personToUpdate.getModuleTags().canRemove(removeOneLesson));
        assertTrue(personToUpdate.getModuleTags().canRemove(removeMultipleLessons));
        assertTrue(personToUpdate.getModuleTags().canRemove(removeBasic));
        assertTrue(personToUpdate.getModuleTags().canRemove(CS2109S_HA));

        assertTrue(personToUpdate.getImmutableModuleTags().contains(removeBasic));
        assertFalse(personToUpdate.getImmutableModuleTags().contains(removeMultipleLessons));
        assertFalse(personToUpdate.getImmutableModuleTags().contains(removeOneLesson));
        assertTrue(personToUpdate.getImmutableModuleTags().contains(CS2109S_HA));

        UntagCommand untagCommand = new UntagCommand(index, toRemove, TagType.MODULE);
        assertDoesNotThrow(() -> untagCommand.execute(model));

        Person updatedPerson = getPersonFromIndexHandler(indexHandler, HALF);

        assertTrue(updatedPerson.getModuleTags().containsKey("MA2104"));
        assertTrue(updatedPerson.getModuleTags().containsKey("MA3252"));
        assertFalse(updatedPerson.getModuleTags().containsKey("CFG1002"));
        assertFalse(updatedPerson.getModuleTags().containsKey("CS2109S"));

        assertFalse(updatedPerson.getImmutableModuleTags().contains(removeBasic));
        assertFalse(updatedPerson.getImmutableModuleTags().contains(removeMultipleLessons));
        assertFalse(updatedPerson.getImmutableModuleTags().contains(removeOneLesson));
        assertFalse(updatedPerson.getImmutableModuleTags().contains(CS2109S_HA));

        assertFalse(updatedPerson.getModuleTags().canRemove(removeBasic));
        assertFalse(updatedPerson.getModuleTags().canRemove(removeOneLesson));
        assertTrue(updatedPerson.getModuleTags().canRemove(shouldRemainOne));
        assertFalse(updatedPerson.getModuleTags().canRemove(removeMultipleLessons));
        assertTrue(updatedPerson.getModuleTags().canRemove(shouldRemainMultiple));
        assertFalse(updatedPerson.getModuleTags().canRemove(CS2109S_HA));
    }

    @Test
    public void execute_removeGroupFromKevin() throws CommandException {
        Set<GroupTag> groupToRemove = new HashSet<>() {{
                add(new GroupTag("NUS"));
            }};

        ContactIndex index = getContactIndexOfPerson(KEVIN);

        UntagCommand untag = new UntagCommand(index, groupToRemove, TagType.GROUP);
        untag.execute(model);

        Person person = indexHandler.getPersonByIndex(index).orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));

        Set<GroupTag> groups = new HashSet<>();

        assertEquals(person.getImmutableGroupTags(), groups);

        TagCommand tag = new TagCommand(index, groupToRemove, TagType.GROUP);
        tag.execute(model);
    }

}
