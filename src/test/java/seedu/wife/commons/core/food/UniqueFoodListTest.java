package seedu.wife.commons.core.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_CHOCOLATE;
import static seedu.wife.testutil.Assert.assertThrows;
import static seedu.wife.testutil.TypicalFood.CHOCOLATE;
import static seedu.wife.testutil.TypicalFood.MEIJI;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.wife.model.food.Food;
import seedu.wife.model.food.UniqueFoodList;
import seedu.wife.model.food.exceptions.DuplicateFoodException;
import seedu.wife.model.food.exceptions.FoodNotFoundException;
import seedu.wife.testutil.FoodBuilder;

public class UniqueFoodListTest {

    private final UniqueFoodList UniqueFoodList = new UniqueFoodList();

    @Test
    public void contains_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueFoodList.contains(null));
    }

    @Test
    public void contains_FoodNotInList_returnsFalse() {
        assertFalse(UniqueFoodList.contains(MEIJI));
    }

    @Test
    public void contains_FoodInList_returnsTrue() {
        UniqueFoodList.add(MEIJI);
        assertTrue(UniqueFoodList.contains(MEIJI));
    }
    
    @Test
    public void contains_FoodWithSameIdentityFieldsInList_returnsTrue() {
        UniqueFoodList.add(MEIJI);
        Food editedMeiji = new FoodBuilder(MEIJI).withExpiryDate(VALID_EXPIRY_DATE_CHOCOLATE).withTags(VALID_TAG_CHOCOLATE)
                .build();
        assertTrue(UniqueFoodList.contains(editedMeiji));
    }

    @Test
    public void add_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueFoodList.add(null));
    }

    @Test
    public void add_duplicateFood_throwsDuplicateFoodException() {
        UniqueFoodList.add(MEIJI);
        assertThrows(DuplicateFoodException.class, () -> UniqueFoodList.add(MEIJI));
    }

    @Test
    public void setFood_nullTargetFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueFoodList.setFood(null, MEIJI));
    }

    @Test
    public void setFood_nullEditedFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueFoodList.setFood(MEIJI, null));
    }

    @Test
    public void setFood_targetFoodNotInList_throwsFoodNotFoundException() {
        assertThrows(FoodNotFoundException.class, () -> UniqueFoodList.setFood(MEIJI, MEIJI));
    }

    @Test
    public void setFood_editedFoodIsSameFood_success() {
        UniqueFoodList.add(MEIJI);
        UniqueFoodList.setFood(MEIJI, MEIJI);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(MEIJI);
        assertEquals(expectedUniqueFoodList, UniqueFoodList);
    }

    @Test
    public void setFood_editedFoodHasSameIdentity_success() {
        UniqueFoodList.add(MEIJI);
        Food editedMeiji = new FoodBuilder(MEIJI).withExpiryDate(VALID_EXPIRY_DATE_CHOCOLATE).withTags(VALID_TAG_CHOCOLATE)
                .build();
        UniqueFoodList.setFood(MEIJI, editedMeiji);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(editedMeiji);
        assertEquals(expectedUniqueFoodList, UniqueFoodList);
    }

    @Test
    public void setFood_editedFoodHasDifferentIdentity_success() {
        UniqueFoodList.add(MEIJI);
        UniqueFoodList.setFood(MEIJI, CHOCOLATE);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(CHOCOLATE);
        assertEquals(expectedUniqueFoodList, UniqueFoodList);
    }

    @Test
    public void setFood_editedFoodHasNonUniqueIdentity_throwsDuplicateFoodException() {
        UniqueFoodList.add(MEIJI);
        UniqueFoodList.add(CHOCOLATE);
        assertThrows(DuplicateFoodException.class, () -> UniqueFoodList.setFood(MEIJI, CHOCOLATE));
    }

    @Test
    public void remove_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueFoodList.remove(null));
    }

    @Test
    public void remove_FoodDoesNotExist_throwsFoodNotFoundException() {
        assertThrows(FoodNotFoundException.class, () -> UniqueFoodList.remove(MEIJI));
    }

    @Test
    public void remove_existingFood_removesFood() {
        UniqueFoodList.add(MEIJI);
        UniqueFoodList.remove(MEIJI);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        assertEquals(expectedUniqueFoodList, UniqueFoodList);
    }

    @Test
    public void setFoods_nullUniqueFoodList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueFoodList.setFoods((UniqueFoodList) null));
    }

    @Test
    public void setFoods_UniqueFoodList_replacesOwnListWithProvidedUniqueFoodList() {
        UniqueFoodList.add(MEIJI);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(CHOCOLATE);
        UniqueFoodList.setFoods(expectedUniqueFoodList);
        assertEquals(expectedUniqueFoodList, UniqueFoodList);
    }

    @Test
    public void setFoods_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueFoodList.setFoods((List<Food>) null));
    }

    @Test
    public void setFoods_list_replacesOwnListWithProvidedList() {
        UniqueFoodList.add(MEIJI);
        List<Food> foodList = Collections.singletonList(CHOCOLATE);
        UniqueFoodList.setFoods(foodList);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(CHOCOLATE);
        assertEquals(expectedUniqueFoodList, UniqueFoodList);
    }

    @Test
    public void setFoods_listWithDuplicateFoods_throwsDuplicateFoodException() {
        List<Food> listWithDuplicateFoods = Arrays.asList(MEIJI, MEIJI);
        assertThrows(DuplicateFoodException.class, () -> UniqueFoodList.setFoods(listWithDuplicateFoods));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> UniqueFoodList.asUnmodifiableObservableList().remove(0));
    }
}
