package seedu.connectus.model.person;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.connectus.commons.util.CollectionUtil;
import seedu.connectus.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s information fields matches any of the keywords given.
 */
public class FieldsContainKeywordsPredicate implements Predicate<Person> {
    private List<String> keywords;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String instagram;
    private String whatsapp;
    private String telegram;
    private Set<String> remarks;
    private String birthday;
    private Set<String> modules;
    private Set<String[]> ccas;
    private Set<String> majors;
    public FieldsContainKeywordsPredicate() {}
    public FieldsContainKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }
    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public FieldsContainKeywordsPredicate(FieldsContainKeywordsPredicate toCopy) {
        setKeywords(toCopy.keywords);
        setName(toCopy.name);
        setPhone(toCopy.phone);
        setEmail(toCopy.email);
        setAddress(toCopy.address);
        setInstagram(toCopy.instagram);
        setWhatsapp(toCopy.whatsapp);
        setTelegram(toCopy.telegram);
        setRemarks(toCopy.remarks);
        setModules(toCopy.modules);
        setCcas(toCopy.ccas);
        setMajors(toCopy.majors);
        setBirthday(toCopy.birthday);
    }

    /**
     * Returns true if at least one field has keyword given.
     */
    public boolean isFieldKeywordPresent() {
        return CollectionUtil.isAnyNonNull(name, phone, email, address, instagram, telegram, whatsapp, remarks,
                birthday, modules, ccas, majors, keywords);
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public Optional<List<String>> getKeywords() {
        return Optional.ofNullable(keywords);
    }
    public void setName(String name) {
        this.name = name;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Optional<String> getPhone() {
        return Optional.ofNullable(phone);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Optional<String> getAddress() {
        return (address != null)
                ? Optional.of(address)
                : Optional.empty();
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Optional<String> getBirthday() {
        return Optional.ofNullable(birthday);
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public Optional<String> getTelegram() {
        return Optional.ofNullable(telegram);
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public Optional<String> getWhatsapp() {
        return Optional.ofNullable(whatsapp);
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public Optional<String> getInstagram() {
        return Optional.ofNullable(instagram);
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setRemarks(Set<String> remarks) {
        this.remarks = (remarks != null) ? new HashSet<>(remarks) : null;
    }

    /**
     * Returns an unmodifiable tag set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<String>> getRemarks() {
        return (remarks != null) ? Optional.of(Collections.unmodifiableSet(remarks)) : Optional.empty();
    }


    /**
     * Sets {@code modules} to this object's {@code modules}.
     * A defensive copy of {@code modules} is used internally.
     */
    public void setModules(Set<String> modules) {
        this.modules = (modules != null) ? new HashSet<>(modules) : null;
    }

    /**
     * Returns an unmodifiable modules set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code modules} is null.
     */
    public Optional<Set<String>> getModules() {
        return (modules != null) ? Optional.of(Collections.unmodifiableSet(modules)) : Optional.empty();
    }

    /**
     * Sets {@code ccas} to this object's {@code ccas}.
     * A defensive copy of {@code ccas} is used internally.
     */
    public void setCcas(Set<String[]> ccas) {
        this.ccas = (ccas != null) ? new HashSet<>(ccas) : null;
    }

    /**
     * Returns an unmodifiable ccas set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code ccas} is null.
     */
    public Optional<Set<String[]>> getCcas() {
        return (ccas != null) ? Optional.of(Collections.unmodifiableSet(ccas)) : Optional.empty();
    }

    /**
     * Sets {@code ccaPositions} to this object's {@code ccaPositions}.
     * A defensive copy of {@code ccaPositions} is used internally.
     */
    public void setMajors(Set<String> majors) {
        this.majors = (majors != null) ? new HashSet<>(majors) : null;
    }

    /**
     * Returns an unmodifiable ccaPositions set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code ccaPositions} is null.
     */
    public Optional<Set<String>> getMajors() {
        return (majors != null) ? Optional.of(Collections.unmodifiableSet(majors)) : Optional.empty();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FieldsContainKeywordsPredicate)) {
            return false;
        }

        // state check
        FieldsContainKeywordsPredicate e = (FieldsContainKeywordsPredicate) other;

        return getName().equals(e.getName())
                && getKeywords().equals((e.getKeywords()))
                && getPhone().equals(e.getPhone())
                && getEmail().equals(e.getEmail())
                && getAddress().equals(e.getAddress())
                && getRemarks().equals(e.getRemarks())
                && getModules().equals(e.getModules())
                && getCcas().equals(e.getCcas())
                && getMajors().equals(e.getMajors())
                && getBirthday().equals(e.getBirthday())
                && getTelegram().equals(e.getTelegram())
                && getWhatsapp().equals(e.getWhatsapp())
                && getInstagram().equals(e.getInstagram());
    }

    @Override
    public boolean test(Person person) {
        if (getName().isPresent()) {
            if (!StringUtil.containsKeywordsListIgnoreCase(person.getName().toString(), name)) {
                return false;
            }
        }
        if (getAddress().isPresent()) {
            if (person.getAddress().isEmpty()
                    || !StringUtil.containsKeywordsListIgnoreCase(person.getAddress().toString(), address)) {
                return false;
            }
        }
        if (getEmail().isPresent()) {
            if (person.getEmail().isEmpty()
                    || !StringUtil.containsKeywordsListIgnoreCase(person.getEmail().toString(), email)) {
                return false;
            }
        }
        if (getPhone().isPresent()) {
            if (person.getPhone().isEmpty()
                    || !StringUtil.containsKeywordsListIgnoreCase(person.getPhone().toString(), phone)) {
                return false;
            }
        }
        if (getBirthday().isPresent()) {
            if (person.getBirthday().isEmpty()
                    || !StringUtil.containsKeywordsListIgnoreCase(person.getBirthday().toString(),
                    birthday)) {
                return false;
            }
        }
        if (getInstagram().isPresent()) {
            if (person.getSocialMedia().isEmpty() || person.getSocialMedia().get().getInstagram() == null
                    || !StringUtil.containsKeywordsListIgnoreCase(person.getSocialMedia().get()
                            .getInstagram().toString(),
                    instagram)) {
                return false;
            }
        }
        if (getWhatsapp().isPresent()) {
            if (person.getSocialMedia().isEmpty() || person.getSocialMedia().get().getWhatsapp() == null
                    || !StringUtil.containsKeywordsListIgnoreCase(person.getSocialMedia().get()
                            .getWhatsapp().toString(),
                    whatsapp)) {
                return false;
            }
        }
        if (getTelegram().isPresent()) {
            if (person.getSocialMedia().isEmpty() || person.getSocialMedia().get().getTelegram() == null
                    || !StringUtil.containsKeywordsListIgnoreCase(person.getSocialMedia().get()
                            .getTelegram().toString(),
                    telegram)) {
                return false;
            }
        }
        if (getKeywords().isPresent()) {
            if (!keywords.stream().allMatch(keyword ->
                    StringUtil.containsKeywordsListIgnoreCase(person.getAllFieldsAsString(), keyword))) {
                return false;
            }
        }
        if (getRemarks().isPresent()) {
            if (!remarks.stream().allMatch(remarkKey -> person.getRemarks().stream().anyMatch(remark ->
                            StringUtil.containsKeywordsListIgnoreCase(remark.toString(), remarkKey)))) {
                return false;
            }
        }
        if (getCcas().isPresent()) {
            boolean result = ccas.stream().allMatch(ccaKey -> person.getCcas().stream().anyMatch(cca -> {
                boolean check = true;
                check = check
                        && (ccaKey[0].trim().isEmpty()
                                    || StringUtil.containsKeywordsListIgnoreCase(cca.ccaName, ccaKey[0]));
                if (ccaKey.length > 1) {
                    check = check && cca.hasPosition() && StringUtil.containsKeywordsListIgnoreCase(cca.ccaPositionName,
                            ccaKey[1]);
                }
                return check;
            }));
            if (!result) {
                return false;
            }
        }
        if (getMajors().isPresent()) {
            if (!majors.stream().allMatch(majorKey ->
                    person.getMajors().stream().anyMatch(major ->
                            StringUtil.containsKeywordsListIgnoreCase(major.toString(),
                                    majorKey)))) {
                return false;
            }
        }
        if (getModules().isPresent()) {
            if (!modules.stream().allMatch(moduleKey -> person.getModules().stream().anyMatch(module ->
                    StringUtil.containsKeywordsListIgnoreCase(module.toString(), moduleKey)))) {
                return false;
            }
        }

        return true;
    }
}
