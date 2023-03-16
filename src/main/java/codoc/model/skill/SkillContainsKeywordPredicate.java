package codoc.model.skill;

import java.util.List;
import java.util.function.Predicate;

import codoc.commons.util.StringUtil;
import codoc.model.person.Person;

public class SkillContainsKeywordPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public SkillContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        /*return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
*/

        if (person.getSkills().isEmpty()) {
            return false;
        }
        return keywords.stream()
                .anyMatch(keyword -> person.getSkills().contains(new Skill(keyword)));
        //return person.getSkills().stream().anyMatch(skill -> keywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(skill.toString(), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SkillContainsKeywordPredicate // instanceof handles nulls
                && keywords.equals(((SkillContainsKeywordPredicate) other).keywords)); // state check
    }

}