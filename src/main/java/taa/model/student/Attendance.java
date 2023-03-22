package taa.model.student;

import java.util.Arrays;

/**
 * Attendance class, manages attendance and class participation points
 */
public class Attendance {
    public static final String WEEK_ERROR_MSG = "Week number out of range, should be integer between 1-12";
    public static final String POINTS_ERROR_MSG = "Participation points should be integer between 0-700";
    private final boolean[] attendanceList = new boolean[12];

    private final int[] participationPoint = new int[12];

    /**
     * constructor for attendance class
     */
    public Attendance() {
        Arrays.fill(participationPoint, -1);
    }

    /**
     * Checks if the week is valid
     * @param week String value of week
     * @return true if week is valid and otherwise
     */
    public static boolean isValidWeek(String week) {
        if (!week.matches("[0-9]+")) {
            return false;
        }
        int intWeek = Integer.parseInt(week);
        if (intWeek <= 0 || intWeek > 12) {
            System.out.println(week);
            return false;
        }
        return true;
    }

    /**
     * @return number of weeks marked present
     */
    public int getNumWeeksPresent() {
        int count = 0;
        for (int i = 0; i < 12; i++) {
            if (this.attendanceList[i]) {
                count++;
            }
        }
        return count;
    }

    /**
     * Marks attendance as true
     * @param week week that is to be marked
     */
    public void markAttendance(int week) {
        this.attendanceList[week] = true;
        this.participationPoint[week] = 0;
    }

    /**
     * Checks if the week have been marked
     * @param week week to be checked
     * @return true if week is marked else false
     */
    public boolean isMarkedWeek(int week) {
        return this.attendanceList[week];
    }

    /**
     * Convert string value of week into int, called after ensuring {@code week}
     * is valid
     * @param week String value of week
     * @return int value of week
     */
    public static int convertToIntegerWeek(String week) {
        return Integer.parseInt(week);
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
     * Checks if points is a valid value
     * @param points String version of points to be checked
     * @return boolean if points is valid or not
     */
    public static boolean isValidParticipationPoints(String points) {
        if (!points.matches("[0-9]+")) {
            return false;
        }
        int pp = Integer.parseInt(points);
        return pp > 0 && pp < 700;
    }

    /**
     * Inserts participation points to the specified week.
     * Called only after ensuring points and week is valid
     * @param week week to add points
     * @param points value of points to add
     */
    public void insertParticipationPoints(int week, int points) {
        this.participationPoint[week] = points;
    }

    /**
     * Calculate average points of student. Only consider weeks
     * when participation points are inserted
     * @return the average participation points of student
     */
    public float getAveragePP() {
        int pt = 0;
        int weeks = 0;
        for (int i = 0; i < 12; i++) {
            if (this.attendanceList[i] && this.participationPoint[i] > 0) {
                pt += this.participationPoint[i];
                weeks += 1;
            }
        }
        if (weeks == 0) {
            return 0;
        }
        return (float) pt / weeks;
    }
}
