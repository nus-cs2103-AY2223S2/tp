package seedu.recipe.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalRecipes.CORNDOGS;
import static seedu.recipe.testutil.TypicalRecipes.SOUP;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.commons.util.JsonUtil;
import seedu.recipe.model.RecipeBook;
import seedu.recipe.testutil.TypicalRecipes;

public class JsonSerializableRecipeBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableRecipeBookTest");
    private static final Path TYPICAL_RECIPES_FILE = TEST_DATA_FOLDER.resolve("typicalRecipesRecipeBook.json");
    private static final Path INVALID_RECIPE_FILE = TEST_DATA_FOLDER.resolve("invalidRecipeRecipeBook.json");
    private static final Path DUPLICATE_RECIPE_FILE = TEST_DATA_FOLDER.resolve("duplicateRecipeRecipeBook.json");

    @Test
    public void toModelType_typicalRecipesFile_success() throws Exception {
        JsonSerializableRecipeBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_RECIPES_FILE,
                JsonSerializableRecipeBook.class).get();
        RecipeBook recipeBookFromFile = dataFromFile.toModelType();
        RecipeBook typicalRecipesRecipeBook = TypicalRecipes.getTypicalRecipeBook();
        assertEquals(recipeBookFromFile, typicalRecipesRecipeBook);
    }

    @Test
    public void toModelType_invalidRecipeFile_throwsIllegalValueException() throws Exception {
        JsonSerializableRecipeBook dataFromFile = JsonUtil.readJsonFile(INVALID_RECIPE_FILE,
                JsonSerializableRecipeBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateRecipes_throwsIllegalValueException() throws Exception {
        JsonSerializableRecipeBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_RECIPE_FILE,
                JsonSerializableRecipeBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableRecipeBook.MESSAGE_DUPLICATE_RECIPE,
                dataFromFile::toModelType);
    }

}
