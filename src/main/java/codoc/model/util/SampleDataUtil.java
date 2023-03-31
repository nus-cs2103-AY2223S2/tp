package codoc.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import codoc.model.Codoc;
import codoc.model.ReadOnlyCodoc;
import codoc.model.course.Course;
import codoc.model.module.Module;
import codoc.model.person.Email;
import codoc.model.person.Github;
import codoc.model.person.Linkedin;
import codoc.model.person.Name;
import codoc.model.person.Person;
import codoc.model.person.ProfilePicture;
import codoc.model.person.Year;
import codoc.model.skill.Skill;

/**
 * Contains utility methods for populating {@code Codoc} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(
                    new ProfilePicture("/images/avataricons/001-bear.png"),
                    new Name("Lim Jun Jie"),
                    new Course("6"),
                    new Year("2"),
                    new Github("limjj10"),
                    new Email("limjj10@example.com"),
                    new Linkedin("linkedin.com/in/limjj10"),
                    getSkillSet("Java", "Python", "JavaScript", "SQL", "C", "React Native"),
                    getModuleSet("AY2122S1 CS1101S", "AY2122S1 CS1231S", "AY2122S1 MA1521", "AY2122S1 FSC2101",
                            "AY2122S1 GEC1013", "AY2122S2 CS2030S", "AY2122S2 CS2040S", "AY2122S2 MA2001",
                            "AY2122S2 GEA1000", "AY2122S2 IS1103", "AY2223S1 CS2109S", "AY2223S1 CS2100", "AY2223S1 "
                                    + "CS2105", "AY2223S1 ES2660", "AY2223S1 GEN2000", "AY2223S2 CS2101", "AY2223S2 "
                                    + "CS2103T", "AY2223S2 CS2107", "AY2223S2 CS2102", "AY2223S2 GESS1025")
            ),
            new Person(
                    new ProfilePicture("/images/avataricons/002-rabbit.png"),
                    new Name("Nicole Lee"),
                    new Course("6"),
                    new Year("2"),
                    new Github("nicoleee"),
                    new Email("nicoleee@example.com"),
                    new Linkedin("linkedin.com/in/nicole-lee"),
                    getSkillSet("Java", "Python", "JavaScript", "C", "ReactJS"),
                    getModuleSet("AY2122S1 CS1101S", "AY2122S1 CS1231S", "AY2122S1 MA2001", "AY2122S1 PC1201",
                            "AY2122S1 GEC1015", "AY2122S2 CS2030S", "AY2122S2 CS2040S", "AY2122S2 MA1521",
                            "AY2122S2 GEA1000", "AY2122S2 IS1103", "AY2223S1 CS2106", "AY2223S1 CS2100", "AY2223S1 "
                                    + "CS2105", "AY2223S1 ES2660", "AY2223S1 ST2234", "AY2223S2 CS2101", "AY2223S2 "
                                    + "CS2103T", "AY2223S2 CS2107", "AY2223S2 GEN2002", "AY2223S2 GESS1025")
            ),
            new Person(
                    new ProfilePicture("/images/avataricons/003-panda.png"),
                    new Name("Nicole Tan"),
                    new Course("4"),
                    new Year("1"),
                    new Github("nykhoul"),
                    new Email("nykhoul02@example.com"),
                    new Linkedin("linkedin.com/in/nicole-tan"),
                    getSkillSet("Java", "Python", "JavaScript", "R", "SQL"),
                    getModuleSet("AY2223S1 CS1010S", "AY2223S1 BT1101", "AY2223S1 IS1108", "AY2223S1 MA1522",
                            "AY2223S1 MA1521", "AY2223S2 BT2101", "AY2223S2 BT2102", "AY2223S2 CS2030",
                            "AY2223S2 CS2040", "AY2223S2 IS2101")
            ),
            new Person(
                    new ProfilePicture("/images/avataricons/004-sloth.png"),
                    new Name("Muhammad Syafiq"),
                    new Course("9"),
                    new Year("2"),
                    new Github("sp33dysyafiq"),
                    new Email("syafiq2000@example.com"),
                    new Linkedin("linkedin.com/in/syafiqqq"),
                    getSkillSet("Java", "Python", "JavaScript", "C", "SQL", "Go"),
                    getModuleSet("AY2122S1 CS1010J", "AY2122S1 BT1101", "AY2122S1 IS1108", "AY2122S1 MA1521",
                            "AY2122S1 GEC1013", "AY2122S2 CS2030", "AY2122S2 CS2040", "AY2122S2 BT2102",
                            "AY2122S2 ST2234", "AY2122S2 IS2101", "AY2223S1 IS2102", "AY2223S1 IS2103", "AY2223S1 "
                                    + "GEX1011", "AY2223S1 LAK1201", "AY2223S1 CS2105", "AY2223S2 CS2107", "AY2223S2 "
                                    + "IS3103", "AY2223S2 LAK2201", "AY2223S2 IS3106", "AY2223S2 GESS1025")
            ),
            new Person(
                    new ProfilePicture("/images/avataricons/005-hen.png"),
                    new Name("Madhu Kumar"),
                    new Course("5"),
                    new Year("2"),
                    new Github("madhu-kumar"),
                    new Email("madhukumar@example.com"),
                    new Linkedin("linkedin.com/in/kumar-madhu"),
                    getSkillSet("Java", "Python", "JavaScript", "C", "R", "C++", "Assembly"),
                    getModuleSet("AY2122S1 CS1010", "AY2122S1 CG1111A", "AY2122S1 CS1231", "AY2122S1 EG1311",
                            "AY2122S1 MA1511", "AY2122S1 MA1512", "AY2122S2 MA1508E", "AY2122S2 IE2141", "AY2122S2 "
                                    + "EE2211",
                            "AY2122S2 EG2501", "AY2122S2 GEC1013", "AY2223S1 CG2111A", "AY2223S1 CG2023", "AY2223S1 "
                                    + "CG2027", "AY2223S1 CG2028", "AY2223S1 CS2040C", "AY2223S2 CG2271", "AY2223S2 "
                                    + "CS2113", "AY2223S2 DTK1234", "AY2223S2 ES2631", "AY2223S2 GEA1000")
            ),
            new Person(
                    new ProfilePicture("/images/avataricons/006-puffer-fish.png"),
                    new Name("Nguyen Thanh Tran"),
                    new Course("10"),
                    new Year("4"),
                    new Github("ilovebanhmi"),
                    new Email("e0542769@example.com"),
                    new Linkedin("linkedin.com/in/nguyenTT"),
                    getSkillSet("Java", "Python", "JavaScript", "C", "SQL", "C++", "ReactJS", "React Native", "PHP",
                            "Ruby",
                            "Rust", "Ook!"),
                    getModuleSet("AY1920S1 CS1231S", "AY1920S1 MA1521", "AY1920S1 MA1522", "AY1920S1 IS1108",
                            "AY1920S1 IS1128", "AY1920S1 DTK1234", "AY1920S2 CS2040C", "AY1920S2 CS2100", "AY1920S2 "
                                    + "ST2334",
                            "AY1920S2 ACC1701X", "AY1920S2 GEH1015", "AY2021S1 CS2101", "AY2021S1 CS2113T", "AY2021S1 "
                                    + "CS2105", "AY2021S1 GEA1000", "AY2021S1 GEX1011", "AY2021S2 CS2106", "AY2021S2 "
                                    + "CS2107", "AY2021S2 CS1010", "AY2021S2 GES1035", "AY2021S2 IE2141", "AY2122S1 "
                                    + "CS3235", "AY2122S1 CS4276", "AY2122S1 CS4230", "AY2122S1 IS4302",
                            "AY2122S1 LAK1201", "AY2122S2 LAK2201", "AY2122S2 CP3200", "AY2122S2 "
                                    + "PL1101E", "AY2223S1 CP3202", "AY2223S1 EN1101E", "AY2223S1 "
                                    + "HSH1000", "AY2223S2 IFS4205", "AY2223S2 "
                                    + "IS4231", "AY2223S2 SC1101E", "AY2223S2 EL1101E", "AY2223S2 NUR1113A")
            ),
            new Person(
                    new ProfilePicture("/images/avataricons/007-beaver.png"),
                    new Name("Jeremy Lim"),
                    new Course("6"),
                    new Year("2"),
                    new Github("jljw23"),
                    new Email("jeremylimjw23@example.com"),
                    new Linkedin("linkedin.com/in/jeremylimjw"),
                    getSkillSet("Java", "Python", "JavaScript", "C", "SQL", "Whitespace"),
                    getModuleSet("AY2122S1 CS1101S", "AY2122S1 CS1231S", "AY2122S1 MA2001", "AY2122S1 LSM1301",
                            "AY2122S1 GEC1017", "AY2122S2 CS2030S", "AY2122S2 CS2040S", "AY2122S2 MA1521",
                            "AY2122S2 ES2660", "AY2122S2 IS1103", "AY2223S1 CS2109S", "AY2223S1 CS2100", "AY2223S1 "
                                    + "CS2106", "AY2223S1 GEA1000", "AY2223S1 ST2234", "AY2223S2 CS2101", "AY2223S2 "
                                    + "CS2103T", "AY2223S2 CS2102", "AY2223S2 LAK1201", "AY2223S2 GESS1025")
            ),
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
