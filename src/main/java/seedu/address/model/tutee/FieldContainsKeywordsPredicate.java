package seedu.address.model.tutee;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Tutee}'s {@code Name} matches any of the keywords given.
 */
public class FieldContainsKeywordsPredicate implements Predicate<Tutee> {
    private final List<String> nameKeywords;
    private final String phoneKeyword;
    private final String emailKeyword;
    private final String addressKeyword;
    private final String subjectkeyword;
    private final String scheduleKeyword;
    private final String startTimeKeyword;
    private final String endTimeKeyword;


    public FieldContainsKeywordsPredicate(List<String> nameKeywords, String phoneKeyword, String emailKeyword,
                                          String addressKeyword, String subjectkeyword,String scheduleKeyword,
                                          String startTimeKeyword, String endTimeKeyword) {
        this.nameKeywords = nameKeywords;
        this.phoneKeyword = phoneKeyword;
        this.emailKeyword = emailKeyword;
        this.addressKeyword = addressKeyword;
        this.subjectkeyword = subjectkeyword;
        this.scheduleKeyword = scheduleKeyword;
        this.startTimeKeyword = startTimeKeyword;
        this.endTimeKeyword = endTimeKeyword;
    }

    @Override
    public boolean test(Tutee tutee) {
        boolean containsName = nameKeywords.isEmpty() || nameKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tutee.getName().fullName, keyword));
        boolean containsPhone = phoneKeyword.isEmpty() ||
                StringUtil.containsWordIgnoreCase(tutee.getPhone().value, phoneKeyword);
        boolean containsEmail = emailKeyword.isEmpty() ||
                StringUtil.containsWordIgnoreCase(tutee.getEmail().value, emailKeyword);
        boolean containsAddress = addressKeyword.isEmpty() ||
                StringUtil.containsWordIgnoreCase(tutee.getAddress().value, addressKeyword);
        boolean containsSubject = subjectkeyword.isEmpty() ||
                StringUtil.containsWordIgnoreCase(tutee.getSubject().subject, subjectkeyword);
        boolean containsSchedule = scheduleKeyword.isEmpty() ||
                StringUtil.containsWordIgnoreCase(tutee.getSchedule().schedule, scheduleKeyword);
        boolean containsStartTime = startTimeKeyword.isEmpty() ||
                StringUtil.containsWordIgnoreCase(tutee.getStartTime().startTime, startTimeKeyword);
        boolean containsEndTime = endTimeKeyword.isEmpty() ||
                StringUtil.containsWordIgnoreCase(tutee.getEndTime().endTime, endTimeKeyword);

        return containsName && containsPhone && containsEmail && containsAddress && containsSubject && containsSchedule
                && containsStartTime && containsEndTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FieldContainsKeywordsPredicate // instanceof handles nulls
                && nameKeywords.equals(((FieldContainsKeywordsPredicate) other).nameKeywords)) // state check
                && phoneKeyword.equals(((FieldContainsKeywordsPredicate) other).phoneKeyword)
                && emailKeyword.equals(((FieldContainsKeywordsPredicate) other).emailKeyword)
                && addressKeyword.equals(((FieldContainsKeywordsPredicate) other).addressKeyword)
                && subjectkeyword.equals(((FieldContainsKeywordsPredicate) other).subjectkeyword)
                && scheduleKeyword.equals(((FieldContainsKeywordsPredicate) other).scheduleKeyword)
                && startTimeKeyword.equals(((FieldContainsKeywordsPredicate) other).startTimeKeyword)
                && endTimeKeyword.equals(((FieldContainsKeywordsPredicate) other).endTimeKeyword);
    }

}
