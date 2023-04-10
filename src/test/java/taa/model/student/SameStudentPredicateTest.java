package taa.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import taa.model.ClassList;

class SameStudentPredicateTest {

    @Test
    void test1() {
        ClassList sampleClass = new ClassList();
        SameStudentPredicate predicate = new SameStudentPredicate(sampleClass);
        String sampleAttd = Attendance.ORIGINAL_ATD;
        String samplePP = Attendance.ORIGINAL_PP;
        Student sampleStudent = new Student(new Name("John"), sampleAttd, samplePP, new ArrayList<>(), new HashSet<>());
        sampleClass.addStudent(sampleStudent);
        Student sampleStudent2 = new Student(new Name("Flora"), sampleAttd, samplePP, new ArrayList<>(),
                new HashSet<>());
        assertTrue(predicate.test(sampleStudent));
        assertFalse(predicate.test(sampleStudent2));
    }
}
