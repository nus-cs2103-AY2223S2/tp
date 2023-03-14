package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.model.person.Region.Regions;

public class PersonLivesInRegionPredicate implements Predicate<Person> {

    private Regions targetRegion;

    public PersonLivesInRegionPredicate(Regions targetRegions) {
        this.targetRegion = targetRegions;
    }

    @Override
    public boolean test(Person t) {
        return t.getAddress().getRegion().equals(targetRegion);
    }

}
