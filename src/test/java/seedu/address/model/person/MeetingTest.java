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
    public void testCheckTimeClash() throws Exception {
        Meeting m1 = generateMeeting(2000, 10, 23, 23, 0, 2000, 10, 24, 0, 15);
        //start and end at the same time
        Meeting m2 = generateMeeting(2000, 10, 23, 23, 0, 2000, 10, 24, 0, 15);
        //end at m1.start
        Meeting m3 = generateMeeting(2000, 10, 23, 22, 0, 2000, 10, 23, 23, 0);
        //ends in between m1.start and m1.end
        Meeting m4 = generateMeeting(2000, 10, 23, 22, 0, 2000, 10, 24, 0, 0);
        //starts at the same time as m1
        Meeting m5 = generateMeeting(2000, 10, 23, 23, 0, 2000, 10, 24, 0, 30);
        //starts in between m1.start and m1.end
        Meeting m6 = generateMeeting(2000, 10, 23, 23, 30, 2000, 10, 23, 23, 45);
        //starts at m1.end
        Meeting m7 = generateMeeting(2000, 10, 24, 0, 15, 2000, 10, 24, 0, 45);
        //start and end before m1.start
        Meeting m8 = generateMeeting(2000, 10, 23, 22, 0, 2000, 10, 23, 22, 45);
        //start and end after m1.end
        Meeting m9 = generateMeeting(2000, 10, 24, 12, 0, 2000, 10, 24, 13, 0);
        assertTrue(m1.checkTimeClash(m2));
        assertTrue(m1.checkTimeClash(m3));
        assertTrue(m1.checkTimeClash(m4));
        assertTrue(m1.checkTimeClash(m5));
        assertTrue(m1.checkTimeClash(m6));
        assertTrue(m1.checkTimeClash(m7));
        assertFalse(m1.checkTimeClash(m8));
        assertFalse(m1.checkTimeClash(m9));
    }
}
