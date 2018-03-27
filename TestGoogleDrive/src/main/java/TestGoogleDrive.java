<<<<<<< HEAD


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

import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.*;
import com.google.api.services.drive.Drive;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

// my imports
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import com.google.api.client.http.FileContent;

public class TestGoogleDrive {
    /** Application name. */
    private static final String APPLICATION_NAME =
        "Drive API Java Quickstart";

    /** Directory to store user credentials for this application. */
    //private static final java.io.File DATA_STORE_DIR = new java.io.File(
    //    System.getProperty("user.home"), ".credentials/drive-java-quickstart");
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/2/drive-java-quickstart.json");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/drive-java-quickstart
     */
    //private static final List<String> SCOPES =
    //    Arrays.asList(DriveScopes.DRIVE_METADATA_READONLY);
    private static final List<String> SCOPES =
        Arrays.asList(DriveScopes.DRIVE);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
            TestGoogleDrive.class.getResourceAsStream("/client_secret.json");
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

    /**
     * Build and return an authorized Drive client service.
     * @return an authorized Drive client service
     * @throws IOException
     */
    public static Drive getDriveService() throws IOException {
        Credential credential = authorize();
        return new Drive.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static void main(String[] args) throws IOException {
        // Build a new authorized API client service.
        Drive service = getDriveService();

        // Print the names and IDs for up to 10 files.
//        FileList result = service.files().list()
//             .setPageSize(10)
//             .setFields("nextPageToken, files(id, name)")
//             .execute();

//        List<File> files = result.getFiles();
//        if (files == null || files.size() == 0) {
//            System.out.println("No files found.");
//        } else {
//            System.out.println("Files:");
//            for (File file : files) {
//                System.out.printf("%s (%s)\n", file.getName(), file.getId());
//            }
//        }

///*
        // Search for files
        // https://developers.google.com/drive/v3/web/search-parameters
        String pageToken = null;
        do {
            FileList result = service.files().list()
                .setQ("mimeType='application/json'")
                .setQ("name='dd-MM-yy.json'")
                .setSpaces("drive")
                .setFields("nextPageToken, files(id, name)")
                .setPageToken(pageToken)
                .execute();
            for(File file: result.getFiles()) {
                System.out.printf("Found file: %s (%s)\n",
                    file.getName(), file.getId());
            }
            pageToken = result.getNextPageToken();
        } while (pageToken != null);
//*/
/*
        // Upload a file
        // https://developers.google.com/drive/v3/web/manage-uploads
        File fileMetadata = new File();
        fileMetadata.setName("dd-MM-yy.json");
        fileMetadata.setMimeType("application/json");

        java.io.File filePath = new java.io.File("C:\\Users\\Klasing\\Documents\\dd-MM-yy.json");
        FileContent mediaContent = new FileContent("application/json", filePath);
        File file = service.files().create(fileMetadata, mediaContent)
            .setFields("id")
            .execute();
        System.out.println("File ID: " + file.getId());
*/
/*
        // Download a file
        // https://developers.google.com/drive/v3/web/manage-downloads
        String fileId = "0B_ZtO9REIvggVVozV3VLRjNCR2s";
        OutputStream outputStream = new ByteArrayOutputStream();
        service.files().get(fileId)
            .executeMediaAndDownloadTo(outputStream);
        byte b[] = ((ByteArrayOutputStream)outputStream).toByteArray();
        for(int x = 0; x < b.length; x++) {
            // printing the characters
            System.out.print((char)b[x]);
        }
*/
    }
}
/*
// Build a new authorized API client service.
Drive driveService = getDriveService();

String pageToken = null;
do {
    FileList result = driveService.files().list()
            .setQ("mimeType='application/json'")
            .setSpaces("drive")
            .setFields("nextPageToken, files(id, name)")
            .setPageToken(pageToken)
            .execute();
    for(File file: result.getFiles()) {
        System.out.printf("Found file: %s (%s)\n",
                file.getName(), file.getId());
    }
    pageToken = result.getNextPageToken();
} while (pageToken != null);

File fileMetadata = new File();
fileMetadata.setName("My JSON");//fileMetadata.setName("My Report");
fileMetadata.setMimeType("application/json");//fileMetadata.setMimeType("application/vnd.google-apps.spreadsheet");

java.io.File filePath = new java.io.File("dd-MM-yy.json");//java.io.File filePath = new java.io.File("files/report.csv");
FileContent mediaContent = new FileContent("application/json", filePath);
File file = driveService.files().create(fileMetadata, mediaContent)
        .setFields("id")
        .execute();
System.out.println("File ID: " + file.getId());
=======


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

import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.*;
import com.google.api.services.drive.Drive;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

// my imports
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import com.google.api.client.http.FileContent;

public class TestGoogleDrive {
    /** Application name. */
    private static final String APPLICATION_NAME =
        "Drive API Java Quickstart";

