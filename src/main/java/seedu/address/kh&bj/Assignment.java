package duke.assignment;

import java.util.ArrayList;
import java.util.HashMap;


public class Assignment {
    private String name;
    private ArrayList<Submission> submissions = new ArrayList<>();
    private HashMap<Integer, Submission> submissionMap =  new HashMap<>();


    public Assignment(String name, StudentList sl) {
        this.name = name;
        for (Student stu : sl.getStudents()) {
            Submission sub = new Submission(stu.getId());
            submissions.add(sub);
            submissionMap.put(stu.getId(), sub);
        }
    }

    public void gradeSubmission(int studentId, int marks) {
        if (submissionMap.containsKey(studentId)) {
            submissionMap.get(studentId).grade(marks);
        } else {
            System.out.println("Submission of " + studentId  + "not found");
        }
    }
}