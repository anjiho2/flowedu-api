package com.flowedu.controller;

import com.flowedu.domain.GoogleCredential;
import com.flowedu.youtube.service.YoutubeService;
import com.google.api.client.auth.oauth2.Credential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(value = "youtube")
public class YoutubeController {

    private static final Logger logger = LoggerFactory.getLogger(YoutubeController.class);

    @Autowired
    private YoutubeService youtubeService;

    /**
     * <PRE>
     * 1. Comment : 유투브 영상 업로드
     * 2. 작성자 : 안지호
     * 3. 작성일 : 2017. 12 .22
     * </PRE>
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload_video", method = RequestMethod.POST)
    public ResponseEntity uploadVideo(MultipartHttpServletRequest request) throws IOException {
        MultipartFile videoFile = request.getFile("file_name");
        File destFile = new File(videoFile.getOriginalFilename());
        videoFile.transferTo(destFile);
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        youtubeService.uploadYoutubeVideo(destFile, title, description);
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @RequestMapping(value = "/video_list", method = RequestMethod.POST)
    public ResponseEntity videoList(MultipartHttpServletRequest request) {
        youtubeService.videoList();
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @RequestMapping(value = "/credential_info", method = RequestMethod.POST)
    public ResponseEntity googleCredential() throws IOException {
        GoogleCredential googleCredential = youtubeService.getGoogleCredentialInfo();
        return new ResponseEntity(googleCredential, HttpStatus.OK);
    }

}
