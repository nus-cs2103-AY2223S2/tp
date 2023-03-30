package seedu.address.model.tag.util;

import static seedu.address.model.timetable.util.TypicalLesson.BT1101_MON_12PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.BT1101_TUE_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.BT1101_WED_4PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.BT2102_MON_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.BT2102_TUE_2PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS1010J_TUE_2PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS1010J_WED_2PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS1101S_FRI_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS1101S_MON_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS1101S_THU_11AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS1101S_TUE_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS1101S_WED_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS1101S_WED_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS1231S_FRI_3PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS1231S_THU_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS1231S_WED_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2030S_MON_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2030S_THU_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2030S_THU_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2030S_THU_1PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2030S_THU_2PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2030S_WED_8AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2040S_FRI_10AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2040S_MON_4PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2040S_THU_10AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2040S_THU_9AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2040S_TUE_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2040S_TUE_9AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2040S_WED_2PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2040S_WED_3PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2100_FRI_11AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2100_FRI_9AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2100_TUE_4PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2100_WED_11AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2100_WED_12PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2101_FRI_4PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2101_MON_8AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2101_THU_8AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2101_TUE_4PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2102_FRI_10AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2102_TUE_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2103T_FRI_2PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2103T_THU_1PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2103T_WED_2PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2105_MON_3PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2105_THU_10AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2105_THU_4PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2106_MON_1PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2106_THU_11AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2106_THU_2PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2106_THU_3PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2106_THU_4PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2106_THU_5PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2106_WED_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2106_WED_3PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2106_WED_4PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2108_THU_5PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2108_TUE_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2109S_MON_2PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2109S_TUE_4PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2109S_WED_12PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2109S_WED_1PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2109S_WED_3PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2109S_WED_5PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS3223_FRI_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS3223_THU_10AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS3230_TUE_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS3230_WED_10AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS3245_FRI_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS3245_THU_11AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS3245_THU_12PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS4225_THU_2PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS4225_THU_4PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS4230_THU_4PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS4230_TUE_2PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.ES2660_FRI_8AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.ES2660_MON_2PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.ES2660_THU_2PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.ES2660_TUE_8AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.GEA1000_THU_3PM_3HR;
import static seedu.address.model.timetable.util.TypicalLesson.GEC1030_MON_8AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.GEC1030_TUE_4PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.GEN2050_FRI_4PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.GESS1019_MON_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.GESS1019_TUE_2PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.IS1108_MON_2PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.IS1108_THU_9AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.IS2218_MON_9AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.LAJ1201_MON_6PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.LAJ1201_THU_6PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.LAJ1201_TUE_6PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA1521_FRI_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA1521_FRI_6PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA1521_MON_8AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA1521_THU_2PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA1521_WED_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA1521_WED_6PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2001_FRI_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2001_FRI_3PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2001_MON_8AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2001_THU_8AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2001_WED_2PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2101_FRI_4PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2101_WED_1PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2101_WED_4PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2104_FRI_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2104_FRI_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2104_MON_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2104_MON_11AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2104_THU_3PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2104_TUE_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2104_WED_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2104_WED_12PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2108_FRI_2PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2108_THU_9AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2108_TUE_2PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA3252_FRI_7PM_3HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA3252_WED_10AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA3252_WED_9AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.ST2131_FRI_6PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.ST2131_TUE_6PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.ST2334_FRI_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.ST2334_MON_2PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.ST2334_TUE_12PM_2HR;

import seedu.address.model.tag.ModuleTag;

/**
 * A typical Module Tag consisting of a module code and lessons.
 * Lessons are extracted from {@code TypicalLesson}.
 */
public class TypicalModuleTag {

    public static final ModuleTag CS1010J_DZ = new ModuleTagBuilder()
            .withModuleCode("CS1010J")
            .withLessons(CS1010J_TUE_2PM_2HR, CS1010J_WED_2PM_2HR)
            .build();

    public static final ModuleTag CS1101S_HA = new ModuleTagBuilder()
            .withModuleCode("CS1101S")
            .withLessons(CS1101S_MON_12PM_2HR,
                    CS1101S_WED_10AM_2HR,
                    CS1101S_THU_11AM_1HR,
                    CS1101S_FRI_10AM_2HR)
            .build();

    public static final ModuleTag CS1101S_F = new ModuleTagBuilder()
            .withModuleCode("CS1101S")
            .withLessons(CS1101S_TUE_12PM_2HR,
                    CS1101S_WED_12PM_2HR,
                    CS1101S_THU_11AM_1HR)
            .build();

