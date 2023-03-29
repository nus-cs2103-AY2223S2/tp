//package seedu.vms.storage.keyword;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import seedu.vms.commons.exceptions.IllegalValueException;
//import seedu.vms.commons.exceptions.LimitExceededException;
//import seedu.vms.model.IdData;
//import seedu.vms.model.keyword.Keyword;
//import seedu.vms.model.keyword.KeywordManager;
////import seedu.vms.model.keyword.ReadOnlyKeywordManager;
//
///**
// * An Immutable KeywordManager that is serializable to JSON format.
// */
//public class JsonSerializableKeywordManager {
//
//    public static final String DUPLICATE_ID = "Keyword list contains duplicate ID(s).";
//
//    private final List<JsonAdaptedKeywordData> datas = new ArrayList<>();
//
//    /**
//     * Constructs a {@code JsonSerializableKeywordManager} with the given keywords.
//     */
//    @JsonCreator
//    public JsonSerializableKeywordManager(@JsonProperty("datas") List<JsonAdaptedKeywordData> datas) {
//        this.datas.addAll(datas);
//    }
//
//    /**
//     * Converts a given {@code ReadOnlyKeywordManager} into this class for Jackson use.
//     *
//     * @param source future changes to this will not affect the created {@code JsonSerializableKeywordManager}.
//     */
//    public JsonSerializableKeywordManager(ReadOnlyKeywordManager source) {
//        datas.addAll(source.getMapView()
//                .values()
//                .stream()
//                .map(JsonAdaptedKeywordData::new)
//                .collect(Collectors.toList()));
//    }
//
//    /**
//     * Converts this patient manager into the model's {@code KeywordManager} object.
//     *
//     * @throws IllegalValueException if there were any data constraints violated.
//     */
//    public KeywordManager toModelType() throws IllegalValueException {
//        KeywordManager keywordManager = new KeywordManager();
//        for (JsonAdaptedKeywordData jsonAdaptedKeywordData : datas) {
//            IdData<Keyword> keywordData = jsonAdaptedKeywordData.toModelType();
//            if (keywordManager.contains(keywordData.getId())) {
//                throw new IllegalValueException(DUPLICATE_ID);
//            }
//            try {
//                keywordManager.add(keywordData);
//            } catch (LimitExceededException limitEx) {
//                // TODO: better message
//                throw new IllegalValueException("ID limit reached");
//            }
//        }
//        return keywordManager;
//    }
//
//}
