package seedu.address.testutil;

import seedu.address.model.navigation.NavigationContext;

/**
 * A utility class containing list of {@code NavigationContext} objects to be used in tests.
 */
public class TypicalNavigationContexts {

    public static final NavigationContext ROOT = new NavigationContext();

    public static final NavigationContext MODULE_CS2040S =
            new NavigationContext().addModule(TypicalModules.CS2040S.getCode());

    public static final NavigationContext MODULE_CS2107 =
            new NavigationContext().addModule(TypicalModules.CS2107.getCode());

    public static final NavigationContext MODULE_ST23234 =
            new NavigationContext().addModule(TypicalModules.ST2334.getCode());

    public static final NavigationContext LECTURE_CS2040S_WEEK_1 = new NavigationContext()
            .addModule(TypicalModules.CS2040S.getCode()).addLecture(TypicalLectures.CS2040S_WEEK_1.getName());
}
