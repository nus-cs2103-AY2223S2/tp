package seedu.recipe.model.recipe;

import seedu.recipe.model.recipe.unit.TimeUnit;

public class RecipeDuration {

    private final double time;
    private final TimeUnit duration;

    public RecipeDuration(double time, TimeUnit duration){
        this.time = time;
        this.duration = duration;
    }
}
