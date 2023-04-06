package seedu.address.model.entity.shop.exception;

import java.time.LocalDate;

/**
 * Thrown when estimated finish date is before entry date
 */
public class InvalidDateException extends Exception {
    public InvalidDateException(LocalDate estimatedFinishDate, LocalDate entryDate) {
        super(String.format("Estimated finish date %s is before entry date %s", estimatedFinishDate, entryDate));
    }
}
