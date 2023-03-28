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
import static seedu.address.model.timetable.util.TypicalLesson.CS2105_THU_4PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2106_THU_2PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2106_THU_3PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2106_WED_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2106_WED_3PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2106_WED_4PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2108_THU_5PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2108_TUE_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2109S_MON_2PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2109S_TUE_4PM_2HR;
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

import java.util.Set;

import seedu.address.model.tag.ModuleTag;

public class TypicalModuleTag {

    public static final ModuleTag CS1010J_V3 = new ModuleTagBuilder()
            .withModuleCode("CS1010J")
            .withExtraLessons(CS1010J_TUE_2PM_2HR, CS1010J_WED_2PM_2HR)
            .build();

    public static final ModuleTag CS1101S_V1 = new ModuleTagBuilder()
            .withModuleCode("CS1101S")
            .withExtraLessons(CS1101S_MON_12PM_2HR,
                    CS1101S_WED_10AM_2HR,
                    CS1101S_THU_11AM_1HR,
                    CS1101S_FRI_10AM_2HR)
            .build();

    public static final ModuleTag CS1101S_F = new ModuleTagBuilder()
            .withModuleCode("CS1101S")
            .withExtraLessons(CS1101S_TUE_12PM_2HR,
                    CS1101S_WED_12PM_2HR,
                    CS1101S_THU_11AM_1HR)
            .build();

    public static final ModuleTag CS1231S_V1 = new ModuleTagBuilder()
            .withModuleCode("CS1231S")
            .withExtraLessons(CS1231S_WED_12PM_2HR, CS1231S_THU_12PM_2HR, CS1231S_FRI_3PM_1HR)
            .build();

    public static final ModuleTag CS2030S_V1 = new ModuleTagBuilder()
            .withModuleCode("CS2030S")
            .withExtraLessons(CS2030S_MON_12PM_2HR,
                    CS2030S_THU_10AM_2HR,
                    CS2030S_THU_1PM_1HR)
            .build();

    public static final ModuleTag CS2030S_V2 = new ModuleTagBuilder()
            .withModuleCode("CS2030S")
            .withExtraLessons(CS2030S_MON_12PM_2HR,
                    CS2030S_WED_8AM_1HR,
                    CS2030S_THU_12PM_2HR)
            .build();

    public static final ModuleTag CS2030S_F = new ModuleTagBuilder()
            .withModuleCode("CS2030S")
            .withExtraLessons(CS2030S_MON_12PM_2HR,
                    CS2030S_THU_1PM_1HR,
                    CS2030S_THU_2PM_2HR)
            .build();

    public static final ModuleTag CS2040S_V1 = new ModuleTagBuilder()
            .withModuleCode("CS2040S")
            .withExtraLessons(CS2040S_MON_4PM_2HR,
                    CS2040S_TUE_9AM_2HR,
                    CS2040S_WED_2PM_1HR,
                    CS2040S_FRI_10AM_1HR)
            .build();

    public static final ModuleTag CS2040S_V2 = new ModuleTagBuilder()
            .withModuleCode("CS2040S")
            .withExtraLessons(CS2040S_MON_4PM_2HR,
                    CS2040S_WED_2PM_1HR,
                    CS2040S_WED_3PM_2HR,
                    CS2040S_THU_9AM_1HR)
            .build();

    public static final ModuleTag CS2040S_F = new ModuleTagBuilder()
            .withModuleCode("CS2040S")
            .withExtraLessons(CS2040S_MON_4PM_2HR,
                    CS2040S_TUE_10AM_2HR,
                    CS2040S_THU_10AM_1HR,
                    CS2040S_WED_2PM_1HR)
            .build();

    public static final ModuleTag CS2100_V1 = new ModuleTagBuilder()
            .withModuleCode("CS2100")
            .withExtraLessons(CS2100_TUE_4PM_2HR,
                    CS2100_WED_11AM_1HR,
                    CS2100_FRI_11AM_1HR)
            .build();

    public static final ModuleTag CS2100_V2 = new ModuleTagBuilder()
            .withModuleCode("CS2100")
            .withExtraLessons(CS2100_FRI_9AM_1HR,
                    CS2100_TUE_4PM_2HR,
                    CS2100_WED_12PM_1HR)
            .build();

    public static final ModuleTag CS2101_V1_1 = new ModuleTagBuilder()
            .withModuleCode("CS2101")
            .withExtraLessons(CS2101_MON_8AM_2HR, CS2101_THU_8AM_2HR)
            .build();

    public static final ModuleTag CS2101_V1_2 = new ModuleTagBuilder()
            .withModuleCode("CS2101")
            .withExtraLessons(CS2101_TUE_4PM_2HR, CS2101_FRI_4PM_2HR)
            .build();

