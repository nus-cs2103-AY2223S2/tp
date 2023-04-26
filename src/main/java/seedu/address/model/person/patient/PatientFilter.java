package seedu.address.model.person.patient;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.doctor.Doctor;

/**
 * Filter Class for passing to PatientContainsKeywordsPredicate
 */
public class PatientFilter {

    //Filter fields
    private String nameFilter = "";
    private String phoneFilter = "";
    private String emailFilter = "";
    private String heightFilter = "";
    private String weightFilter = "";
    private String diagnosisFilter = "";
    private String statusFilter = "";
    private String remarkFilter = "";
    private Set<String> tagsFilter = new HashSet<>();
    private Doctor doctorFilter = null;

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
        this.nameFilter = name;
        this.phoneFilter = phone;
        this.emailFilter = email;
        this.heightFilter = height;
        this.weightFilter = weight;
        this.diagnosisFilter = diagnosis;
        this.statusFilter = status;
        this.remarkFilter = remark;
        this.tagsFilter = tags;
    }

    /**
     * Constructs a {@code PatientFilter}.
     *
     * @param doctor a doctor object (can be null).
     */
    public PatientFilter(Doctor doctor) {
        this.doctorFilter = doctor;
    }

    /**
     * Matches filter fields to patient
     * Returns true if each filter field is a substring of the respective field of patient
     */
    public boolean isMatch(Patient patient) {

        boolean result = StringUtil.containsSubstringIgnoreCase(patient.getName().getValue(), nameFilter)
                && StringUtil.containsSubstringIgnoreCase(patient.getPhone().getValue(), phoneFilter)
                && StringUtil.containsSubstringIgnoreCase(patient.getEmail().getValue(), emailFilter)
                && StringUtil.containsSubstringIgnoreCase(patient.getHeight().getValue(), heightFilter)
                && StringUtil.containsSubstringIgnoreCase(patient.getWeight().getValue(), weightFilter)
                && StringUtil.containsSubstringIgnoreCase(patient.getDiagnosis().getValue(), diagnosisFilter)
                && StringUtil.containsSubstringIgnoreCase(patient.getStatus().getValue(), statusFilter)
                && StringUtil.containsSubstringIgnoreCase(patient.getRemark().getValue(), remarkFilter);

        if (!tagsFilter.isEmpty()) {
            Set<String> tagStringSet = patient
                    .getTags()
                    .stream()
                    .map(tag -> tag.getTagName().toLowerCase())
                    .collect(Collectors.toSet());
            result = result && tagStringSet.containsAll(tagsFilter
                    .stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet()));
        }

        if (doctorFilter != null) {
            result = result && patient.hasDoctor(doctorFilter);
        }

        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PatientFilter)) {
            return false;
        }

        PatientFilter otherFilter = (PatientFilter) other;
        boolean isSameDoctorFilter;
        if (this.doctorFilter == null) {
            isSameDoctorFilter = otherFilter.doctorFilter == null;
        } else {
            isSameDoctorFilter = this.doctorFilter.equals(otherFilter.doctorFilter);
        }

        return this.nameFilter.equals(otherFilter.nameFilter)
                && this.phoneFilter.equals(otherFilter.phoneFilter)
                && this.emailFilter.equals(otherFilter.emailFilter)
                && this.heightFilter.equals(otherFilter.heightFilter)
                && this.weightFilter.equals(otherFilter.weightFilter)
                && this.diagnosisFilter.equals(otherFilter.diagnosisFilter)
                && this.statusFilter.equals(otherFilter.statusFilter)
                && this.remarkFilter.equals(otherFilter.remarkFilter)
                && this.tagsFilter.equals(otherFilter.tagsFilter)
                && isSameDoctorFilter; // state check
    }
}
