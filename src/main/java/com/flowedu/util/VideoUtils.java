//package com.flowedu.util;
//
//import com.flowedu.config.ConfigHolder;
//import com.flowedu.video.MyVideoListener;
//import com.flowedu.video.Resizer;
//import com.xuggle.mediatool.IMediaReader;
//import com.xuggle.mediatool.IMediaWriter;
//import com.xuggle.mediatool.ToolFactory;
//import com.xuggle.xuggler.*;
//import org.jcodec.api.FrameGrab;
//import org.jcodec.api.JCodecException;
//import org.jcodec.common.DemuxerTrack;
//import org.jcodec.common.NIOUtils;
//import org.jcodec.common.SeekableByteChannel;
//import org.jcodec.common.model.Picture;
//import org.jcodec.containers.mp4.demuxer.MP4Demuxer;
//import org.jcodec.scale.AWTUtil;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.*;
//
///**
// * Created by jihoan on 2017. 11. 30..
// */
//public class VideoUtils {
//
//    private static final Integer WIDTH = 640;
//    private static final Integer HEIGHT = 360;
//
//    private static final String INPUT_FILE = "/Users/jihoan/Downloads/IMG_0287.MOV";
//    private static final String OUTPUT_FILE = "/Users/jihoan/Downloads/output.mp4";
//
//    public static void getImageFromFrame(File videoFile) {
//        System.out.print("file >>> " + videoFile);
//        String fileName = videoFile.getAbsolutePath();
//        String baseName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.lastIndexOf("."));
//        String savePath = "/Users/jihoan/Downloads";
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
//
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
//
//    public static void main(String[] args) throws Exception {
//        Integer WIDTH = 640;
//        Integer HEIGHT = 380;
//        //String VIDEO_UPLOAD_PATH = ConfigHolder.getVideoUploadsPath();
//        String VIDEO_PATH = "/Users/jihoan/Downloads/TEST.mp4";
//
//        // create custom listeners
//        MyVideoListener myVideoListener = new MyVideoListener(WIDTH, HEIGHT);
//        Resizer resizer = new Resizer(WIDTH, HEIGHT);
//
//        // reader
//        IMediaReader reader = ToolFactory.makeReader(VIDEO_PATH);
//        reader.addListener(resizer);
//
//        String outputFile = "/Users/jihoan/Downloads" + "/" +  "test22.mp4";
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
//        }
//
//
//    }
//
//}
