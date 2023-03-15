package seedu.address.storage;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import seedu.address.model.socialmedia.Instagram;
import seedu.address.model.socialmedia.SocialMedia;
import seedu.address.model.socialmedia.Telegram;
import seedu.address.model.socialmedia.WhatsApp;

/**
 * Jackson-friendly version of {@link SocialMedia}.
 */
public class JsonAdaptedSocialMedia {
    private Optional<String> instagram;
    private Optional<String> telegram;
    private Optional<String> whatsApp;

    /**
     * Constructs a {@code JsonAdaptedSocialMedia} with the given {@code instagram},
     * {@code telegram}, {@code whatsApp}.
     */
    public JsonAdaptedSocialMedia(String instagram,
                                  String telegram,
                                  String whatsApp) {
        if (instagram == null || instagram.equals("")) {
            this.instagram = Optional.empty();
        } else {
            this.instagram = Optional.of(instagram);
        }

        if (telegram == null || telegram.equals("")) {
            this.telegram = Optional.empty();
        } else {
            this.telegram = Optional.of(telegram);
        }

        if (whatsApp == null || whatsApp.equals("")) {
            this.whatsApp = Optional.empty();
        } else {
            this.whatsApp = Optional.of(whatsApp);
        }
    }

    /**
     * Converts a given {@code SocialMedia} into this class for Jackson use.
     */
    public JsonAdaptedSocialMedia(SocialMedia source) {
        instagram = Optional.ofNullable(source.getInstagram()).map(i -> i.value);
        telegram = Optional.ofNullable(source.getTelegram()).map(i -> i.value);
        whatsApp = Optional.ofNullable(source.getWhatsapp()).map(i -> i.value);
    }

    /**
     * For our dear Jackson
     */
    public JsonAdaptedSocialMedia() {
        instagram = Optional.empty();
        telegram = Optional.empty();
        whatsApp = Optional.empty();
    }

    @JsonGetter("instagram")
    public String getInstagram() {
        return instagram.orElse(null);
    }
    @JsonGetter("telegram")
    public String getTelegram() {
        return telegram.orElse(null);
    }
    @JsonGetter("whatsApp")
    public String getWhatsApp() {
        return whatsApp.orElse(null);
    }

    @JsonSetter("instagram")
    public void setInstagram(String instagram) {
        this.instagram = Optional.ofNullable(instagram);
    }
    @JsonSetter("telegram")
    public void setTelegram(String telegram) {
        this.telegram = Optional.ofNullable(telegram);
    }
    @JsonSetter("whatsApp")
    public void setWhatsApp(String whatsApp) {
        this.whatsApp = Optional.ofNullable(whatsApp);
    }


    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     */
    public Optional<SocialMedia> toModelType() {
        return Optional.of(SocialMedia.create()
            .withInstagram(instagram.map(Instagram::of).orElse(null))
            .withTelegram(telegram.map(Telegram::of).orElse(null))
            .withWhatsapp(whatsApp.map(WhatsApp::of).orElse(null)));
    }

}
