package seedu.address.model.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import seedu.address.model.lecture.Lecture;
import seedu.address.model.module.Module;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;

public class NavigationContextTest {

    @Test
    void constructor() {
        NavigationContext context = new NavigationContext();
        assertNull(context.getModuleCode());
        assertNull(context.getLectureName());
    }

    @Test
    void addModule() {
        Module mod = TypicalModules.CS2040S;
        NavigationContext context = new NavigationContext();
        context = context.addModule(mod.getCode());

        assertNull(context.getLectureName());
        assertEquals(mod.getCode(), context.getModuleCode());
    }

    @Test
    void addLecture() {
        Module mod = TypicalModules.CS2040S;
        Lecture lec = TypicalLectures.CS2040S_WEEK_1;

        NavigationContext context = new NavigationContext();

        context = context.addModule(mod.getCode());
        context = context.addLecture(lec.getName());

        assertEquals(mod.getCode(), context.getModuleCode());
        assertEquals(lec.getName(), context.getLectureName());
    }
}
