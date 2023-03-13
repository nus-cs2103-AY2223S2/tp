package taa.model.student;

/**
 * Attendance class
 */
class Attendance {
    private final boolean[] attendanceList = new boolean[12];

    /**
     * constructor for attendance class
     */
    public Attendance() {
    }

    /**
     * Function to mark attendance
     * @param index index to mark
     */
    public void mark(int index) {
        if (index <= 0 || index >= 12) {
            System.out.println("Error, attendance index out of range");
        }
        this.attendanceList[index - 1] = true;
    }

    /**
     * Function to unmark attendance
     *
     * @param index index to unmark
     */
    public void unmark(int index) {
        if (index <= 0 || index >= 12) {
            System.out.println("Error, attendance index out of range");
        }
        this.attendanceList[index - 1] = false;
    }
}
