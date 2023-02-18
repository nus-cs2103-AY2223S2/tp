package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_BOB;

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
            .withGroupTags("TA")
            .withModuleTags("CS2113T", "CE3115", "IS3221", "CS3241", "BT2102", "CE3116").build();

    public static final Person BART = new PersonBuilder().withName("Bart Lee")
            .withAddress("90 Bartley Road").withEmail("bartlee@gmail.com")
            .withPhone("82630347").withTelegramHandle("@bartlee")
            .withGroupTags("CCA")
            .withModuleTags("CE2183", "CS1010XCP", "CS1010R", "CS3223", "CS3245", "CS3211").build();

    public static final Person CLARK = new PersonBuilder().withName("Clark Kee")
            .withAddress("10 Eu Tong Sen Street").withEmail("clarkkee@gmail.com")
            .withPhone("81342028").withTelegramHandle("@clarkkee")
            .withGroupTags("NUS")
            .withModuleTags("CS2107", "CS3282", "CE3102", "BT2101", "CS3244R", "CS3236").build();

    public static final Person DAWSON = new PersonBuilder().withName("Dawson Quentin")
            .withAddress("301 Commonwealth Avenue").withEmail("dawsonquentin@gmail.com")
            .withPhone("87190565").withTelegramHandle("@dawsonquentin")
            .withGroupTags("Groupmate")
            .withModuleTags("IS3261", "BT2010", "CS2030", "CS2040", "CS3245").build();

    public static final Person EDWARD = new PersonBuilder().withName("Edward Richards")
            .withAddress("301 South Buona Vista Road").withEmail("edwardrichards@gmail.com")
            .withPhone("92463693").withTelegramHandle("@edwardrichards")
            .withGroupTags("CCA")
            .withModuleTags("CS2010", "CE1101", "IS3103", "CS2103", "CS2040", "CS3245").build();

    public static final Person FORD = new PersonBuilder().withName("Ford Canning")
            .withAddress("170 River Valley Road").withEmail("fordcanning@gmail.com")
            .withPhone("88032666").withTelegramHandle("@fordcanning")
            .withGroupTags("TA")
            .withModuleTags("CE3115", "CS1010R", "CS3281", "CS2030", "CS3219", "CS3245R").build();

    public static final Person GEORGE = new PersonBuilder().withName("George Richards")
            .withAddress("301 South Buona Vista Road").withEmail("georgerichards@gmail.com")
            .withPhone("98796811").withTelegramHandle("@georgerichards")
            .withGroupTags("CCA")
            .withModuleTags("CE3165", "IS3261", "IS1103", "IS3103", "CS2309", "CS1010").build();

    public static final Person HONG = new PersonBuilder().withName("Hong Shan")
            .withAddress("920 Tiong Bahru Road").withEmail("hongshan@gmail.com")
            .withPhone("91345682").withTelegramHandle("@hongshan")
            .withGroupTags("Groupmate")
            .withModuleTags("CS2107", "BT2010", "CS3235", "CE3121", "CS2220", "BT2101").build();

    public static final Person ISAAC = new PersonBuilder().withName("Isaac Newton")
            .withAddress("49 Scotts Road").withEmail("isaacnewton@gmail.com")
            .withPhone("93175044").withTelegramHandle("@isaacnewton")
            .withGroupTags("TA", "NS")
            .withModuleTags("IS2103", "IS3106", "CS1010X", "CS3233", "CS3245", "CS2104").build();

    public static final Person JOOKOON = new PersonBuilder().withName("Joo Koon")
            .withAddress("91 Joo Koon Circle").withEmail("jookoon@gmail.com")
            .withPhone("84709651").withTelegramHandle("@jookoon")
            .withGroupTags("Groupmate")
            .withModuleTags("CE3101", "IS1103", "CE2112", "CS2108", "CS3103", "CS2113T").build();

    public static final Person KEVIN = new PersonBuilder().withName("Kevin Ho")
            .withAddress("900 Upper Serangoon Road").withEmail("kevinho@gmail.com")
            .withPhone("99997496").withTelegramHandle("@kevinho")
            .withGroupTags("NUS")
            .withModuleTags("CS2220", "CS3223", "CS3242", "CS3203", "CE3165", "CS3281").build();

    public static final Person LAVENDER = new PersonBuilder().withName("Lavender Crawford")
            .withAddress("50 Kallang Road").withEmail("lavendercrawford@gmail.com")
            .withPhone("83340546").withTelegramHandle("@lavendercrawford")
            .withGroupTags("Groupmate")
            .withModuleTags("IS3106", "CS1010X", "CS2102", "CS1010J", "CS2103T", "CS2113T").build();

    public static final Person MARY = new PersonBuilder().withName("Mary Meng")
            .withAddress("60 Marymount Road").withEmail("marymeng@gmail.com")
            .withPhone("93816073").withTelegramHandle("@marymeng")
            .withGroupTags("CCA")
            .withModuleTags("CS2106", "CS3282", "CS2108", "CS3203", "CS2103T", "CS2104").build();

    public static final Person NICOLE = new PersonBuilder().withName("Nicole Hai Wei Ting")
            .withAddress("20 Republic Avenue").withEmail("nicolehaiweiting@gmail.com")
            .withPhone("92574283").withTelegramHandle("@nicolehaiweiting")
            .withGroupTags("CCA")
            .withModuleTags("CS2101", "IS1103", "CS1010E", "CS3211", "IS3240", "CS1010J").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withGroupTags(VALID_TAG_FRIEND)
            .withTelegramHandle(VALID_TELEGRAM_HANDLE_AMY).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTelegramHandle(VALID_TELEGRAM_HANDLE_BOB)
            .withAddress(VALID_ADDRESS_BOB).withGroupTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
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
        return new ArrayList<>(Arrays.asList(ALBERT, BART, CLARK,
                DAWSON, EDWARD, FORD, GEORGE, HONG, ISAAC, JOOKOON, KEVIN, LAVENDER, MARY, NICOLE));
    }

}