    public static final ModuleTag CS2102_V1 = new ModuleTagBuilder()
            .withModuleCode("CS2102")
            .withExtraLessons(CS2102_TUE_12PM_2HR, CS2102_FRI_10AM_1HR)
            .build();

    public static final ModuleTag CS2103T_V1_1 = new ModuleTagBuilder()
            .withModuleCode("CS2103T")
            .withExtraLessons(CS2103T_FRI_2PM_2HR, CS2103T_WED_2PM_1HR)
            .build();

    public static final ModuleTag CS2103T_V1_2 = new ModuleTagBuilder()
            .withModuleCode("CS2103T")
            .withExtraLessons(CS2103T_THU_1PM_1HR, CS2103T_FRI_2PM_2HR)
            .build();

    public static final ModuleTag CS2105_F = new ModuleTagBuilder()
            .withModuleCode("CS2105")
            .withExtraLessons(CS2105_MON_3PM_1HR, CS2105_THU_4PM_2HR)
            .build();

    public static final ModuleTag CS2106_V1 = new ModuleTagBuilder()
            .withModuleCode("CS2106")
            .withExtraLessons(CS2106_WED_10AM_2HR,
                    CS2106_THU_2PM_1HR,
                    CS2106_THU_3PM_1HR)
            .build();

    public static final ModuleTag CS2106_F = new ModuleTagBuilder()
            .withModuleCode("CS2106")
            .withExtraLessons(CS2106_WED_10AM_2HR,
                    CS2106_WED_3PM_1HR,
                    CS2106_WED_4PM_1HR)
            .build();

    public static final ModuleTag CS2108_V1 = new ModuleTagBuilder()
            .withModuleCode("CS2108")
            .withExtraLessons(CS2108_TUE_10AM_2HR, CS2108_THU_5PM_1HR)
            .build();

    public static final ModuleTag CS2109S_V1 = new ModuleTagBuilder()
            .withModuleCode("CS2109S")
            .withExtraLessons(CS2109S_TUE_4PM_2HR, CS2109S_WED_3PM_1HR)
            .build();

    public static final ModuleTag CS2109S_V2_1 = new ModuleTagBuilder()
            .withModuleCode("CS2109S")
            .withExtraLessons(CS2109S_MON_2PM_2HR, CS2109S_WED_1PM_1HR)
            .build();

    public static final ModuleTag CS2109S_F = new ModuleTagBuilder()
            .withModuleCode("CS2109S")
            .withExtraLessons(CS2109S_TUE_4PM_2HR, CS2109S_WED_5PM_1HR)
            .build();

    public static final ModuleTag CS3223_V1 = new ModuleTagBuilder()
            .withModuleCode("CS3223")
            .withExtraLessons(CS3223_THU_10AM_1HR, CS3223_FRI_10AM_2HR)
            .build();

    public static final ModuleTag CS3223_F = new ModuleTagBuilder()
            .withModuleCode("CS3223")
            .withExtraLessons(CS3223_FRI_10AM_2HR)
            .build();

    public static final ModuleTag CS3230_V1 = new ModuleTagBuilder()
            .withModuleCode("CS3230")
            .withExtraLessons(CS3230_TUE_10AM_2HR, CS3230_WED_10AM_1HR)
            .build();

    public static final ModuleTag CS3230_V2 = new ModuleTagBuilder()
            .withModuleCode("CS3230")
            .withExtraLessons(CS3230_TUE_10AM_2HR, CS3230_WED_10AM_1HR)
            .build();

    public static final ModuleTag CS3245_V1 = new ModuleTagBuilder()
            .withModuleCode("CS3245")
            .withExtraLessons(CS3245_THU_11AM_1HR, CS3245_FRI_12PM_2HR)
            .build();

    public static final ModuleTag CS3245_F = new ModuleTagBuilder()
            .withModuleCode("CS3245")
            .withExtraLessons(CS3245_THU_12PM_1HR, CS3245_FRI_12PM_2HR)
            .build();

    public static final ModuleTag CS4225_V1 = new ModuleTagBuilder()
            .withModuleCode("CS4225")
            .withExtraLessons(CS4225_THU_2PM_2HR, CS4225_THU_4PM_1HR)
            .build();

    public static final ModuleTag BT1101_V3 = new ModuleTagBuilder()
            .withModuleCode("BT1101")
            .withExtraLessons(BT1101_MON_12PM_1HR,
                    BT1101_TUE_12PM_2HR,
                    BT1101_WED_4PM_2HR)
            .build();

    public static final ModuleTag BT2102_F = new ModuleTagBuilder()
            .withModuleCode("BT2102")
            .withExtraLessons(BT2102_MON_10AM_2HR, BT2102_TUE_2PM_2HR)
            .build();

    public static final ModuleTag IS1108_V3 = new ModuleTagBuilder()
            .withModuleCode("IS1108")
            .withExtraLessons(IS1108_MON_2PM_2HR, IS1108_THU_9AM_2HR)
            .build();

