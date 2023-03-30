package seedu.dengue.model.predicate;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Postal;
import seedu.dengue.model.person.SubPostal;
import seedu.dengue.model.variant.Variant;

/**
 * Contains utility methods used for checking string validity in the various Predicate classes.
 */
public abstract class PredicateUtil<T> implements Predicate<T> {

    /**
     * Checks for whether the {@code String name} is valid.
     * Leading and trailing whitespaces will be trimmed from the {@code String name}.
     */
    public static boolean isNameValid(String name) {
        requireNonNull(name);
        String trimmedName = name.trim();
        return Name.isValidName(trimmedName);
    }

    /**
     * Checks for whether the {@code String postal} is valid.
     * Leading and trailing whitespaces will be trimmed from the {@code String postal}.
     */
    public static boolean isPostalValid(String postal) {
        requireNonNull(postal);
        String trimmedPostal = postal.trim();
        return Postal.isValidPostal(trimmedPostal);
    }

    /**
     * Checks for whether the {@code String subPostal} is valid.
     * Leading and trailing whitespaces will be trimmed from the {@code String subPostal}.
     */
    public static boolean isSubPostalValid(String subPostal) {
        requireNonNull(subPostal);
        String trimmedSubPostal = subPostal.trim();
        return SubPostal.isValidSubPostal(trimmedSubPostal);
    }

    /**
     * Checks for whether the {@code String age} is valid.
     * Leading and trailing whitespaces will be trimmed from the {@code String age}.
     */
    public static boolean isAgeValid(String age) {
        requireNonNull(age);
        String trimmedAge = age.trim();
        return Age.isValidAge(trimmedAge);
    }

    /**
     * Checks for whether the {@code String date} is valid.
     * Leading and trailing whitespaces will be trimmed from the {@code String date}.
     */
    public static boolean isDateValid(String date) {
        requireNonNull(date);
        String trimmedDate = date.trim();
        return Date.isValidDate(trimmedDate);
    }

    /**
     * Checks for whether the {@code String variant} is valid.
     * Leading and trailing whitespaces will be trimmed from the {@code String variant}.
     */
    public static boolean isVariantValid(String variant) {
        requireNonNull(variant);
        String trimmedVariant = variant.trim();
        return Variant.isValidVariantName(trimmedVariant);
    }

    /**
     * Composes all predicates with "and".
     * @param predicates Predicates to be composed.
     * @param <R> Any data-type.
     * @return A new predicate.
     */
    public static <R> Predicate<R> andAll(Predicate<R>... predicates) {
        Predicate<R> initial = x -> true;
        for (Predicate<R> predicate : predicates) {
            initial = initial.and(predicate);
        }
        return initial;
    }

    /**
     * Tests the predicate against the input.
     * @param thing the input argument.
     * @return A boolean value.
     */
    @Override
    public abstract boolean test(T thing);
}
