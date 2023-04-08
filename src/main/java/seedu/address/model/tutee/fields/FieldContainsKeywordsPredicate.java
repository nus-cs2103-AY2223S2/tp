package seedu.address.model.tutee.fields;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tutee.Tutee;

/**
 * Tests that a {@code Tutee}'s {@code Name} matches any of the keywords given.
 */
// Solution adapted from https://github.com/AY2223S2-CS2103T-W10-3/tp/blob/master/src/main/java/seedu/address/model/person/FieldContainsPartialKeywordsPredicate.java
public class FieldContainsKeywordsPredicate implements Predicate<Tutee> {
    private final List<String> nameKeywords;
    private final String phoneKeyword;
    private final String emailKeyword;
    private final List<String> addressKeywords;
    private final String subjectkeyword;
    private final String scheduleKeyword;
    private final String startTimeKeyword;
    private final String endTimeKeyword;
    private final List<String> tagKeywords;

    public FieldContainsKeywordsPredicate(List<String> nameKeywords, String phoneKeyword, String emailKeyword,
                                          List<String> addressKeywords, String subjectkeyword,String scheduleKeyword,
                                          String startTimeKeyword, String endTimeKeyword, List<String> tagKeywords) {
        this.nameKeywords = nameKeywords;
        this.phoneKeyword = phoneKeyword;
        this.emailKeyword = emailKeyword;
        this.addressKeywords = addressKeywords;
        this.subjectkeyword = subjectkeyword;
        this.scheduleKeyword = scheduleKeyword;
        this.startTimeKeyword = startTimeKeyword;
        this.endTimeKeyword = endTimeKeyword;
        this.tagKeywords = tagKeywords;
    }

    @Override
    public boolean test(Tutee tutee) {
        boolean containsName = nameKeywords.isEmpty() ||
                nameKeywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tutee.getName().toString(), keyword));
        boolean containsPhone = phoneKeyword.isEmpty() ||
                StringUtil.containsWordIgnoreCase(tutee.getPhone().value, phoneKeyword);
        boolean containsEmail = emailKeyword.isEmpty() ||
                StringUtil.containsWordIgnoreCase(tutee.getEmail().value, emailKeyword);
        boolean containsAddress = addressKeywords.isEmpty() ||
                addressKeywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tutee.getAddress().toString(), keyword));
        boolean containsSubject = subjectkeyword.isEmpty() ||
                StringUtil.containsWordIgnoreCase(tutee.getSubject().subject, subjectkeyword);
        boolean containsSchedule = scheduleKeyword.isEmpty() ||
                StringUtil.containsWordIgnoreCase(tutee.getSchedule().schedule, scheduleKeyword);
        boolean containsStartTime = startTimeKeyword.isEmpty() ||
                StringUtil.containsWordIgnoreCase(tutee.getStartTime().startTime, startTimeKeyword);
        boolean containsEndTime = endTimeKeyword.isEmpty() ||
                StringUtil.containsWordIgnoreCase(tutee.getEndTime().endTime, endTimeKeyword);
        boolean containsTag = tagKeywords.isEmpty() ||
                tutee.getTags().stream().anyMatch(tag ->
                        tagKeywords.stream().anyMatch(tagKeyword -> StringUtil.containsWordIgnoreCase(tag.tagName, tagKeyword)));

        return containsName && containsPhone && containsEmail && containsAddress && containsSubject && containsSchedule
                && containsStartTime && containsEndTime && containsTag;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FieldContainsKeywordsPredicate // instanceof handles nulls
                && nameKeywords.equals(((FieldContainsKeywordsPredicate) other).nameKeywords)) // state check
                && phoneKeyword.equals(((FieldContainsKeywordsPredicate) other).phoneKeyword)
                && emailKeyword.equals(((FieldContainsKeywordsPredicate) other).emailKeyword)
                && addressKeywords.equals(((FieldContainsKeywordsPredicate) other).addressKeywords)
                && subjectkeyword.equals(((FieldContainsKeywordsPredicate) other).subjectkeyword)
                && scheduleKeyword.equals(((FieldContainsKeywordsPredicate) other).scheduleKeyword)
                && startTimeKeyword.equals(((FieldContainsKeywordsPredicate) other).startTimeKeyword)
                && endTimeKeyword.equals(((FieldContainsKeywordsPredicate) other).endTimeKeyword)
                && tagKeywords.equals(((FieldContainsKeywordsPredicate) other).tagKeywords);
    }

}
