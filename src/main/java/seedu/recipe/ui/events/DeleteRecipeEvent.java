package seedu.recipe.ui.events;

import javafx.event.Event;
import javafx.event.EventType;

public class DeleteRecipeEvent extends Event {
    public static final EventType<DeleteRecipeEvent> DELETE_RECIPE_EVENT_TYPE = new EventType<>(Event.ANY, "DELETE_RECIPE");

    private final int recipeIndex;

    public DeleteRecipeEvent(int recipeIndex) {
        super(DELETE_RECIPE_EVENT_TYPE);
        this.recipeIndex = recipeIndex;
    }

    public int getRecipeIndex() {
        return recipeIndex;
    }
}
