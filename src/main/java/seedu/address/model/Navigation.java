package seedu.address.model;

import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.navigation.NavigationContext;
import seedu.address.model.navigation.NavigationContext.NavLayer;

/**
 * The Navigation class allows the user to "navigate" to various contexts (i.e. modules, lectures).
 */
public interface Navigation extends ReadOnlyNavigation {

    /**
     * Returns the current navigation context.
     * @return current navigation context
     */
    @Override
    public NavigationContext getCurrentContext();

    /**
     * Navigates to the root context.
     */
    public void goToRoot();

    /**
     * Navigates to the previous context.
     */
    public void back();

    /**
     * Navigates to a specified module of {@code moduleCode}.
     */
    public void navigateTo(ModuleCode moduleCode);

    /**
     * Navigates to a specified lecture of {@code lectureName} belonging to a module of {@code moduleCode}.
     */
    public void navigateTo(ModuleCode moduleCode, LectureName lectureName);

    /**
     * Navigates to a specified module of {@code moduleCode} only if the current context is root.
     */
    public void navigateToModFromRoot(ModuleCode moduleCode);

    /**
     * Navigates to a specified lecture of {@code lectureName} only if the current context is some module.
     */
    public void navigateToLecFromMod(LectureName lectureName);

    /**
     * Returns true if index of the current layer of the context matches {@code layer}. Otherwise, returns false.
     * @param layer
     * @return true if index of the current layer of the context matches {@code layer}, otherwise false.
     */
    public boolean isAtLayer(NavLayer layer);

    /**
     * Gets layer of the current working context.
     *
     * @return layer of the current working context
     */
    public NavLayer getCurrentLayer();
}
