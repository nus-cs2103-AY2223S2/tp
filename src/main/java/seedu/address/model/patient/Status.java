package seedu.address.model.patient;

public enum Status {
  /**
   * The colour codes below reflect the severity of a patient's condition
   * and the urgency of treatment needed.
   * 
   * GRAY: Unknown condition, waiting for evaluation
   * GREEN: Non-urgent, re-evaluation every 180 min
   * YELLOW: Potentially unstable, re-evaluation every 60 min
   * RED: Requires immediate evaluation by physician
   */

  GRAY,
  GREEN,
  YELLOW,
  RED;
}
