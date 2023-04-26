package taa.storage;

import java.util.Arrays;
import java.util.Objects;

import taa.model.ClassList;
import taa.model.ReadOnlyStudentList;
import taa.model.assignment.Assignment;

/** A container that takes a snapshot of the student list and the assignment list. */
public class TaaData {
    public final ReadOnlyStudentList studentList;
    public final Assignment[] asgnArr;

    /** Creates a {@link TaaData} with specified student list and assignments. */
    public TaaData(ReadOnlyStudentList studentList, Assignment[] asgnArr) {
        this.studentList = studentList;
        this.asgnArr = asgnArr;
    }

    /** Creates a {@link TaaData} with specified student list and a new empty assignment array. */
    public TaaData(ReadOnlyStudentList studentList) {
        this(studentList, new Assignment[0]);
    }

    /** Creates a {@link TaaData} with a new empty student list and a new empty assignment array. */
    public TaaData() {
        this(new ClassList(), new Assignment[0]);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TaaData)) {
            return false;
        }
        final TaaData other = ((TaaData) o);
        return Objects.equals(other.studentList, studentList) && Arrays.equals(other.asgnArr, asgnArr);
    }
}