    public static final ModuleTag CS1231S_HA = new ModuleTagBuilder()
            .withModuleCode("CS1231S")
            .withLessons(CS1231S_WED_12PM_2HR, CS1231S_THU_12PM_2HR, CS1231S_FRI_3PM_1HR)
            .build();

    public static final ModuleTag CS2030S_HA = new ModuleTagBuilder()
            .withModuleCode("CS2030S")
            .withLessons(CS2030S_MON_12PM_2HR,
                    CS2030S_THU_10AM_2HR,
                    CS2030S_THU_1PM_1HR)
            .build();

    public static final ModuleTag CS2030S_RU = new ModuleTagBuilder()
            .withModuleCode("CS2030S")
            .withLessons(CS2030S_MON_12PM_2HR,
                    CS2030S_WED_8AM_1HR,
                    CS2030S_THU_12PM_2HR)
            .build();

    public static final ModuleTag CS2030S_F = new ModuleTagBuilder()
            .withModuleCode("CS2030S")
            .withLessons(CS2030S_MON_12PM_2HR,
                    CS2030S_THU_1PM_1HR,
                    CS2030S_THU_2PM_2HR)
            .build();

    public static final ModuleTag CS2040S_HA = new ModuleTagBuilder()
            .withModuleCode("CS2040S")
            .withLessons(CS2040S_MON_4PM_2HR,
                    CS2040S_TUE_9AM_2HR,
                    CS2040S_WED_2PM_1HR,
                    CS2040S_FRI_10AM_1HR)
            .build();

    public static final ModuleTag CS2040S_RU = new ModuleTagBuilder()
            .withModuleCode("CS2040S")
            .withLessons(CS2040S_MON_4PM_2HR,
                    CS2040S_WED_2PM_1HR,
                    CS2040S_WED_3PM_2HR,
                    CS2040S_THU_9AM_1HR)
            .build();

    public static final ModuleTag CS2040S_F = new ModuleTagBuilder()
            .withModuleCode("CS2040S")
            .withLessons(CS2040S_MON_4PM_2HR,
                    CS2040S_TUE_10AM_2HR,
                    CS2040S_THU_10AM_1HR,
                    CS2040S_WED_2PM_1HR)
            .build();

    public static final ModuleTag CS2100_HA = new ModuleTagBuilder()
            .withModuleCode("CS2100")
            .withLessons(CS2100_TUE_4PM_2HR,
                    CS2100_WED_11AM_1HR,
                    CS2100_FRI_11AM_1HR)
            .build();

    public static final ModuleTag CS2100_RU = new ModuleTagBuilder()
            .withModuleCode("CS2100")
            .withLessons(CS2100_FRI_9AM_1HR,
                    CS2100_TUE_4PM_2HR,
                    CS2100_WED_12PM_1HR)
            .build();

    public static final ModuleTag CS2101_HA = new ModuleTagBuilder()
            .withModuleCode("CS2101")
            .withLessons(CS2101_MON_8AM_2HR, CS2101_THU_8AM_2HR)
            .build();

    public static final ModuleTag CS2101_SE = new ModuleTagBuilder()
            .withModuleCode("CS2101")
            .withLessons(CS2101_MON_8AM_2HR, CS2101_THU_8AM_2HR)
            .build();

    public static final ModuleTag CS2101_KE = new ModuleTagBuilder()
            .withModuleCode("CS2101")
            .withLessons(CS2101_MON_8AM_2HR, CS2101_THU_8AM_2HR)
            .build();

    public static final ModuleTag CS2101_RU = new ModuleTagBuilder()
            .withModuleCode("CS2101")
            .withLessons(CS2101_MON_8AM_2HR, CS2101_THU_8AM_2HR)
            .build();

    public static final ModuleTag CS2101_ALT_1 = new ModuleTagBuilder()
            .withModuleCode("CS2101")
            .withLessons(CS2101_TUE_4PM_2HR, CS2101_FRI_4PM_2HR)
            .build();

    public static final ModuleTag CS2102_HA = new ModuleTagBuilder()
            .withModuleCode("CS2102")
            .withLessons(CS2102_TUE_12PM_2HR, CS2102_FRI_10AM_1HR)
            .build();

    public static final ModuleTag CS2103T_HA = new ModuleTagBuilder()
            .withModuleCode("CS2103T")
            .withLessons(CS2103T_FRI_2PM_2HR, CS2103T_WED_2PM_1HR)
            .build();

