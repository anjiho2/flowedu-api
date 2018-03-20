//package com.flowedu.service;
//
//import com.flowedu.config.ConfigHolder;
//import com.flowedu.error.FlowEduErrorCode;
//import com.flowedu.error.FlowEduException;
//import com.flowedu.util.FileUtil;
//import com.flowedu.video.MyVideoListener;
//import com.flowedu.video.Resizer;
//import com.xuggle.mediatool.IMediaReader;
//import com.xuggle.mediatool.IMediaWriter;
//import com.xuggle.mediatool.ToolFactory;
//import org.jcodec.api.FrameGrab;
//import org.jcodec.api.JCodecException;
//import org.jcodec.common.DemuxerTrack;
//import org.jcodec.common.NIOUtils;
//import org.jcodec.common.SeekableByteChannel;
//import org.jcodec.common.model.Picture;
//import org.jcodec.containers.mp4.demuxer.MP4Demuxer;
//import org.jcodec.scale.AWTUtil;
//import org.springframework.stereotype.Service;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.*;
//
///**
// * Created by jihoan on 2017. 11. 30..
// */
//@Service
//public class VideoService {
//
//    /**
//     * 동영상 용량 줄이기
//     * @param destFile
//     * @throws Exception
//     */
//    public void resizeVideo(File destFile) throws Exception {
//        Integer WIDTH = ConfigHolder.getVideoResizeWidth();
//        Integer HEIGHT = ConfigHolder.getVideoResizeHeight();
//        String VIDEO_UPLOAD_PATH = ConfigHolder.getVideoUploadsPath();
//        //String VIDEO_UPLOAD_PATH = "/Users/jihoan/Documents/test1.mp4";
//
//        // create custom listeners
//        MyVideoListener myVideoListener = new MyVideoListener(WIDTH, HEIGHT);
//        Resizer resizer = new Resizer(WIDTH, HEIGHT);
//
//        // reader
//        IMediaReader reader = ToolFactory.makeReader(destFile.toString());
//        reader.addListener(resizer);
//
//        String outputFile = VIDEO_UPLOAD_PATH + "/" + FileUtil.removeFileExtension(destFile.toString()) + ".mp4";
//        // writer
//        IMediaWriter writer = ToolFactory.makeWriter(outputFile, reader);
//        resizer.addListener(writer);
//        writer.addListener(myVideoListener);
//
//        // show video when encoding
//        //reader.addListener(ToolFactory.makeViewer(false));
//
//        while (reader.readPacket() == null) {
//            // continue coding
//            throw new FlowEduException(FlowEduErrorCode.CUSTOM_VIDEO_RESIZE_NULL);
//        }
//    }
//
//    /**
//     * 동영상 썸네일 추출 하기
//     * @param videoFile
//     */
//    public void getThumbnailFromVideoFile(File videoFile) {
//        String fileName = videoFile.getAbsolutePath();
//        //String baseName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.lastIndexOf("."));
//        String savePath = ConfigHolder.getVideoThumbnailUploadsPath();
//
//        double frameNumber = 0d;
//        try {
//            SeekableByteChannel bc = NIOUtils.readableFileChannel(videoFile);
//            MP4Demuxer dm = new MP4Demuxer(bc);
//            DemuxerTrack vt = dm.getVideoTrack();
//            frameNumber = vt.getMeta().getTotalDuration() / 5.0;
//        } catch (FileNotFoundException e1) {
//            e1.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            Picture frame = FrameGrab.getNativeFrame(new File(fileName), frameNumber);
//            BufferedImage img = AWTUtil.toBufferedImage(frame);
//            File pngFile = new File(savePath + "/" + FileUtil.removeFileExtension(videoFile.toString()) + ".jpg");
//            if (!pngFile.exists()) {
//                pngFile.createNewFile();
//            }
//            ImageIO.write(img, "jpg", pngFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JCodecException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
