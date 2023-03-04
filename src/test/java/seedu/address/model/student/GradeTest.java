package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class GradeTest {

    @Test
    public void constructor_validScoreAndFullMarks_success() {
        Grade grade = new Grade(80, 100);
        assertEquals(80, grade.getScore());
        assertEquals(100, grade.getFullMarks());
        assertEquals(80.0, grade.getPercentageScore());
    }

    @Test
    public void constructor_invalidScore_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Grade(-10, 100));
    }

    @Test
    public void constructor_scoreGreaterThanFullMarks_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Grade(110, 100));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        Grade grade = new Grade(80, 100);
        assertEquals(grade, grade);
    }

    @Test
    public void equals_differentObjectsWithSameFields_returnsTrue() {
        Grade grade1 = new Grade(80, 100);
        Grade grade2 = new Grade(80, 100);
        assertEquals(grade1, grade2);
    }

    @Test
    public void equals_differentObjectsWithDifferentFields_returnsFalse() {
        Grade grade1 = new Grade(80, 100);
        Grade grade2 = new Grade(70, 100);
        assertNotEquals(grade1, grade2);
    }

    @Test
    public void toString_validGrade_returnsFormattedString() {
        Grade grade = new Grade(80, 100);
        String expectedString = "80.00 / 100.00 (80.00%)";
        assertEquals(expectedString, grade.toString());
    }

    @Test
    public void equals_differentScore_returnsFalse() {
        Grade grade1 = new Grade(50, 100);
        Grade grade2 = new Grade(70, 100);
        assertNotEquals(grade1, grade2);
    }

    @Test
    public void equals_differentFullMarks_returnsFalse() {
        Grade grade1 = new Grade(50, 100);
        Grade grade2 = new Grade(50, 200);
        assertNotEquals(grade1, grade2);
    }

    @Test
    public void equals_differentTypes_returnsFalse() {
        Grade grade = new Grade(50, 100);
        String notGrade = "not a grade";
        assertNotEquals(grade, notGrade);
    }

    @Test
    public void hashCode_equalGrades_returnsEqualHashCodes() {
        Grade grade1 = new Grade(50, 100);
        Grade grade2 = new Grade(50, 100);
        assertEquals(grade1.hashCode(), grade2.hashCode());
    }

    @Test
    public void hashCode_differentScores_returnsDifferentHashCodes() {
        Grade grade1 = new Grade(50, 100);
        Grade grade2 = new Grade(70, 100);
        assertNotEquals(grade1.hashCode(), grade2.hashCode());
    }

    @Test
    public void hashCode_differentFullMarks_returnsDifferentHashCodes() {
        Grade grade1 = new Grade(50, 100);
        Grade grade2 = new Grade(50, 200);
        assertNotEquals(grade1.hashCode(), grade2.hashCode());
    }

    @Test
    public void getPercentageScore_maxScore_returns100() {
        Grade grade = new Grade(100, 100);
        assertEquals(100, grade.getPercentageScore(), 0.0001);
    }

    @Test
    public void getPercentageScore_halfScore_returns50() {
        Grade grade = new Grade(50, 100);
        assertEquals(50, grade.getPercentageScore(), 0.0001);
    }
}
