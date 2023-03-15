package seedu.address.model.socialmedia;

import java.util.Optional;

/**
 * Represents all social media entries.
 */
public class SocialMedia {
    private final Instagram instagram;
    private final Telegram telegram;
    private final WhatsApp whatsapp;

    /**
     * Constructs a dummy {@code SocialMedia}.
     */
    public SocialMedia() {
        instagram = null;
        telegram = null;
        whatsapp = null;
    }

    /**
     * Constructs a {@code SocialMedia}.
     */
    public SocialMedia(Instagram i, Telegram t, WhatsApp w) {
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

    /**
     * Checks if the {@code SocialMedia} holds any value.
     */
    public boolean isBlank() {
        return !(instagram != null || telegram != null || whatsapp != null);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        if (instagram != null) {
            builder.append("Instagram: ").append(instagram).append("    ");
        }

        if (telegram != null) {
            builder.append("Telegram: ").append(telegram).append("    ");
        }

        if (whatsapp != null) {
            builder.append("WhatsApp: ").append(whatsapp).append("    ");
        }

        return builder.toString();
    }
}
