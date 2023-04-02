package seedu.internship.model.internship;
import java.util.function.Predicate;


/**
 * Predicate filters to give you a List with one required internship.
 */
public class InternshipByPositionCompanyPredicate implements Predicate<Internship> {
    private final Position pos;
    private final Company cmp;

    /**
     * Intialises Predicate with position and company.
     * @param pos position of the internship
     * @param cmp company of the internship
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