    public static final ModuleTag CS2103T_KE = new ModuleTagBuilder()
            .withModuleCode("CS2103T")
            .withLessons(CS2103T_FRI_2PM_2HR, CS2103T_WED_2PM_1HR)
            .build();

    public static final ModuleTag CS2103T_RU = new ModuleTagBuilder()
            .withModuleCode("CS2103T")
            .withLessons(CS2103T_FRI_2PM_2HR, CS2103T_WED_2PM_1HR)
            .build();

    public static final ModuleTag CS2103T_SE = new ModuleTagBuilder()
            .withModuleCode("CS2103T")
            .withLessons(CS2103T_FRI_2PM_2HR, CS2103T_WED_2PM_1HR)
            .build();

    public static final ModuleTag CS2103T_HA_ALT_1 = new ModuleTagBuilder()
            .withModuleCode("CS2103T")
            .withLessons(CS2103T_THU_1PM_1HR, CS2103T_FRI_2PM_2HR)
            .build();

    public static final ModuleTag CS2105_KE = new ModuleTagBuilder()
            .withModuleCode("CS2105")
            .withLessons(CS2105_THU_10AM_1HR, CS2105_THU_4PM_2HR)
            .build();

    public static final ModuleTag CS2105_F = new ModuleTagBuilder()
            .withModuleCode("CS2105")
            .withLessons(CS2105_MON_3PM_1HR, CS2105_THU_4PM_2HR)
            .build();

    public static final ModuleTag CS2106_HA = new ModuleTagBuilder()
            .withModuleCode("CS2106")
            .withLessons(CS2106_WED_10AM_2HR,
                    CS2106_THU_2PM_1HR,
                    CS2106_THU_3PM_1HR)
            .build();

    public static final ModuleTag CS2106_KE = new ModuleTagBuilder()
            .withModuleCode("CS2106")
            .withLessons(CS2106_WED_10AM_2HR,
                    CS2106_MON_1PM_1HR,
                    CS2106_THU_11AM_1HR)
            .build();

    public static final ModuleTag CS2106_SE = new ModuleTagBuilder()
            .withModuleCode("CS2106")
            .withLessons(CS2106_WED_10AM_2HR,
                    CS2106_THU_4PM_1HR,
                    CS2106_THU_5PM_1HR)
            .build();

    public static final ModuleTag CS2106_F = new ModuleTagBuilder()
            .withModuleCode("CS2106")
            .withLessons(CS2106_WED_10AM_2HR,
                    CS2106_WED_3PM_1HR,
                    CS2106_WED_4PM_1HR)
            .build();

    public static final ModuleTag CS2108_HA = new ModuleTagBuilder()
            .withModuleCode("CS2108")
            .withLessons(CS2108_TUE_10AM_2HR, CS2108_THU_5PM_1HR)
            .build();

    public static final ModuleTag CS2109S_HA = new ModuleTagBuilder()
            .withModuleCode("CS2109S")
            .withLessons(CS2109S_TUE_4PM_2HR, CS2109S_WED_3PM_1HR)
            .build();

    public static final ModuleTag CS2109S_KE = new ModuleTagBuilder()
            .withModuleCode("CS2109S")
            .withLessons(CS2109S_TUE_4PM_2HR, CS2109S_WED_12PM_1HR)
            .build();

    public static final ModuleTag CS2109S_SE = new ModuleTagBuilder()
            .withModuleCode("CS2109S")
            .withLessons(CS2109S_TUE_4PM_2HR, CS2109S_WED_12PM_1HR)
            .build();

    public static final ModuleTag CS2109S_RU = new ModuleTagBuilder()
            .withModuleCode("CS2109S")
            .withLessons(CS2109S_TUE_4PM_2HR, CS2109S_WED_12PM_1HR)
            .build();

    public static final ModuleTag CS2109S_RU_ALT_1 = new ModuleTagBuilder()
            .withModuleCode("CS2109S")
            .withLessons(CS2109S_MON_2PM_2HR, CS2109S_WED_1PM_1HR)
            .build();

    public static final ModuleTag CS2109S_F = new ModuleTagBuilder()
            .withModuleCode("CS2109S")
            .withLessons(CS2109S_TUE_4PM_2HR, CS2109S_WED_5PM_1HR)
            .build();

    public static final ModuleTag CS3223_HA = new ModuleTagBuilder()
            .withModuleCode("CS3223")
            .withLessons(CS3223_THU_10AM_1HR, CS3223_FRI_10AM_2HR)
            .build();

