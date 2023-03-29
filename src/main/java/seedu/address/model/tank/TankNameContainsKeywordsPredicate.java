package seedu.address.model.tank;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Tank}'s {@code Name} matches any of the keywords given.
 */
public class TankNameContainsKeywordsPredicate implements Predicate<Tank> {
    private final List<String> keywords;

    public TankNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Tank tank) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tank.getTankName().fullTankName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TankNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TankNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
