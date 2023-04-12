package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Region.Regions;
import seedu.address.testutil.PersonBuilder;

public class PersonLivesInRegionPredicateTest {

    private Person someGuyWhoLivesInTheNorth = new PersonBuilder().withAddress("JTC 22 Woodlands Link #02-61").build();
    private Person anotherGuyWhoLivesInTheNorth = new PersonBuilder().withAddress("30 Kranji Way ").build();
    private Person someWestGuy = new PersonBuilder()
            .withAddress("Lim Chu Kang Community Centre, Lim Chu Kang Road, 580, Singapore, Lim Chu Kang")
            .build();

    // Clementi is in west of singaproe
    private Person clementiGuy = new PersonBuilder().withAddress("321 Clementi Ave 3, Singapore 129905").build();

    @Test
    public void test_validAddressMatchesRegion_returnsTrue() {
        PersonLivesInRegionPredicate northPredicate = new PersonLivesInRegionPredicate(Regions.NORTH);
        assertTrue(northPredicate.test(someGuyWhoLivesInTheNorth));
        assertTrue(northPredicate.test(anotherGuyWhoLivesInTheNorth));
        PersonLivesInRegionPredicate westPredicate = new PersonLivesInRegionPredicate(Regions.WEST);
        assertTrue(westPredicate.test(clementiGuy));
    }

    @Test
    public void test_validAddressDoesNotMatchRegion_returnsFalse() {
        PersonLivesInRegionPredicate southPredicate = new PersonLivesInRegionPredicate(Regions.SOUTH);
        assertFalse(southPredicate.test(someGuyWhoLivesInTheNorth));
        assertFalse(southPredicate.test(anotherGuyWhoLivesInTheNorth));
        assertFalse(southPredicate.test(someWestGuy));
        assertFalse(southPredicate.test(clementiGuy));
    }
}
