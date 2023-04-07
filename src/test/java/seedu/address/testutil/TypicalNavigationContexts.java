package seedu.address.testutil;

import seedu.address.model.navigation.NavigationContext;

/**
 * A utility class containing list of {@code NavigationContext} objects to be used in tests.
 */
public class TypicalNavigationContexts {

    public static final NavigationContext ROOT = new NavigationContext();

    public static final NavigationContext MODULE_CS2040S =
                    new NavigationContext().addModule(TypicalModules.getCs2040s().getCode());

    public static final NavigationContext MODULE_CS2107 =
                    new NavigationContext().addModule(TypicalModules.getCs2107().getCode());

    public static final NavigationContext MODULE_ST23234 =
                    new NavigationContext().addModule(TypicalModules.getSt2334().getCode());

    public static final NavigationContext LECTURE_CS2040S_WEEK_1 = new NavigationContext()
            .addModule(TypicalModules.getCs2040s().getCode()).addLecture(TypicalLectures.getCs2040sWeek1().getName());

    public static final NavigationContext LECTURE_CS2040S_WEEK_2 = new NavigationContext()
            .addModule(TypicalModules.getCs2040s().getCode()).addLecture(TypicalLectures.getCs2040sWeek2().getName());
}
