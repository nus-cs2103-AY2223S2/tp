package seedu.address.model;

import java.util.Stack;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.navigation.NavigationContext;

public class Navigation implements ReadOnlyNavigation {

    public static final int ROOT_LAYER = 1;
    public static final int MODULE_LAYER = 2;
    public static final int LECTURE_LAYER = 3;

    private final Stack<NavigationContext> contextStack;

    /**
     * Initializes a Navigation with root context.
     */
    public Navigation() {
        contextStack = new Stack<NavigationContext>();
        contextStack.push(new NavigationContext());
    }

    @Override
    public NavigationContext getCurrentContext() {
        return contextStack.peek();
    }

    public void goToRoot() {
        while (contextStack.size() > ROOT_LAYER) {
            back();
        }
    }

    public void back() {
        // At root.
        if (contextStack.size() <= ROOT_LAYER) {
            return;
        }
        contextStack.pop();
    }

    public void navigateTo(ModuleCode moduleCode) {

        goToRoot();
        navigateToModFromRoot(moduleCode);
    }

    public void navigateTo(ModuleCode moduleCode, LectureName lectureName) {

        navigateTo(moduleCode);
        navigateToLecFromMod(lectureName);
    }

    public void navigateToModFromRoot(ModuleCode moduleCode) {

        if (!isAtLayer(ROOT_LAYER)) {
            return;
        }

        contextStack.push(getCurrentContext().addModule(moduleCode));
    }

    public void navigateToLecFromMod(LectureName lectureName) {

        if (!isAtLayer(MODULE_LAYER)) {
            return;
        }

        contextStack.push(getCurrentContext().addLecture(lectureName));
    }

    public boolean isAtLayer(int layer) {
        return contextStack.size() == layer;
    }
}
