package com.flowedu.controller;

import com.flowedu.service.VideoService;
import com.flowedu.util.DateUtils;
import com.flowedu.util.FileUploadUtil;
import com.flowedu.util.FileUtil;
import com.flowedu.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jihoan on 2017. 11. 30..
 */
@Controller
@RequestMapping(value = "upload")
public class UploadController {

    protected static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "/video", method = RequestMethod.POST)
    public ResponseEntity uploadVideo(MultipartHttpServletRequest request) {
        Map<String, Object> uploadInfo = FileUploadUtil.fileUploadYmdLocation(request, "/Users/jihoan/Documents");
        return new ResponseEntity(uploadInfo, HttpStatus.OK);
    }

    @RequestMapping(value = "/convert_video", method = RequestMethod.POST)
    public ResponseEntity convertVideo(MultipartHttpServletRequest request) throws Exception {
        MultipartFile videoFile = request.getFile("file_name");
        File destFile = new File(videoFile.getOriginalFilename());
        long videoFileSize = videoFile.getSize();
        videoFile.transferTo(destFile);
        if (videoFileSize > 10485760L) {
            videoService.resizeVideo(destFile);
        }
        videoService.getThumbnailFromVideoFile(destFile);
        FileUtil.fileDelete(videoFile.getOriginalFilename());
        return new ResponseEntity("OK", HttpStatus.OK);
    }

}