    public static final ModuleTag IS2218_V3 = new ModuleTagBuilder()
            .withModuleCode("IS2218")
            .withExtraLessons(IS2218_MON_9AM_2HR)
            .build();

    public static final ModuleTag MA1521_V1 = new ModuleTagBuilder()
            .withModuleCode("MA1521")
            .withExtraLessons(MA1521_WED_6PM_2HR,
                    MA1521_THU_2PM_1HR,
                    MA1521_FRI_6PM_2HR)
            .build();

    public static final ModuleTag MA1521_V3 = new ModuleTagBuilder()
            .withModuleCode("MA121")
            .withExtraLessons(MA1521_MON_8AM_1HR,
                    MA1521_WED_10AM_2HR,
                    MA1521_FRI_10AM_2HR)
            .build();

    public static final ModuleTag MA2001_V1 = new ModuleTagBuilder()
            .withModuleCode("MA2001")
            .withExtraLessons(MA2001_WED_2PM_1HR, MA2001_FRI_12PM_2HR)
            .build();

    public static final ModuleTag MA2001_F = new ModuleTagBuilder()
            .withModuleCode("MA2001")
            .withExtraLessons(MA2001_MON_8AM_2HR,
                    MA2001_THU_8AM_2HR,
                    MA2001_FRI_3PM_1HR)
            .build();

    public static final ModuleTag MA2101_V1 = new ModuleTagBuilder()
            .withModuleCode("MA2101")
            .withExtraLessons(MA2101_WED_1PM_1HR, MA2101_WED_4PM_2HR, MA2101_FRI_4PM_2HR)
            .build();

    public static final ModuleTag MA2104_V1 = new ModuleTagBuilder()
            .withModuleCode("MA2104")
            .withExtraLessons(MA2104_TUE_12PM_2HR,
                    MA2104_WED_12PM_1HR,
                    MA2104_FRI_12PM_2HR)
            .build();

    public static final ModuleTag MA2104_V2_1 = new ModuleTagBuilder()
            .withModuleCode("MA2104")
            .withExtraLessons(MA2104_WED_10AM_2HR,
                    MA2104_THU_3PM_1HR,
                    MA2104_FRI_10AM_2HR)
            .build();

    public static final ModuleTag MA2104_V2_2 = new ModuleTagBuilder()
            .withModuleCode("MA2104")
            .withExtraLessons(MA2104_MON_10AM_2HR,
                    MA2104_FRI_10AM_2HR)
            .build();

    public static final ModuleTag MA2108_V1 = new ModuleTagBuilder()
            .withModuleCode("MA2108")
            .withExtraLessons(MA2108_TUE_2PM_2HR, MA2108_THU_9AM_1HR, MA2108_FRI_2PM_2HR)
            .build();

    public static final ModuleTag MA2108_V2 = new ModuleTagBuilder()
            .withModuleCode("MA2108")
            .withExtraLessons(MA2108_TUE_2PM_2HR, MA2108_THU_9AM_1HR, MA2108_FRI_2PM_2HR)
            .build();

    public static final ModuleTag MA3252_V1 = new ModuleTagBuilder()
            .withModuleCode("MA3252")
            .withExtraLessons(MA3252_WED_10AM_1HR, MA3252_FRI_7PM_3HR)
            .build();

    public static final ModuleTag MA3252_F = new ModuleTagBuilder()
            .withModuleCode("MA3252")
            .withExtraLessons(MA3252_WED_9AM_1HR, MA3252_FRI_7PM_3HR)
            .build();

    public static final ModuleTag ST2131_V2 = new ModuleTagBuilder()
            .withModuleCode("ST2131")
            .withExtraLessons(ST2131_TUE_6PM_2HR, ST2131_FRI_6PM_2HR)
            .build();

    public static final ModuleTag ST2334_V1 = new ModuleTagBuilder()
            .withModuleCode("ST2334")
            .withExtraLessons(ST2334_MON_2PM_1HR, ST2334_TUE_12PM_2HR, ST2334_FRI_12PM_2HR)
            .build();

    public static final ModuleTag GEC1030_V1 = new ModuleTagBuilder()
            .withModuleCode("GEC1030")
            .withExtraLessons(GEC1030_MON_8AM_2HR, GEC1030_TUE_4PM_2HR)
            .build();

    public static final ModuleTag GEA1000_V1 = new ModuleTagBuilder()
            .withModuleCode("GEA1000")
            .withExtraLessons(GEA1000_THU_3PM_3HR)
            .build();

    public static final ModuleTag GESS1019_V1 = new ModuleTagBuilder()
            .withModuleCode("GESS1019")
            .withExtraLessons(GESS1019_MON_10AM_2HR, GESS1019_TUE_2PM_2HR)
            .build();