    public static final ModuleTag CS3223_F = new ModuleTagBuilder()
            .withModuleCode("CS3223")
            .withLessons(CS3223_FRI_10AM_2HR)
            .build();

    public static final ModuleTag CS3230_HA = new ModuleTagBuilder()
            .withModuleCode("CS3230")
            .withLessons(CS3230_TUE_10AM_2HR, CS3230_WED_10AM_1HR)
            .build();

    public static final ModuleTag CS3230_RU = new ModuleTagBuilder()
            .withModuleCode("CS3230")
            .withLessons(CS3230_TUE_10AM_2HR, CS3230_WED_10AM_1HR)
            .build();

    public static final ModuleTag CS3245_HA = new ModuleTagBuilder()
            .withModuleCode("CS3245")
            .withLessons(CS3245_THU_11AM_1HR, CS3245_FRI_12PM_2HR)
            .build();

    public static final ModuleTag CS3245_F = new ModuleTagBuilder()
            .withModuleCode("CS3245")
            .withLessons(CS3245_THU_12PM_1HR, CS3245_FRI_12PM_2HR)
            .build();

    public static final ModuleTag CS4225_HA = new ModuleTagBuilder()
            .withModuleCode("CS4225")
            .withLessons(CS4225_THU_2PM_2HR, CS4225_THU_4PM_1HR)
            .build();

    public static final ModuleTag CS4230_RU = new ModuleTagBuilder()
            .withModuleCode("CS4230")
            .withLessons(CS4230_TUE_2PM_1HR, CS4230_THU_4PM_2HR)
            .build();

    public static final ModuleTag BT1101_DZ = new ModuleTagBuilder()
            .withModuleCode("BT1101")
            .withLessons(BT1101_MON_12PM_1HR,
                    BT1101_TUE_12PM_2HR,
                    BT1101_WED_4PM_2HR)
            .build();

    public static final ModuleTag BT2102_F = new ModuleTagBuilder()
            .withModuleCode("BT2102")
            .withLessons(BT2102_MON_10AM_2HR, BT2102_TUE_2PM_2HR)
            .build();

    public static final ModuleTag IS1108_DZ = new ModuleTagBuilder()
            .withModuleCode("IS1108")
            .withLessons(IS1108_MON_2PM_2HR, IS1108_THU_9AM_2HR)
            .build();

    public static final ModuleTag IS2218_DZ = new ModuleTagBuilder()
            .withModuleCode("IS2218")
            .withLessons(IS2218_MON_9AM_2HR)
            .build();

    public static final ModuleTag MA1521_HA = new ModuleTagBuilder()
            .withModuleCode("MA1521")
            .withLessons(MA1521_WED_6PM_2HR,
                    MA1521_THU_2PM_1HR,
                    MA1521_FRI_6PM_2HR)
            .build();

    public static final ModuleTag MA1521_DZ = new ModuleTagBuilder()
            .withModuleCode("MA1521")
            .withLessons(MA1521_MON_8AM_1HR,
                    MA1521_WED_10AM_2HR,
                    MA1521_FRI_10AM_2HR)
            .build();

    public static final ModuleTag MA2001_HA = new ModuleTagBuilder()
            .withModuleCode("MA2001")
            .withLessons(MA2001_WED_2PM_1HR, MA2001_FRI_12PM_2HR)
            .build();

    public static final ModuleTag MA2001_F = new ModuleTagBuilder()
            .withModuleCode("MA2001")
            .withLessons(MA2001_MON_8AM_2HR,
                    MA2001_THU_8AM_2HR,
                    MA2001_FRI_3PM_1HR)
            .build();

    public static final ModuleTag MA2101_HA = new ModuleTagBuilder()
            .withModuleCode("MA2101")
            .withLessons(MA2101_WED_1PM_1HR, MA2101_WED_4PM_2HR, MA2101_FRI_4PM_2HR)
            .build();

    public static final ModuleTag MA2104_HA = new ModuleTagBuilder()
            .withModuleCode("MA2104")
            .withLessons(MA2104_TUE_12PM_2HR,
                    MA2104_WED_12PM_1HR,
                    MA2104_FRI_12PM_2HR)
            .build();

    public static final ModuleTag MA2104_RU = new ModuleTagBuilder()
            .withModuleCode("MA2104")
            .withLessons(MA2104_TUE_12PM_2HR,
                    MA2104_MON_11AM_1HR,
                    MA2104_FRI_12PM_2HR)
            .build();

