package taa.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AttendanceTest {
    private static final String INVALID_PP_STORAGE_STRING = "-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1";
    private static final String INVALID_ATTENDANCE_STORAGE_STRING = "0;0;0;0;0;0;0;0;0;0;0;0;0;0;0";
    private static final String BASIC_ATTENDANCE_STRING = "1;1;0;0;0;0;0;0;0;0;0;0";
    private static final String BASIC_PP_STRING = "120;240;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1";
    @Test
    void isValidParticipationPoints() {
        assertTrue(Attendance.isValidParticipationPoints("0"));
        assertTrue(Attendance.isValidParticipationPoints("700"));
        assertTrue(Attendance.isValidParticipationPoints("450"));
        assertFalse(Attendance.isValidParticipationPoints("701"));
        assertFalse(Attendance.isValidParticipationPoints("-2"));
        assertFalse(Attendance.isValidParticipationPoints("abc"));
    }

    @Test
    void isValidWeek() {
        assertTrue(Attendance.isValidWeek("1"));
        assertTrue(Attendance.isValidWeek("12"));
        assertFalse(Attendance.isValidWeek("0"));
        assertFalse(Attendance.isValidWeek("13"));
        assertFalse(Attendance.isValidWeek("abc"));
    }

    @Test
    void convertToIntegerWeek() {
        assertEquals(1, Attendance.convertToIntegerWeek("1"));
        assertEquals(12, Attendance.convertToIntegerWeek("12"));
    }

    @Test
    void isAcceptablePpStr() {
        assertTrue(Attendance.isAcceptablePpStr(Attendance.ORIGINAL_PP));
        assertFalse(Attendance.isAcceptablePpStr(INVALID_PP_STORAGE_STRING));
    }

    @Test
    void isValidAtdStr() {
        assertTrue(Attendance.isValidAtdStr(Attendance.ORIGINAL_ATD));
        assertFalse(Attendance.isValidAtdStr(INVALID_ATTENDANCE_STORAGE_STRING));
    }

    @Test
    void getNumWeeksPresent() {
        Attendance atd = new Attendance(BASIC_ATTENDANCE_STRING, BASIC_PP_STRING);
        assertEquals(atd.getNumWeeksPresent(), 2);
    }

    @Test
    void markAttendance() {
        Attendance atd = new Attendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PP);
        atd.markAttendance(1);
        assertTrue(atd.isMarkedWeek(1));
    }

    @Test
    void isMarkedWeek() {
        Attendance attendance = new Attendance(BASIC_ATTENDANCE_STRING, BASIC_PP_STRING);
        assertFalse(attendance.isMarkedWeek(4));
        assertTrue(attendance.isMarkedWeek(1));
    }

    @Test
    void unmarkAttendance() {
        Attendance atd = new Attendance(BASIC_ATTENDANCE_STRING, BASIC_PP_STRING);
        atd.unmarkAttendance(1);
        assertFalse(atd.isMarkedWeek(1));
    }
    @Test
    void getAveragePP() {
        Attendance atd = new Attendance(BASIC_ATTENDANCE_STRING, BASIC_PP_STRING);
        assertEquals(atd.getAveragePP(), 180);
    }

    @Test
    void atdStrorageStr() {
        Attendance atd = new Attendance(BASIC_ATTENDANCE_STRING, BASIC_PP_STRING);
        assertEquals(atd.atdStrorageStr(), BASIC_ATTENDANCE_STRING);
    }

    @Test
    void partPointsStorageStr() {
        Attendance atd = new Attendance(BASIC_ATTENDANCE_STRING, BASIC_PP_STRING);
        assertEquals(atd.partPointsStorageStr(), BASIC_PP_STRING);
    }
    @Test
    void listAtdString() {
        Attendance atd = new Attendance(BASIC_ATTENDANCE_STRING, BASIC_PP_STRING);
        String res = "Week 1: [X]\nWeek 2: [X]\nWeek 3: [ ]\n"
                + "Week 4: [ ]\nWeek 5: [ ]\nWeek 6: [ ]\nWeek 7: [ ]\nWeek 8: [ ]\n"
                + "Week 9: [ ]\nWeek 10: [ ]\nWeek 11: [ ]\nWeek 12: [ ]\n";
        assertEquals(atd.listAtdString(), res);
    }

    @Test
    void listPpString() {
        Attendance atd = new Attendance(BASIC_ATTENDANCE_STRING, BASIC_PP_STRING);
        String res = "Week 1: [120]\nWeek 2: [240]\nWeek 3: [-1]\n"
                + "Week 4: [-1]\nWeek 5: [-1]\nWeek 6: [-1]\nWeek 7: [-1]\nWeek 8: [-1]\n"
                + "Week 9: [-1]\nWeek 10: [-1]\nWeek 11: [-1]\nWeek 12: [-1]\n";
        assertEquals(atd.listPpString(), res);
    }

    @Test
    void isValidAttendanceStorageString() {
        assertTrue(Attendance.isValidAttendanceStorageString(BASIC_ATTENDANCE_STRING));
        assertTrue(Attendance.isValidAttendanceStorageString(Attendance.ORIGINAL_ATD));
        assertFalse(Attendance.isValidAttendanceStorageString(INVALID_ATTENDANCE_STORAGE_STRING));
    }

    @Test
    void isValidPpStorageString() {
        assertTrue(Attendance.isValidPpStorageString(BASIC_PP_STRING));
        assertTrue(Attendance.isValidPpStorageString(Attendance.ORIGINAL_PP));
        assertFalse(Attendance.isValidPpStorageString(INVALID_PP_STORAGE_STRING));
    }
}
