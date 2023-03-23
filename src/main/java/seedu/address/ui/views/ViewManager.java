package seedu.address.ui.views;

import seedu.address.model.job.Role;
import seedu.address.ui.ResultDisplay;

import static java.util.Objects.requireNonNull;

public class ViewManager {
    private final ResultDisplay display;

    /**
     * UI Manager linked to the result display box. This lets us use the
     * result display box to display more dynamic views.
     *
     * @param display the display box to show the views at.
     */
    public ViewManager(ResultDisplay display) {
        this.display = display;
    }

    public void viewOf(Object object) {
        requireNonNull(object);
        display.clear();
        if (object instanceof String) {
            display.place(StringView.from((String) object));
            return;
        }
        if (object instanceof Role) {
            display.place(RoleDetailsView.from((Role) object));
            return;
        }
    }

}