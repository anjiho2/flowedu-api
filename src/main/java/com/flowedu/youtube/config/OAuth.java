package com.flowedu.youtube.config;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.FileDataStoreFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * <PRE>
 * 1. Comment : 구글 OAuth2 인증
 * 2. 작성자 : 안지호
 * 3. 작성일 : 2017. 12 .21
 * </PRE>
 *
 */
public class OAuth {

    private static final Logger logger = LoggerFactory.getLogger(OAuth.class);

    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    public static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private static final String CREDENTIALS_DIRECTORY = ".oauth-credentials2";

    public static Credential googleAuthorize(List<String> scopes, String credentialDatastore) throws IOException {

        Reader clientSecretReader = new InputStreamReader(OAuth.class.getResourceAsStream("/client_secret.json"));
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, clientSecretReader);

        if (clientSecrets.getDetails().getClientId().startsWith("Ender")
                || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
            logger.info("Enter Client ID and Secret from https://console.developers.google.com/project/_/apiui/credential "
                    + "into src/main/resources/client_secrets.json");
            System.exit(1);
        }

        FileDataStoreFactory fileDataStoreFactory = new FileDataStoreFactory(new File(System.getProperty("user.home") + "/" + CREDENTIALS_DIRECTORY));
        DataStore<StoredCredential> dataStore = fileDataStoreFactory.getDataStore(credentialDatastore);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, scopes).setCredentialDataStore(dataStore).build();

        LocalServerReceiver localServerReceiver = new LocalServerReceiver.Builder().setPort(8080).build();

        return new AuthorizationCodeInstalledApp(flow, localServerReceiver).authorize("user");
    }
}
