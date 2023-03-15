package codoc.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import codoc.model.Codoc;
import codoc.model.ReadOnlyCodoc;
import codoc.model.module.Module;
import codoc.model.person.Course;
import codoc.model.person.Email;
import codoc.model.person.Github;
import codoc.model.person.Linkedin;
import codoc.model.person.Name;
import codoc.model.person.Person;
import codoc.model.person.Year;
import codoc.model.skill.Skill;

/**
 * Contains utility methods for populating {@code Codoc} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(
                    new Name("Alex Yeoh"),
                    new Course("Computer Science"),
                    new Year("1"),
                    new Github("alexyeoh"),
                    new Email("alexyeoh@example.com"),
                    new Linkedin("linkedin.com/in/alexyeoh"),
                    getSkillSet("python"),
                    getModuleSet("AY2223S2 CS1101")
            ),
            new Person(
                    new Name("Bernice Yu"),
                    new Course("Business"),
                    new Year("2"),
                    new Github("bernice-yu"),
                    new Email("berniceyu@example.com"),
                    new Linkedin("linkedin.com/in/berNICE"),
                    getSkillSet("java", "python"),
                    getModuleSet("AY2223S2 CS1101", "AY2223S2 CS2030S")
            ),
            new Person(
                    new Name("Charlotte Oliveiro"),
                    new Course("FASS"),
                    new Year("3"),
                    new Github("ch4rl0tt3"),
                    new Email("charlotte@example.com"),
                    new Linkedin("linkedin.com/in/charlotte-oliveiro"),
                    getSkillSet("sql"),
                    getModuleSet("AY2223S2 CS1101", "AY2223S2 CS2030S", "AY2223S2 CS2040S")
            ),
            new Person(
                    new Name("David Li"),
                    new Course("Engineering"),
                    new Year("4"),
                    new Github("David-Li"),
                    new Email("lidavid@example.com"),
                    new Linkedin("linkedin.com/in/d4v1d-l1"),
                    getSkillSet("c"),
                    getModuleSet("AY2223S2 CS1101", "AY2223S2 CS2030S")
            ),
            new Person(
                    new Name("Irfan Ibrahim"),
                    new Course("Engineering"),
                    new Year("4"),
                    new Github("iRf4n"),
                    new Email("irfan@example.com"),
                    new Linkedin("linkedin.com/in/1rf4n"),
                    getSkillSet("javascript"),
                    getModuleSet("AY2223S2 CS1101", "AY2223S2 CS2030S")
            ),
            new Person(
                    new Name("Roy Balakrishnan"),
                    new Course("Engineering"),
                    new Year("4"),
                    new Github("b4l4Kr15H-n4n"),
                    new Email("royb@example.com"),
                    new Linkedin("linkedin.com/in/Roy"),
                    getSkillSet("java"),
                    getModuleSet("AY2223S2 CS1101", "AY2223S2 CS2030S")
            )
        };
    }

    public static ReadOnlyCodoc getSampleCodoc() {
        Codoc sampleAb = new Codoc();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a skill set containing the list of strings given.
     */
    public static Set<Skill> getSkillSet(String... strings) {
        return Arrays.stream(strings)
                .map(Skill::new)
                .collect(Collectors.toSet());
    }
    /**
     * Returns a module list containing the list of strings given.
     */
    public static Set<Module> getModuleSet(String... strings) {
        return Arrays.stream(strings)
                .map(Module::new)
                .collect(Collectors.toSet());
    }
}
