package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.person.information.Region.Place;
import seedu.address.testutil.ElderlyBuilder;
import seedu.address.testutil.VolunteerBuilder;

class RegionIsEqualPredicateTest {
    @Test
    public void equals() {
        RegionIsEqualPredicate<Person> firstPredicate = new RegionIsEqualPredicate<>(Place.CENTRAL);
        RegionIsEqualPredicate<Person> secondPredicate = new RegionIsEqualPredicate<>(Place.NORTH);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RegionIsEqualPredicate<Person> firstPredicateCopy = new RegionIsEqualPredicate<>(Place.CENTRAL);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different region -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_regionIsEqual_returnsTrue() {
        // same case
        RegionIsEqualPredicate<Person> predicate = new RegionIsEqualPredicate<>(Place.CENTRAL);
        assertTrue(predicate.test(new ElderlyBuilder().withRegion("CENTRAL").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withRegion("CENTRAL").build()));

        // lower case
        assertTrue(predicate.test(new ElderlyBuilder().withRegion("central").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withRegion("central").build()));

        // mix case
        assertTrue(predicate.test(new ElderlyBuilder().withRegion("CenTRAl").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withRegion("CenTRAl").build()));
    }

    @Test
    public void test_regionIsNotEqual_returnsFalse() {
        // Non-matching region
        RegionIsEqualPredicate<Person> predicate = new RegionIsEqualPredicate<>(Place.CENTRAL);
        assertFalse(predicate.test(new ElderlyBuilder().withRegion("NORTH").build()));
        assertFalse(predicate.test(new ElderlyBuilder().withRegion("NORTHEAST").build()));
        assertFalse(predicate.test(new ElderlyBuilder().withRegion("EAST").build()));
        assertFalse(predicate.test(new ElderlyBuilder().withRegion("WEST").build()));
        assertFalse(predicate.test(new VolunteerBuilder().withRegion("NORTH").build()));
        assertFalse(predicate.test(new VolunteerBuilder().withRegion("NORTHEAST").build()));
        assertFalse(predicate.test(new VolunteerBuilder().withRegion("EAST").build()));
        assertFalse(predicate.test(new VolunteerBuilder().withRegion("WEST").build()));
    }
}
