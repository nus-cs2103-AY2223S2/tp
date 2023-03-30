package teambuilder.model.util;

import org.junit.jupiter.api.Test;

import teambuilder.model.person.Name;
import teambuilder.model.person.Person;

import java.util.Set;

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
