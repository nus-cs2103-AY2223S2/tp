package seedu.address.model.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import org.junit.jupiter.api.Test;

import seedu.address.model.lecture.Lecture;
import seedu.address.model.module.Module;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalNavigationContexts;

public class NavigationContextTest {

    @Test
    void constructor() {
        NavigationContext context = new NavigationContext();
        assertNull(context.getModuleCode());
        assertNull(context.getLectureName());
    }

    @Test
    void addModule_success() {
        Module mod = TypicalModules.getCs2040s();
        NavigationContext context = new NavigationContext();
        context = context.addModule(mod.getCode());

        assertNull(context.getLectureName());
        assertEquals(mod.getCode(), context.getModuleCode());
    }

    @Test
    void addModule_fromLectureContext_noChange() {
        NavigationContext expected = TypicalNavigationContexts.LECTURE_CS2040S_WEEK_1;
        assertEquals(expected, expected.addModule(TypicalModules.getCs2040s().getCode()));
    }

    @Test
    void addLecture_success() {
        Module mod = TypicalModules.getCs2040s();
        Lecture lec = TypicalLectures.getCs2040sWeek1();

        NavigationContext context = new NavigationContext();

        context = context.addModule(mod.getCode());
        context = context.addLecture(lec.getName());

        assertEquals(mod.getCode(), context.getModuleCode());
        assertEquals(lec.getName(), context.getLectureName());
    }


    @Test
    void addLecture_fromRootContext_noChange() {
        NavigationContext expected = TypicalNavigationContexts.ROOT;
        assertEquals(expected, expected.addLecture(TypicalLectures.getCs2040sWeek1().getName()));
    }

    @Test
    void equals_isSameContext_true() {
        NavigationContext context1 = new NavigationContext();
        NavigationContext context2 = new NavigationContext();
        assertTrue(context1.equals(context2));

        context1 = context1.addModule(TypicalModules.getCs2040s().getCode());
        context2 = context2.addModule(TypicalModules.getCs2040s().getCode());
        assertTrue(context1.equals(context2));

        context1 = context1.addLecture(TypicalLectures.getCs2040sWeek1().getName());
        context2 = context2.addLecture(TypicalLectures.getCs2040sWeek1().getName());
        assertTrue(context1.equals(context2));
    }

    @Test
    void equals_otherIsNotNavigationContext_false() {
        NavigationContext context = new NavigationContext();
        assertFalse(context.equals(TypicalModules.getCs2040s()));
    }

    @Test
    void equals_modulesNotEqual_false() {
        assertFalse(TypicalNavigationContexts.MODULE_CS2040S.equals(TypicalNavigationContexts.MODULE_CS2107));
    }

    @Test
    void equals_lecturesNotEqual_false() {
        assertFalse(TypicalNavigationContexts.LECTURE_CS2040S_WEEK_1
                .equals(TypicalNavigationContexts.LECTURE_CS2040S_WEEK_2));
    }

    @Test
    void equals_withModuleContextAndLectureContext_false() {
        assertFalse(TypicalNavigationContexts.LECTURE_CS2040S_WEEK_1.equals(TypicalNavigationContexts.MODULE_CS2040S));
    }

    @Test
    void equals_withRootContextAndModuleContext_false() {
        assertFalse(TypicalNavigationContexts.ROOT.equals(TypicalNavigationContexts.MODULE_CS2040S));
    }

    @Test
    void equals_withRootContextAndLectureContext_false() {
        assertFalse(TypicalNavigationContexts.ROOT.equals(TypicalNavigationContexts.LECTURE_CS2040S_WEEK_1));
    }

    @Test
    void getCommandPrefixes_withLectureContext() {
        String modCode = TypicalModules.getCs2040s().getCode().toString();
        String lecName = TypicalLectures.getCs2040sWeek1().getName().toString();
        String expectedPrefixes =
                String.join(" ", PREFIX_MODULE.toString(), modCode, PREFIX_LECTURE.toString(), lecName);

        assertEquals(expectedPrefixes, TypicalNavigationContexts.LECTURE_CS2040S_WEEK_1.getCommandPrefixes());
    }

    @Test
    void getCommandPrefixes_withRootContext() {
        assertEquals(NavigationContext.ROOT_LEVEL_NAME, new NavigationContext().getCommandPrefixes());
    }
}
