package seedu.recipe.ui.events;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Represents an event that indicates a request to edit a recipe.
 * This event is used to communicate between the UI components when
 * a user wants to edit a recipe in the recipe list.
 */
public class EditRecipeEvent extends Event {
    /**
     * The event type for editing a recipe.
     */
    public static final EventType<EditRecipeEvent> EDIT_RECIPE_EVENT_TYPE = new EventType<>(Event.ANY, "EDIT_RECIPE");

    private final String commandText;

    /**
     * Constructs an EditRecipeEvent with the EDIT_RECIPE_EVENT_TYPE,
     * specified recipe index, and the map of changed values.
     *
     * @param commandText String representation of the Edit command to be executed.
     */
    public EditRecipeEvent(String commandText) {
        super(EDIT_RECIPE_EVENT_TYPE);
        this.commandText = commandText;
    }

    public String getCommandText() {
        return commandText;
    }

}
