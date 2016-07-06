package com.example.android.miwok;


public class Word {
    private String defaultTranslation;
    private String miwokTranslation;
    private int imageResourceId;
    public static final int NO_IMAGE = -1;

    public Word(String defaultTranslation, String miwokTranslation) {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.imageResourceId = NO_IMAGE;
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

    public boolean hasImage() {
        return imageResourceId != NO_IMAGE;
    }
}
