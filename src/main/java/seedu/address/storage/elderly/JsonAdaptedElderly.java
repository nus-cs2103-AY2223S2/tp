package seedu.address.storage.elderly;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.person.information.Region;
import seedu.address.model.person.information.RiskLevel;
import seedu.address.model.tag.Tag;
import seedu.address.storage.JsonAdaptedPerson;
import seedu.address.storage.JsonAdaptedTag;
import seedu.address.storage.JsonSerializable;

/**
 * Jackson-friendly version of {@link Elderly}.
 */
public class JsonAdaptedElderly extends JsonAdaptedPerson implements JsonSerializable<Elderly> {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Elderly's %s field is missing!";
    private final String riskLevel;

    /**
     * Constructs a {@code JsonAdaptedElderly} with the given elderly details.
     */
    @JsonCreator
    public JsonAdaptedElderly(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("nric") String nric, @JsonProperty("age") String age,
            @JsonProperty("region") String region,
            @JsonProperty("riskLevel") String riskLevel, @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {

        super(name, phone, email, address, nric, age, region, tagged);
        this.riskLevel = riskLevel;
    }

    /**
     * Converts a given {@code Elderly} into this class for Jackson use.
     */
    public JsonAdaptedElderly(Elderly source) {
        super(source);
        riskLevel = String.valueOf(source.getRiskLevel().riskStatus);
    }

    public RiskLevel getModelRiskLevel(String missingFieldMessageFormat) throws IllegalValueException {
        if (riskLevel == null) {
            throw new IllegalValueException(String.format(missingFieldMessageFormat, RiskLevel.class.getSimpleName()));
        }
        if (!RiskLevel.isValidRisk(riskLevel)) {
            throw new IllegalValueException(RiskLevel.MESSAGE_CONSTRAINTS);
        }
        return new RiskLevel(riskLevel);
    }

    /**
     * Converts this Jackson-friendly adapted elderly object into the model's {@code Elderly} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted elderly.
     */
    public Elderly toModelType(FriendlyLink friendlyLink) throws IllegalValueException {
        Name modelName = super.getModelName(MISSING_FIELD_MESSAGE_FORMAT);
        Phone modelPhone = super.getModelPhone(MISSING_FIELD_MESSAGE_FORMAT);
        Email modelEmail = super.getModelEmail(MISSING_FIELD_MESSAGE_FORMAT);
        Address modelAddress = super.getModelAddress(MISSING_FIELD_MESSAGE_FORMAT);
        Set<Tag> modelTags = super.getTagSet(friendlyLink);
        Nric modelNric = super.getModelNric(MISSING_FIELD_MESSAGE_FORMAT);
        Age modelAge = super.getModelAge(MISSING_FIELD_MESSAGE_FORMAT);
        Region modelRegion = super.getModelRegion(MISSING_FIELD_MESSAGE_FORMAT);
        RiskLevel modelRiskLevel = getModelRiskLevel(MISSING_FIELD_MESSAGE_FORMAT);

        return new Elderly(modelName, modelPhone, modelEmail, modelAddress,
               modelNric, modelAge, modelRegion, modelRiskLevel, modelTags);
    }
}
