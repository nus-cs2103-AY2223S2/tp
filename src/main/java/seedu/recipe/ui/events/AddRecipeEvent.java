package seedu.recipe.ui.events;

import javafx.event.Event;
import javafx.event.EventType;

public class AddRecipeEvent extends Event {
    public static final EventType<AddRecipeEvent> ADD_RECIPE_EVENT_TYPE = new EventType<>(Event.ANY, "ADD_RECIPE");

    public AddRecipeEvent() {
        super(ADD_RECIPE_EVENT_TYPE);
    }
}