package seedu.dengue.model;

import java.nio.file.Path;

import seedu.dengue.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getDengueHotspotTrackerFilePath();

}
