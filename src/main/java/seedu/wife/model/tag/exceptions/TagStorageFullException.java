package seedu.wife.model.tag.exceptions;

public class TagStorageFullException extends RuntimeException {

  public TagStorageFullException() {
    super(
      "Tag storage is full, please remove at existing tags in storage."
    );
  }
}
