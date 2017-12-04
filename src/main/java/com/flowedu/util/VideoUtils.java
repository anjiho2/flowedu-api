package com.flowedu.util;

import com.flowedu.video.MyVideoListener;
import com.flowedu.video.Resizer;
import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.*;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.DemuxerTrack;
import org.jcodec.common.NIOUtils;
import org.jcodec.common.SeekableByteChannel;
import org.jcodec.common.model.Picture;
import org.jcodec.containers.mp4.demuxer.MP4Demuxer;
import org.jcodec.scale.AWTUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by jihoan on 2017. 11. 30..
 */
public class VideoUtils {

    private static final Integer WIDTH = 640;
    private static final Integer HEIGHT = 360;

    private static final String INPUT_FILE = "/Users/jihoan/Downloads/IMG_0287.MOV";
    private static final String OUTPUT_FILE = "/Users/jihoan/Downloads/output.mp4";

    public static void getImageFromFrame(File videoFile) {
        System.out.print("file >>> " + videoFile);
        String fileName = videoFile.getAbsolutePath();
        String baseName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.lastIndexOf("."));
        String savePath = "/Users/jihoan/Downloads";

        double frameNumber = 0d;
        try {
            SeekableByteChannel bc = NIOUtils.readableFileChannel(videoFile);
            MP4Demuxer dm = new MP4Demuxer(bc);
            DemuxerTrack vt = dm.getVideoTrack();
            frameNumber = vt.getMeta().getTotalDuration() / 5.0;
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Picture frame = FrameGrab.getNativeFrame(new File(fileName), frameNumber);
            BufferedImage img = AWTUtil.toBufferedImage(frame);
            File pngFile = new File(savePath + "/" + FileUtil.removeFileExtension(videoFile.toString()) + ".jpg");
            if (!pngFile.exists()) {
                pngFile.createNewFile();
            }
            ImageIO.write(img, "jpg", pngFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JCodecException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        //String filenamevideo = "f:/testvidfol/video.mp4"; //video file on your disk
        File vFile = null;
        //File initialFile = new File("src/main/resources/sample.txt");
        InputStream targetStream = new FileInputStream(vFile);

        IMediaWriter mWriter = ToolFactory.makeWriter("f:/testvidfol/videowriter.flv"); //output file

        IContainer containerVideo = IContainer.make();

        if (containerVideo.open(targetStream, null) < 0)
            throw new IllegalArgumentException("Cant find " + vFile.toString());

        int numStreamVideo = containerVideo.getNumStreams();

        System.out.println("Number of video streams: "+numStreamVideo );

        int videostreamt = -1; //this is the video stream id

        IStreamCoder videocoder = null;

        for(int i=0; i<numStreamVideo; i++){
            IStream stream = containerVideo.getStream(i);
            IStreamCoder code = stream.getStreamCoder();

            if(code.getCodecType() == ICodec.Type.CODEC_TYPE_VIDEO)
            {
                videostreamt = i;
                videocoder = code;
                break;
            }

        }


        if (videostreamt == -1) throw new RuntimeException("No video steam found");

        if(videocoder.open()<0 ) throw new RuntimeException("Cant open video coder");
        IPacket packetvideo = IPacket.make();


        mWriter.addVideoStream(1, 1, videocoder.getWidth(), videocoder.getHeight());

        while(containerVideo.readNextPacket(packetvideo) >= 0){
            if(packetvideo.getStreamIndex() == videostreamt){
                //video packet
                IVideoPicture picture = IVideoPicture.make(videocoder.getPixelType(),
                        videocoder.getWidth(),
                        videocoder.getHeight());
                int offset = 0;
                while (offset < packetvideo.getSize()){
                    int bytesDecoded = videocoder.decodeVideo(picture,
                            packetvideo,
                            offset);
                    if(bytesDecoded < 0) throw new RuntimeException("bytesDecoded not working");
                    offset += bytesDecoded;

                    if(picture.isComplete()){
                        System.out.println(picture.getPixelType());
                        mWriter.encodeVideo(1, picture);

                    }
                }
            }
        }









        // create custom listeners
//
//        MyVideoListener myVideoListener = new MyVideoListener(WIDTH, HEIGHT);
//        Resizer resizer = new Resizer(WIDTH, HEIGHT);
//
//        // reader
        IMediaReader reader = ToolFactory.makeReader(INPUT_FILE);
//        reader.addListener(resizer);
//
//        // writer
//        IMediaWriter writer = ToolFactory.makeWriter(OUTPUT_FILE, reader);
//        resizer.addListener(writer);
//        writer.addListener(myVideoListener);
//
//        // show video when encoding
//        reader.addListener(ToolFactory.makeViewer(true));
//
//        while (reader.readPacket() == null) {
//            // continue coding
//        }
//
    }

}
