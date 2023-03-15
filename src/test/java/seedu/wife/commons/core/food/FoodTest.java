package seedu.wife.commons.core.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_NAME_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_QUANTITY_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_UNIT_CHOCOLATE;
import static seedu.wife.testutil.Assert.assertThrows;
import static seedu.wife.testutil.TypicalFood.CHOCOLATE;
import static seedu.wife.testutil.TypicalFood.MEIJI;

import org.junit.jupiter.api.Test;

import seedu.wife.model.food.Food;
import seedu.wife.testutil.FoodBuilder;

public class FoodTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Food food = new FoodBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> food.getTags().remove(0));
    }

    @Test
    public void isSameFood() {
        // same object -> returns true
        assertTrue(MEIJI.isSameFood(MEIJI));

        // null -> returns false
        assertFalse(MEIJI.isSameFood(null));

        // same name, all other attributes different -> returns true
        Food editedMeiji = new FoodBuilder(MEIJI).withUnit(VALID_UNIT_CHOCOLATE).withQuantity(VALID_QUANTITY_CHOCOLATE)
                .withExpiryDate(VALID_EXPIRY_DATE_CHOCOLATE).withTags(VALID_TAG_CHOCOLATE).build();
        assertTrue(MEIJI.isSameFood(editedMeiji));

        // different name, all other attributes same -> returns false
        editedMeiji = new FoodBuilder(MEIJI).withName(VALID_NAME_CHOCOLATE).build();
        assertFalse(MEIJI.isSameFood(editedMeiji));

        // name differs in case, all other attributes same -> returns false
        Food editedChocolate = new FoodBuilder(CHOCOLATE).withName(VALID_NAME_CHOCOLATE.toLowerCase()).build();
        assertFalse(CHOCOLATE.isSameFood(editedChocolate));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_CHOCOLATE + " ";
        editedChocolate = new FoodBuilder(CHOCOLATE).withName(nameWithTrailingSpaces).build();
        assertFalse(CHOCOLATE.isSameFood(editedChocolate));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Food meijiCopy = new FoodBuilder(MEIJI).build();
        assertTrue(MEIJI.equals(meijiCopy));

        // same object -> returns true
        assertTrue(MEIJI.equals(MEIJI));

        // null -> returns false
        assertFalse(MEIJI.equals(null));

        // different type -> returns false
        assertFalse(MEIJI.equals(5));

        // different person -> returns false
        assertFalse(MEIJI.equals(CHOCOLATE));

        // different name -> returns false
        Food editedMeiji = new FoodBuilder(MEIJI).withName(VALID_NAME_CHOCOLATE).build();
        assertFalse(MEIJI.equals(editedMeiji));

        // different Unit -> returns false
        editedMeiji = new FoodBuilder(MEIJI).withUnit(VALID_UNIT_CHOCOLATE).build();
        assertFalse(MEIJI.equals(editedMeiji));

        // different email -> returns false
        editedMeiji = new FoodBuilder(MEIJI).withQuantity(VALID_QUANTITY_CHOCOLATE).build();
        assertFalse(MEIJI.equals(editedMeiji));

        // different address -> returns false
        editedMeiji = new FoodBuilder(MEIJI).withExpiryDate(VALID_EXPIRY_DATE_CHOCOLATE).build();
        assertFalse(MEIJI.equals(editedMeiji));

        // different tags -> returns false
        editedMeiji = new FoodBuilder(MEIJI).withTags(VALID_TAG_CHOCOLATE).build();
        assertFalse(MEIJI.equals(editedMeiji));
    }
}
