package com.flowedu.config;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

/**
 * Created by jihoan on 2017. 11. 2..
 */
public class ConfigHolder {

    private static ConfigHolder config;

    @Value("#{config['service.isQuartzUseCheck']}")
    private boolean isQuartzUseCheck;
    @Value("#{config['file.uploads.path']}")
    private String uploadRoot;
    @Value("#{config['service.version']}")
    private String version;
    @Value("#{config['video.uploads.path']}")
    private String videoUploadsPath;
    @Value("#{config['video.resize.width']}")
    private int videoResizeWidth;
    @Value("#{config['video.resize.height']}")
    private int videoResizeHeight;
    @Value("#{config['video.thumbnail.uploads.path']}")
    private String videoThumbnailUploadsPath;


    @PostConstruct
    private ConfigHolder init() {
        config = this;
        return this;
    }

    public static boolean isQuartzUseCheck() {
        return config.isQuartzUseCheck;
    }

    public static String uploadRoot() {
        return config.uploadRoot;
    }

    public static String gerVersion() {
        return config.version;
    }

    public static String getVideoUploadsPath() {
        return config.videoUploadsPath;
    }

    public static int getVideoResizeWidth() {
        return config.videoResizeWidth;
    }

    public static int getVideoResizeHeight() {
        return config.videoResizeHeight;
    }

    public static String getVideoThumbnailUploadsPath() {
        return config.videoThumbnailUploadsPath;
    }


}
