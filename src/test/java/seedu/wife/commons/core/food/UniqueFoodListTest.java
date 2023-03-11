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

    private final UniqueFoodList uniqueFoodList = new UniqueFoodList();

    @Test
    public void contains_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.contains(null));
    }

    @Test
    public void contains_foodNotInList_returnsFalse() {
        assertFalse(uniqueFoodList.contains(MEIJI));
    }

    @Test
    public void contains_foodInList_returnsTrue() {
        uniqueFoodList.add(MEIJI);
        assertTrue(uniqueFoodList.contains(MEIJI));
    }

    @Test
    public void contains_foodWithSameIdentityFieldsInList_returnsTrue() {
        uniqueFoodList.add(MEIJI);
        Food editedMeiji = new FoodBuilder(MEIJI)
                .withExpiryDate(VALID_EXPIRY_DATE_CHOCOLATE)
                .withTags(VALID_TAG_CHOCOLATE)
                .build();
        assertTrue(uniqueFoodList.contains(editedMeiji));
    }

    @Test
    public void add_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.add(null));
    }

    @Test
    public void add_duplicateFood_throwsDuplicateFoodException() {
        uniqueFoodList.add(MEIJI);
        assertThrows(DuplicateFoodException.class, () -> uniqueFoodList.add(MEIJI));
    }

    @Test
    public void setFood_nullTargetFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setFood(null, MEIJI));
    }

    @Test
    public void setFood_nullEditedFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setFood(MEIJI, null));
    }

    @Test
    public void setFood_targetFoodNotInList_throwsFoodNotFoundException() {
        assertThrows(FoodNotFoundException.class, () -> uniqueFoodList.setFood(MEIJI, MEIJI));
    }

    @Test
    public void setFood_editedFoodIsSameFood_success() {
        uniqueFoodList.add(MEIJI);
        uniqueFoodList.setFood(MEIJI, MEIJI);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(MEIJI);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFood_editedFoodHasSameIdentity_success() {
        uniqueFoodList.add(MEIJI);
        Food editedMeiji = new FoodBuilder(MEIJI)
                .withExpiryDate(VALID_EXPIRY_DATE_CHOCOLATE)
                .withTags(VALID_TAG_CHOCOLATE)
                .build();
        uniqueFoodList.setFood(MEIJI, editedMeiji);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(editedMeiji);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFood_editedFoodHasDifferentIdentity_success() {
        uniqueFoodList.add(MEIJI);
        uniqueFoodList.setFood(MEIJI, CHOCOLATE);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(CHOCOLATE);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFood_editedFoodHasNonUniqueIdentity_throwsDuplicateFoodException() {
        uniqueFoodList.add(MEIJI);
        uniqueFoodList.add(CHOCOLATE);
        assertThrows(DuplicateFoodException.class, () -> uniqueFoodList.setFood(MEIJI, CHOCOLATE));
    }

    @Test
    public void remove_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.remove(null));
    }

    @Test
    public void remove_foodDoesNotExist_throwsFoodNotFoundException() {
        assertThrows(FoodNotFoundException.class, () -> uniqueFoodList.remove(MEIJI));
    }

    @Test
    public void remove_existingFood_removesFood() {
        uniqueFoodList.add(MEIJI);
        uniqueFoodList.remove(MEIJI);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFoods_nullUniqueFoodList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setFoods((UniqueFoodList) null));
    }

    @Test
    public void setFoods_uniqueFoodList_replacesOwnListWithProvidedUniqueFoodList() {
        uniqueFoodList.add(MEIJI);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(CHOCOLATE);
        uniqueFoodList.setFoods(expectedUniqueFoodList);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFoods_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setFoods((List<Food>) null));
    }

    @Test
    public void setFoods_list_replacesOwnListWithProvidedList() {
        uniqueFoodList.add(MEIJI);
        List<Food> foodList = Collections.singletonList(CHOCOLATE);
        uniqueFoodList.setFoods(foodList);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(CHOCOLATE);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFoods_listWithDuplicateFoods_throwsDuplicateFoodException() {
        List<Food> listWithDuplicateFoods = Arrays.asList(MEIJI, MEIJI);
        assertThrows(DuplicateFoodException.class, () -> uniqueFoodList.setFoods(listWithDuplicateFoods));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueFoodList.asUnmodifiableObservableList().remove(0));
    }
}
