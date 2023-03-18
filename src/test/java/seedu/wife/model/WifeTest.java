package seedu.wife.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_DAIRY;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_UNIT_MEIJI;
import static seedu.wife.testutil.Assert.assertThrows;
import static seedu.wife.testutil.TypicalFood.MEIJI;
import static seedu.wife.testutil.TypicalFood.getTypicalWife;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.wife.model.food.Food;
import seedu.wife.model.food.exceptions.DuplicateFoodException;
import seedu.wife.model.tag.Tag;
import seedu.wife.testutil.FoodBuilder;
//import seedu.wife.testutil.WifeBuilder;

public class WifeTest {

    private final Wife wife = new Wife();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), wife.getFoodList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wife.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyWife_replacesData() {
        Wife newData = getTypicalWife();
        wife.resetData(newData);
        assertEquals(newData, wife);
    }

    @Test
    public void resetData_withDuplicateFood_throwsDuplicateFoodException() {
        // Two foods with the same identity fields
        Food editedMeiji = new FoodBuilder(MEIJI).withUnit(VALID_UNIT_MEIJI).withTags(VALID_TAG_DAIRY)
                .build();
        List<Food> newFood = Arrays.asList(MEIJI, editedMeiji);
        WifeStub newData = new WifeStub(newFood);

        assertThrows(DuplicateFoodException.class, () -> wife.resetData(newData));
    }

    @Test
    public void hasFood_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wife.hasFood(null));
    }

    @Test
    public void hasFood_foodNotInWife_returnsFalse() {
        assertFalse(wife.hasFood(MEIJI));
    }

    @Test
    public void hasFood_foodInWife_returnsTrue() {
        wife.addFood(MEIJI);
        assertTrue(wife.hasFood(MEIJI));
    }

    @Test
    public void hasFood_foodWithSameIdentityFieldsInWife_returnsTrue() {
        wife.addFood(MEIJI);
        Food editedMeiji = new FoodBuilder(MEIJI).withUnit(VALID_UNIT_MEIJI).withTags(VALID_TAG_DAIRY)
                .build();
        assertTrue(wife.hasFood(editedMeiji));
    }

    @Test
    public void getFoodList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> wife.getFoodList().remove(0));
    }

    /**
     * A stub ReadOnlyWife whose foods list can violate interface constraints.
     */
    private static class WifeStub implements ReadOnlyWife {
        private final ObservableList<Food> foods = FXCollections.observableArrayList();
        private final ObservableList<Tag> tags = FXCollections.observableArrayList();

        WifeStub(Collection<Food> foods) {
            this.foods.setAll(foods);
        }

        @Override
        public ObservableList<Food> getFoodList() {
            return foods;
        }

        @Override
        public ObservableList<Tag> getTagList() {
            return tags;
        }
    }

}
