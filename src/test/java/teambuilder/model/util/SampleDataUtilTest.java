package teambuilder.model.util;

import org.junit.jupiter.api.Test;

import teambuilder.model.person.Person;

public class SampleDataUtilTest {
    @Test
    public void samplePersons_samplePersons_isValid() {
        Person[] persons = SampleDataUtil.getSamplePersons();
    }
}
