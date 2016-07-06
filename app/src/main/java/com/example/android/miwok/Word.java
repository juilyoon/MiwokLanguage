package com.example.android.miwok;


public class Word {
    private String defaultTranslation;
    private String miwokTranslation;
    private int imageResourceId;

    public Word(String defaultTranslation, String miwokTranslation) {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
    }

    public Word(String miwokTranslation, String defaultTranslation, int imageResourceId) {
        this.miwokTranslation = miwokTranslation;
        this.defaultTranslation = defaultTranslation;
        this.imageResourceId = imageResourceId;
    }

    /** Get image associated w/ word */
    public int getImageResourceId() {
        return imageResourceId;
    }

    /** Get Miwok translation of a word. */
    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    /** Get default translation of word. */
    public String getDefaultTranslation() {
        return defaultTranslation;
    }
}