    public static final ModuleTag GEN2050_F = new ModuleTagBuilder()
            .withModuleCode("GEN2050")
            .withExtraLessons(GEN2050_FRI_4PM_2HR)
            .build();

    public static final ModuleTag ES2660_V2_1 = new ModuleTagBuilder()
            .withModuleCode("ES2660")
            .withExtraLessons(ES2660_TUE_8AM_2HR, ES2660_FRI_8AM_2HR)
            .build();

    public static final ModuleTag ES2660_V2_2 = new ModuleTagBuilder()
            .withModuleCode("ES2660")
            .withExtraLessons(ES2660_MON_2PM_2HR, ES2660_THU_2PM_2HR)
            .build();

    public static final ModuleTag ES2660_F = new ModuleTagBuilder()
            .withModuleCode("ES2660")
            .withExtraLessons(ES2660_TUE_8AM_2HR, ES2660_FRI_8AM_2HR)
            .build();

    public static final ModuleTag LAJ1201_F = new ModuleTagBuilder()
            .withModuleCode("LAJ1201")
            .withExtraLessons(LAJ1201_MON_6PM_2HR,
                    LAJ1201_TUE_6PM_2HR,
                    LAJ1201_THU_6PM_2HR)
            .build();

    public static final Set<ModuleTag> MODULE_TAG_SET_1 =
            Set.of(CS2103T_V1_1, CS2101_V1_1, CS2109S_V1, CS2108_V1, MA2104_V1, MA3252_V1);

    public static final Set<ModuleTag> MODULE_TAG_SET_2 =
            Set.of(MA2108_V1, MA2101_V1, CS3230_V1, CS2102_V1, CS2100_V1);

    public static final Set<ModuleTag> MODULE_TAG_SET_3 =
            Set.of(GEC1030_V1, GEA1000_V1, ST2334_V1, CS2030S_V1, CS2040S_V1);

    public static final Set<ModuleTag> MODULE_TAG_SET_4 =
            Set.of(CS1101S_V1, GESS1019_V1, CS1231S_V1, MA2001_V1, MA1521_V1);

    public static final Set<ModuleTag> MODULE_TAG_SET_5 =
            Set.of(CS2030S_V2, CS2040S_V2, ES2660_V2_1, MA2104_V2_1, ST2131_V2, CS2109S_V2_1);

    public static final Set<ModuleTag> MODULE_TAG_SET_6 =
            Set.of(BT1101_V3, IS1108_V3, IS2218_V3, MA1521_V3, CS1010J_V3);

    public static final Set<ModuleTag> MODULE_TAG_SET_7 =
            Set.of(MA2104_V2_2, CS3230_V2, CS2100_V2, MA2108_V2, ES2660_V2_2);

    public static final Set<ModuleTag> MODULE_TAG_SET_8 =
            Set.of(CS2108_V1, CS3223_V1, CS3245_V1, CS2103T_V1_2, CS2101_V1_2, CS4225_V1);

    public static final Set<ModuleTag> MODULE_TAG_SET_9 =
            Set.of(CS2108_V1, CS2106_V1, CS3245_V1, CS2103T_V1_2, CS2101_V1_2);

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

    public static final Set<ModuleTag> MODULE_TAG_SET_10 =
            Set.of(MA2001_F, LAJ1201_F, CS1101S_F, MA3252_F, CS2106_F);

    public static final Set<ModuleTag> MODULE_TAG_SET_11 =
            Set.of(CS2040S_F, CS2106_F, CS2109S_F, CS3245_F, CS3223_F, CS2105_F);

    public static final Set<ModuleTag> MODULE_TAG_SET_12 =
            Set.of(GEN2050_F, CS3245_F, CS3223_F, MA3252_F, LAJ1201_F, MA2001_F);

    public static final Set<ModuleTag> MODULE_TAG_SET_13 =
            Set.of(ES2660_F, CS1101S_F, CS3245_F, CS2106_F, MA3252_F, CS2040S_F);

    public static final Set<ModuleTag> MODULE_TAG_SET_14 =
            Set.of(MA2001_F, LAJ1201_F, CS1101S_F, MA3252_F, CS2106_F, ES2660_F);

    public static final Set<ModuleTag> MODULE_TAG_SET_15 =
            Set.of(MA3252_F, CS3223_F, BT2102_F, CS2030S_F, CS1101S_F, ES2660_F);

    public static final Set<ModuleTag> MODULE_TAG_SET_F =
            Set.of(MA2001_F, BT2102_F, CS2030S_F, CS2105_F,
                    CS2040S_F, LAJ1201_F, ES2660_F, CS1101S_F,
                    MA3252_F, CS2106_F, CS2109S_F, CS3245_F,
                    CS3223_F, GEN2050_F);
}

