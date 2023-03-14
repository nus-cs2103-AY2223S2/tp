package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.model.person.Region.Regions;

/**
 * Tests that a {@code Person} lives in the given region
 */
public class PersonLivesInRegionPredicate implements Predicate<Person> {

    private Regions targetRegion;

    /**
     * Constructs a new predicate that tests for the target region <p>
     * Returned predicate will evaluate to {@code True} if person lives in {@code targetRegions}. False Otherwise.
     * @param targetRegions target region that predicate will return {@code True} for
     */
    public PersonLivesInRegionPredicate(Regions targetRegions) {
        this.targetRegion = targetRegions;
    }

    @Override
    public boolean test(Person t) {
        return t.getAddress().getRegion().equals(targetRegion);
    }
}
