package taa.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import taa.model.ClassList;

class SameStudentPredicateTest {

    @Test
    void test1() {
        ClassList sampleClass = new ClassList();
        SameStudentPredicate predicate = new SameStudentPredicate(sampleClass);
        String sampleAttd = "0;0;0;0;0;0;0;0;0;0;0;0";
        String samplePP = "-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1";
        Student sampleStudent = new Student(new Name("John"), sampleAttd, samplePP, new ArrayList<String>(),
                new HashSet<>());
        sampleClass.addStudent(sampleStudent);
        Student sampleStudent2 = new Student(new Name("Flora"), sampleAttd, samplePP, new ArrayList<String>(),
                new HashSet<>());
        assertEquals(true, predicate.test(sampleStudent));
        assertEquals(false, predicate.test(sampleStudent2));
    }
}
