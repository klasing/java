package src;
//****************************************************************************
//*                     import
//****************************************************************************
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

// my imports
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import com.google.api.client.http.FileContent;

//****************************************************************************
//*                     GoogleDrive
//****************************************************************************
public class GoogleDrive {
    /** Application name. */
    private static final String APPLICATION_NAME =
        "Drive API Java Quickstart";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/2/drive-java-quickstart.json");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final com.google.api.client.json.JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/drive-java-quickstart
     */
    private static final List<String> SCOPES =
        Arrays.asList(com.google.api.services.drive.DriveScopes.DRIVE);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    //************************************************************************
    //*                 authorize
    //************************************************************************
    public static Credential authorize() throws IOException {
        System.out.println("GoogleDrive.authorize()");
        // Load client secrets.
        InputStream in =
            GoogleDrive.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    //************************************************************************
    //*                 getDriveService
    //************************************************************************
    public static Drive getDriveService() throws IOException {
        System.out.println("GoogleDrive.getDriveService()");

        Credential credential = authorize();
        return new Drive.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    //************************************************************************
    //*                 initialize
    //************************************************************************
    public static void initialize() throws IOException {
        System.out.println("GoogleDrive.initialize()");
        // build a new authorized API client service
        service = getDriveService();
    }

    //************************************************************************
    //*                 search
    //************************************************************************
    public static boolean search(String strJSONFileName) throws IOException {
        System.out.println("GoogleDrive.search()");

        String pageToken = null;
        do {
            FileList result = service.files().list()
                .setQ("mimeType='application/json'")
                .setQ("name='" + strJSONFileName + "'")
                .setSpaces("drive")
                .setFields("nextPageToken, files(id, name)")
                .setPageToken(pageToken)
                .execute();
            for(File file: result.getFiles()) {
                System.out.printf("Found file: %s (%s)\n",
                    file.getName(), file.getId());
                fileJSON = file;
                fileId = file.getId();
                return true;
            }
            pageToken = result.getNextPageToken();
        } while (pageToken != null);
        return false;
    }

    //************************************************************************
    //*                 download
    //************************************************************************
    public static String download() throws IOException {
        System.out.println("GoogleDrive.download()");

        String jsonData = "";
        OutputStream outputStream = new ByteArrayOutputStream();
        service.files().get(fileId)
            .executeMediaAndDownloadTo(outputStream);
        byte b[] = ((ByteArrayOutputStream)outputStream).toByteArray();
        for(int x = 0; x < b.length; x++) {
            // store the characters into a string
            jsonData += (char)b[x];
        }
        return jsonData;
    }

    //************************************************************************
    //*                 upload
    //************************************************************************
    public static void upload(String JSON_FILE_PATH,
        String strJSONFileName) throws IOException {

        System.out.println("GoogleDrive.uploadJSONFile()");

        File fileMetadata = new File();
        fileMetadata.setName(strJSONFileName);
        fileMetadata.setMimeType("application/json");

        java.io.File filePath = new java.io.File(JSON_FILE_PATH + "\\" + strJSONFileName);
        FileContent mediaContent = new FileContent("application/json", filePath);
        File file = service.files().create(fileMetadata, mediaContent)
            .setFields("id")
            .execute();
    }

    //************************************************************************
    //*                 getRevisions
    //************************************************************************
    public static List<Revision> getRevisions() {
        System.out.println("GoogleDrive.getRevisions()");

        if (fileId.equals("")) return null;

        try {
            RevisionList revisions = service.revisions().list(fileId)
                .execute();
            //System.out.println(revisions.toString());
            for(Revision revision: revisions.getRevisions()) {
                System.out.println(revision.toString());
            }
            return revisions.getRevisions();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            ;
        }

        return null;
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static Drive service;
    private static File fileJSON = null;
    private static String fileId = "";
}