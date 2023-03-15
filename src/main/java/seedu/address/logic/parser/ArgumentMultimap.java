package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Stores mapping of prefixes to their respective arguments.
 * Each key may be associated with multiple argument values.
 * Values for a given key are stored in a list, and the insertion ordering is maintained.
 * Keys are unique, but the list of argument values may contain duplicate argument values, i.e. the same argument value
 * can be inserted multiple times for the same prefix.
 */
public class ArgumentMultimap {

    /** Prefixes mapped to their respective arguments**/
    private final Map<Prefix, List<String>> argMultimap = new HashMap<>();

    /**
     * Associates the specified argument value with {@code prefix} key in this map.
     * If the map previously contained a mapping for the key, the new value is appended to the list of existing values.
     *
     * @param prefix   Prefix key with which the specified argument value is to be associated
     * @param argValue Argument value to be associated with the specified prefix key
     */
    public void put(Prefix prefix, String argValue) {
        List<String> argValues = getAllValues(prefix);
        argValues.add(argValue);
        argMultimap.put(prefix, argValues);
    }

    /**
     * Returns the last value of {@code prefix}.
     */
    public Optional<String> getValue(Prefix prefix) {
        if (!argMultimap.containsKey(prefix)) {
            switch(prefix.getPrefix()) {
            case "ageS/":
                Optional<String> missingStudentAge = Optional.of("Insert student age here!");
                return missingStudentAge;
            case "imgS/":
                Optional<String> missingImage = Optional.of("Insert student image here!");
                return missingImage;
            case "eS/":
                Optional<String> missingEmail = Optional.of("Insert student email here!");
                return missingEmail;
            case "pnS/":
                Optional<String> missingPhoneNumber = Optional.of("Insert student phone number here!");
                return missingPhoneNumber;
            case "cca/":
                Optional<String> missingCca = Optional.of("Insert student CCA here!");
                return missingCca;
            case "a/":
                Optional<String> missingAddress = Optional.of("Insert student address here!");
                return missingAddress;
            case "att/":
                Optional<String> missingAttendance = Optional.of("Insert student attendance here!");
                return missingAttendance;
            case "com/":
                Optional<String> missingComment = Optional.of("Insert student comment here!");
                return missingComment;
            case "score/":
                Optional<String> missingScore = Optional.of("Insert student score here!");
                return missingScore;
            case "weightage/":
                Optional<String> missingWeightage = Optional.of("Insert student weightage here!");
                return missingWeightage;
            case "deadline/":
                Optional<String> missingDeadline = Optional.of("Insert student deadline here!");
                return missingDeadline;
            case "hwdone/":
                Optional<String> missingHwDone = Optional.of("Insert student homework done here!");
                return missingHwDone;
            /*
            case "p/":
                Optional<String> missingParentPhone = Optional.of("Insert parent phone here!");
                return missingParentPhone;
             */
            case "imgP/":
                Optional<String> missingParentImage = Optional.of("Insert parent image here!");
                return missingParentImage;
            case "e/":
                Optional<String> missingParentEmail = Optional.of("Insert parent email here!");
                return missingParentEmail;
            case "test/":
                Optional<String> missingTest = Optional.of("Insert student test here!");
                return missingTest;
            case "hw/":
                Optional<String> missingHomework = Optional.of("Insert student homework here!");
                return missingHomework;
            default:
                List<String> values = getAllValues(prefix);
                return values.isEmpty() ? Optional.empty() : Optional.of(values.get(values.size() - 1));
            }
        }
        List<String> values = getAllValues(prefix);
        return values.isEmpty() ? Optional.empty() : Optional.of(values.get(values.size() - 1));
    }

    /**
     * Returns all values of {@code prefix}.
     * If the prefix does not exist or has no values, this will return an empty list.
     * Modifying the returned list will not affect the underlying data structure of the ArgumentMultimap.
     */
    public List<String> getAllValues(Prefix prefix) {
        if (!argMultimap.containsKey(prefix)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(argMultimap.get(prefix));
    }

    /**
     * Returns the preamble (text before the first valid prefix). Trims any leading/trailing spaces.
     */
    public String getPreamble() {
        return getValue(new Prefix("")).orElse("");
    }
}
