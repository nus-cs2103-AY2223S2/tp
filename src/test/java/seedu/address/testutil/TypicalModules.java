package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESOURCE_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESOURCE_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEACHER_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEACHER_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_CS3230;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ModuleTracker;
import seedu.address.model.module.Module;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {

    public static final Module CS2106_TUT = new ModuleBuilder().withName("CS2106")
            .withAddress("COM1-0217").withTimeSlot("290323 12:00")
            .withResource("https://nus-cs2103-ay2223s2.github.io/website/schedule/week12/index.html")
            .withTags("Tutorial").build();
    public static final Module CS2103T_LEC = new ModuleBuilder().withName("CS2103T")
            .withAddress("I3-Aud")
            .withTimeSlot("290323 12:00")
            .withTags("Lecture").build();
    public static final Module CS2101_OP = new ModuleBuilder().withName("CS2101")
            .withTimeSlot("040423 10:00").withAddress("COM1-0210").withTags("Presentation").build();
    public static final Module CS1231S_TUT = new ModuleBuilder().withName("CS1231S")
            .withTimeSlot("290323 12:00").withAddress("COM3").withTags("Tutorial").build();
    public static final Module CS1101S_LEC = new ModuleBuilder().withName("CS1101S")
            .withTimeSlot("290323 12:00").withAddress("Hybrid").withTags("Lecture").build();

    // Manually added
    public static final Module CS1231S_LEC = new ModuleBuilder().withName("CS1231S")
            .withTimeSlot("290323 12:00").withAddress("LT19").withTags("Lecture").build();
    public static final Module CS2030S_LAB = new ModuleBuilder().withName("CS2030S").withTags("Lab")
            .withTimeSlot("290323 12:00").withAddress("AS5").build();

    // Manually added - Module's details found in {@code CommandTestUtil}
    public static final Module CS3230 = new ModuleBuilder().withName(VALID_NAME_CS3230)
            .withTimeSlot(VALID_TIMESLOT_CS3230)
            .withAddress(VALID_ADDRESS_CS3230)
            .withResource(VALID_RESOURCE_CS3230)
            .withDeadline(VALID_DEADLINE_CS3230)
            .withTeacher(VALID_TEACHER_CS3230)
            .withRemark(VALID_REMARK_CS3230)
            .withTags(VALID_TAG_TUTORIAL).build();
    public static final Module CS3219 = new ModuleBuilder().withName(VALID_NAME_CS3219)
            .withResource(VALID_RESOURCE_CS3219)
            .withTimeSlot(VALID_TIMESLOT_CS3219).withAddress(VALID_ADDRESS_CS3219)
            .withTags(VALID_TAG_LECTURE).withDeadline(VALID_DEADLINE_CS3219).withRemark(VALID_REMARK_CS3219)
            .withTeacher(VALID_TEACHER_CS3219)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalModules() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical modules.
     */
    public static ModuleTracker getTypicalAddressBook() {
        ModuleTracker ab = new ModuleTracker();
        for (Module module : getTypicalModules()) {
            ab.addModule(module);
        }
        return ab;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2106_TUT, CS2103T_LEC, CS2101_OP, CS1231S_TUT, CS1101S_LEC));
    }
}
