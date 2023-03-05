package seedu.recipe.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

import seedu.recipe.commons.core.index.Index;
import seedu.recipe.commons.util.StringUtil;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.model.recipe.Description;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Title;
import seedu.recipe.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDesc = description.trim();
        if (!Description.isValidDesc(trimmedDesc)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDesc);
    }

    /**
     * Parses a {@code String step} into a {@code Step}. Parses each ingredient that is separated by a whitespace.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code step} is invalid.
     */
    public static ArrayList<Step> parseSteps(String steps) throws ParseException {
        requireNonNull(steps);
        String trimmedSteps = steps.trim();
        if (!Step.isValidStep(trimmedSteps)) {
            throw new ParseException(Step.MESSAGE_CONSTRAINTS);
        }
        ArrayList<Step> listOfSteps = new ArrayList<>();
        String[] tempSteps = trimmedSteps.split(",");
        for (int i = 0; i < tempSteps.length; i++) {
            Step currentStep = parseStepHelper(tempSteps[i]);
            listOfSteps.add(currentStep);
        }
        return listOfSteps;
    }

    public static Step parseStepHelper(String step) throws ParseException {
        requireNonNull(step);
        String trimmedStep = step.trim();
        if (!Tag.isValidTagName(trimmedStep)) {
            throw new ParseException(Step.MESSAGE_CONSTRAINTS);
        }
        return new Step(trimmedStep);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static ArrayList<Ingredient> parseIngredients(String ingredients) throws ParseException {
        requireNonNull(ingredients);
        String trimmedIngredients = ingredients.trim();
        if (!Ingredient.isValidIngredient(trimmedIngredients)) {
            throw new ParseException(Ingredient.MESSAGE_CONSTRAINTS);
        }
        ArrayList<Ingredient> listOfIngredients = new ArrayList<>();
        String[] tempIngredients = trimmedIngredients.split(",");
        for (int i = 0; i < tempIngredients.length; i++) {
            Ingredient currentIngredient = parseIngredientHelper(tempIngredients[i]);
            listOfIngredients.add(currentIngredient);
        }
        return listOfIngredients;
    }

    public static Ingredient parseIngredientHelper(String ingredient) throws ParseException {
        requireNonNull(ingredient);
        String trimmedIngredient = ingredient.trim();
        if (!Ingredient.isValidIngredient(trimmedIngredient)) {
            throw new ParseException(Ingredient.MESSAGE_CONSTRAINTS);
        }
        return new Ingredient(trimmedIngredient);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Title parseTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();
        if (!Title.isValidTitle(trimmedTitle)) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(trimmedTitle);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
}
