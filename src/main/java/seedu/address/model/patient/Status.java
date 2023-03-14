package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Status {
  /**
   * The colour codes below reflect the severity of a patient's condition
   * and the urgency of treatment needed.
   * 
   * GRAY: Unknown condition, waiting for evaluation
   * GREEN: Non-urgent, re-evaluation every 180 min
   * YELLOW: Potentially unstable, re-evaluation every 60 min
   * RED: Requires immediate evaluation by physician
   */

  public static String[] VALUES = { "GRAY", "GREEN", "YELLOW", "RED" };
  public static final String MESSAGE_CONSTRAINTS = "Statuses should only be 'GRAY', 'GREEN', 'YELLOW', or 'RED'";

  public final String value;

  /**
   * Constructs an {@code status}.
   *
   * @param status A valid status.
   */
  public Status(String status) {
    requireNonNull(status);
    checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
    value = status;
  }

  /**
   * Returns true if a given string is a valid status.
   */
  public static boolean isValidStatus(String test) {
    for (String value : VALUES) {
      if (test.equals(value)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return value;
  }
}
