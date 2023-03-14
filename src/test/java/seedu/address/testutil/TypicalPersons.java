package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_BEN;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_BEN;
import static seedu.address.logic.commands.CommandTestUtil.NAME_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.NAME_BEN;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_BEN;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_HANDLE_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_HANDLE_BEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.EduMate;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALBERT = new PersonBuilder().withName("Albert Park")
            .withAddress("King Albert Park").withEmail("albertpark@gmail.com")
            .withPhone("89760441").withTelegramHandle("@albertpark")
            .withContactIndex(1)
            .withGroupTags("TA")
            .withModuleTags("CS2105", "CS2104", "CS2103", "CS1010", "CHC5338", "BT2103").build();

    public static final Person ANG = new PersonBuilder().withName("Ang Mei Hua")
            .withAddress("Mayflower").withEmail("angmeihua@gmail.com")
            .withPhone("86203169").withTelegramHandle("@angmeihua")
            .withContactIndex(2)
            .withGroupTags("Study")
            .withModuleTags("CS2108", "GEN2061", "CS2107", "DSA2102", "CS2102", "CS2106").build();

    public static final Person BART = new PersonBuilder().withName("Bart Lee")
            .withAddress("Bartley").withEmail("bartlee@gmail.com")
            .withPhone("82630347").withTelegramHandle("@bartlee")
            .withContactIndex(3)
            .withGroupTags("CCA")
            .withModuleTags("CE2183", "CS2108", "PL3551R", "CS2113", "CS2107", "CS3216").build();

    public static final Person CLARK = new PersonBuilder().withName("Clark Kee")
            .withAddress("Clarke Quay").withEmail("clarkkee@gmail.com")
            .withPhone("81342028").withTelegramHandle("@clarkkee")
            .withContactIndex(4)
            .withGroupTags("NUS")
            .withModuleTags("CS2103", "CE2003", "CS2105", "IE4102", "SC3206", "CS2106").build();

    public static final Person DAKOTA = new PersonBuilder().withName("Dakota Dunman")
            .withAddress("Dakota").withEmail("dakotadunman@gmail.com")
            .withPhone("86263617").withTelegramHandle("@dakotadunman")
            .withContactIndex(5)
            .withGroupTags("CCA")
            .withModuleTags("GE2102", "QF3101", "LLJ5203", "CS2104", "ST2132", "CM3261").build();

    public static final Person EDWARD = new PersonBuilder().withName("Edward Richards")
            .withAddress("Kent Ridge").withEmail("edwardrichards@gmail.com")
            .withPhone("92463693").withTelegramHandle("@edwardrichards")
            .withContactIndex(6)
            .withGroupTags("CCA")
            .withModuleTags("CS2105", "CS2309", "CS2104", "CS2100", "ST3289", "CE4103").build();

    public static final Person FORD = new PersonBuilder().withName("Ford Canning")
            .withAddress("Fort Canning").withEmail("fordcanning@gmail.com")
            .withPhone("88032666").withTelegramHandle("@fordcanning")
            .withContactIndex(7)
            .withGroupTags("TA")
            .withModuleTags("CS2101", "CS2103", "CS2108", "CS2030S", "CS2102", "CS2107").build();

    public static final Person GEORGE = new PersonBuilder().withName("George Richards")
            .withAddress("Kent Ridge").withEmail("georgerichards@gmail.com")
            .withPhone("98796811").withTelegramHandle("@georgerichards")
            .withContactIndex(8)
            .withGroupTags("CCA")
            .withModuleTags("CS4249", "CS2105", "GE2103", "BT2101", "CS2108", "CS2103R").build();

    public static final Person HONG = new PersonBuilder().withName("Hong Shan")
            .withAddress("Redhill").withEmail("hongshan@gmail.com")
            .withPhone("91345682").withTelegramHandle("@hongshan")
            .withContactIndex(9)
            .withGroupTags("Groupmate")
            .withModuleTags("MA2101", "CS2104", "CS2113", "LSM3259", "CE2407A", "CS2107").build();

    public static final Person ISAAC = new PersonBuilder().withName("Isaac Newton")
            .withAddress("Newton").withEmail("isaacnewton@gmail.com")
            .withPhone("93175044").withTelegramHandle("@isaacnewton")
            .withContactIndex(10)
            .withGroupTags("TA", "NS")
            .withModuleTags("CE5315", "CS2109S", "CS2113T", "CS2105", "CS2107", "CS2104").build();

    public static final Person JUKUN = new PersonBuilder().withName("Ju Kun")
            .withAddress("Joo Koon").withEmail("jukun@gmail.com")
            .withPhone("84709651").withTelegramHandle("@jukun")
            .withContactIndex(11)
            .withGroupTags("Groupmate")
            .withModuleTags("CS2101", "DSA2101", "BN3301", "CS2106", "CS2108", "CS2107").build();

    public static final Person KEVIN = new PersonBuilder().withName("Kevin Ho")
            .withAddress("Kovan").withEmail("kevinho@gmail.com")
            .withPhone("99997496").withTelegramHandle("@kevinho")
            .withContactIndex(12)
            .withGroupTags("NUS")
            .withModuleTags("IS2101", "CS2030S", "LAC2201", "YSC1207", "CS2113T", "CS2107").build();

    public static final Person LAVENDER = new PersonBuilder().withName("Lavender Crawford")
            .withAddress("Lavender").withEmail("lavendercrawford@gmail.com")
            .withPhone("83340546").withTelegramHandle("@lavendercrawford")
            .withContactIndex(13)
            .withGroupTags("Groupmate")
            .withModuleTags("BT2102", "CS2113T", "CS2113", "CS4243", "CS2100", "IS2103").build();

    public static final Person MARY = new PersonBuilder().withName("Mary Meng")
            .withAddress("Marymount").withEmail("marymeng@gmail.com")
            .withPhone("93816073").withTelegramHandle("@marymeng")
            .withContactIndex(14)
            .withGroupTags("CCA")
            .withModuleTags("CS2103", "CS2104", "CS2106", "LL4377V", "CS2102", "ID4106").build();

    public static final Person NICOLE = new PersonBuilder().withName("Nicole Hai Wei Ting")
            .withAddress("Nicoll Highway").withEmail("nicolehaiweiting@gmail.com")
            .withPhone("92574283").withTelegramHandle("@nicolehaiweiting")
            .withContactIndex(15)
            .withGroupTags("CCA")
            .withModuleTags("NGN2001E", "CS3203", "LL5394", "CS2105", "CS3242", "CS2107").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person ALEX = new PersonBuilder().withName(NAME_ALEX).withPhone(PHONE_ALEX)
            .withEmail(EMAIL_ALEX).withAddress(ADDRESS_ALEX).withGroupTags(VALID_GROUP_2)
            .withTelegramHandle(TELEGRAM_HANDLE_ALEX).build();
    public static final Person BEN = new PersonBuilder().withName(NAME_BEN).withPhone(PHONE_BEN)
            .withEmail(EMAIL_BEN).withTelegramHandle(TELEGRAM_HANDLE_BEN)
            .withAddress(ADDRESS_BEN).withGroupTags(VALID_GROUP_1, VALID_GROUP_2)
            .build();

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code EduMate} with all the typical persons.
     */
    public static EduMate getTypicalEduMate() {
        EduMate ab = new EduMate();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        ab.setUser(TypicalUser.getTypicalUser());
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALBERT, ANG, BART, CLARK,
                DAKOTA, EDWARD, FORD, GEORGE, HONG, ISAAC, JUKUN, KEVIN, LAVENDER, MARY, NICOLE));
    }

}
