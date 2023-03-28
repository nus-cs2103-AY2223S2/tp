package seedu.address.model.person;

import java.util.Locale;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Mark {

    public static final String MESSAGE_CONSTRAINTS =
            "Mark should only be 'yes' or 'no', and it should not be blank.";

    public final boolean mark;

    public Mark(String mark) {
        requireNonNull(mark);
        checkArgument(!mark.toLowerCase(Locale.ROOT).equals("yes")
                && !mark.toLowerCase(Locale.ROOT).equals("no"), MESSAGE_CONSTRAINTS);
        this.mark = mark.toLowerCase(Locale.ROOT).equals("yes");
    }

    public static boolean isValidMark(String mark) {
        return mark.trim().toLowerCase(Locale.ROOT).equals("yes")
                || mark.trim().toLowerCase(Locale.ROOT).equals("no");
    }

    @Override
    public String toString() {
        return mark ? "Need Follow-Up." : "No Need Follow-Up.";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Mark // instanceof handles nulls
                && mark == ((Mark) other).mark); // state check
    }

}
