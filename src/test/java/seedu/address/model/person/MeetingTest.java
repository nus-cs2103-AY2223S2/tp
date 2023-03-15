package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


public class MeetingTest {

    public Meeting generateMeeting(int y1, int m1, int dom1, int h1, int min1, int y2, int m2, int dom2, int h2,
                                   int min2) throws Exception {
        LocalDateTime start = LocalDateTime.of(y1, m1, dom1, h1, min1);
        LocalDateTime end = LocalDateTime.of(y2, m2, dom2, h2, min2);
        return new Meeting(start, end);
    }

    @Test
    public void checkTimeClash_startEndSameTime_returnsTrue() throws Exception {
        Meeting m1 = generateMeeting(2000, 10, 23, 23, 0, 2000, 10, 24, 0, 15);
        Meeting m2 = generateMeeting(2000, 10, 23, 23, 0, 2000, 10, 24, 0, 15);
        assertTrue(m1.checkTimeClash(m2));
    }

    @Test
    public void checkTimeClash_endAtOtherStart_returnsTrue() throws Exception {
        Meeting m1 = generateMeeting(2000, 10, 23, 23, 0, 2000, 10, 24, 0, 15);
        Meeting m2 = generateMeeting(2000, 10, 23, 22, 0, 2000, 10, 23, 23, 0);
        assertTrue(m1.checkTimeClash(m2));
    }

    @Test
    public void checkTimeClash_endBetweenOtherMeeting_returnsTrue() throws Exception {
        Meeting m1 = generateMeeting(2000, 10, 23, 23, 0, 2000, 10, 24, 0, 15);
        Meeting m2 = generateMeeting(2000, 10, 23, 22, 0, 2000, 10, 24, 0, 0);
        assertTrue(m1.checkTimeClash(m2));
    }

    @Test
    public void checkTimeClash_startSameTime_returnsTrue() throws Exception {
        Meeting m1 = generateMeeting(2000, 10, 23, 23, 0, 2000, 10, 24, 0, 15);
        Meeting m2 = generateMeeting(2000, 10, 23, 23, 0, 2000, 10, 24, 0, 30);
        assertTrue(m1.checkTimeClash(m2));
    }

    @Test
    public void checkTimeClash_startBetweenOtherMeeting_returnsTrue() throws Exception {
        Meeting m1 = generateMeeting(2000, 10, 23, 23, 0, 2000, 10, 24, 0, 15);
        Meeting m6 = generateMeeting(2000, 10, 23, 23, 30, 2000, 10, 23, 23, 45);
        assertTrue(m1.checkTimeClash(m6));
    }

    @Test
    public void checkTimeClash_startAtOtherEnd_returnsTrue() throws Exception {
        Meeting m1 = generateMeeting(2000, 10, 23, 23, 0, 2000, 10, 24, 0, 15);
        Meeting m7 = generateMeeting(2000, 10, 24, 0, 15, 2000, 10, 24, 0, 45);
        assertTrue(m1.checkTimeClash(m7));
    }

    @Test
    public void checkTimeClash_startEndBeforeOtherMeeting_returnsFalse() throws Exception {
        Meeting m1 = generateMeeting(2000, 10, 23, 23, 0, 2000, 10, 24, 0, 15);
        Meeting m2 = generateMeeting(2000, 10, 23, 22, 0, 2000, 10, 23, 22, 45);
        assertFalse(m1.checkTimeClash(m2));
    }

    @Test
    public void checkTimeClash_startEndAfterOtherMeeting_returnsFalse() throws Exception {
        Meeting m1 = generateMeeting(2000, 10, 23, 23, 0, 2000, 10, 24, 0, 15);
        Meeting m2 = generateMeeting(2000, 10, 24, 12, 0, 2000, 10, 24, 13, 0);
        assertFalse(m1.checkTimeClash(m2));
    }

    @Test
    public void checkEquals_sameObject_returnsTrue() throws Exception {
        Meeting m1 = generateMeeting(2000, 10, 23, 23, 0, 2000, 10, 24, 0, 15);
        Meeting m2 = m1;
        assertTrue(m1.equals(m2));
    }

    @Test
    public void checkEquals_sameStartAndEndDifferentObject_returnsTrue() throws Exception {
        Meeting m1 = generateMeeting(2000, 10, 23, 23, 0, 2000, 10, 24, 0, 15);
        Meeting m2 = generateMeeting(2000, 10, 23, 23, 0, 2000, 10, 24, 0, 15);
        assertTrue(m1.equals(m2));
    }

    @Test
    public void checkEquals_sameStartDifferentEnd_returnsFalse() throws Exception {
        Meeting m1 = generateMeeting(2000, 10, 23, 23, 0, 2000, 10, 24, 0, 15);
        Meeting m2 = generateMeeting(2000, 10, 23, 23, 0, 2000, 10, 24, 10, 15);
        assertFalse(m1.equals(m2));
    }

    @Test
    public void checkEquals_sameEndDifferentStart_returnsFalse() throws Exception {
        Meeting m1 = generateMeeting(2000, 10, 23, 23, 0, 2000, 10, 24, 0, 15);
        Meeting m2 = generateMeeting(2000, 10, 23, 20, 0, 2000, 10, 24, 0, 15);
        assertFalse(m1.equals(m2));
    }

    @Test
    public void checkEquals_differentStartAndEnd_returnsFalse() throws Exception {
        Meeting m1 = generateMeeting(2000, 10, 23, 23, 0, 2000, 10, 24, 0, 15);
        Meeting m2 = generateMeeting(2000, 10, 23, 20, 0, 2000, 10, 24, 15, 15);
        assertFalse(m1.equals(m2));
    }
}
