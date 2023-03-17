package seedu.recipe.model.recipe;

import seedu.recipe.model.recipe.exceptions.RecipeDurationInvalidArgumentLengthException;
import seedu.recipe.model.recipe.exceptions.RecipeDurationInvalidDurationException;
import seedu.recipe.model.recipe.unit.TimeUnit;

import java.util.Objects;

public class RecipeDuration {

    public static final String MESSAGE_CONSTRAINTS =
            "A Recipe Duration should consist of a numeric/decimal portion and an alphanumeric time unit";
    private static final String VALIDATION_REGEX =
            "^\\d+(.\\d+)?\\s+[A-Za-z]*$";
    private final double time;
    private final TimeUnit timeUnit;

    public RecipeDuration(double time, TimeUnit timeUnit) {
        this.time = time;
        this.timeUnit = timeUnit;
    }

    public static boolean isValidRecipeDuration(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            String[] tokens = test.split("\\s+");
            if (tokens.length < 2) {
                return false;
            }
            try {
                double timeAmount = Double.parseDouble(tokens[0]);
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static RecipeDuration of(String duration) {
        String[] tokens = duration.split("\\s+");
        if (tokens.length < 2) {
            throw new RecipeDurationInvalidArgumentLengthException();
        }
        try {
            double timeAmount = Double.parseDouble(tokens[0]);
            return new RecipeDuration(timeAmount, new TimeUnit(tokens[1]));
        } catch (NumberFormatException e) {
            throw new RecipeDurationInvalidDurationException(tokens[0]);
        }

    }

    @Override
    public String toString() {
        return String.format("%s %s", this.time, this.timeUnit.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, timeUnit);
    }

    public double getTime() {
        return time;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
