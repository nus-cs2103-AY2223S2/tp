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
            .withAddress("2 Blackmore Drive").withEmail("albertpark@gmail.com")
            .withPhone("89760441").withTelegramHandle("@albertpark")
            .withContactIndex(1)
            .withGroupTags("TA")
            .withModuleTags("CS2113T", "CE3115", "IS3221", "CS3241", "BT2102", "CE3116").build();

    public static final Person ANG = new PersonBuilder().withName("Ang Mei Hua")
            .withAddress("91 Ang Mo Kio Avenue 4").withEmail("angmeihua@gmail.com")
            .withPhone("92752656").withTelegramHandle("@angmeihua")
            .withContactIndex(2)
            .withGroupTags("Study", "Mayflower")
            .withModuleTags("CE3121", "CS3219", "CS3216", "CE3115", "CS3234", "IS3261").build();

    public static final Person BART = new PersonBuilder().withName("Bart Lee")
            .withAddress("90 Bartley Road").withEmail("bartlee@gmail.com")
            .withPhone("82630347").withTelegramHandle("@bartlee")
            .withContactIndex(3)
            .withGroupTags("CCA")
            .withModuleTags("CE2183", "CS1010XCP", "CS1010R", "CS3223", "CS3245", "CS3211").build();

    public static final Person CLARK = new PersonBuilder().withName("Clark Kee")
            .withAddress("10 Eu Tong Sen Street").withEmail("clarkkee@gmail.com")
            .withPhone("81342028").withTelegramHandle("@clarkkee")
            .withContactIndex(4)
            .withGroupTags("NUS")
            .withModuleTags("CS2107", "CS3282", "CE3102", "BT2101", "CS3244R", "CS3236").build();

    public static final Person DAWSON = new PersonBuilder().withName("Dawson Quentin")
            .withAddress("301 Commonwealth Avenue").withEmail("dawsonquentin@gmail.com")
            .withPhone("87190565").withTelegramHandle("@dawsonquentin")
            .withContactIndex(5)
            .withGroupTags("Groupmate")
            .withModuleTags("IS3261", "BT2010", "CS2030", "CS2040", "CS3245").build();

    public static final Person EDWARD = new PersonBuilder().withName("Edward Richards")
            .withAddress("301 South Buona Vista Road").withEmail("edwardrichards@gmail.com")
            .withPhone("92463693").withTelegramHandle("@edwardrichards")
            .withContactIndex(6)
            .withGroupTags("CCA")
            .withModuleTags("CS2010", "CE1101", "IS3103", "CS2103", "CS2040", "CS3245").build();

    public static final Person FORD = new PersonBuilder().withName("Ford Canning")
            .withAddress("170 River Valley Road").withEmail("fordcanning@gmail.com")
            .withPhone("88032666").withTelegramHandle("@fordcanning")
            .withContactIndex(7)
            .withGroupTags("TA")
            .withModuleTags("CE3115", "CS1010R", "CS3281", "CS2030", "CS3219", "CS3245R").build();

    public static final Person GEORGE = new PersonBuilder().withName("George Richards")
            .withAddress("301 South Buona Vista Road").withEmail("georgerichards@gmail.com")
            .withPhone("98796811").withTelegramHandle("@georgerichards")
            .withContactIndex(8)
            .withGroupTags("CCA")
            .withModuleTags("CE3165", "IS3261", "IS1103", "IS3103", "CS2309", "CS1010").build();

    public static final Person HONG = new PersonBuilder().withName("Hong Shan")
            .withAddress("920 Tiong Bahru Road").withEmail("hongshan@gmail.com")
            .withPhone("91345682").withTelegramHandle("@hongshan")
            .withContactIndex(9)
            .withGroupTags("Groupmate")
            .withModuleTags("CS2107", "BT2010", "CS3235", "CE3121", "CS2220", "BT2101").build();

    public static final Person ISAAC = new PersonBuilder().withName("Isaac Newton")
            .withAddress("49 Scotts Road").withEmail("isaacnewton@gmail.com")
            .withPhone("93175044").withTelegramHandle("@isaacnewton")
            .withContactIndex(10)
            .withGroupTags("TA", "NS")
            .withModuleTags("IS2103", "IS3106", "CS1010X", "CS3233", "CS3245", "CS2104").build();

    public static final Person JOOKOON = new PersonBuilder().withName("Joo Koon")
            .withAddress("91 Joo Koon Circle").withEmail("jookoon@gmail.com")
            .withPhone("84709651").withTelegramHandle("@jookoon")
            .withContactIndex(11)
            .withGroupTags("Groupmate")
            .withModuleTags("CE3101", "IS1103", "CE2112", "CS2108", "CS3103", "CS2113T").build();

    public static final Person KEVIN = new PersonBuilder().withName("Kevin Ho")
            .withAddress("900 Upper Serangoon Road").withEmail("kevinho@gmail.com")
            .withPhone("99997496").withTelegramHandle("@kevinho")
            .withContactIndex(12)
            .withGroupTags("NUS")
            .withModuleTags("CS2220", "CS3223", "CS3242", "CS3203", "CE3165", "CS3281").build();

    public static final Person LAVENDER = new PersonBuilder().withName("Lavender Crawford")
            .withAddress("50 Kallang Road").withEmail("lavendercrawford@gmail.com")
            .withPhone("83340546").withTelegramHandle("@lavendercrawford")
            .withContactIndex(13)
            .withGroupTags("Groupmate")
            .withModuleTags("IS3106", "CS1010X", "CS2102", "CS1010J", "CS2103T", "CS2113T").build();

    public static final Person MARY = new PersonBuilder().withName("Mary Meng")
            .withAddress("60 Marymount Road").withEmail("marymeng@gmail.com")
            .withPhone("93816073").withTelegramHandle("@marymeng")
            .withContactIndex(14)
            .withGroupTags("CCA")
            .withModuleTags("CS2106", "CS3282", "CS2108", "CS3203", "CS2103T", "CS2104").build();

    public static final Person NICOLE = new PersonBuilder().withName("Nicole Hai Wei Ting")
            .withAddress("20 Republic Avenue").withEmail("nicolehaiweiting@gmail.com")
            .withPhone("92574283").withTelegramHandle("@nicolehaiweiting")
            .withContactIndex(15)
            .withGroupTags("CCA")
            .withModuleTags("CS2101", "IS1103", "CS1010E", "CS3211", "IS3240", "CS1010J").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person ALEX = new PersonBuilder().withName(NAME_ALEX).withPhone(PHONE_ALEX)
            .withEmail(EMAIL_ALEX).withAddress(ADDRESS_ALEX).withGroupTags(VALID_GROUP_2)
            .withTelegramHandle(TELEGRAM_HANDLE_ALEX).build();
    public static final Person BEN = new PersonBuilder().withName(NAME_BEN).withPhone(PHONE_BEN)
            .withEmail(EMAIL_BEN).withTelegramHandle(TELEGRAM_HANDLE_BEN)
            .withAddress(ADDRESS_BEN).withGroupTags(VALID_GROUP_1, VALID_GROUP_2)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

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
                DAWSON, EDWARD, FORD, GEORGE, HONG, ISAAC, JOOKOON, KEVIN, LAVENDER, MARY, NICOLE));
    }

}
