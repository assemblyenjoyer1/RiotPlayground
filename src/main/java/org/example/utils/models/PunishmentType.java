package org.example.utils.models;

public enum PunishmentType {
    AFK("afk"),
    HATE_SPEECH("hateSpeech"),
    INAPPROPRIATE_NAME("inappropriateName"),
    INTENTIONAL_FEEDING("intentionalFeeding"),
    NEGATIVE_ATTITUDE("negativeAttitude"),
    SPAMMING("spamming"),
    VERBAL_ABUSE("verbalAbuse"),
    VICTORY_DEFEAT_REFUSAL("victoryDefeatRefusal");

    private final String code;

    PunishmentType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}