    /** Directory to store user credentials for this application. */
    //private static final java.io.File DATA_STORE_DIR = new java.io.File(
    //    System.getProperty("user.home"), ".credentials/drive-java-quickstart");
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/2/drive-java-quickstart.json");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/drive-java-quickstart
     */
    //private static final List<String> SCOPES =
    //    Arrays.asList(DriveScopes.DRIVE_METADATA_READONLY);
    private static final List<String> SCOPES =
        Arrays.asList(DriveScopes.DRIVE);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
            TestGoogleDrive.class.getResourceAsStream("/client_secret.json");
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

    /**
     * Build and return an authorized Drive client service.
     * @return an authorized Drive client service
     * @throws IOException
     */
    public static Drive getDriveService() throws IOException {
        Credential credential = authorize();
        return new Drive.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static void main(String[] args) throws IOException {
        // Build a new authorized API client service.
        Drive service = getDriveService();

        // Print the names and IDs for up to 10 files.
//        FileList result = service.files().list()
//             .setPageSize(10)
//             .setFields("nextPageToken, files(id, name)")
//             .execute();

//        List<File> files = result.getFiles();
//        if (files == null || files.size() == 0) {
//            System.out.println("No files found.");
//        } else {
//            System.out.println("Files:");
//            for (File file : files) {
//                System.out.printf("%s (%s)\n", file.getName(), file.getId());
//            }
//        }

///*
        // Search for files
        // https://developers.google.com/drive/v3/web/search-parameters
        String pageToken = null;
        do {
            FileList result = service.files().list()
                .setQ("mimeType='application/json'")
                .setQ("name='dd-MM-yy.json'")
                .setSpaces("drive")
                .setFields("nextPageToken, files(id, name)")
                .setPageToken(pageToken)
                .execute();
            for(File file: result.getFiles()) {
                System.out.printf("Found file: %s (%s)\n",
                    file.getName(), file.getId());
            }
            pageToken = result.getNextPageToken();
        } while (pageToken != null);
//*/
/*
        // Upload a file
        // https://developers.google.com/drive/v3/web/manage-uploads
        File fileMetadata = new File();
        fileMetadata.setName("dd-MM-yy.json");
        fileMetadata.setMimeType("application/json");

        java.io.File filePath = new java.io.File("C:\\Users\\Klasing\\Documents\\dd-MM-yy.json");
        FileContent mediaContent = new FileContent("application/json", filePath);
        File file = service.files().create(fileMetadata, mediaContent)
            .setFields("id")
            .execute();
        System.out.println("File ID: " + file.getId());
*/
/*
        // Download a file
        // https://developers.google.com/drive/v3/web/manage-downloads
        String fileId = "0B_ZtO9REIvggVVozV3VLRjNCR2s";
        OutputStream outputStream = new ByteArrayOutputStream();
        service.files().get(fileId)
            .executeMediaAndDownloadTo(outputStream);
        byte b[] = ((ByteArrayOutputStream)outputStream).toByteArray();
        for(int x = 0; x < b.length; x++) {
            // printing the characters
            System.out.print((char)b[x]);
        }
*/
    }
}
/*
// Build a new authorized API client service.
Drive driveService = getDriveService();

String pageToken = null;
do {
    FileList result = driveService.files().list()
            .setQ("mimeType='application/json'")
            .setSpaces("drive")
            .setFields("nextPageToken, files(id, name)")
            .setPageToken(pageToken)
            .execute();
    for(File file: result.getFiles()) {
        System.out.printf("Found file: %s (%s)\n",
                file.getName(), file.getId());
    }
    pageToken = result.getNextPageToken();
} while (pageToken != null);

File fileMetadata = new File();
fileMetadata.setName("My JSON");//fileMetadata.setName("My Report");
fileMetadata.setMimeType("application/json");//fileMetadata.setMimeType("application/vnd.google-apps.spreadsheet");

java.io.File filePath = new java.io.File("dd-MM-yy.json");//java.io.File filePath = new java.io.File("files/report.csv");
FileContent mediaContent = new FileContent("application/json", filePath);
File file = driveService.files().create(fileMetadata, mediaContent)
        .setFields("id")
        .execute();
System.out.println("File ID: " + file.getId());
>>>>>>> 5839fb13e8eecee60acee4bb332b91b2b147bac9
*/