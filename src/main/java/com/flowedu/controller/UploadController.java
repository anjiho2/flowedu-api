package com.flowedu.controller;

import com.flowedu.config.ConfigHolder;
import com.flowedu.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.security.krb5.Config;

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

    //@Autowired
    //private VideoService videoService;

    /**
     * 동영상 파일 업로드 하기
     * @param request
     * @return
     */
    /*
    @RequestMapping(value = "/video", method = RequestMethod.POST)
    public ResponseEntity uploadVideo(MultipartHttpServletRequest request) {
        //Map<String, Object> uploadInfo = FileUploadUtil.fileUploadYmdLocation(request, "/Users/jihoan/Documents");
        String uploadInfo = FileUploadUtil.fileUploadNoRename(request, ConfigHolder.getVideoUploadsPath());
        return new ResponseEntity(uploadInfo, HttpStatus.OK);
    }
    */

    /**
     * 동영상 파일 업로드 (용량 변환, 썸네일 추출)
     * @param request
     * @return
     * @throws Exception
     */
    /*
    @RequestMapping(value = "/convert_video", method = RequestMethod.POST)
    public ResponseEntity convertVideo(MultipartHttpServletRequest request) throws Exception {
        MultipartFile videoFile = request.getFile("file_name");
        File destFile = new File(videoFile.getOriginalFilename());
        long videoFileSize = videoFile.getSize();
        videoFile.transferTo(destFile);
        if (videoFileSize > 10485760L) {
            // 용량 변환
            videoService.resizeVideo(destFile);
        } else {
            FileUploadUtil.fileUploadNoRename(request, ConfigHolder.getVideoUploadsPath());
        }
        //동영상 썸네일 추출
        videoService.getThumbnailFromVideoFile(destFile);
        FileUtil.fileDelete(videoFile.getOriginalFilename());
        return new ResponseEntity("OK", HttpStatus.OK);
    }
    */
    /**
     * 학원증명서 업로드
     * @param request
     * @param savePath
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "certificate_file", method = RequestMethod.POST)
    public @ResponseBody String uploadCetificateFile(MultipartHttpServletRequest request, @RequestParam("save_path") String savePath) {
        Map<String, Object>resultMap = FileUploadUtil.fileUploadCertificateFile(request, savePath);
        return new JsonBuilder().add("result", resultMap).build();
    }

    /**
     * 과제파일 업로드
     * @param request
     * @param savePath
     * @param lectureId
     * @return
     */
    @RequestMapping(value = "assignment_file", method = RequestMethod.POST)
    public @ResponseBody String uploadAssignmentFile(MultipartHttpServletRequest request,
                                                     @RequestParam("save_path") String savePath,
                                                     @RequestParam("lecture_id") Long lectureId) {
        Map<String, Object>resultMap = FileUploadUtil.fileUploadAssignmentFile(request, savePath, lectureId);
        return new JsonBuilder().add("result", resultMap).build();
    }

    /**
     * 학생이미지 파일 업로드
     * @param request
     * @param savePath
     * @return
     */
    @RequestMapping(value = "student_img_file", method = RequestMethod.POST)
    public @ResponseBody String uploadAssignmentFile(MultipartHttpServletRequest request, @RequestParam("save_path") String savePath) {
        Map<String, Object>resultMap = FileUploadUtil.fileUploadStudentImage(request, savePath);
        return new JsonBuilder().add("result", resultMap).build();
    }



}
