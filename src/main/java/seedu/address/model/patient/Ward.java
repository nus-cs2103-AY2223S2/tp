package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's ward in MedInfo.
 */
public class Ward {

  public static final String MESSAGE_CONSTRAINTS = "Wards should only contain alphanumeric characters and spaces, and it should not be blank";

  /*
   * The first character of the ward must not be a whitespace,
   * otherwise " " (a blank string) becomes a valid input.
   */
  public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

  public final String value;

  /**
   * Constructs a {@code Ward}.
   *
   * @param ward A valid ward.
   */
  public Ward(String ward) {
    requireNonNull(ward);
    checkArgument(isValidWard(ward), MESSAGE_CONSTRAINTS);
    value = ward;
  }

  /**
   * Returns true if a given string is a valid ward.
   */
  public static boolean isValidWard(String test) {
    return test.matches(VALIDATION_REGEX);
  }

  @Override
  public String toString() {
    return value;
  }

  @Override
  public boolean equals(Object other) {
    return other == this // short circuit if same object
        || (other instanceof Ward // instanceof handles nulls
            && value.equals(((Ward) other).value)); // state check
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

}
