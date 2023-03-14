package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Region.Regions;
import seedu.address.testutil.PersonBuilder;

public class PersonLivesInRegionPredicateTest {

    Person someGuyWhoLivesInTheNorth = new PersonBuilder().withAddress("JTC 22 Woodlands Link #02-61").build();
    Person anotherGuyWhoLivesInTheNorth = new PersonBuilder().withAddress("30 Kranji Way ").build();
    Person someNorthGuy = new PersonBuilder().withAddress("Lim Chu Kang Community Centre, Lim Chu Kang Road, 580, Singapore, Lim Chu Kang, Neo Tiew, Kranji, North: 719418").build();

    // Clementi is in west of singaproe
    Person clementiGuy = new PersonBuilder().withAddress("321 Clementi Ave 3, Singapore 129905").build();

    @Test
    public void test_validAddressMatchesRegion_returnsTrue() {
        PersonLivesInRegionPredicate northPredicate = new PersonLivesInRegionPredicate(Regions.NORTH);
        assertTrue(northPredicate.test(someGuyWhoLivesInTheNorth));
        assertTrue(northPredicate.test(anotherGuyWhoLivesInTheNorth));
        assertTrue(northPredicate.test(someNorthGuy));

        PersonLivesInRegionPredicate westPredicate = new PersonLivesInRegionPredicate(Regions.WEST);
        assertTrue(westPredicate.test(clementiGuy));
    }

    @Test
    public void test_validAddressDoesNotMatchRegion_returnsFalse() {
        PersonLivesInRegionPredicate southPredicate = new PersonLivesInRegionPredicate(Regions.SOUTH);
        assertFalse(southPredicate.test(someGuyWhoLivesInTheNorth));
        assertFalse(southPredicate.test(anotherGuyWhoLivesInTheNorth));
        assertFalse(southPredicate.test(someNorthGuy));
        assertFalse(southPredicate.test(clementiGuy));
    }
}
