package seedu.address.model.person.patient;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.doctor.Doctor;

/**
 * Filter Class for passing to PatientContainsKeywordsPredicate
 */
public class PatientFilter {

    //Filter fields
    private final String nameFilter;
    private final String phoneFilter;
    private final String emailFilter;
    private final String heightFilter;
    private final String weightFilter;
    private final String diagnosisFilter;
    private final String statusFilter;
    private final String remarkFilter;
    private final Set<String> tagsFilter;
    private final Optional<Doctor> doctorFilter;

    /**
     * Constructs a {@code PatientFilter}.
     *
     * @param name name in string (can be empty string).
     * @param phone phone in string (can be empty string).
     * @param email email in string (can be empty string).
     * @param height height in string (can be empty string).
     * @param weight weight in string (can be empty string).
     * @param diagnosis diagnosis in string (can be empty string).
     * @param status status in string (can be empty string).
     * @param remark remark in string (can be empty string).
     * @param tags set of tags in string (can be empty list).
     * @param doctor optional containing doctor object (OfNullable).
     */
    public PatientFilter(String name, String phone, String email, String height,
                         String weight, String diagnosis, String status,
                         String remark, Set<String> tags, Optional<Doctor> doctor) {
        this.nameFilter = name;
        this.phoneFilter = phone;
        this.emailFilter = email;
        this.heightFilter = height;
        this.weightFilter = weight;
        this.diagnosisFilter = diagnosis;
        this.statusFilter = status;
        this.remarkFilter = remark;
        this.tagsFilter = tags;
        this.doctorFilter = doctor;
    }

    /**
     * Constructs a {@code PatientFilter}.
     *
     * @param name name in string (can be empty string).
     * @param phone phone in string (can be empty string).
     * @param email email in string (can be empty string).
     * @param height height in string (can be empty string).
     * @param weight weight in string (can be empty string).
     * @param diagnosis diagnosis in string (can be empty string).
     * @param status status in string (can be empty string).
     * @param remark remark in string (can be empty string).
     * @param tags set of tags in string (can be empty list).
     */
    public PatientFilter(String name,
                         String phone,
                         String email,
                         String height,
                         String weight,
                         String diagnosis,
                         String status,
                         String remark, Set<String> tags) {
        this(name, phone, email, height, weight,
                diagnosis, status, remark, tags, Optional.empty());
    }

    /**
     * Constructs a {@code PatientFilter}.
     *
     * @param doctor optional containing doctor object (OfNullable).
     */
    public PatientFilter(Optional<Doctor> doctor) {
        this("", "", "", "", "",
                "", "", "", new HashSet<>(), doctor);
    }

    /**
     * Matches filter fields to patient
     * Returns true if each filter field is a substring of the respective field of patient
     */
    public boolean isMatch(Patient patient) {

        boolean result = StringUtil.containsSubstringIgnoreCase(patient.getName().fullName, nameFilter)
                && StringUtil.containsSubstringIgnoreCase(patient.getPhone().value, phoneFilter)
                && StringUtil.containsSubstringIgnoreCase(patient.getEmail().value, emailFilter)
                && StringUtil.containsSubstringIgnoreCase(patient.getHeight().value, heightFilter)
                && StringUtil.containsSubstringIgnoreCase(patient.getWeight().value, weightFilter)
                && StringUtil.containsSubstringIgnoreCase(patient.getDiagnosis().diagnosis, diagnosisFilter)
                && StringUtil.containsSubstringIgnoreCase(patient.getStatus().status, statusFilter)
                && StringUtil.containsSubstringIgnoreCase(patient.getRemark().remark, remarkFilter);

        if (!tagsFilter.isEmpty()) {
            Set<String> tagStringSet = patient
                    .getTags()
                    .stream()
                    .map(tag -> tag.tagName.toLowerCase())
                    .collect(Collectors.toSet());
            result = result && tagStringSet.containsAll(tagsFilter
                    .stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet()));
        }

        if (doctorFilter.isPresent()) {
            result = result && patient.hasDoctor(doctorFilter.get());
        }

        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PatientFilter // instanceof handles nulls
                && this.nameFilter.equals(((PatientFilter) other).nameFilter)
                && this.phoneFilter.equals(((PatientFilter) other).phoneFilter)
                && this.emailFilter.equals(((PatientFilter) other).emailFilter)
                && this.heightFilter.equals(((PatientFilter) other).heightFilter)
                && this.weightFilter.equals(((PatientFilter) other).weightFilter)
                && this.diagnosisFilter.equals(((PatientFilter) other).diagnosisFilter)
                && this.statusFilter.equals(((PatientFilter) other).statusFilter)
                && this.remarkFilter.equals(((PatientFilter) other).remarkFilter)
                && this.tagsFilter.equals(((PatientFilter) other).tagsFilter))
                && this.doctorFilter.equals(((PatientFilter) other).doctorFilter); // state check
    }
}
