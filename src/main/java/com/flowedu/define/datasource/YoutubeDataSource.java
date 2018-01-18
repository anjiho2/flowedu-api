package com.flowedu.define.datasource;

public enum YoutubeDataSource {

    APPLICATION_NAME("youtube-flowedu-uploadvideo"),
    VIDEO_FILE_FORMAT("video/*"),
    VIDEO_UPLOAD_URI("https://www.googleapis.com/auth/youtube.upload"),

    CREDENTIAL_DATASTORE_UPLOAD("uploadvideo"),

    PRIVATE_STATUS("private"),
    PUBLIC_STATUS("public");
    ;

    final private String property;

    YoutubeDataSource(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }
}
