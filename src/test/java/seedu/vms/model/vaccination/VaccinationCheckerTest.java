package seedu.vms.model.vaccination;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.Test;

public class VaccinationCheckerTest {
    private static final LocalDateTime TIME_FIRST_DOSE = LocalDateTime.of(2023, 3, 5, 4, 55);
    private static final LocalDateTime TIME_SECOND_DOSE = TIME_FIRST_DOSE.plus(56, ChronoUnit.DAYS);
    private static final LocalDateTime TIME_THIRD_DOSE = TIME_SECOND_DOSE.plus(56, ChronoUnit.DAYS);

    private static final LocalDateTime TIME_INVALID_SECOND_DOSE = TIME_FIRST_DOSE.plus(55, ChronoUnit.DAYS);

    private static final List<VaccinationRecord> VALID_FIRST_DOSE_REC = List.of();
    private static final List<VaccinationRecord> VALID_SECOND_DOSE_REC = List.of(
            new VaccinationRecord(Vaccination.PFIZER_DOSE_1, TIME_FIRST_DOSE));
    private static final List<VaccinationRecord> VALID_THIRD_DOSE_REC = List.of(
            new VaccinationRecord(Vaccination.NOVAVAX_DOSE_1, TIME_FIRST_DOSE),
            new VaccinationRecord(Vaccination.SINOVAC_DOSE_2, TIME_SECOND_DOSE));

    private static final int MODERNA_VALID_AGE = 20;
    private static final int MODERNA_UNDER_AGE = 5;


    @Test
    public void check_satisfiedAttributesFirstDose_true() {
        assertEquals(true,
                VaccinationChecker.check(
                        Vaccination.MODERNA_DOSE_1,
                        MODERNA_VALID_AGE,
                        VALID_FIRST_DOSE_REC,
                        TIME_FIRST_DOSE));
    }


    @Test
    public void check_satisfiedAttributesSecondDose_true() {
        assertEquals(true,
                VaccinationChecker.check(
                        Vaccination.MODERNA_DOSE_2,
                        MODERNA_VALID_AGE,
                        VALID_SECOND_DOSE_REC,
                        TIME_SECOND_DOSE));
    }


    @Test
    public void check_satisfiedAttributesThirdDose_true() {
        assertEquals(true,
                VaccinationChecker.check(
                        Vaccination.MODERNA_DOSE_3,
                        MODERNA_VALID_AGE,
                        VALID_THIRD_DOSE_REC,
                        TIME_THIRD_DOSE));
    }


    @Test
    public void check_underAge_false() {
        assertEquals(false,
                VaccinationChecker.check(
                        Vaccination.MODERNA_DOSE_3,
                        MODERNA_UNDER_AGE,
                        VALID_THIRD_DOSE_REC,
                        TIME_THIRD_DOSE));
    }


    @Test
    public void check_tooEarlyAppt_false() {
        assertEquals(false,
                VaccinationChecker.check(
                        Vaccination.MODERNA_DOSE_2,
                        MODERNA_VALID_AGE,
                        VALID_SECOND_DOSE_REC,
                        TIME_INVALID_SECOND_DOSE));
    }


    @Test
    public void check_duplicateDoseType_false() {
        assertEquals(false,
                VaccinationChecker.check(
                        Vaccination.MODERNA_DOSE_1,
                        MODERNA_VALID_AGE,
                        VALID_SECOND_DOSE_REC,
                        TIME_SECOND_DOSE));
    }
}
