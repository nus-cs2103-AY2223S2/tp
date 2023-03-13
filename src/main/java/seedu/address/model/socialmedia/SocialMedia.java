package seedu.address.model.socialmedia;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents all social media entries.
 */
public class SocialMedia {
    @JsonProperty("instagram")
    private final Instagram instagram;
    @JsonProperty("telegram")
    private final Telegram telegram;
    @JsonProperty("whatsapp")
    private final WhatsApp whatsapp;

    /**
     * Constructs a dummy {@code SocialMedia}.
     */
    public SocialMedia() {
        instagram = null;
        telegram = null;
        whatsapp = null;
    }

    private SocialMedia(Instagram i, Telegram t, WhatsApp w) {
        instagram = i;
        telegram = t;
        whatsapp = w;
    }

    /**
     * Returns a blank {@code SocialMedia}.
     */
    public static SocialMedia create() {
        return new SocialMedia();
    }

    /**
     * Adds Instagram to existing instance of {@code SocialMedia}.
     * @param i Instagram entry.
     * @return Updated {@code SocialMedia} object.
     */
    public SocialMedia withInstagram(Instagram i) {
        return i == null
            ? this
            : new SocialMedia(i, this.telegram, this.whatsapp);
    }

    /**
     * Adds Telegram to existing instance of {@code SocialMedia}.
     * @param t Telegram entry.
     * @return Updated {@code SocialMedia} object.
     */
    public SocialMedia withTelegram(Telegram t) {
        return t == null
            ? this
            : new SocialMedia(this.instagram, t, this.whatsapp);
    }

    /**
     * Adds WhatsApp to existing instance of {@code SocialMedia}.
     * @param w WhatsApp entry.
     * @return Updated {@code SocialMedia} object.
     */
    public SocialMedia withWhatsapp(WhatsApp w) {
        return w == null
            ? this
            : new SocialMedia(this.instagram, this.telegram, w);
    }

    /**
     * Supplements existing records with new entry.
     */
    public SocialMedia updateWith(SocialMedia updated) {
        if (updated == null) {
            return this;
        }

        return new SocialMedia(
            Optional.ofNullable(updated.instagram).orElse(this.instagram),
            Optional.ofNullable(updated.telegram).orElse(this.telegram),
            Optional.ofNullable(updated.whatsapp).orElse(this.whatsapp)
        );
    }

    public Instagram getInstagram() {
        return instagram;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    public WhatsApp getWhatsapp() {
        return whatsapp;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Social media");
        if (telegram != null) {
            builder.append("; Telegram: ").append(telegram);
        }

        if (instagram != null) {
            builder.append("; Instagram: ").append(instagram);
        }

        if (whatsapp != null) {
            builder.append("; WhatsApp: ").append(whatsapp);
        }

        return builder.toString();
    }
}
