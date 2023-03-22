package seedu.recipe.ui.events;

import java.util.Map;

import javafx.event.Event;
import javafx.event.EventType;

public class EditRecipeEvent extends Event {
    public static final EventType<EditRecipeEvent> EDIT_RECIPE_EVENT_TYPE = new EventType<>(Event.ANY, "EDIT_RECIPE");

    private final int recipeIndex;

    private final Map<String, String> changedValues;

    public EditRecipeEvent(int recipeIndex, Map<String, String> changedValues) {
        super(EDIT_RECIPE_EVENT_TYPE);
        this.recipeIndex = recipeIndex;
        this.changedValues = changedValues;
    }

    public int getRecipeIndex() {
        return recipeIndex;
    }

    public Map<String, String> getChangedValues() {
        return changedValues;
    }
}
