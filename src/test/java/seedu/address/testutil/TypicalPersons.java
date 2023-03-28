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
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_2;
import static seedu.address.model.location.util.TypicalLocation.BARTLEY;
import static seedu.address.model.location.util.TypicalLocation.CLARKE_QUAY;
import static seedu.address.model.location.util.TypicalLocation.FARRER_PARK;
import static seedu.address.model.location.util.TypicalLocation.FORT_CANNING;
import static seedu.address.model.location.util.TypicalLocation.JOO_KOON;
import static seedu.address.model.location.util.TypicalLocation.JURONG_EAST;
import static seedu.address.model.location.util.TypicalLocation.KENT_RIDGE;
import static seedu.address.model.location.util.TypicalLocation.KING_ALBERT_PARK;
import static seedu.address.model.location.util.TypicalLocation.KOVAN;
import static seedu.address.model.location.util.TypicalLocation.LAVENDER;
import static seedu.address.model.location.util.TypicalLocation.MARYMOUNT;
import static seedu.address.model.location.util.TypicalLocation.MAYFLOWER;
import static seedu.address.model.location.util.TypicalLocation.NEWTON;
import static seedu.address.model.location.util.TypicalLocation.NICOLL_HIGHWAY;
import static seedu.address.model.location.util.TypicalLocation.REDHILL;
import static seedu.address.model.tag.util.TypicalModuleTag.MODULE_TAG_SET_1;
import static seedu.address.model.tag.util.TypicalModuleTag.MODULE_TAG_SET_10;
import static seedu.address.model.tag.util.TypicalModuleTag.MODULE_TAG_SET_11;
import static seedu.address.model.tag.util.TypicalModuleTag.MODULE_TAG_SET_12;
import static seedu.address.model.tag.util.TypicalModuleTag.MODULE_TAG_SET_13;
import static seedu.address.model.tag.util.TypicalModuleTag.MODULE_TAG_SET_14;
import static seedu.address.model.tag.util.TypicalModuleTag.MODULE_TAG_SET_15;
import static seedu.address.model.tag.util.TypicalModuleTag.MODULE_TAG_SET_2;
import static seedu.address.model.tag.util.TypicalModuleTag.MODULE_TAG_SET_3;
import static seedu.address.model.tag.util.TypicalModuleTag.MODULE_TAG_SET_4;
import static seedu.address.model.tag.util.TypicalModuleTag.MODULE_TAG_SET_5;
import static seedu.address.model.tag.util.TypicalModuleTag.MODULE_TAG_SET_6;
import static seedu.address.model.tag.util.TypicalModuleTag.MODULE_TAG_SET_7;
import static seedu.address.model.tag.util.TypicalModuleTag.MODULE_TAG_SET_8;
import static seedu.address.model.tag.util.TypicalModuleTag.MODULE_TAG_SET_9;
import static seedu.address.model.tag.util.TypicalModuleTag.MODULE_TAG_SET_F;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.EduMate;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALBERT = new PersonBuilder().withName("Albert Park")
            .withAddress(KING_ALBERT_PARK)
            .withEmail("albertpark@gmail.com")
            .withPhone("89760441")
            .withTelegramHandle("@albertpark")
            .withGroupTags("TA")
            .withModuleTags(MODULE_TAG_SET_1)
            .build();

    public static final Person ANG = new PersonBuilder().withName("Ang Mei Hua")
            .withAddress(MAYFLOWER)
            .withEmail("angmeihua@gmail.com")
            .withPhone("86203169").withTelegramHandle("@angmeihua")
            .withGroupTags("Study")
            .withModuleTags(MODULE_TAG_SET_2)
            .build();

    public static final Person BART = new PersonBuilder().withName("Bart Lee")
            .withAddress(BARTLEY)
            .withEmail("bartlee@gmail.com")
            .withPhone("82630347")
            .withTelegramHandle("@bartlee")
            .withGroupTags("CCA")
            .withModuleTags(MODULE_TAG_SET_3)
            .build();

    public static final Person CLARK = new PersonBuilder().withName("Clark Kee")
            .withAddress(CLARKE_QUAY)
            .withEmail("clarkkee@gmail.com")
            .withPhone("81342028").withTelegramHandle("@clarkkee")
            .withGroupTags("NUS")
            .withModuleTags(MODULE_TAG_SET_4)
            .build();

    public static final Person DONG = new PersonBuilder().withName("Dong Yu Lang")
            .withAddress(JURONG_EAST)
            .withEmail("dongyulang@gmail.com")
            .withPhone("86263617")
            .withTelegramHandle("@dongyulang")
            .withGroupTags("Groupmate")
            .withModuleTags(MODULE_TAG_SET_5)
            .build();

    public static final Person EDWARD = new PersonBuilder().withName("Edward Richards")
            .withAddress(KENT_RIDGE)
            .withEmail("edwardrichards@gmail.com")
            .withPhone("92463693")
            .withTelegramHandle("@edwardrichards")
            .withGroupTags("CCA")
            .withModuleTags(MODULE_TAG_SET_6)
            .build();

    public static final Person FORD = new PersonBuilder().withName("Ford Canning")
            .withAddress(FORT_CANNING)
            .withEmail("fordcanning@gmail.com")
            .withPhone("88032666")
            .withTelegramHandle("@fordcanning")
            .withGroupTags("TA")
            .withModuleTags(MODULE_TAG_SET_7)
            .build();

    public static final Person GEORGE = new PersonBuilder().withName("George Richards")
            .withAddress(KENT_RIDGE)
            .withEmail("georgerichards@gmail.com")
            .withPhone("98796811")
            .withTelegramHandle("@georgerichards")
            .withGroupTags("CCA")
            .withModuleTags(MODULE_TAG_SET_8).build();

    public static final Person HONG = new PersonBuilder().withName("Hong Shan")
            .withAddress(REDHILL)
            .withEmail("hongshan@gmail.com")
            .withPhone("91345682")
            .withTelegramHandle("@hongshan")
            .withGroupTags("Groupmate")
            .withModuleTags(MODULE_TAG_SET_9)
            .build();

    // ISAAC onwards have the same module tag set as OWEN.

    public static final Person ISAAC = new PersonBuilder().withName("Isaac Newton")
            .withAddress(NEWTON)
            .withEmail("isaacnewton@gmail.com")
            .withPhone("93175044")
            .withTelegramHandle("@isaacnewton")
            .withGroupTags("TA", "NS")
            .withModuleTags(MODULE_TAG_SET_10)
            .build();

    public static final Person JUKUN = new PersonBuilder().withName("Ju Kun")
            .withAddress(JOO_KOON)
            .withEmail("jukun@gmail.com")
            .withPhone("84709651")
            .withTelegramHandle("@jukun")
            .withGroupTags("Groupmate")
            .withModuleTags(MODULE_TAG_SET_11)
            .build();

    public static final Person KEVIN = new PersonBuilder().withName("Kevin Ho")
            .withAddress(KOVAN)
            .withEmail("kevinho@gmail.com")
            .withPhone("99997496")
            .withTelegramHandle("@kevinho")
            .withGroupTags("NUS")
            .withModuleTags(MODULE_TAG_SET_12).build();

    public static final Person LAO = new PersonBuilder().withName("Lao Ming Da")
            .withAddress(LAVENDER)
            .withEmail("laomingda@gmail.com")
            .withPhone("83340546")
            .withTelegramHandle("@laomingda")
            .withGroupTags("Groupmate")
            .withModuleTags(MODULE_TAG_SET_13)
            .build();

    public static final Person MARY = new PersonBuilder().withName("Mary Meng")
            .withAddress(MARYMOUNT)
            .withEmail("marymeng@gmail.com")
            .withPhone("93816073")
            .withTelegramHandle("@marymeng")
            .withGroupTags("CCA")
            .withModuleTags(MODULE_TAG_SET_14)
            .build();

    public static final Person NICOLE = new PersonBuilder().withName("Nicole Hai Wei Ting")
            .withAddress(NICOLL_HIGHWAY)
            .withEmail("nicolehaiweiting@gmail.com")
            .withPhone("92574283")
            .withTelegramHandle("@nicolehaiweiting")
            .withGroupTags("CCA")
            .withModuleTags(MODULE_TAG_SET_15)
            .build();

    public static final Person OWEN = new PersonBuilder().withName("Owen Farrer")
            .withAddress(FARRER_PARK)
            .withEmail("owenfarrer@gmail.com")
            .withPhone("97561235")
            .withTelegramHandle("@owenfarrer")
            .withGroupTags("CCA")
            .withModuleTags(MODULE_TAG_SET_F)
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person ALEX = new PersonBuilder().withName(NAME_ALEX).withPhone(PHONE_ALEX)
            .withEmail(EMAIL_ALEX).withAddress(ADDRESS_ALEX).withGroupTags(VALID_GROUP_2)
            .withModuleTags(VALID_MODULE_1, VALID_MODULE_2)
            .withTelegramHandle(TELEGRAM_HANDLE_ALEX).build();
    public static final Person BEN = new PersonBuilder().withName(NAME_BEN).withPhone(PHONE_BEN)
            .withEmail(EMAIL_BEN).withTelegramHandle(TELEGRAM_HANDLE_BEN)
            .withAddress(ADDRESS_BEN).withGroupTags(VALID_GROUP_1, VALID_GROUP_2)
            .withModuleTags(VALID_MODULE_2)
            .build();

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code EduMate} with all the typical persons.
     */
    public static EduMate getTypicalEduMate() {
        EduMate ab = new EduMate();
        int index = 1;
        for (Person person : getTypicalPersons()) {
            Person copiedPerson = new PersonBuilder(person).build();
            copiedPerson.setContactIndex(new ContactIndex(index));
            System.out.println(copiedPerson.toString());
            ab.addPerson(copiedPerson);
            index += 1;
        }
        ab.setUser(TypicalUser.getTypicalUser());
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(
                ALBERT, ANG, BART, CLARK,
                DONG, EDWARD, FORD, GEORGE,
                HONG, ISAAC, JUKUN, KEVIN,
                LAO, MARY, NICOLE, OWEN));
    }

}
