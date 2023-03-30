package taa.model.student;

import java.util.Objects;

/**
 * Attendance class, manages attendance and class participation points
 */
public class Attendance {
    public static final String WEEK_ERROR_MSG = "Week number out of range, should be integer between 1-12";
    public static final String POINTS_ERROR_MSG = "Participation points should be integer between 0-700";
    public static final String ORIGINAL_ATD = "0,0,0,0,0,0,0,0,0,0,0,0";

    public static final String ORIGINAL_PP = "-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,";
    private final boolean[] attendanceList = new boolean[12];

    private final int[] participationPoint = new int[12];

    /**
     * constructor for attendance class
     */
    public Attendance(String atd, String pp) {
        String[] atdArr = atd.split(";");
        String[] ppArr = pp.split(";");
        for (int i = 0; i < atdArr.length; i++) {
            if (Objects.equals(atdArr[i], "1")) {
                this.attendanceList[i] = true;
            }
            this.participationPoint[i] = Integer.parseInt(ppArr[i]);
        }
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

    /**
     * @return string version of attendancelist to be stored in json file
     */
    public String atdStrorageStr() {
        String res = "";
        for (boolean atd : this.attendanceList) {
            if (atd) {
                res += "1;";
            } else {
                res += "0;";
            }
        }
        return res.substring(0, 23);
    }

    /**
     * @return returns string version of participation points to be stored in json file
     */
    public String partPointsStorageStr() {
        String res = "";
        for (int val : this.participationPoint) {
            res += String.valueOf(val) + ";";
        }
        return res.substring(0, res.length() - 1);
    }

    /**
     * @return String version of participation points for list command
     */
    public String listAtdString() {
        String res = "";
        for (int i = 0; i < this.attendanceList.length; i++) {
            res += String.format("Week %d: [%s]\n", i + 1, this.attendanceList[i] ? "X" : " ");
        }
        return res;
    }

    /**
     * @return String version of participation points for list command
     */
    public String listPpString() {
        String res = "";
        for (int i = 0; i < this.participationPoint.length; i++) {
            res += String.format("Week %d: [%d]\n", i + 1, this.participationPoint[i]);
        }
        return res;
    }
}
