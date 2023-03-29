package seedu.connectus.model.person;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.connectus.commons.util.CollectionUtil;
import seedu.connectus.commons.util.StringUtil;
import seedu.connectus.model.socialmedia.SocialMedia;
import seedu.connectus.model.tag.Cca;
import seedu.connectus.model.tag.CcaPosition;
import seedu.connectus.model.tag.Module;
import seedu.connectus.model.tag.Tag;

/**
 * Tests that a {@code Person}'s information fields matches any of the keywords given.
 */
public class FieldsContainKeywordsPredicate implements Predicate<Person> {
    private List<String> keywords;
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private SocialMedia socialMedia;
    private Set<Tag> tags;
    private Birthday birthday;
    private Set<Module> modules;
    private Set<Cca> ccas;
    private Set<CcaPosition> ccaPositions;
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
        setSocialMedia(toCopy.socialMedia);
        setTags(toCopy.tags);
        setModules(toCopy.modules);
        setCcas(toCopy.ccas);
        setCcaPositions(toCopy.ccaPositions);
        setBirthday(toCopy.birthday);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isFieldKeywordPresent() {
        return CollectionUtil.isAnyNonNull(name, phone, email, address, socialMedia, tags,
                birthday, modules, ccas, ccaPositions);
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public Optional<List<String>> getKeywords() {
        return Optional.ofNullable(keywords);
    }
    public void setName(Name name) {
        this.name = name;
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Optional<Phone> getPhone() {
        return Optional.ofNullable(phone);
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Optional<Email> getEmail() {
        return Optional.ofNullable(email);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Optional<Address> getAddress() {
        return (address != null)
                ? Optional.of(address)
                : Optional.empty();
    }

    public void setBirthday(Birthday birthday) {
        this.birthday = birthday;
    }

    public Optional<Birthday> getBirthday() {
        return Optional.ofNullable(birthday);
    }

    public void setSocialMedia(SocialMedia socialMedia) {
        this.socialMedia = socialMedia;
    }

    public Optional<SocialMedia> getSocialMedia() {
        return Optional.ofNullable(socialMedia);
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(Set<Tag> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    /**
     * Returns an unmodifiable tag set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<Tag>> getTags() {
        return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
    }


    /**
     * Sets {@code modules} to this object's {@code modules}.
     * A defensive copy of {@code modules} is used internally.
     */
    public void setModules(Set<Module> modules) {
        this.modules = (modules != null) ? new HashSet<>(modules) : null;
    }

    /**
     * Returns an unmodifiable modules set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code modules} is null.
     */
    public Optional<Set<Module>> getModules() {
        return (modules != null) ? Optional.of(Collections.unmodifiableSet(modules)) : Optional.empty();
    }

    /**
     * Sets {@code ccas} to this object's {@code ccas}.
     * A defensive copy of {@code ccas} is used internally.
     */
    public void setCcas(Set<Cca> ccas) {
        this.ccas = (ccas != null) ? new HashSet<>(ccas) : null;
    }

    /**
     * Returns an unmodifiable ccas set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code ccas} is null.
     */
    public Optional<Set<Cca>> getCcas() {
        return (ccas != null) ? Optional.of(Collections.unmodifiableSet(ccas)) : Optional.empty();
    }

    /**
     * Sets {@code ccaPositions} to this object's {@code ccaPositions}.
     * A defensive copy of {@code ccaPositions} is used internally.
     */
    public void setCcaPositions(Set<CcaPosition> ccaPositions) {
        this.ccaPositions = (ccaPositions != null) ? new HashSet<>(ccaPositions) : null;
    }

    /**
     * Returns an unmodifiable ccaPositions set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code ccaPositions} is null.
     */
    public Optional<Set<CcaPosition>> getCcaPositions() {
        return (ccaPositions != null) ? Optional.of(Collections.unmodifiableSet(ccaPositions)) : Optional.empty();
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
                && getTags().equals(e.getTags())
                && getModules().equals(e.getModules())
                && getCcas().equals(e.getCcas())
                && getCcaPositions().equals(e.getCcaPositions())
                && getBirthday().equals(e.getBirthday())
                && getSocialMedia().equals(e.getSocialMedia());
    }

    //todo
    @Override
    public boolean test(Person person) {
        return keywords.stream().anyMatch(keyword ->
                StringUtil.containsKeywordsListIgnoreCase(person.getAllFieldsAsString(), keyword));
    }
}
