package seedu.vms.model.vaccination;

import java.util.List;


/** Represents a vaccination. */
public enum Vaccination {
    // FIRST DOSE
    PFIZER_DOSE_1("First dose (PFIZER)", DoseType.FIRST_DOSE,
            0, Integer.MAX_VALUE,
            56 /* 8 weeks */,
            List.of()),
    MODERNA_DOSE_1("First Does (MODERNA)", DoseType.FIRST_DOSE,
            18, Integer.MAX_VALUE,
            56 /* 8 weeks */,
            List.of()),
    NOVAVAX_DOSE_1("First Does (NOVAVAX)", DoseType.FIRST_DOSE,
            18, Integer.MAX_VALUE,
            56 /* 8 weeks */,
            List.of()),
    SINOVAC_DOSE_1("First Does (SINOVAC)", DoseType.FIRST_DOSE,
            18, Integer.MAX_VALUE,
            56 /* 8 weeks */,
            List.of()),

    // SECOND DOSE
    PFIZER_DOSE_2("Second dose (PFIZER)", DoseType.SECOND_DOSE,
            0, Integer.MAX_VALUE,
            56 /* 8 weeks */,
            List.of(DoseType.FIRST_DOSE)),
    MODERNA_DOSE_2("Second Does (MODERNA)", DoseType.SECOND_DOSE,
            18, Integer.MAX_VALUE,
            56 /* 8 weeks */,
            List.of(DoseType.FIRST_DOSE)),
    NOVAVAX_DOSE_2("Second Does (NOVAVAX)", DoseType.SECOND_DOSE,
            18, Integer.MAX_VALUE,
            56 /* 8 weeks */,
            List.of(DoseType.FIRST_DOSE)),
    SINOVAC_DOSE_2("Second Does (SINOVAC)", DoseType.SECOND_DOSE,
            18, Integer.MAX_VALUE,
            56 /* 8 weeks */,
            List.of(DoseType.FIRST_DOSE)),

    //THIRD DOSE
    PFIZER_DOSE_3("Third dose (PFIZER)", DoseType.THIRD_DOSE,
            0, Integer.MAX_VALUE,
            56 /* 8 weeks */,
            List.of(DoseType.SECOND_DOSE)),
    MODERNA_DOSE_3("Third Does (MODERNA)", DoseType.THIRD_DOSE,
            18, Integer.MAX_VALUE,
            56 /* 8 weeks */,
            List.of(DoseType.SECOND_DOSE)),
    NOVAVAX_DOSE_3("Third Does (NOVAVAX)", DoseType.THIRD_DOSE,
            18, Integer.MAX_VALUE,
            56 /* 8 weeks */,
            List.of(DoseType.SECOND_DOSE)),
    SINOVAC_DOSE_3("Third Does (SINOVAC)", DoseType.THIRD_DOSE,
            18, Integer.MAX_VALUE,
            56 /* 8 weeks */,
            List.of(DoseType.SECOND_DOSE));


    private final String name;
    private final DoseType doesType;
    private final int minAge;
    private final int maxAge;
    private final int minSpacing;
    private final List<DoseType> prereqs;


    private Vaccination(String name, DoseType doseType,
                int minAge, int maxAge,
                int minSpacing, List<DoseType> prereqs) {
        this.name = name;
        this.doesType = doseType;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minSpacing = minSpacing;
        this.prereqs = prereqs;
    }


    public DoseType getDoseType() {
        return doesType;
    }


    /**
     * Returns the minimum allowable age (inclusive) to take this vaccination.
     */
    public int getMinAge() {
        return minAge;
    }


    /**
     * Returns the maximum allowable age (inclusive) to take this vaccination.
     */
    public int getMaxAge() {
        return maxAge;
    }


    /**
     * Returns the minimum time spacing in days this vaccination must be taken
     * from the last dose in days.
     */
    public int getMinSpacing() {
        return minSpacing;
    }


    /**
     * Returns a list of required vaccination {@code DoseType} that has to be
     * taken before this one can be taken.
     *
     * @return a list of prerequisites.
     */
    public List<DoseType> getPrereqs() {
        return prereqs;
    }


    @Override
    public String toString() {
        return name;
    }
}
