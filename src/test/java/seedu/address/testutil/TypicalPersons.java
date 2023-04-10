package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.EMAIL_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_BEN;
import static seedu.address.logic.commands.CommandTestUtil.NAME_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.NAME_BEN;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_BEN;
import static seedu.address.logic.commands.CommandTestUtil.STATION_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.STATION_BEN;
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
import static seedu.address.model.location.util.TypicalLocation.KAKI_BUKIT;
import static seedu.address.model.location.util.TypicalLocation.KENT_RIDGE;
import static seedu.address.model.location.util.TypicalLocation.KING_ALBERT_PARK;
import static seedu.address.model.location.util.TypicalLocation.KOVAN;
import static seedu.address.model.location.util.TypicalLocation.LAVENDER;
import static seedu.address.model.location.util.TypicalLocation.MARYMOUNT;
import static seedu.address.model.location.util.TypicalLocation.MAYFLOWER;
import static seedu.address.model.location.util.TypicalLocation.NEWTON;
import static seedu.address.model.location.util.TypicalLocation.NICOLL_HIGHWAY;
import static seedu.address.model.location.util.TypicalLocation.PASIR_RIS;
import static seedu.address.model.location.util.TypicalLocation.REDHILL;
import static seedu.address.model.location.util.TypicalLocation.SERANGOON;
import static seedu.address.model.tag.util.TypicalModuleTag.BT1101_DZ;
import static seedu.address.model.tag.util.TypicalModuleTag.BT2102_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CFG1002_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS1010J_DZ;
import static seedu.address.model.tag.util.TypicalModuleTag.CS1101S_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS1101S_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.CS1231S_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2030S_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2030S_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2030S_RU;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2040S_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2040S_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2040S_RU;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2100_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2100_RU;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2101_ALT_1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2101_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2101_KE;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2101_RU;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2101_SE;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2102_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2103T_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2103T_HA_ALT_1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2103T_KE;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2103T_RU;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2103T_SE;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2105_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2105_KE;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2106_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2106_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2106_KE;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2106_SE;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2108_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2109S_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2109S_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2109S_KE;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2109S_RU;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2109S_RU_ALT_1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2109S_SE;
import static seedu.address.model.tag.util.TypicalModuleTag.CS3223_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS3223_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.CS3230_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.CS3230_RU;
import static seedu.address.model.tag.util.TypicalModuleTag.CS3245_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS3245_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.CS4225_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.CS4230_RU;
import static seedu.address.model.tag.util.TypicalModuleTag.ES2660_F;
import static seedu.address.model.tag.util.TypicalModuleTag.ES2660_RU_ALT_1;
import static seedu.address.model.tag.util.TypicalModuleTag.ES2660_RU_ALT_2;
import static seedu.address.model.tag.util.TypicalModuleTag.GEA1000_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.GEC1030_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.GEN2050_F;
import static seedu.address.model.tag.util.TypicalModuleTag.GESS1019_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.IS1108_DZ;
import static seedu.address.model.tag.util.TypicalModuleTag.IS2218_DZ;
import static seedu.address.model.tag.util.TypicalModuleTag.LAJ1201_F;
import static seedu.address.model.tag.util.TypicalModuleTag.MA1521_DZ;
import static seedu.address.model.tag.util.TypicalModuleTag.MA1521_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.MA2001_F;
import static seedu.address.model.tag.util.TypicalModuleTag.MA2001_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.MA2101_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.MA2104_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.MA2104_RU;
import static seedu.address.model.tag.util.TypicalModuleTag.MA2104_RU_ALT_1;
import static seedu.address.model.tag.util.TypicalModuleTag.MA2104_RU_ALT_2;
import static seedu.address.model.tag.util.TypicalModuleTag.MA2108_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.MA2108_RU;
import static seedu.address.model.tag.util.TypicalModuleTag.MA3252_F;
import static seedu.address.model.tag.util.TypicalModuleTag.MA3252_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.MA3252_RU;
import static seedu.address.model.tag.util.TypicalModuleTag.MA3252_SE;
import static seedu.address.model.tag.util.TypicalModuleTag.ST2131_RU;
import static seedu.address.model.tag.util.TypicalModuleTag.ST2334_HA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import seedu.address.logic.parser.IndexHandler;
import seedu.address.model.EduMate;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALBERT = new PersonBuilder().withName("Albert Park")
            .withStation(KING_ALBERT_PARK)
            .withEmail("albertpark@gmail.com")
            .withPhone("89760441")
            .withTelegramHandle("@albertpark")
            .withGroupTags("TA")
            .withModuleTags()
            .build();

    public static final Person ANG = new PersonBuilder().withName("Ang Mei Hua")
            .withStation(MAYFLOWER)
            .withEmail("angmeihua@gmail.com")
            .withPhone("86203169").withTelegramHandle("@angmeihua")
            .withGroupTags("Study")
            .withModuleTags(MA2108_HA, MA2101_HA, CS3230_HA, CS2102_HA, CS2100_HA)
            .build();

    public static final Person BART = new PersonBuilder().withName("Bart Lee")
            .withStation(BARTLEY)
            .withEmail("bartlee@gmail.com")
            .withPhone("82630347")
            .withTelegramHandle("@bartlee")
            .withGroupTags("CCA")
            .withModuleTags(GEC1030_HA, GEA1000_HA, ST2334_HA, CS2030S_HA, CS2040S_HA)
            .build();

    public static final Person CLARK = new PersonBuilder().withName("Clark Kee")
            .withStation(CLARKE_QUAY)
            .withEmail("clarkkee@gmail.com")
            .withPhone("81342028").withTelegramHandle("@clarkkee")
            .withGroupTags("NUS")
            .withModuleTags(CS1101S_HA, GESS1019_HA, CS1231S_HA, MA2001_HA, MA1521_HA)
            .build();

    public static final Person DONG = new PersonBuilder().withName("Dong Yu Lang")
            .withStation(JURONG_EAST)
            .withEmail("dongyulang@gmail.com")
            .withPhone("86263617")
            .withTelegramHandle("@dongyulang")
            .withGroupTags("Groupmate")
            .withModuleTags(CS2030S_RU, CS2040S_RU, ES2660_RU_ALT_1,
                    MA2104_RU_ALT_1, ST2131_RU, CS2109S_RU_ALT_1)
            .build();

    public static final Person EDWARD = new PersonBuilder().withName("Edward Richards")
            .withStation(KENT_RIDGE)
            .withEmail("edwardrichards@gmail.com")
            .withPhone("92463693")
            .withTelegramHandle("@edwardrichards")
            .withGroupTags("CCA")
            .withModuleTags(BT1101_DZ, IS1108_DZ, IS2218_DZ, MA1521_DZ, CS1010J_DZ)
            .build();

    public static final Person FORD = new PersonBuilder().withName("Ford Canning")
            .withStation(FORT_CANNING)
            .withEmail("fordcanning@gmail.com")
            .withPhone("88032666")
            .withTelegramHandle("@fordcanning")
            .withGroupTags("TA")
            .withModuleTags(MA2104_RU_ALT_2, CS3230_RU, CS2100_RU, MA2108_RU, ES2660_RU_ALT_2)
            .build();

    public static final Person GEORGE = new PersonBuilder().withName("George Richards")
            .withStation(KENT_RIDGE)
            .withEmail("georgerichards@gmail.com")
            .withPhone("98796811")
            .withTelegramHandle("@georgerichards")
            .withGroupTags("CCA")
            .withModuleTags(CS2108_HA, CS3223_HA, CS3245_HA, CS2103T_HA_ALT_1, CS2101_ALT_1, CS4225_HA)
            .build();

    public static final Person HONG = new PersonBuilder().withName("Hong Shan")
            .withStation(REDHILL)
            .withEmail("hongshan@gmail.com")
            .withPhone("91345682")
            .withTelegramHandle("@hongshan")
            .withGroupTags("Groupmate")
            .withModuleTags(CS2108_HA, CS2106_HA, CS3245_HA, CS2103T_HA_ALT_1, CS2101_ALT_1)
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
            .withStation(NEWTON)
            .withEmail("isaacnewton@gmail.com")
            .withPhone("93175044")
            .withTelegramHandle("@isaacnewton")
            .withGroupTags("TA", "NS")
            .withModuleTags(MA2001_F, LAJ1201_F, CS1101S_F, MA3252_F, CS2106_F)
            .build();

    public static final Person JUKUN = new PersonBuilder().withName("Ju Kun")
            .withStation(JOO_KOON)
            .withEmail("jukun@gmail.com")
            .withPhone("84709651")
            .withTelegramHandle("@jukun")
            .withGroupTags("Groupmate")
            .withModuleTags(CS2040S_F, CS2106_F, CS2109S_F,
                    CS3245_F, CS3223_F, CS2105_F)
            .build();

    public static final Person KEVIN = new PersonBuilder().withName("Kevin Ho")
            .withStation(KOVAN)
            .withEmail("kevinho@gmail.com")
            .withPhone("99997496")
            .withTelegramHandle("@kevinho")
            .withGroupTags("NUS")
            .withModuleTags(GEN2050_F, CS3245_F, CS3223_F,
                    MA3252_F, LAJ1201_F, MA2001_F)
            .build();

    public static final Person LAO = new PersonBuilder().withName("Lao Ming Da")
            .withStation(LAVENDER)
            .withEmail("laomingda@gmail.com")
            .withPhone("83340546")
            .withTelegramHandle("@laomingda")
            .withGroupTags("Groupmate")
            .withModuleTags(ES2660_F, CS1101S_F, CS3245_F,
                    CS2106_F, MA3252_F, CS2040S_F)
            .build();

    public static final Person MARY = new PersonBuilder().withName("Mary Meng")
            .withStation(MARYMOUNT)
            .withEmail("marymeng@gmail.com")
            .withPhone("93816073")
            .withTelegramHandle("@marymeng")
            .withGroupTags("CCA")
            .withModuleTags(MA2001_F, LAJ1201_F, CS1101S_F,
                    MA3252_F, CS2106_F, ES2660_F)
            .build();

    public static final Person NICOLE = new PersonBuilder().withName("Nicole Hai Wei Ting")
            .withStation(NICOLL_HIGHWAY)
            .withEmail("nicolehaiweiting@gmail.com")
            .withPhone("92574283")
            .withTelegramHandle("@nicolehaiweiting")
            .withGroupTags("CCA")
            .withModuleTags(MA3252_F, CS3223_F, BT2102_F,
                    CS2030S_F, CS1101S_F, ES2660_F)
            .build();

    public static final Person OWEN = new PersonBuilder().withName("Owen Farrer")
            .withStation(FARRER_PARK)
            .withEmail("owenfarrer@gmail.com")
            .withPhone("97561235")
            .withTelegramHandle("@owenfarrer")
            .withGroupTags("CCA")
            .withModuleTags(MA2001_F, BT2102_F, CS2030S_F, CS2105_F,
                    CS2040S_F, LAJ1201_F, ES2660_F, CS1101S_F,
                    MA3252_F, CS2106_F, CS2109S_F, CS3245_F,
                    CS3223_F, GEN2050_F)
            .build();

    // our info

    public static final Person HALF = new PersonBuilder().withName("Half")
            .withStation(PASIR_RIS)
            .withEmail("half@gmail.com")
            .withPhone("91355634")
            .withTelegramHandle("@half00000")
            .withGroupTags("Groupmate")
            .withModuleTags(CS2103T_HA, CS2101_HA, CS2109S_HA,
                    CS2108_HA, MA2104_HA, MA3252_HA, CFG1002_F)
            .build();

    public static final Person FIREFOX = new PersonBuilder().withName("Fire Fox")
            .withStation(SERANGOON)
            .withEmail("firefox@gmail.com")
            .withPhone("95673158")
            .withTelegramHandle("@firefox")
            .withGroupTags("CCA", "NUS")
            .withModuleTags(CS2103T_SE, CS2101_SE, CS2109S_SE,
                    MA3252_SE, CS2106_SE, CFG1002_F)
            .build();

    public static final Person JINYONG = new PersonBuilder().withName("Jin Yong")
            .withStation(KAKI_BUKIT)
            .withEmail("jinyong@gmail.com")
            .withPhone("89654235")
            .withTelegramHandle("@jinyong")
            .withGroupTags("TA")
            .withModuleTags(CS2106_KE, CS2103T_KE, CS2101_KE, CS2105_KE, CS2109S_KE)
            .build();

    public static final Person KAIMIN = new PersonBuilder().withName("Kai Min")
            .withStation(SERANGOON)
            .withEmail("kaimin@gmail.com")
            .withPhone("89654235")
            .withTelegramHandle("@kaimin")
            .withGroupTags("CCA", "NUS")
            .withModuleTags(CS4230_RU, CS2109S_RU, CS2101_RU,
                    CS2103T_RU, MA3252_RU, MA2104_RU)
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person ALEX = new PersonBuilder().withName(NAME_ALEX).withPhone(PHONE_ALEX)
            .withEmail(EMAIL_ALEX).withStation(STATION_ALEX).withGroupTags(VALID_GROUP_2)
            .withModuleTags(VALID_MODULE_1, VALID_MODULE_2)
            .withTelegramHandle(TELEGRAM_HANDLE_ALEX).build();
    public static final Person BEN = new PersonBuilder().withName(NAME_BEN).withPhone(PHONE_BEN)
            .withEmail(EMAIL_BEN).withTelegramHandle(TELEGRAM_HANDLE_BEN)
            .withStation(STATION_BEN).withGroupTags(VALID_GROUP_1, VALID_GROUP_2)
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
                LAO, MARY, NICOLE, OWEN,
                HALF, FIREFOX, JINYONG, KAIMIN));
    }

    public static ContactIndex getContactIndexOfPerson(Person person) {
        return new ContactIndex(getTypicalPersons().indexOf(person) + 1);
    }

    public static Person getPersonFromIndexHandler(
            IndexHandler indexHandler, Person person) throws PersonNotFoundException {
        ContactIndex indexOwen = getContactIndexOfPerson(person);

        Optional<Person> optionalPerson = indexHandler.getPersonByIndex(indexOwen);

        if (optionalPerson.isEmpty()) {
            throw new PersonNotFoundException();
        }

        return optionalPerson.get();
    }

}
