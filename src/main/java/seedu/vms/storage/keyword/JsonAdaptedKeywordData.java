//package seedu.vms.storage.keyword;
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import seedu.vms.commons.exceptions.IllegalValueException;
//import seedu.vms.model.IdData;
//import seedu.vms.model.keyword.Keyword;
//
///**
// * Jackson-friendly version of {@code IdData<Keyword>}.
// */
//public class JsonAdaptedKeywordData {
//    private final boolean isActive;
//    private final int id;
//    private final JsonAdaptedKeyword keyword;
//
//
//    /**
//     * Constructs a {@code JsonAdaptedPatientData} with the given patient details.
//     */
//    @JsonCreator
//    public JsonAdaptedKeywordData(
//            @JsonProperty("isActive") boolean isActive,
//            @JsonProperty("id") int id,
//            @JsonProperty("keyword") JsonAdaptedKeyword keyword) {
//        this.isActive = isActive;
//        this.id = id;
//        this.keyword = keyword;
//    }
//
//
//    /**
//     * Converts a given {@code IdData<Keyword>} into this class for Jackson use.
//     */
//    public JsonAdaptedKeywordData(IdData<Keyword> keywordData) {
//        isActive = keywordData.isActive();
//        id = keywordData.getId();
//        keyword = new JsonAdaptedKeyword(keywordData.getValue());
//    }
//
//
//    /**
//     * Converts this Jackson-friendly adapted patient data object into the model's {@code IdData<Patient>} object.
//     *
//     * @throws IllegalValueException if there were any data constraints violated in the adapted patient data.
//     */
//    public IdData<Keyword> toModelType() throws IllegalValueException {
//        return new IdData<>(isActive, id, keyword.toModelType());
//    }
//}
