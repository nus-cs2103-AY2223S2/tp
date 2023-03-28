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
import static seedu.address.model.tag.util.TypicalModuleTag.BT1101_V3;
import static seedu.address.model.tag.util.TypicalModuleTag.BT2102_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS1010J_V3;
import static seedu.address.model.tag.util.TypicalModuleTag.CS1101S_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS1101S_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS1231S_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2030S_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2030S_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2030S_V2;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2040S_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2040S_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2040S_V2;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2100_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2100_V2;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2101_V1_1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2101_V1_2;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2102_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2103T_V1_1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2103T_V1_2;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2105_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2106_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2106_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2108_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2109S_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2109S_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2109S_V2_1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS3223_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS3223_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS3230_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS3230_V2;
import static seedu.address.model.tag.util.TypicalModuleTag.CS3245_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS3245_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS4225_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.ES2660_F;
import static seedu.address.model.tag.util.TypicalModuleTag.ES2660_V2_1;
import static seedu.address.model.tag.util.TypicalModuleTag.ES2660_V2_2;
import static seedu.address.model.tag.util.TypicalModuleTag.GEA1000_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.GEC1030_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.GEN2050_F;
import static seedu.address.model.tag.util.TypicalModuleTag.GESS1019_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.IS1108_V3;
import static seedu.address.model.tag.util.TypicalModuleTag.IS2218_V3;
import static seedu.address.model.tag.util.TypicalModuleTag.LAJ1201_F;
import static seedu.address.model.tag.util.TypicalModuleTag.MA1521_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.MA1521_V3;
import static seedu.address.model.tag.util.TypicalModuleTag.MA2001_F;
import static seedu.address.model.tag.util.TypicalModuleTag.MA2001_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.MA2101_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.MA2104_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.MA2104_V2_1;
import static seedu.address.model.tag.util.TypicalModuleTag.MA2104_V2_2;
import static seedu.address.model.tag.util.TypicalModuleTag.MA2108_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.MA2108_V2;
import static seedu.address.model.tag.util.TypicalModuleTag.MA3252_F;
import static seedu.address.model.tag.util.TypicalModuleTag.MA3252_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.ST2131_V2;
import static seedu.address.model.tag.util.TypicalModuleTag.ST2334_V1;

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
            .withModuleTags(CS2103T_V1_1, CS2101_V1_1, CS2109S_V1,
                    CS2108_V1, MA2104_V1, MA3252_V1)
            .build();

    public static final Person ANG = new PersonBuilder().withName("Ang Mei Hua")
            .withAddress(MAYFLOWER)
            .withEmail("angmeihua@gmail.com")
            .withPhone("86203169").withTelegramHandle("@angmeihua")
            .withGroupTags("Study")
            .withModuleTags(MA2108_V1, MA2101_V1, CS3230_V1, CS2102_V1, CS2100_V1)
            .build();

    public static final Person BART = new PersonBuilder().withName("Bart Lee")
            .withAddress(BARTLEY)
            .withEmail("bartlee@gmail.com")
            .withPhone("82630347")
            .withTelegramHandle("@bartlee")
            .withGroupTags("CCA")
            .withModuleTags(GEC1030_V1, GEA1000_V1, ST2334_V1, CS2030S_V1, CS2040S_V1)
            .build();

    public static final Person CLARK = new PersonBuilder().withName("Clark Kee")
            .withAddress(CLARKE_QUAY)
            .withEmail("clarkkee@gmail.com")
            .withPhone("81342028").withTelegramHandle("@clarkkee")
            .withGroupTags("NUS")
            .withModuleTags(CS1101S_V1, GESS1019_V1, CS1231S_V1, MA2001_V1, MA1521_V1)
            .build();

    public static final Person DONG = new PersonBuilder().withName("Dong Yu Lang")
            .withAddress(JURONG_EAST)
            .withEmail("dongyulang@gmail.com")
            .withPhone("86263617")
            .withTelegramHandle("@dongyulang")
            .withGroupTags("Groupmate")
            .withModuleTags(CS2030S_V2, CS2040S_V2, ES2660_V2_1,
                    MA2104_V2_1, ST2131_V2, CS2109S_V2_1)
            .build();

    public static final Person EDWARD = new PersonBuilder().withName("Edward Richards")
            .withAddress(KENT_RIDGE)
            .withEmail("edwardrichards@gmail.com")
            .withPhone("92463693")
            .withTelegramHandle("@edwardrichards")
            .withGroupTags("CCA")
            .withModuleTags(BT1101_V3, IS1108_V3, IS2218_V3, MA1521_V3, CS1010J_V3)
            .build();

    public static final Person FORD = new PersonBuilder().withName("Ford Canning")
            .withAddress(FORT_CANNING)
            .withEmail("fordcanning@gmail.com")
            .withPhone("88032666")
            .withTelegramHandle("@fordcanning")
            .withGroupTags("TA")
            .withModuleTags(MA2104_V2_2, CS3230_V2, CS2100_V2, MA2108_V2, ES2660_V2_2)
            .build();

    public static final Person GEORGE = new PersonBuilder().withName("George Richards")
            .withAddress(KENT_RIDGE)
            .withEmail("georgerichards@gmail.com")
            .withPhone("98796811")
            .withTelegramHandle("@georgerichards")
            .withGroupTags("CCA")
            .withModuleTags(CS2108_V1, CS3223_V1, CS3245_V1, CS2103T_V1_2, CS2101_V1_2, CS4225_V1)
            .build();

    public static final Person HONG = new PersonBuilder().withName("Hong Shan")
            .withAddress(REDHILL)
            .withEmail("hongshan@gmail.com")
            .withPhone("91345682")
            .withTelegramHandle("@hongshan")
            .withGroupTags("Groupmate")
            .withModuleTags(CS2108_V1, CS2106_V1, CS3245_V1, CS2103T_V1_2, CS2101_V1_2)
            .build();

    // ISAAC onwards have the same module tag set as OWEN.

    // https://nusmods.com/timetable/sem-2/share?
    // BT2102=LAB:03,LEC:1&
    // CS1101S=REC:03,TUT:04,LEC:1&
    // CS2030S=LAB:14A,REC:16,LEC:1&
    // CS2040S=TUT:08,REC:02,LEC:1&
    // CS2101=&CS2105=LEC:1,TUT:03&
    // CS2106=LAB:13,TUT:14,LEC:1&
    // CS2109S=LEC:1,TUT:10&
    // CS3223=TUT:6,LEC:1&
    // CS3245=TUT:3,LEC:1&
    // ES2660=SEC:G12&
    // GEN2050=TUT:04&
    // LAJ1201=TUT:A9,TUT2:B8,LEC:3&
    // MA2001=TUT:19,LEC:1&
    // MA3252=TUT:2,LEC:1

    public static final Person ISAAC = new PersonBuilder().withName("Isaac Newton")
            .withAddress(NEWTON)
            .withEmail("isaacnewton@gmail.com")
            .withPhone("93175044")
            .withTelegramHandle("@isaacnewton")
            .withGroupTags("TA", "NS")
            .withModuleTags(MA2001_F, LAJ1201_F, CS1101S_F, MA3252_F, CS2106_F)
            .build();

    public static final Person JUKUN = new PersonBuilder().withName("Ju Kun")
            .withAddress(JOO_KOON)
            .withEmail("jukun@gmail.com")
            .withPhone("84709651")
            .withTelegramHandle("@jukun")
            .withGroupTags("Groupmate")
            .withModuleTags(CS2040S_F, CS2106_F, CS2109S_F,
                    CS3245_F, CS3223_F, CS2105_F)
            .build();

    public static final Person KEVIN = new PersonBuilder().withName("Kevin Ho")
            .withAddress(KOVAN)
            .withEmail("kevinho@gmail.com")
            .withPhone("99997496")
            .withTelegramHandle("@kevinho")
            .withGroupTags("NUS")
            .withModuleTags(GEN2050_F, CS3245_F, CS3223_F,
                    MA3252_F, LAJ1201_F, MA2001_F)
            .build();

    public static final Person LAO = new PersonBuilder().withName("Lao Ming Da")
            .withAddress(LAVENDER)
            .withEmail("laomingda@gmail.com")
            .withPhone("83340546")
            .withTelegramHandle("@laomingda")
            .withGroupTags("Groupmate")
            .withModuleTags(ES2660_F, CS1101S_F, CS3245_F,
                    CS2106_F, MA3252_F, CS2040S_F)
            .build();

    public static final Person MARY = new PersonBuilder().withName("Mary Meng")
            .withAddress(MARYMOUNT)
            .withEmail("marymeng@gmail.com")
            .withPhone("93816073")
            .withTelegramHandle("@marymeng")
            .withGroupTags("CCA")
            .withModuleTags(MA2001_F, LAJ1201_F, CS1101S_F,
                    MA3252_F, CS2106_F, ES2660_F)
            .build();

    public static final Person NICOLE = new PersonBuilder().withName("Nicole Hai Wei Ting")
            .withAddress(NICOLL_HIGHWAY)
            .withEmail("nicolehaiweiting@gmail.com")
            .withPhone("92574283")
            .withTelegramHandle("@nicolehaiweiting")
            .withGroupTags("CCA")
            .withModuleTags(MA3252_F, CS3223_F, BT2102_F,
                    CS2030S_F, CS1101S_F, ES2660_F)
            .build();

    public static final Person OWEN = new PersonBuilder().withName("Owen Farrer")
            .withAddress(FARRER_PARK)
            .withEmail("owenfarrer@gmail.com")
            .withPhone("97561235")
            .withTelegramHandle("@owenfarrer")
            .withGroupTags("CCA")
            .withModuleTags(MA2001_F, BT2102_F, CS2030S_F, CS2105_F,
                    CS2040S_F, LAJ1201_F, ES2660_F, CS1101S_F,
                    MA3252_F, CS2106_F, CS2109S_F, CS3245_F,
                    CS3223_F, GEN2050_F)
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
            Person copiedPerson = person.setContactIndex(new ContactIndex(index));
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

    public static ContactIndex getContactIndexOfPerson(Person person) {
        return new ContactIndex(getTypicalPersons().indexOf(person) + 1);
    }

}
