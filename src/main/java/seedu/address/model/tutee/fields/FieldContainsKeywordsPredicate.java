package seedu.address.model.tutee.fields;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tutee.Tutee;

/**
 * Tests that a {@code Tutee}'s {@code Name} matches any of the keywords given.
 */
public class FieldContainsKeywordsPredicate implements Predicate<Tutee> {
    private final List<String> nameKeyword;
    private final String phoneKeyword;
    private final String emailKeyword;
    private final List<String> addressKeyword;
    private final String subjectkeyword;
    private final String scheduleKeyword;
    private final String startTimeKeyword;
    private final String endTimeKeyword;
    private final String tagKeyword;

    public FieldContainsKeywordsPredicate(List<String> nameKeyword, String phoneKeyword, String emailKeyword,
                                          List<String> addressKeyword, String subjectkeyword,String scheduleKeyword,
                                          String startTimeKeyword, String endTimeKeyword, String tagKeyword) {
        this.nameKeyword = nameKeyword;
        this.phoneKeyword = phoneKeyword;
        this.emailKeyword = emailKeyword;
        this.addressKeyword = addressKeyword;
        this.subjectkeyword = subjectkeyword;
        this.scheduleKeyword = scheduleKeyword;
        this.startTimeKeyword = startTimeKeyword;
        this.endTimeKeyword = endTimeKeyword;
        this.tagKeyword = tagKeyword;
    }

    @Override
    public boolean test(Tutee tutee) {
        boolean containsName = nameKeyword.isEmpty() ||
                nameKeyword.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tutee.getName().toString(), keyword));
        boolean containsPhone = phoneKeyword.isEmpty() ||
                StringUtil.containsWordIgnoreCase(tutee.getPhone().value, phoneKeyword);
        boolean containsEmail = emailKeyword.isEmpty() ||
                StringUtil.containsWordIgnoreCase(tutee.getEmail().value, emailKeyword);
        boolean containsAddress = addressKeyword.isEmpty() ||
                addressKeyword.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tutee.getAddress().toString(), keyword));
        boolean containsSubject = subjectkeyword.isEmpty() ||
                StringUtil.containsWordIgnoreCase(tutee.getSubject().subject, subjectkeyword);
        boolean containsSchedule = scheduleKeyword.isEmpty() ||
                StringUtil.containsWordIgnoreCase(tutee.getSchedule().schedule, scheduleKeyword);
        boolean containsStartTime = startTimeKeyword.isEmpty() ||
                StringUtil.containsWordIgnoreCase(tutee.getStartTime().startTime, startTimeKeyword);
        boolean containsEndTime = endTimeKeyword.isEmpty() ||
                StringUtil.containsWordIgnoreCase(tutee.getEndTime().endTime, endTimeKeyword);
        boolean containsTag = tagKeyword.isEmpty() ||
                tutee.getTags().stream().anyMatch(tag -> StringUtil.containsWordIgnoreCase(tag.tagName, tagKeyword));

        return containsName && containsPhone && containsEmail && containsAddress && containsSubject && containsSchedule
                && containsStartTime && containsEndTime && containsTag;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FieldContainsKeywordsPredicate // instanceof handles nulls
                && nameKeyword.equals(((FieldContainsKeywordsPredicate) other).nameKeyword)) // state check
                && phoneKeyword.equals(((FieldContainsKeywordsPredicate) other).phoneKeyword)
                && emailKeyword.equals(((FieldContainsKeywordsPredicate) other).emailKeyword)
                && addressKeyword.equals(((FieldContainsKeywordsPredicate) other).addressKeyword)
                && subjectkeyword.equals(((FieldContainsKeywordsPredicate) other).subjectkeyword)
                && scheduleKeyword.equals(((FieldContainsKeywordsPredicate) other).scheduleKeyword)
                && startTimeKeyword.equals(((FieldContainsKeywordsPredicate) other).startTimeKeyword)
                && endTimeKeyword.equals(((FieldContainsKeywordsPredicate) other).endTimeKeyword)
                && tagKeyword.equals(((FieldContainsKeywordsPredicate) other).tagKeyword);
    }

}
