package seedu.dengue.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.testutil.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.dengue.model.range.EndAge;
import seedu.dengue.model.range.EndDate;
import seedu.dengue.model.range.Range;
import seedu.dengue.model.range.StartAge;
import seedu.dengue.model.range.StartDate;

public class RangeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> ContinuousData.generateRange(null, null));
    }

    @Test
    public void constructor_invalidAgeRange_throwsIllegalArgumentException() {
        Age invalidStartAge = new Age("14");
        Age invalidEndAge = new Age("10");
        assertThrows(IllegalArgumentException.class, ()
                -> ContinuousData.generateRange(
                        new StartAge(Optional.of(invalidStartAge)),
                                new EndAge(Optional.of(invalidEndAge))));
    }

    @Test
    public void constructor_invalidDateRange_throwsIllegalArgumentException() {
        Date invalidStartDate = new Date("2011-11-11");
        Date invalidEndDate = new Date("2010-11-11");
        assertThrows(IllegalArgumentException.class, ()
                -> ContinuousData.generateRange(
                        new StartDate(Optional.of(invalidStartDate)),
                        new EndDate(Optional.of(invalidEndDate))));
    }

    @Test
    public void isValidAgeRange() {

        Age tempBoundaryStartAge = new Age("0");
        Age tempBoundaryEndAge = new Age("199");
        Age tempMiddleStartAge = new Age("15");
        Age tempMiddleEndAge = new Age("100");

        // null age
        assertThrows(NullPointerException.class, ()
                -> ContinuousData.generateRange(null, null));

        // invalid age
        StartAge invalidBoundaryStartAge = new StartAge(Optional.of(tempBoundaryEndAge));
        EndAge invalidBoundaryEndAge = new EndAge(Optional.of(tempBoundaryStartAge));
        StartAge invalidMiddleStartAge = new StartAge(Optional.of(tempMiddleEndAge));
        EndAge invalidMiddleEndAge = new EndAge(Optional.of(tempMiddleStartAge));

        assertFalse(Range.isValidRange(invalidBoundaryStartAge, invalidBoundaryEndAge));
        assertFalse(Range.isValidRange(invalidMiddleStartAge, invalidMiddleEndAge));

        // valid age ranges
        StartAge validBoundaryStartAge = new StartAge(Optional.of(tempBoundaryStartAge));
        EndAge validBoundaryEndAge = new EndAge(Optional.of(tempBoundaryEndAge));
        StartAge validMiddleStartAge = new StartAge(Optional.of(tempMiddleStartAge));
        EndAge validMiddleEndAge = new EndAge(Optional.of(tempMiddleEndAge));

        assertTrue(Range.isValidRange(validBoundaryStartAge, validBoundaryEndAge));
        assertTrue(Range.isValidRange(validMiddleStartAge, validMiddleEndAge));
    }

    @Test
    public void isValidDateRange() {

        Date tempBoundaryStartDate = new Date("0000-01-01");
        Date tempBoundaryEndDate = new Date("9999-12-31");
        Date tempMiddleStartDate = new Date("2000-12-10");
        Date tempMiddleEndDate = new Date("2023-04-09");

        // null date
        assertThrows(NullPointerException.class, ()
                -> ContinuousData.generateRange(null, null));

        // invalid date
        StartDate invalidBoundaryStartDate = new StartDate(Optional.of(tempBoundaryEndDate));
        EndDate invalidBoundaryEndDate = new EndDate(Optional.of(tempBoundaryStartDate));
        StartDate invalidMiddleStartDate = new StartDate(Optional.of(tempMiddleEndDate));
        EndDate invalidMiddleEndDate = new EndDate(Optional.of(tempMiddleStartDate));

        assertFalse(Range.isValidRange(invalidBoundaryStartDate, invalidBoundaryEndDate));
        assertFalse(Range.isValidRange(invalidMiddleStartDate, invalidMiddleEndDate));

        // valid date ranges
        StartDate validBoundaryStartDate = new StartDate(Optional.of(tempBoundaryStartDate));
        EndDate validBoundaryEndDate = new EndDate(Optional.of(tempBoundaryEndDate));
        StartDate validMiddleStartDate = new StartDate(Optional.of(tempMiddleStartDate));
        EndDate validMiddleEndDate = new EndDate(Optional.of(tempMiddleEndDate));

        assertTrue(Range.isValidRange(validBoundaryStartDate, validBoundaryEndDate));
        assertTrue(Range.isValidRange(validMiddleStartDate, validMiddleEndDate));
    }
}
