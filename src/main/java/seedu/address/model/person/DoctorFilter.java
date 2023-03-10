package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.ArgumentMultimap;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import seedu.address.model.tag.Tag;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DoctorFilter {

    //Filter fields
    private final String nameFilter;
    private final String phoneFilter;
    private final String emailFilter;
    private final String specialtyFilter;
    private final String yoeFilter;
    private final Set<String> tagsFilter;

    public DoctorFilter(String name,
                        String phone,
                        String email,
                        String specialty,
                        String yoe, Set<String> tags) {
        this.nameFilter = name;
        this.phoneFilter = phone;
        this.emailFilter = email;
        this.specialtyFilter = specialty;
        this.yoeFilter = yoe;
        this.tagsFilter = tags;
    }

    /**
     * Matches filter fields to doctor
     * Returns true if each filter field is a substring of the respective field of doctor
     */
    public boolean isMatch(Doctor doctor) {

        boolean result = StringUtil.containsSubstringIgnoreCase(doctor.getName().fullName, nameFilter)
                && StringUtil.containsSubstringIgnoreCase(doctor.getPhone().value, phoneFilter)
                && StringUtil.containsSubstringIgnoreCase(doctor.getEmail().value, emailFilter)
                && StringUtil.containsSubstringIgnoreCase(doctor.getSpecialty().specialty, specialtyFilter)
                && StringUtil.containsSubstringIgnoreCase(doctor.getYoe().value, yoeFilter);

        if (!tagsFilter.isEmpty()) {
            Set<String> tagStringSet = doctor
                    .getTags()
                    .stream()
                    .map(tag -> tag.tagName.toLowerCase())
                    .collect(Collectors.toSet());
            result = result && tagStringSet.containsAll(tagsFilter
                    .stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet()));
        }

        return result;
    }
}
