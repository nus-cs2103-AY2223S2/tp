package seedu.address.model.task;

public enum TaskStatus {
    INPROGRESS, LATE, COMPLETE;

    public static void main(String[] args) {
        TaskStatus t1, t2, t3;

        t1 = TaskStatus.INPROGRESS;
        t2 = TaskStatus.LATE;
        t3 = TaskStatus.INPROGRESS;

        //Comparing using compareTo() method
        if (t1.compareTo(t2) > 0) {
            System.out.println(t2 + "BEFORE" + t1);
        }

        if (t1.compareTo(t2) < 0) {
            System.out.println(t1 + " completed before " + t2);
        }

        if (t1.equals(t3)) {
            System.out.println(t1 + " completed with " + t2);
        }
    }
}
