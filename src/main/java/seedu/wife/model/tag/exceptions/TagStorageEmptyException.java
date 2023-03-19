package seedu.wife.model.tag.exceptions;

public class TagStorageEmptyException extends RuntimeException {

  /**
   * Constructor
   */
  public TagStorageEmptyException() {
    super("Tag storage is empty.");
  }
}
