package taa.model.student;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.function.Predicate;

import taa.commons.util.AppUtil;
import taa.logic.parser.ParserUtil;

/**
 * Attendance class, manages attendance and class participation points
 */
public class Attendance {
    public static final int NUM_WEEKS = 12;
    public static final int MAX_PP = 700;
    public static final String WEEK_ERROR_MSG = "Week number invalid. Must be integer between 1-" + NUM_WEEKS + " (incl"
            + "usive).";
    public static final String ATD_ERROR_MSG = "Attendance marks invalid. Must be exactly " + NUM_WEEKS + " integers ei"
            + "ther 0 or 1.";
    public static final String POINTS_ERROR_MSG = "Participation points invalid. Must be exactly " + NUM_WEEKS + " inte"
            + "gers between 0-" + MAX_PP + " (inclusive)";
    public static final String PP_UNACCEPTABLE_MSG = "Participation points invalid. Must be exactly " + NUM_WEEKS + " i"
            + "ntegers between -1 and " + MAX_PP + " (inclusive)";
    public static final String STR_SEP = ";";
    public static final String ORIGINAL_ATD = "0;0;0;0;0;0;0;0;0;0;0;0";
    public static final String ORIGINAL_PP = "-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1";

    /**
     * Checks if points is a valid value or -1
     *
     * @param points String version of points to be checked
     * @return boolean if points is valid or not
     */
    public static final Predicate<String> IS_ACCEPTABLE_PARTICIPATION_POINT =
            points -> "-1".equals(points) || isValidParticipationPoints(points);
    private final boolean[] attendanceList = new boolean[NUM_WEEKS];
    private final int[] participationPoint = new int[NUM_WEEKS];

    /**
     * constructor for attendance class
     */
    public Attendance(String atd, String pp) {
        requireNonNull(atd);
        AppUtil.checkArgument(isValidAtdStr(atd), ATD_ERROR_MSG);
        requireNonNull(pp);
        AppUtil.checkArgument(isAcceptablePpStr(pp), POINTS_ERROR_MSG);
        String[] atdArr = atd.split(STR_SEP);
        String[] ppArr = pp.split(STR_SEP);
        for (int i = 0; i < atdArr.length; i++) {
            this.attendanceList[i] = "1".equals(atdArr[i].trim());
            this.participationPoint[i] = Integer.parseInt(ppArr[i].trim());
        }
    }

    /**
     * Checks if points is a valid value
     *
     * @param points String version of points to be checked
     * @return boolean if points is valid or not
     */
    public static boolean isValidParticipationPoints(String points) {
        final int pp;
        try {
            pp = Integer.parseInt(points);
        } catch (NumberFormatException e) {
            return false;
        }
        return pp >= 0 && pp <= MAX_PP;
    }

    /**
     * Checks if the week is valid
     *
     * @param week String value of week
     * @return true if week is valid and otherwise
     */
    public static boolean isValidWeek(String week) {
        final int intWeek;
        try {
            intWeek = Integer.parseInt(week);
        } catch (NumberFormatException e) {
            return false;
        }
        return intWeek > 0 && intWeek <= NUM_WEEKS;
    }

    /**
     * Convert string value of week into int, called after ensuring {@code week} is valid
     *
     * @param week String value of week
     * @return int value of week
     */
    public static int convertToIntegerWeek(String week) {
        return Integer.parseInt(week);
    }

    private static boolean isValidArgStr(String argStr, Predicate<String> p) {
        final String[] args = argStr.split(STR_SEP);
        return args.length == NUM_WEEKS && Arrays.stream(args).map(String::trim).allMatch(p);
    }

    /**
     * @param ppStr Participation points string from storage files
     * @return true if string is valid and false otherwise
     */
    public static boolean isAcceptablePpStr(String ppStr) {
        return isValidArgStr(ppStr, IS_ACCEPTABLE_PARTICIPATION_POINT);
    }

    /**
     * @param atdStr Attendance string from storage files
     * @return true if string is valid and false otherwise
     */
    public static boolean isValidAtdStr(String atdStr) {
        return isValidArgStr(atdStr, ParserUtil.IS_BIN_INT);
    }

    /**
     * @return number of weeks marked present
     */
    public int getNumWeeksPresent() {
        int count = 0;
        for (boolean atd:attendanceList) {
            if (atd) {
                count++;
            }
        }
        return count;
    }

    /**
     * Marks attendance as true
     *
     * @param week week that is to be marked
     */
    public void markAttendance(int week) {
        this.attendanceList[week] = true;
        this.participationPoint[week] = 0;
    }

    /**
     * Checks if the week have been marked
     *
     * @param week week to be checked
     * @return true if week is marked else false
     */
    public boolean isMarkedWeek(int week) {
        return this.attendanceList[week];
    }

    /**
     * Function to unmark attendance
     *
     * @param week index to unmark
     */
    public void unmarkAttendance(int week) {
        this.attendanceList[week] = false;
        this.participationPoint[week] = -1;
    }

    /**
     * Inserts participation points to the specified week. Called only after ensuring points and week is valid
     *
     * @param week   week to add points
     * @param points value of points to add
     */
    public void insertParticipationPoints(int week, int points) {
        this.participationPoint[week] = points;
    }

    /**
     * Calculate average points of student. Only consider weeks where attendance is marked
     *
     * @return the average participation points of student
     */
    public float getAveragePP() {
        int pt = 0;
        int weeks = 0;
        for (int i = 0; i < NUM_WEEKS; i++) {
            if (this.attendanceList[i]) {
                pt += this.participationPoint[i];
                weeks += 1;
            }
        }
        if (weeks == 0) {
            return 0;
        }
        return (float) pt / weeks;
    }

    /**
     * @return string version of attendancelist to be stored in json file
     */
    public String atdStrorageStr() {
        StringBuilder res = new StringBuilder();
        for (boolean atd : this.attendanceList) {
            res.append(atd ? '1' : '0').append(STR_SEP);
        }
        return res.substring(0, res.length() - 1);
    }

    /**
     * @return returns string version of participation points to be stored in json file
     */
    public String partPointsStorageStr() {
        StringBuilder res = new StringBuilder();
        for (int val : this.participationPoint) {
            res.append(val).append(STR_SEP);
        }
        return res.substring(0, res.length() - 1);
    }

    /**
     * Updates the attendance counter for the entire class list.
     */
    public void updateAttendanceCounter(int[] counter) {
        for (int i = 0; i < NUM_WEEKS; i++) {
            counter[i] += attendanceList[i] ? 1 : 0;
        }
    }

    /**
     * @return String version of participation points for list command
     */
    public String listAtdString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.attendanceList.length; i++) {
            res.append(String.format("Week %d: [%s]\n", i + 1, this.attendanceList[i] ? "X" : " "));
        }
        return res.toString();
    }

    /**
     * @return String version of participation points for list command
     */
    public String listPpString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.participationPoint.length; i++) {
            res.append(String.format("Week %d: [%d]\n", i + 1, this.participationPoint[i]));
        }
        return res.toString();
    }
}
