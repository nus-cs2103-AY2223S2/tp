package seedu.address.model;

import java.util.Stack;

import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.navigation.NavigationContext;
import seedu.address.model.navigation.NavigationContext.NavLayer;


/**
 * Represents a Navigation component that uses a stack implementation.
 */
public class NavigationStack implements Navigation {
    private final Stack<NavigationContext> contextStack;

    /**
     * Initializes a NavigationStack with root context.
     */
    public NavigationStack() {
        contextStack = new Stack<NavigationContext>();
        contextStack.push(new NavigationContext());
    }

    @Override
    public NavigationContext getCurrentContext() {
        return contextStack.peek();
    }

    @Override
    public void goToRoot() {
        while (getCurrentLayerId() > NavLayer.ROOT.getLayerId()) {
            back();
        }
    }

    @Override
    public void back() {
        // At root.
        if (getCurrentLayerId() <= NavLayer.ROOT.getLayerId()) {
            return;
        }
        contextStack.pop();
    }

    @Override
    public void navigateTo(ModuleCode moduleCode) {

        goToRoot();
        navigateToModFromRoot(moduleCode);
    }

    /**
     * Navigates to a specified lecture of {@code lectureName} belonging to a module of {@code moduleCode}.
     */
    @Override
    public void navigateTo(ModuleCode moduleCode, LectureName lectureName) {

        navigateTo(moduleCode);
        navigateToLecFromMod(lectureName);
    }

    /**
     * Navigates to a specified module of {@code moduleCode} only if the current context is root.
     */
    @Override
    public void navigateToModFromRoot(ModuleCode moduleCode) {

        if (!isAtLayer(NavLayer.ROOT)) {
            return;
        }

        contextStack.push(getCurrentContext().addModule(moduleCode));
    }

    /**
     * Navigates to a specified lecture of {@code lectureName} only if the current context is some module.
     */
    @Override
    public void navigateToLecFromMod(LectureName lectureName) {

        if (!isAtLayer(NavLayer.MODULE)) {
            return;
        }

        contextStack.push(getCurrentContext().addLecture(lectureName));
    }

    /**
     * Returns true if index of the current layer of the context matches {@code layer}. Otherwise, returns false.
     *
     * @param layer
     * @return true if index of the current layer of the context matches {@code layer}, otherwise false.
     */
    @Override
    public boolean isAtLayer(NavLayer layer) {
        return contextStack.size() == layer.getLayerId();
    }

    @Override
    public NavLayer getCurrentLayer() {
        return NavLayer.values()[getCurrentLayerId()];
    }

    private int getCurrentLayerId() {
        if (isContextStackSizeOutOfValidRange()) {
            return NavLayer.INVALID.getLayerId();
        }
        return contextStack.size();
    }

    private boolean isContextStackSizeOutOfValidRange() {
        return contextStack.size() < NavLayer.LOWEST_LAYER_ID || contextStack.size() > NavLayer.HIGHEST_LAYER_ID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof NavigationStack)) {
            return false;
        }

        NavigationStack other = (NavigationStack) obj;
        return contextStack.equals(other.contextStack);
    }

}
