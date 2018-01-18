package com.flowedu.youtube.service.impl;

import com.flowedu.define.datasource.YoutubeDataSource;
import com.flowedu.domain.GoogleCredential;
import com.flowedu.util.FileUtil;
import com.flowedu.youtube.config.OAuth;
import com.flowedu.youtube.service.YoutubeService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@Service
public class YoutubeServiceImpl implements YoutubeService {

    private final Logger logger = LoggerFactory.getLogger(YoutubeServiceImpl.class);

    private static YouTube youTube;

    public GoogleCredential getGoogleCredentialInfo() throws IOException {
        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.readonly");
        Credential credential = OAuth.googleAuthorize(scopes, "myuploads");
        GoogleCredential googleCredential = new GoogleCredential(
                credential.getAccessToken(),
                credential.getRefreshToken(),
                credential.getExpirationTimeMilliseconds()
        );
        return googleCredential;
    }

    /**
     * <PRE>
     * 1. Comment : 유투브에 영상 업로드 하기
     * 2. 작성자 : 안지호
     * 3. 작성일 : 2017. 12 .22
     * </PRE>
     * @param videoFile
     * @param videoTitle
     * @param videoDescription
     * @return
     */
    @Override
    public Video uploadYoutubeVideo(File videoFile, String videoTitle, String videoDescription) {

        Video returnVideo = new Video();
        //유투브 업로드 uri 정의 값 가져오기
        List<String> scopes = Lists.newArrayList(YoutubeDataSource.VIDEO_UPLOAD_URI.getProperty());

        try {
            //구글 OAuth2 인증
            Credential credential = OAuth.googleAuthorize(scopes, YoutubeDataSource.CREDENTIAL_DATASTORE_UPLOAD.getProperty());
            //유투브 어플리케이션에 담기
            youTube = new YouTube.Builder(OAuth.HTTP_TRANSPORT, OAuth.JSON_FACTORY, credential)
                    .setApplicationName(YoutubeDataSource.APPLICATION_NAME.getProperty()).build();

            logger.info("Uploading: " + videoFile.toString());

            Video videoObjectDefiningMetadata = new Video();

            VideoStatus status = new VideoStatus();
            //동영상 상태 정의 (public : 공개, private : 비공개)
            status.setPrivacyStatus(YoutubeDataSource.PRIVATE_STATUS.getProperty());
            videoObjectDefiningMetadata.setStatus(status);

            VideoSnippet snippet = new VideoSnippet();
            Calendar cal = Calendar.getInstance();
            //동영상 설명 값
            snippet.setDescription(videoDescription + cal.getTime());

            /*  키워드 검색이 필요할때 사용
            List<String> tags = new ArrayList<String>();
            tags.add("test");
            tags.add("example");
            tags.add("java");
            tags.add("YouTube Data API V3");
            tags.add("erase me");
            snippet.setTags(tags);
            */
            //동영상 제목 값
            snippet.setTitle(videoTitle);

            videoObjectDefiningMetadata.setSnippet(snippet);
            InputStream is = new FileInputStream(videoFile);
            InputStreamContent mediaContent = new InputStreamContent(YoutubeDataSource.VIDEO_FILE_FORMAT.getProperty(), is);
            //동영상 올리기
            YouTube.Videos.Insert videoInsert = youTube.videos()
                    .insert("snippet,statistics,status", videoObjectDefiningMetadata, mediaContent);

            MediaHttpUploader uploader = videoInsert.getMediaHttpUploader();

            uploader.setDirectUploadEnabled(false);

            MediaHttpUploaderProgressListener progressListener = new MediaHttpUploaderProgressListener() {
                public void progressChanged(MediaHttpUploader uploader) throws IOException {
                    switch (uploader.getUploadState()) {
                        case INITIATION_STARTED:
                            logger.info("Initiation Started");
                            break;
                        case INITIATION_COMPLETE:
                            logger.info("Initiation Completed");
                            break;
                        case MEDIA_IN_PROGRESS:
                            logger.info("Upload in progress");
                            //logger.info("Upload percentage: " + uploader.getProgress());
                            break;
                        case MEDIA_COMPLETE:
                            logger.info("Upload Completed!");
                            break;
                        case NOT_STARTED:
                            logger.info("Upload Not Started!");
                            break;
                    }
                }
            };
            uploader.setProgressListener(progressListener);

            returnVideo = videoInsert.execute();

            logger.info("\n================== Returned Video ==================\n");
            logger.info("  - Id: " + returnVideo.getId());
            logger.info("  - Title: " + returnVideo.getSnippet().getTitle());
            logger.info("  - Tags: " + returnVideo.getSnippet().getTags());
            logger.info("  - Privacy Status: " + returnVideo.getStatus().getPrivacyStatus());
            logger.info("  - Video Count: " + returnVideo.getStatistics().getViewCount());
            //동영상이 업로드 되면 로컬 동여상은 삭제
            FileUtil.fileDelete(videoFile.toString());
        } catch (GoogleJsonResponseException e) {
            logger.info("GoogleJsonResponseException code: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.info("IOException: " + e.getMessage());
            e.printStackTrace();
        } catch (Throwable t) {
            logger.info("Throwable: " + t.getMessage());
            t.printStackTrace();
        }
        return returnVideo;
    }

    @Override
    public void videoList() {
        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.readonly");

        try {
            Credential credential = OAuth.googleAuthorize(scopes, "myuploads");

            youTube = new YouTube.Builder(OAuth.HTTP_TRANSPORT, OAuth.JSON_FACTORY, credential).setApplicationName(
                    "youtube-cmdline-myuploads-sample").build();

            YouTube.Channels.List channelRequest = youTube.channels().list("contentDetails");

            channelRequest.setMine(true);
            channelRequest.setFields("items/contentDetails,nextPageToken,pageInfo");
            ChannelListResponse channelListResponse = channelRequest.execute();

            List<Channel> channelList = channelListResponse.getItems();

            if (channelList != null) {
                String uploadPlayListId = channelList.get(0).getContentDetails().getRelatedPlaylists().getUploads();

                List<PlaylistItem> playlistItemList = new ArrayList<PlaylistItem>();

                YouTube.PlaylistItems.List playlistItemRequest =
                        youTube.playlistItems().list("id,contentDetails,snippet");
                playlistItemRequest.setPlaylistId(uploadPlayListId);

                playlistItemRequest.setFields("items(contentDetails/videoId,snippet/title,snippet/publishedAt),nextPageToken,pageInfo");

                String nextToken = "";

                do {
                    playlistItemRequest.setPageToken(nextToken);
                    PlaylistItemListResponse playlistItemResult = playlistItemRequest.execute();

                    playlistItemList.addAll(playlistItemResult.getItems());

                    nextToken = playlistItemResult.getNextPageToken();
                } while (nextToken != null);
                // Prints information about the results.
                prettyPrint(playlistItemList.size(), playlistItemList.iterator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void prettyPrint(int size, Iterator<PlaylistItem> playlistEntries) {
        System.out.println("=============================================================");
        System.out.println("\t\tTotal Videos Uploaded: " + size);
        System.out.println("=============================================================\n");

        while (playlistEntries.hasNext()) {
            PlaylistItem playlistItem = playlistEntries.next();
            System.out.println(" video name  = " + playlistItem);
            System.out.println(" video name  = " + playlistItem.getSnippet().getTitle());
            System.out.println(" video id    = " + playlistItem.getContentDetails().getVideoId());
            System.out.println(" upload date = " + playlistItem.getSnippet().getPublishedAt());
            System.out.println("\n-------------------------------------------------------------\n");
        }
    }
}
