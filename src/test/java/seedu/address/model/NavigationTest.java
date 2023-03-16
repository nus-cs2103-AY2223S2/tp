package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.module.Module;
import seedu.address.model.navigation.NavigationContext;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;

public class NavigationTest {

    private Navigation navigation = new Navigation();

    @Test
    public void constructor() {
        assertEquals(new NavigationContext(), navigation.getCurrentContext());
        assertTrue(navigation.isAtLayer(Navigation.ROOT_LAYER));
    }

    @Test
    void navigate1() {
        Module mod = TypicalModules.CS2040S;
        NavigationContext expectedContext = new NavigationContext().addModule(mod.getCode());

        navigation.navigateTo(mod.getCode());
        assertEquals(expectedContext, navigation.getCurrentContext());

        navigation.goToRoot();
        assertEquals(new NavigationContext(), navigation.getCurrentContext());
    }

    @Test
    void navigate2() {
        Module mod = TypicalModules.CS2040S;
        Lecture lec = TypicalLectures.CS2040S_WEEK_1;

        NavigationContext expectedContext =
                new NavigationContext().addModule(mod.getCode()).addLecture(lec.getName());

        navigation.navigateTo(mod.getCode(), lec.getName());
        assertEquals(expectedContext, navigation.getCurrentContext());

        navigation.goToRoot();
        assertEquals(new NavigationContext(), navigation.getCurrentContext());
    }


    @Test
    void testNavigateToModFromRoot() {
        Module mod = TypicalModules.CS2040S;

        NavigationContext expectedContext = new NavigationContext().addModule(mod.getCode());

        navigation.navigateToModFromRoot(mod.getCode());
        assertEquals(expectedContext, navigation.getCurrentContext());

        navigation.goToRoot();
        assertEquals(new NavigationContext(), navigation.getCurrentContext());
    }

    @Test
    void testNavigateToLecFromMod() {
        Module mod = TypicalModules.CS2040S;
        Lecture lec = TypicalLectures.CS2040S_WEEK_1;

        NavigationContext expectedContext =
                new NavigationContext().addModule(mod.getCode()).addLecture(lec.getName());

        navigation.navigateToModFromRoot(mod.getCode());
        navigation.navigateToLecFromMod(lec.getName());
        assertEquals(expectedContext, navigation.getCurrentContext());

        navigation.goToRoot();
        assertEquals(new NavigationContext(), navigation.getCurrentContext());
    }
}
