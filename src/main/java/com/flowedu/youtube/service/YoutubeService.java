package com.flowedu.youtube.service;

import com.flowedu.domain.GoogleCredential;
import com.google.api.services.youtube.model.Video;

import java.io.File;
import java.io.IOException;

public interface YoutubeService {

    GoogleCredential getGoogleCredentialInfo() throws IOException;

    Video uploadYoutubeVideo(File videoFile, String videoTitle, String videoDescription);

    void videoList();
}
