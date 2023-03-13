package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.module.Module;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.skill.Skill;
import seedu.address.model.util.SampleDataUtil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_AY2223S2_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_CSHARP;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final Skill DEFAULT_SKILL = new Skill(VALID_SKILL_CSHARP);
    public static final Module DEFAULT_MODULE = new Module(VALID_MODULE_AY2223S2_CS2103T);



    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Skill> skills;
    private Set<Module> mods;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
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
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
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
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
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
        return new Person(name, phone, email, address, skills, mods);
    }

    public Person buildEditedPerson() {
        name = new Name("Test name");
        phone = new Phone("987654321");
        email = new Email("test@gmail.com");
        address = new Address("Bishan Street 21 #07-90");
        skills = new HashSet<>();
        skills.add(DEFAULT_SKILL);
        skills.add(new Skill("Solidity"));
        mods = new HashSet<>();
        mods.add(DEFAULT_MODULE);
        mods.add(new Module("AY2122S1 GEA1000"));

        return new Person(name, phone, email, address, skills, mods);
    }

}
