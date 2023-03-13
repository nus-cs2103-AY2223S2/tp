package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.socialmedia.Instagram;
import seedu.address.model.socialmedia.SocialMedia;
import seedu.address.model.socialmedia.Telegram;
import seedu.address.model.socialmedia.WhatsApp;

/**
 * Jackson-friendly version of {@link SocialMedia}.
 */
public class JsonAdaptedSocialMedia {
    private final String instagram;
    private final String telegram;
    private final String whatsApp;

    /**
     * Constructs a {@code JsonAdaptedSocialMedia} with the given {@code instagram},
     * {@code telegram}, {@code whatsApp}.
     */
    @JsonCreator
    public JsonAdaptedSocialMedia(@JsonProperty("instagram") String instagram,
                                  @JsonProperty("telegram") String telegram,
                                  @JsonProperty("whatsApp") String whatsApp) {
        this.instagram = instagram;
        this.telegram = telegram;
        this.whatsApp = whatsApp;
    }

    /**
     * Converts a given {@code SocialMedia} into this class for Jackson use.
     */
    public JsonAdaptedSocialMedia(SocialMedia source) {
        instagram = source.getInstagram().value;
        telegram = source.getTelegram().value;
        whatsApp = source.getWhatsapp().value;
    }

    /**
     * For our dear Jackson
     */
    public JsonAdaptedSocialMedia() {
        instagram = "";
        telegram = "";
        whatsApp = "";
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     */
    public SocialMedia toModelType() {
        return SocialMedia.create()
            .withInstagram(Instagram.of(instagram))
            .withTelegram(Telegram.of(telegram))
            .withWhatsapp(WhatsApp.of(whatsApp));
    }

}
