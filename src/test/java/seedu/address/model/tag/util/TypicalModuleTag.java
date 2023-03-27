package seedu.address.model.tag.util;

import static seedu.address.model.timetable.util.TypicalLesson.CS1101S_FRI_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS1101S_MON_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS1101S_THU_11AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS1101S_WED_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS1231S_FRI_3PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS1231S_THU_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS1231S_WED_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2030S_MON_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2030S_THU_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2030S_THU_1PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2040S_FRI_10AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2040S_MON_4PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2040S_TUE_9AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2040S_WED_2PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2100_FRI_11AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2100_TUE_4PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2100_WED_11AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2101_MON_8AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2101_THU_8AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2102_FRI_10AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2102_TUE_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2103T_FRI_2PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2103T_WED_2PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2108_THU_5PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2108_TUE_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2109S_TUE_4PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2109S_WED_3PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS3230_TUE_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS3230_WED_10AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.GEA1000_THU_3PM_3HR;
import static seedu.address.model.timetable.util.TypicalLesson.GEC1030_MON_8AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.GEC1030_TUE_4PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.GESS1019_MON_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.GESS1019_TUE_2PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA1521_FRI_6PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA1521_THU_2PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA1521_WED_6PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2001_FRI_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2001_WED_2PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2101_FRI_4PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2101_WED_1PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2101_WED_4PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2104_FRI_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2104_TUE_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2104_WED_12PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2108_FRI_2PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2108_THU_9AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA2108_TUE_2PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA3252_FRI_7PM_3HR;
import static seedu.address.model.timetable.util.TypicalLesson.MA3252_WED_10AM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.ST2334_FRI_12PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.ST2334_MON_2PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.ST2334_TUE_12PM_2HR;

import java.util.Set;

import seedu.address.model.tag.ModuleTag;

public class TypicalModuleTag {

    public static final ModuleTag CS2103T = new ModuleTagBuilder()
            .withModuleCode("CS2103T")
            .withExtraLessons(CS2103T_FRI_2PM_2HR, CS2103T_WED_2PM_1HR)
            .build();

    public static final ModuleTag CS2101 = new ModuleTagBuilder()
            .withModuleCode("CS2101")
            .withExtraLessons(CS2101_MON_8AM_2HR, CS2101_THU_8AM_2HR)
            .build();

    public static final ModuleTag CS2109S = new ModuleTagBuilder()
            .withModuleCode("CS2109S")
            .withExtraLessons(CS2109S_TUE_4PM_2HR, CS2109S_WED_3PM_1HR)
            .build();

    public static final ModuleTag CS2108 = new ModuleTagBuilder()
            .withModuleCode("CS2108")
            .withExtraLessons(CS2108_TUE_10AM_2HR, CS2108_THU_5PM_1HR)
            .build();

    public static final ModuleTag MA2104 = new ModuleTagBuilder()
            .withModuleCode("MA2104")
            .withExtraLessons(MA2104_TUE_12PM_2HR, MA2104_WED_12PM_1HR, MA2104_FRI_12PM_2HR)
            .build();

    public static final ModuleTag MA3252 = new ModuleTagBuilder()
            .withModuleCode("MA3252")
            .withExtraLessons(MA3252_WED_10AM_1HR, MA3252_FRI_7PM_3HR)
            .build();

    public static final ModuleTag MA2108 = new ModuleTagBuilder()
            .withModuleCode("MA2108")
            .withExtraLessons(MA2108_TUE_2PM_2HR, MA2108_THU_9AM_1HR, MA2108_FRI_2PM_2HR)
            .build();

    public static final ModuleTag MA2101 = new ModuleTagBuilder()
            .withModuleCode("MA2101")
            .withExtraLessons(MA2101_WED_1PM_1HR, MA2101_WED_4PM_2HR, MA2101_FRI_4PM_2HR)
            .build();

    public static final ModuleTag CS3230 = new ModuleTagBuilder()
            .withModuleCode("CS3230")
            .withExtraLessons(CS3230_TUE_10AM_2HR, CS3230_WED_10AM_1HR)
            .build();

