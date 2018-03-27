import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
public class MyGoogleDrive {
    public static void main(String[] args) throws IOException, JSONException {
        System.out.println("MyGoogleDrive.main()");
        GoogleDrive.initialize();
        System.exit(0);
    }
}