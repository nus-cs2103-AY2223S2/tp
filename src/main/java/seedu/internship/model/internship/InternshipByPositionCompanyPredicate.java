package seedu.internship.model.internship;
import java.util.function.Predicate;


/**
 * Tests whether position and company of two internships are the same
 */
public class InternshipByPositionCompanyPredicate implements Predicate<Internship> {
    private final Position pos;
    private final Company cmp;

    /**
     * Constructor for the class
     * @param pos
     * @param cmp
     */
    public InternshipByPositionCompanyPredicate(Position pos, Company cmp) {
        this.pos = pos;
        this.cmp = cmp;
    }

    @Override
    public boolean test(Internship internship) {
        return internship.getPosition().equals(this.pos) && internship.getCompany().equals(this.cmp);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }
        if (other instanceof InternshipByPositionCompanyPredicate) { // instanceof handles nulls
            InternshipByPositionCompanyPredicate otherPred = (InternshipByPositionCompanyPredicate) other;
            return otherPred.pos.equals(this.pos) && otherPred.cmp.equals(this.cmp);
        }
        return false;
    }

}