    public static final ModuleTag MA2104_RU_ALT_1 = new ModuleTagBuilder()
            .withModuleCode("MA2104")
            .withLessons(MA2104_WED_10AM_2HR,
                    MA2104_THU_3PM_1HR,
                    MA2104_FRI_10AM_2HR)
            .build();

    public static final ModuleTag MA2104_RU_ALT_2 = new ModuleTagBuilder()
            .withModuleCode("MA2104")
            .withLessons(MA2104_MON_10AM_2HR,
                    MA2104_FRI_10AM_2HR)
            .build();

    public static final ModuleTag MA2108_HA = new ModuleTagBuilder()
            .withModuleCode("MA2108")
            .withLessons(MA2108_TUE_2PM_2HR, MA2108_THU_9AM_1HR, MA2108_FRI_2PM_2HR)
            .build();

    public static final ModuleTag MA2108_RU = new ModuleTagBuilder()
            .withModuleCode("MA2108")
            .withLessons(MA2108_TUE_2PM_2HR, MA2108_THU_9AM_1HR, MA2108_FRI_2PM_2HR)
            .build();

    public static final ModuleTag MA3252_HA = new ModuleTagBuilder()
            .withModuleCode("MA3252")
            .withLessons(MA3252_WED_10AM_1HR, MA3252_FRI_7PM_3HR)
            .build();

    public static final ModuleTag MA3252_SE = new ModuleTagBuilder()
            .withModuleCode("MA3252")
            .withLessons(MA3252_WED_9AM_1HR, MA3252_FRI_7PM_3HR)
            .build();

    public static final ModuleTag MA3252_RU = new ModuleTagBuilder()
            .withModuleCode("MA3252")
            .withLessons(MA3252_WED_10AM_1HR, MA3252_FRI_7PM_3HR)
            .build();

    public static final ModuleTag MA3252_F = new ModuleTagBuilder()
            .withModuleCode("MA3252")
            .withLessons(MA3252_WED_9AM_1HR, MA3252_FRI_7PM_3HR)
            .build();

    public static final ModuleTag ST2131_RU = new ModuleTagBuilder()
            .withModuleCode("ST2131")
            .withLessons(ST2131_TUE_6PM_2HR, ST2131_FRI_6PM_2HR)
            .build();

    public static final ModuleTag ST2334_HA = new ModuleTagBuilder()
            .withModuleCode("ST2334")
            .withLessons(ST2334_MON_2PM_1HR, ST2334_TUE_12PM_2HR, ST2334_FRI_12PM_2HR)
            .build();

    public static final ModuleTag GEC1030_HA = new ModuleTagBuilder()
            .withModuleCode("GEC1030")
            .withLessons(GEC1030_MON_8AM_2HR, GEC1030_TUE_4PM_2HR)
            .build();

    public static final ModuleTag GEA1000_HA = new ModuleTagBuilder()
            .withModuleCode("GEA1000")
            .withLessons(GEA1000_THU_3PM_3HR)
            .build();

    public static final ModuleTag GESS1019_HA = new ModuleTagBuilder()
            .withModuleCode("GESS1019")
            .withLessons(GESS1019_MON_10AM_2HR, GESS1019_TUE_2PM_2HR)
            .build();

    public static final ModuleTag GEN2050_F = new ModuleTagBuilder()
            .withModuleCode("GEN2050")
            .withLessons(GEN2050_FRI_4PM_2HR)
            .build();

    public static final ModuleTag ES2660_RU_ALT_1 = new ModuleTagBuilder()
            .withModuleCode("ES2660")
            .withLessons(ES2660_TUE_8AM_2HR, ES2660_FRI_8AM_2HR)
            .build();

    public static final ModuleTag ES2660_RU_ALT_2 = new ModuleTagBuilder()
            .withModuleCode("ES2660")
            .withLessons(ES2660_MON_2PM_2HR, ES2660_THU_2PM_2HR)
            .build();

    public static final ModuleTag ES2660_F = new ModuleTagBuilder()
            .withModuleCode("ES2660")
            .withLessons(ES2660_TUE_8AM_2HR, ES2660_FRI_8AM_2HR)
            .build();

    public static final ModuleTag LAJ1201_F = new ModuleTagBuilder()
            .withModuleCode("LAJ1201")
            .withLessons(LAJ1201_MON_6PM_2HR,
                    LAJ1201_TUE_6PM_2HR,
                    LAJ1201_THU_6PM_2HR)
            .build();

    public static final ModuleTag CFG1002_F = new ModuleTagBuilder()
            .withModuleCode("CFG1002")
            .build();
}

