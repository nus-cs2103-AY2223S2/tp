package seedu.address.model;

import java.util.Stack;

import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.navigation.NavigationContext;
import seedu.address.model.navigation.NavigationContext.NavLayer;

/**
 * The Navigation class allows the user to "navigate" to various contexts (i.e. modules, lectures).
 */
public class Navigation implements ReadOnlyNavigation {

    private final Stack<NavigationContext> contextStack;

    /**
     * Initializes a Navigation with root context.
     */
    public Navigation() {
        contextStack = new Stack<NavigationContext>();
        contextStack.push(new NavigationContext());
    }

    /**
     * Returns the current navigation context.
     * @return current navigation context
     */
    @Override
    public NavigationContext getCurrentContext() {
        return contextStack.peek();
    }

    /**
     * Navigates to the root context.
     */
    public void goToRoot() {
        while (getCurrentLayerId() > NavLayer.ROOT.getLayerId()) {
            back();
        }
    }

    /**
     * Navigates to the previous context.
     */
    public void back() {
        // At root.
        if (getCurrentLayerId() <= NavLayer.ROOT.getLayerId()) {
            return;
        }
        contextStack.pop();
    }

    /**
     * Navigates to a specified module of {@code moduleCode}.
     */
    public void navigateTo(ModuleCode moduleCode) {

        goToRoot();
        navigateToModFromRoot(moduleCode);
    }

    /**
     * Navigates to a specified lecture of {@code lectureName} belonging to a module of {@code moduleCode}.
     */
    public void navigateTo(ModuleCode moduleCode, LectureName lectureName) {

        navigateTo(moduleCode);
        navigateToLecFromMod(lectureName);
    }

    /**
     * Navigates to a specified module of {@code moduleCode} only if the current context is root.
     */
    public void navigateToModFromRoot(ModuleCode moduleCode) {

        if (!isAtLayer(NavLayer.ROOT)) {
            return;
        }

        contextStack.push(getCurrentContext().addModule(moduleCode));
    }

    /**
     * Navigates to a specified lecture of {@code lectureName} only if the current context is some module.
     */
    public void navigateToLecFromMod(LectureName lectureName) {

        if (!isAtLayer(NavLayer.MODULE)) {
            return;
        }

        contextStack.push(getCurrentContext().addLecture(lectureName));
    }

    /**
     * Returns true if index of the current layer of the context matches {@code layer}. Otherwise, returns false.
     * @param layer
     * @return true if index of the current layer of the context matches {@code layer}, otherwise false.
     */
    public boolean isAtLayer(NavLayer layer) {
        return contextStack.size() == layer.getLayerId();
    }

    public NavLayer getCurrentLayer() {
        return NavLayer.values()[getCurrentLayerId()];
    }

    public int getCurrentLayerId() {
        if (contextStack.size() < NavLayer.LOWEST_LAYER_ID || contextStack.size() > NavLayer.HIGHEST_LAYER_ID) {
            return 0;
        }
        return contextStack.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Navigation)) {
            return false;
        }

        Navigation other = (Navigation) obj;
        return contextStack.equals(other.contextStack);
    }

}
