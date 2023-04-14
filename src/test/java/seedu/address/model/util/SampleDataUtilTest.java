package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public class SampleDataUtilTest {
    @Test
    public void getSamplePersons_generatesTagsSuccessfully() {
        Person[] persons = SampleDataUtil.getSamplePersons();
        Set<Tag> testSet = new HashSet<Tag>();

        // 1 tag
        testSet.add(new Tag("Diabetic"));
        assertEquals(testSet, persons[0].getTags());
        assertEquals(testSet, persons[5].getTags());
        testSet.clear();
        testSet.add(new Tag("Asthmatic"));
        assertEquals(testSet, persons[2].getTags());
        testSet.clear();
        testSet.add(new Tag("Epileptic"));
        assertEquals(testSet, persons[3].getTags());
        testSet.clear();
        testSet.add(new Tag("Arthritic"));
        assertEquals(testSet, persons[4].getTags());



        // Multiple Tags
        testSet.clear();
        testSet.add(new Tag("Osteoporotic"));
        testSet.add(new Tag("Diabetic"));
        assertEquals(testSet, persons[1].getTags());
    }
}
