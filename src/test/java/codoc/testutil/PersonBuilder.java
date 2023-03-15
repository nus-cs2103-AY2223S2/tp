package codoc.testutil;

import static codoc.logic.commands.CommandTestUtil.VALID_MODULE_AY2223S2_CS2103T;
import static codoc.logic.commands.CommandTestUtil.VALID_SKILL_CSHARP;

import java.util.HashSet;
import java.util.Set;

import codoc.model.module.Module;
import codoc.model.person.Course;
import codoc.model.person.Email;
import codoc.model.person.Github;
import codoc.model.person.Linkedin;
import codoc.model.person.Name;
import codoc.model.person.Person;
import codoc.model.person.Year;
import codoc.model.skill.Skill;
import codoc.model.util.SampleDataUtil;



/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_COURSE = "Computer Science";
    public static final String DEFAULT_YEAR = "1";
    public static final String DEFAULT_GITHUB = "amy-123";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_LINKEDIN = "linkedin.com/in/4my";
    public static final Skill DEFAULT_SKILL = new Skill(VALID_SKILL_CSHARP);
    public static final Module DEFAULT_MODULE = new Module(VALID_MODULE_AY2223S2_CS2103T);



    private Name name;
    private Course course;
    private Year year;
    private Github github;
    private Email email;
    private Linkedin linkedin;
    private Set<Skill> skills;
    private Set<Module> mods;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        course = new Course(DEFAULT_COURSE);
        year = new Year(DEFAULT_YEAR);
        github = new Github(DEFAULT_GITHUB);
        email = new Email(DEFAULT_EMAIL);
        linkedin = new Linkedin(DEFAULT_LINKEDIN);
        skills = new HashSet<>();
        skills.add(DEFAULT_SKILL);
        mods = new HashSet<>();
        mods.add(DEFAULT_MODULE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        course = personToCopy.getCourse();
        year = personToCopy.getYear();
        github = personToCopy.getGithub();
        email = personToCopy.getEmail();
        linkedin = personToCopy.getLinkedin();
        skills = new HashSet<>(personToCopy.getSkills());
        mods = new HashSet<>(personToCopy.getModules());
    }

    /**
     * Parses the {@code mods} into a {@code Set<Mod>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withMods(String ... mods) {
        this.mods = SampleDataUtil.getModuleSet(mods);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }
    /**
     * Sets the {@code Course} of the {@code Person} that we are building.
     */
    public PersonBuilder withCourse(String course) {
        this.course = new Course(course);
        return this;
    }
    /**
     * Sets the {@code Year} of the {@code Person} that we are building.
     */
    public PersonBuilder withYear(String year) {
        this.year = new Year(year);
        return this;
    }

    /**
     * Parses the {@code skills} into a {@code Set<Skill>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withSkills(String ... skills) {
        this.skills = SampleDataUtil.getSkillSet(skills);
        return this;
    }

    /**
     * Parses the {@code modules} into a {@code Set<Module>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withModules(String ... modules) {
        this.mods = SampleDataUtil.getModuleSet(modules);
        return this;
    }

    /**
     * Sets the {@code Linkedin} of the {@code Person} that we are building.
     */
    public PersonBuilder withLinkedin(String linkedin) {
        this.linkedin = new Linkedin(linkedin);
        return this;
    }

    /**
     * Sets the {@code Github} of the {@code Person} that we are building.
     */
    public PersonBuilder withGithub(String github) {
        this.github = new Github(github);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Person build() {
        return new Person(name, course, year, github, email, linkedin, skills, mods);
    }

    /**
     * Build a different person
     * @return
     */
    public Person buildEditedPerson() {
        name = new Name("Test name");
        course = new Course("Computer Science");
        year = new Year("1");
        github = new Github("987654321");
        email = new Email("test@gmail.com");
        linkedin = new Linkedin("linkedin.com/in/test");
        skills = new HashSet<>();
        skills.add(DEFAULT_SKILL);
        skills.add(new Skill("Solidity"));
        mods = new HashSet<>();
        mods.add(DEFAULT_MODULE);
        mods.add(new Module("AY2122S1 GEA1000"));

        return new Person(name, course, year, github, email, linkedin, skills, mods);
    }

}
