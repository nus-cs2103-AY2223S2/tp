package taa.storage;

import java.util.Objects;

import taa.model.ReadOnlyAssignmentList;
import taa.model.ReadOnlyStudentList;

public class TaaData {
    public final ReadOnlyStudentList studentList;
    public final ReadOnlyAssignmentList assignmentList;

    public TaaData(ReadOnlyStudentList studentList, ReadOnlyAssignmentList assignmentList) {
        this.studentList = studentList;
        this.assignmentList = assignmentList;
    }

    @Override
    public boolean equals(Object o){
        if(o==this)
            return true;
        if(!(o instanceof TaaData))
            return false;
        final TaaData other=((TaaData) o);
        return Objects.equals(other.studentList,studentList) && Objects.equals(other.assignmentList,assignmentList);
    }
}
