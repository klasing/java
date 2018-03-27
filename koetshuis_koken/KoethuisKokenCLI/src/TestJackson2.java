package src;
//****************************************************************************
//*                     import
//****************************************************************************
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Console;
import java.io.File;
import java.io.IOException;

import java.lang.Exception;
import java.lang.IndexOutOfBoundsException;

import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import java.text.SimpleDateFormat;

import java.util.Date;
//****************************************************************************
//*                     TestJackson2
//****************************************************************************
public class TestJackson2 {
    //************************************************************************
    //*                 getMode
    //************************************************************************
    private static boolean getMode() throws Exception {
        System.out.println("getMode()");

        String strModeSelect = "";

        strModeSelect =
            CONSOLE.readLine("Do you want to run in test mode? [ Y | N ] ");

        // handle a return as input
        if (strModeSelect.equals("")) return true;

        // handle unrecognused option from input
        if (strModeSelect.equalsIgnoreCase("Y") || strModeSelect.equalsIgnoreCase("N")) {
            ;
        } else {
            throw new UnknownOptionException("Unknown option, enter Y for 'yes' or N for 'no'.");
        }

        return strModeSelect.equalsIgnoreCase("Y");

    }
    //************************************************************************
    //*                 getDate
    //************************************************************************
    private static String getDate() {
        System.out.println("getDate()");

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        Date currentDate = new Date();
        String strDate = sdf.format(currentDate);

        return strDate;
    }
    //************************************************************************
    //*                 findJSONFile
    //************************************************************************
    private static boolean findJSONFile() throws IOException {
        System.out.println("findJSONFile()");

        // search for file on Google Drive
        if (GoogleDrive.searchJSONFile(strJSONFileName)) {
            // download the file from Google Drive
            // the file is found by its fileId
            String jsonData = GoogleDrive.downloadJSONFile();
            // save JSON data string into local file systems
            myObject = mapper.readValue(jsonData, MyObject.class);
            return true;
        }
        return false;

        // find JSON file on local file system
        //Path path = FileSystems.getDefault().getPath(JSON_FILE_PATH, strJSONFileName);
        //return Files.exists(path);
    }
    //************************************************************************
    //*                 readJSONFile
    //************************************************************************
    private static void readJSONFile() throws IOException {
        System.out.println("readJSONFile()");

        // JSON from file to Object
        myObject = mapper.readValue(new File(JSON_FILE_PATH + "\\" + strJSONFileName), MyObject.class);
        // get lock mode to determine if further progress can be made
        bCanProceed = (myObject.getLock()) ? false : true;
    }
    //************************************************************************
    //*                 createJSONFile
    //************************************************************************
    private static void createJSONFile() {
        System.out.println("createJSONFile()");

        // create a new object
        myObject = new MyObject();
    }
    //************************************************************************
    //*                 setLockModeAndWrite
    //************************************************************************
    private static void setLockModeAndWrite(boolean bLockMode) throws IOException {
        System.out.println("setLockModeAndWrite()");

        if (bLockMode) {
            System.out.println("JSON file is being locked.");
        } else {
            System.out.println("JSON file is being unlocked.");
        }
        // set lock mode into object
        myObject.setLock(bLockMode);
        // JSON from Object to file
        mapper.writeValue(new File(JSON_FILE_PATH + "\\" + strJSONFileName), myObject);
        // write JSON file to Google Drive
        GoogleDrive.uploadJSONFile(JSON_FILE_PATH, strJSONFileName);
    }
    //************************************************************************
    //*                 getConsoleInput
    //************************************************************************
    private static void getConsoleInput() {
        System.out.println("TestJackson2.getConsoleInput()");

        String strHeaderCook = String.format("Cook.....: [%s] ", myObject.getCook());

        String strCook = CONSOLE.readLine(strHeaderCook);
        if (!strCook.equals("")) {
            // set new value for "cook"
            myObject.setCook(strCook);
        }
    }
    //************************************************************************
    //*                 getConsoleInput4Array
    //************************************************************************
    private static void getConsoleInput4Array() throws IndexOutOfBoundsException {
        System.out.println("TestJackson2.getConsoleInput4Array()");
        int iIdx = 0;
        boolean bProceedInput = true;
        while (bProceedInput) {
            String strHeaderEating = "";
            if (iIdx < myObject.getListEatingSize()) {
                strHeaderEating = String.format("Eating...: [%d: %s] ", iIdx, myObject.getEatingAt(iIdx));
            } else {
                strHeaderEating = String.format("Eating...: [%d] ", iIdx);
            }
            String strEating = "";
            strEating = CONSOLE.readLine(strHeaderEating);
            if (strEating.equals("")) {
                bProceedInput = false;
                break;
            }
            if (strEating.equals("*")) {
                myObject.removeEating(iIdx);
                continue;
            }
            if (iIdx < myObject.getListEatingSize()) {
                // set new value in array at index iIdx
                myObject.setEatingAt(iIdx, strEating);
            } else {
                // add new value to the array at the end
                myObject.setEating(strEating);
            }
            iIdx++;
        }
    }
    //************************************************************************
    //*                 main
    //************************************************************************
    public static void main(String[] args) throws Exception {
        System.out.println("TestJackson2.main()");

        // connect to Google Drive
        GoogleDrive.initialize();

        if (bTestMode = getMode()) {
            System.out.println("Running in test mode.");
            strJSONFileName = TEST_JSON_FILE;
        } else {
            System.out.println("Running in operational mode.");
            strJSONFileName = getDate() + ".json";
        }
        System.out.println("File name...: " + strJSONFileName);

        // check whether or not a JSON file exists
        if (bJSONFileExist = findJSONFile()) {
            System.out.println("File exists.");
            readJSONFile();
            // show object content
            System.out.println(myObject.toString());
        } else {
            System.out.println("File does not exist.");
            createJSONFile();
        }

        // 1) file does not exist: do proceed
        // 2) file exists and is not locked: do proceed
        if (bCanProceed) {
            // lock JSON file while progressing
            setLockModeAndWrite(true);

            // get user input for values
            getConsoleInput();
            getConsoleInput4Array();

            // JSON from Object to file
            mapper.writeValue(new File(JSON_FILE_PATH + "\\" + strJSONFileName), myObject);

            // unlock JSON file now progressing is done
            setLockModeAndWrite(false);
        } else {
            // file exists but is locked
            System.out.println("The file is locked. Updating is impossible. Try again later!");
        }

        System.exit(0);
    }
    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final Console CONSOLE = System.console();
    private static final String JSON_FILE_PATH = "C:\\Users\\Klasing\\Documents";
    private static final String TEST_JSON_FILE = "dd-MM-yy.json";

    private static boolean bTestMode = true;
    private static String strJSONFileName;

    private static boolean bJSONFileExist = true;
    private static ObjectMapper mapper = new ObjectMapper();
    private static MyObject myObject;
    private static boolean bCanProceed = true;
}