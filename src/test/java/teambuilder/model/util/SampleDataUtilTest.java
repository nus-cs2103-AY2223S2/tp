package teambuilder.model.util;

import java.util.Set;

import org.junit.jupiter.api.Test;

import teambuilder.model.person.Name;
import teambuilder.model.person.Person;

public class SampleDataUtilTest {
    @Test
    public void samplePersons_samplePersons_isValid() {
        Person[] persons = SampleDataUtil.getSamplePersons();
    }

    @Test
    public void sampleNames_samplePersons_isValid() {
        Set<Name> persons = SampleDataUtil.getNameSet();
    }
}
