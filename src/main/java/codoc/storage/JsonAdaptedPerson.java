package codoc.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import codoc.commons.exceptions.IllegalValueException;
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
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String profilePicturePath;
    private final String name;
    private final String course;
    private final String year;
    private final String github;
    private final String email;
    private final String linkedin;
    private final List<JsonAdaptedSkill> skills = new ArrayList<>();
    private final List<JsonAdaptedModule> modules = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(
            @JsonProperty("profilePicturePath") String profilePicturePath,
            @JsonProperty("name") String name,
            @JsonProperty("course") String course,
            @JsonProperty("year") String year,
            @JsonProperty("github") String github,
            @JsonProperty("email") String email,
            @JsonProperty("linkedin") String linkedin,
            @JsonProperty("skills") List<JsonAdaptedSkill> skills,
            @JsonProperty("modules") List<JsonAdaptedModule> modules
    ) {
        this.profilePicturePath = profilePicturePath;
        this.name = name;
        this.course = course;
        this.year = year;
        this.github = github;
        this.email = email;
        this.linkedin = linkedin;
        if (skills != null) {
            this.skills.addAll(skills);
        }
        if (modules != null) {
            this.modules.addAll(modules);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        profilePicturePath = source.getProfilePicture().profilePicturePath;
        name = source.getName().fullName;
        course = source.getCourse().course;
        year = source.getYear().year;
        github = source.getGithub().value;
        email = source.getEmail().value;
        linkedin = source.getLinkedin().value;
        skills.addAll(source.getSkills().stream()
                .map(JsonAdaptedSkill::new)
                .collect(Collectors.toList()));
        modules.addAll(source.getModules().stream()
                .map(JsonAdaptedModule::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Skill> personSkills = new ArrayList<>();
        final List<Module> personModules = new ArrayList<>();
        for (JsonAdaptedSkill skill : skills) {
            personSkills.add(skill.toModelType());
        }
        for (JsonAdaptedModule module : modules) {
            personModules.add(module.toModelType());
        }
        if (profilePicturePath == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ProfilePicture.class.getSimpleName()));
        }
        final ProfilePicture modelProfilePicture = new ProfilePicture(profilePicturePath);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (github != null && !Github.isValidGithub(github)) {
            throw new IllegalValueException(Github.MESSAGE_CONSTRAINTS);
        }
        final Github modelGithub = new Github(github);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (course != null && !Course.isValidCourse(Course.getIndex(course))) {
            throw new IllegalValueException(Course.MESSAGE_CONSTRAINTS);
        }
        final Course modelCourse = new Course(Course.getIndex(course));

        if (year != null && !Year.isValidYear(year)) {
            throw new IllegalValueException(Year.MESSAGE_CONSTRAINTS);
        }
        final Year modelYear = new Year(year);

        if (linkedin != null && !Linkedin.isValidLinkedin(linkedin)) {
            throw new IllegalValueException(Linkedin.MESSAGE_CONSTRAINTS);
        }
        final Linkedin modelLinkedin = new Linkedin(linkedin);

        final Set<Skill> modelSkills = new HashSet<>(personSkills);
        final Set<Module> modelModules = new HashSet<>(personModules);
        return new Person(
                modelProfilePicture,
                modelName,
                modelCourse,
                modelYear,
                modelGithub,
                modelEmail,
                modelLinkedin,
                modelSkills,
                modelModules
        );
    }

}