    public static final ModuleTag CS2102 = new ModuleTagBuilder()
            .withModuleCode("CS2102")
            .withExtraLessons(CS2102_TUE_12PM_2HR, CS2102_FRI_10AM_1HR)
            .build();

    public static final ModuleTag CS2100 = new ModuleTagBuilder()
            .withModuleCode("CS2100")
            .withExtraLessons(CS2100_TUE_4PM_2HR, CS2100_WED_11AM_1HR, CS2100_FRI_11AM_1HR)
            .build();

    public static final ModuleTag GEC1030 = new ModuleTagBuilder()
            .withModuleCode("GEC1030")
            .withExtraLessons(GEC1030_MON_8AM_2HR, GEC1030_TUE_4PM_2HR)
            .build();

    public static final ModuleTag CS2030S = new ModuleTagBuilder()
            .withModuleCode("CS2030S")
            .withExtraLessons(CS2030S_MON_12PM_2HR, CS2030S_THU_10AM_2HR, CS2030S_THU_1PM_1HR)
            .build();

    public static final ModuleTag CS2040S = new ModuleTagBuilder()
            .withModuleCode("CS2040S")
            .withExtraLessons(CS2040S_MON_4PM_2HR,
                    CS2040S_TUE_9AM_2HR,
                    CS2040S_WED_2PM_1HR,
                    CS2040S_FRI_10AM_1HR)
            .build();

    public static final ModuleTag ST2334 = new ModuleTagBuilder()
            .withModuleCode("ST2334")
            .withExtraLessons(ST2334_MON_2PM_1HR, ST2334_TUE_12PM_2HR, ST2334_FRI_12PM_2HR)
            .build();

    public static final ModuleTag GEA1000 = new ModuleTagBuilder()
            .withModuleCode("GEA1000")
            .withExtraLessons(GEA1000_THU_3PM_3HR)
            .build();

    public static final ModuleTag GESS1019 = new ModuleTagBuilder()
            .withModuleCode("GESS1019")
            .withExtraLessons(GESS1019_MON_10AM_2HR, GESS1019_TUE_2PM_2HR)
            .build();

    public static final ModuleTag CS1101S = new ModuleTagBuilder()
            .withModuleCode("CS1101S")
            .withExtraLessons(CS1101S_MON_12PM_2HR,
                    CS1101S_WED_10AM_2HR,
                    CS1101S_THU_11AM_1HR,
                    CS1101S_FRI_10AM_2HR)
            .build();

    public static final ModuleTag CS1231S = new ModuleTagBuilder()
            .withModuleCode("CS1231S")
            .withExtraLessons(CS1231S_WED_12PM_2HR, CS1231S_THU_12PM_2HR, CS1231S_FRI_3PM_1HR)
            .build();

    public static final ModuleTag MA2001 = new ModuleTagBuilder()
            .withModuleCode("MA2001")
            .withExtraLessons(MA2001_WED_2PM_1HR, MA2001_FRI_12PM_2HR)
            .build();

    public static final ModuleTag MA1521 = new ModuleTagBuilder()
            .withModuleCode("MA1521")
            .withExtraLessons(MA1521_WED_6PM_2HR, MA1521_THU_2PM_1HR, MA1521_FRI_6PM_2HR)
            .build();

    public static final Set<ModuleTag> MODULE_TAG_SET_1 =
            Set.of(CS2103T, CS2101, CS2109S, CS2108, MA2104, MA3252);

    public static final Set<ModuleTag> MODULE_TAG_SET_2 =
            Set.of(MA2108, MA2101, CS3230, CS2102, CS2100);

    public static final Set<ModuleTag> MODULE_TAG_SET_3 =
            Set.of(GEC1030, GEA1000, ST2334, CS2030S, CS2040S);

    public static final Set<ModuleTag> MODULE_TAG_SET_4 =
            Set.of(CS1101S, GESS1019, CS1231S, MA2001, MA1521);
}

