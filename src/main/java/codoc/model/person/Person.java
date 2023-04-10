package codoc.model.person;

import static codoc.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import codoc.model.course.Course;
import codoc.model.module.Module;
import codoc.model.skill.Skill;

/**
 * Represents a Person in CoDoc. Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {
    private static final Person dummyPerson = createDummy();

    // Identity fields
    private final ProfilePicture profilePicture;
    private final Name name;
    private final Github github;
    private final Email email;

    // Data fields
    private final Linkedin linkedin;
    private final Course course;
    private final Year year;

    private final Set<Skill> skills = new HashSet<>();
    private final Set<Module> modules = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(
            ProfilePicture profilePicture,
            Name name,
            Course course,
            Year year,
            Github github,
            Email email,
            Linkedin linkedin,
            Set<Skill> skills,
            Set<Module> modules
    ) {
        requireAllNonNull(profilePicture, name, github, email, linkedin, skills, modules, course, year);
        this.profilePicture = profilePicture;
        this.name = name;
        this.github = github;
        this.email = email;
        this.linkedin = linkedin;
        this.course = course;
        this.year = year;
        this.skills.addAll(skills);
        this.modules.addAll(modules);
    }
    public static Person getDummyPerson() {
        return dummyPerson;
    }

    private static Person createDummy() {
        ProfilePicture profilePicture = new ProfilePicture("/images/avataricons/001-bear.png");
        Name name = new Name("DEFAULT NAME");
        Course course = new Course("1");
        Year year = new Year("1");
        Github github = new Github("default-github");
        Email email = new Email("default_email@gmail.com");
        Linkedin linkedin = new Linkedin("linkedin.com/in/default-linkedin");
        Set<Skill> skills = new HashSet<>();
        Set<Module> mods = new HashSet<>();
        return new Person(profilePicture, name, course, year, github, email, linkedin, skills, mods);
    }
    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public Name getName() {
        return name;
    }

    public Github getGithub() {
        return github;
    }

    public Email getEmail() {
        return email;
    }

    public Course getCourse() {
        return course;
    }

    public Year getYear() {
        return year;
    }

    public Linkedin getLinkedin() {
        return linkedin;
    }

    /**
     * Returns an immutable skill set, which throws {@code UnsupportedOperationException} if modification is attempted.
     */
    public Set<Skill> getSkills() {
        return Collections.unmodifiableSet(skills);
    }

    /**
     * Returns an immutable Module List, which throws {@code UnsupportedOperationException} if modification is
     * attempted.
     */
    public Set<Module> getModules() {
        return Collections.unmodifiableSet(modules);
    }

    /**
     * Returns true if both persons have the same email. This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both persons have the same identity and data fields. This defines a stronger notion of equality
     * between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getGithub().equals(getGithub())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getLinkedin().equals(getLinkedin())
                && otherPerson.getCourse().equals(getCourse())
                && otherPerson.getYear().equals(getYear())
                && otherPerson.getSkills().equals(getSkills())
                && otherPerson.getModules().equals(getModules());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, github, email, linkedin, skills, modules);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Year: ")
                .append(getYear())
                .append("; Course: ")
                .append(getCourse())
                .append("; Email: ")
                .append(getEmail());

        builder.append("; GitHub: ");
        Github github = getGithub();
        if (github.value == null) {
            builder.append("Not Added");
        } else {
            builder.append(github);
        }

        builder.append("; LinkedIn: ");
        Linkedin linkedin = getLinkedin();
        if (linkedin.value == null) {
            builder.append("Not Added");
        } else {
            builder.append(linkedin);
        }

        builder.append("; Modules: ");
        Set<Module> modules = getModules();
        if (modules.isEmpty()) {
            builder.append("Not Added");
        } else {
            modules.forEach(builder::append);
        }

        builder.append("; Skills: ");
        Set<Skill> skills = getSkills();
        if (skills.isEmpty()) {
            builder.append("Not Added");
        } else {
            skills.forEach(builder::append);
        }

        return builder.toString();
    }

}
