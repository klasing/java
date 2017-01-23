package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.lang.Exception;

import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.MissingFormatArgumentException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
//****************************************************************************
//*                     TestParseJSON
//****************************************************************************
public class TestParseJSON {
    //************************************************************************
    //*                 getMode
    //************************************************************************
    private static boolean getMode() throws Exception {
        //System.out.println("getMode()");

        String strModeSelect =
            CONSOLE.readLine("Do you want to run in test mode [ Y | N ]? ");

        // handle unrecognised option from input
        if (strModeSelect.equalsIgnoreCase("Y") || strModeSelect.equalsIgnoreCase("N")) {
            ;
        } else {
            throw new UnknownOptionException("Unknown option, enter Y for 'yes' or N for 'no'.");
        }

        return strModeSelect.equalsIgnoreCase("Y");
    }
    //************************************************************************
    //*                 findJSONFile
    //************************************************************************
    private static boolean findJSONFile() {
        //System.out.println("findJSONFile()");

        Path path = FileSystems.getDefault().getPath(JSON_FILE_PATH, strJSONFileName);
        return Files.exists(path);
    }
    //************************************************************************
    //*                 getDate
    //************************************************************************
    private static String getDate() {
        //System.out.println("getDate()");

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        Date currentDate = new Date();
        String strDate = sdf.format(currentDate);

        return strDate;
    }
    //************************************************************************
    //*                 readJSONFile
    //************************************************************************
    private static void readJSONFile() throws IOException, JSONException {
        //System.out.println("readJSONFile()");

        String jsonData = "";
        BufferedReader br = null;
        String line;
        br = new BufferedReader(new FileReader(JSON_FILE_PATH + "\\" + strJSONFileName));
        while ((line = br.readLine()) != null) {
            jsonData += line + "\n";
        }
        if (br != null) {
            br.close();
        }
        objJSON = new JSONObject(jsonData);
        // get lock mode to determine if further progress can proceed
        if (objJSON.getBoolean("lock")) bCanProceed = false; else bCanProceed = true;
    }
    //************************************************************************
    //*                 createJSONFile
    //************************************************************************
    private static void createJSONFile() {
        //System.out.println("createJSONFile()");

        objJSON = new JSONObject();
    }
    //************************************************************************
    //*                 setLockModeAndWrite
    //************************************************************************
    private static void setLockModeAndWrite(boolean bLockMode)
        throws IOException, JSONException {

        //System.out.println("setLockModeAndWrite()");

        if (bLockMode) {
            System.out.println("JSON file is being locked.");
            objJSON.put("lock", true);
        } else {
            System.out.println("JSON file is being unlocked.");
            objJSON.put("lock", false);
        }
        writeJSONFile();
    }
    //************************************************************************
    //*                 writeJSONFile
    //************************************************************************
    private static void writeJSONFile() throws IOException {
        //System.out.println("writeJSONFile()");

        FileWriter fw = null;
        fw = new FileWriter(JSON_FILE_PATH + "\\" + strJSONFileName);
        fw.write(objJSON.toString());
        if (fw != null) {
            fw.flush();
            fw.close();
        }
    }
    //************************************************************************
    //*                 getConsoleInput
    //************************************************************************
    private static void getConsoleInput()
        throws JSONException, MissingFormatArgumentException {

        //System.out.println("getConsoleInput()");

        String strHeaderCook = "Cook.....: %s";
        String strValueCook = "";
        if (bJSONFileExist) {
            strValueCook = objJSON.getString("cook");
            if (!strValueCook.equals("")) {
                strHeaderCook = String.format(strHeaderCook, "[" + strValueCook + "] ");
            }
        } else {
            strHeaderCook = "Cook.....: ";
        }
        String strCook = "";
        strCook = CONSOLE.readLine(strHeaderCook);
        if (!strCook.equals("")) {
            // set new value for "cook"
            objJSON.put("cook", strCook);
        }
    }
    //************************************************************************
    //*                 getArrayValue
    //************************************************************************
    private static String getArrayValue(int iIdx, JSONArray arrJSON)
        throws JSONException {

        //System.out.println("getArrayValue()");

        // return when index is passed array length
        if (iIdx >= arrJSON.length()) return "";

        String strValueEating = "";
        strValueEating = arrJSON.getString(iIdx);
        if (!strValueEating.equals("")) {
            return " [" + strValueEating + "] ";
        }
        return "";
    }
    //************************************************************************
    //*                 removeArrayElement
    //************************************************************************
    private static void removeArrayElement(int iIdx, JSONArray arrJSON) {
        //System.out.println("removeArrayElement()");

        // return when index is passed array length
        if (iIdx >= arrJSON.length()) return;

        arrJSON.remove(iIdx);

    }
    //************************************************************************
    //*                 getConsoleInput4Array
    //************************************************************************
    private static void getConsoleInput4Array() throws JSONException {
        //System.out.println("getConsoleInput4Array()");

        JSONArray arrJSON = new JSONArray();
        if (bJSONFileExist) {
            arrJSON = objJSON.getJSONArray("eating");
        }

        int iIdx = 0;
        boolean bProceedInput = true;
        while (bProceedInput) {
            String strHeaderEating = "Eating...: ";
            strHeaderEating += getArrayValue(iIdx, arrJSON);
            String strEating = "";
            strEating = CONSOLE.readLine(strHeaderEating);
            if (strEating.equals("")) {
                bProceedInput = false;
                break;
            }
            if (strEating.equals("*")) {
                removeArrayElement(iIdx, arrJSON);
                iIdx++;
                continue;
            }
            // set new value in array at index iIdx
            arrJSON.put(iIdx, strEating);
            iIdx++;
        }

        // place array arrJSON into object objJSON
        objJSON.put("eating", arrJSON);
    }
    //************************************************************************
    //*                 main
    //************************************************************************
    public static void main(String[] args) {
        //System.out.println("main()");

        try {
            // determine the mode in which the app has to run
            if (bTestMode = getMode()) {
                System.out.println("Running in test mode.");
                strJSONFileName = TEST_JSON_FILE;
            } else {
                System.out.println("Running in operational mode.");
                strJSONFileName = getDate() + ".json";
            }
            System.out.println("File name...: " + strJSONFileName);

            // check wether or not a JSON file exists
            if (bJSONFileExist = findJSONFile()) {
                System.out.println("File exists.");
                readJSONFile();
                System.out.println(objJSON);
            }
            else {
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

                // unlock JSON file now processing is done
                setLockModeAndWrite(false);
            } else {
                // file exists but is locked
                System.out.println("The file is locked. Try again later!");
            }
        } catch(Exception e) {
            e.printStackTrace();
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
    private static JSONObject objJSON = null;
    private static boolean bCanProceed = true;
}
/*
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import java.text.SimpleDateFormat;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
//****************************************************************************
//*                     TestParseJSON
//****************************************************************************
public class TestParseJSON {
    //************************************************************************
    //*                 getMode
    //************************************************************************
    private static boolean getMode() {
        System.out.println("getMode()");

        String strModeSelect =
            CONSOLE.readLine("Do you want to run in test mode [ Y | N ]? ");

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
    private static boolean findJSONFile() {
        System.out.println("findJSONFile()");

        Path path = FileSystems.getDefault().getPath(JSON_FILE_PATH, strJSONFileName);
        return Files.exists(path);
    }
    //************************************************************************
    //*                 readJSONFile
    //************************************************************************
    private static void readJSONFile() {
        System.out.println("readJSONFile()");

        String jsonData = "";
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(JSON_FILE_PATH + "\\" + strJSONFileName));
            while ((line = br.readLine()) != null) {
                jsonData += line + "\n";
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        try {
            objJSON = new JSONObject(jsonData);
            // get lock mode to determine if further progress can proceed
            if (objJSON.getBoolean("lock")) bCanProceed = false; else bCanProceed = true;
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }
    //************************************************************************
    //*                 createJSONFile
    //************************************************************************
    private static void createJSONFile() {
        System.out.println("createJSONFile()");

        objJSON = new JSONObject();
    }

    //************************************************************************
    //*                 setLockModeAndWrite
    //************************************************************************
    private static void setLockModeAndWrite(boolean bLockMode) {
        System.out.println("setLockModeAndWrite()");

        try {
            if (bLockMode) {
                objJSON.put("lock", true);
                System.out.println("JSON file is being locked.");
            } else {
                objJSON.put("lock", false);
                System.out.println("JSON file is being unlocked.");
            }
        } catch(JSONException e) {
            e.printStackTrace();
        }
        writeJSONFile();
    }
    //************************************************************************
    //*                 writeJSONFile
    //************************************************************************
    private static void writeJSONFile() {
        System.out.println("writeJSONFile()");

        FileWriter fw = null;
        try {
            fw = new FileWriter(JSON_FILE_PATH + "\\" + strJSONFileName);
            fw.write(objJSON.toString());
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.flush();
                    fw.close();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    //************************************************************************
    //*                 getConsoleInput
    //************************************************************************
    private static void getConsoleInput() {
        System.out.println("getConsoleInput()");

        String strValueCook = "";
        String strHeaderCook = "";
        if (bJSONFileExist) {
            try {
                strValueCook = objJSON.getString("cook");
            } catch(JSONException e) {
                e.printStackTrace();
            } finally {
                if (!strValueCook.equals("")) {
                    strHeaderCook = "Cook.....: [" + strValueCook + "] ";
                } else {
                    strHeaderCook = "Cook.....: ";
                }
            }
        }
        String strCook = "";
        strCook = CONSOLE.readLine(strHeaderCook);
        if (!strCook.equals("")) {
            // set new value for "cook"
            try {
                objJSON.put("cook", strCook);
            } catch(JSONException e) {
                e.printStackTrace();
            }
        }
     }
    //************************************************************************
    //*                 getArrayValue
    //************************************************************************
    private static String getArrayValue(int iIdx, JSONArray arrJSON) {
        System.out.println("getArrayValue()");

        String strReturn = "";
        // return when index is passed array length
        if (iIdx >= arrJSON.length()) return strReturn;

        String strValueEating = "";
        try {
            strValueEating = arrJSON.getString(iIdx);
        } catch(JSONException e) {
            e.printStackTrace();
        } finally {
            if (!strValueEating.equals("")) {
                strReturn = " [" + strValueEating + "] ";
            }
        }
        return strReturn;
    }
    //************************************************************************
    //*                 removeArrayElement
    //************************************************************************
    private static void removeArrayElement(int iIdx, JSONArray arrJSON) {
        System.out.println("removeArrayElement()");

        // return when index is passed array length
        if (iIdx >= arrJSON.length()) return;

        arrJSON.remove(iIdx);

    }
    //************************************************************************
    //*                 getConsoleInput4Array
    //************************************************************************
    private static void getConsoleInput4Array() {
        System.out.println("getConsoleInput4Array()");

        JSONArray arrJSON = new JSONArray();
        try {
            arrJSON = objJSON.getJSONArray("eating");
        } catch(JSONException e) {
            e.printStackTrace();
        }

        int iIdx = 0;
        boolean bProceedInput = true;
        while (bProceedInput) {
            String strHeaderEating = "Eating...: ";
            strHeaderEating += getArrayValue(iIdx, arrJSON);
            String strEating = "";
            strEating = CONSOLE.readLine(strHeaderEating);
            if (strEating.equals("*")) {
                removeArrayElement(iIdx, arrJSON);
                iIdx++;
            } else {
                if (strEating.equals("")) {
                    bProceedInput = false;
                } else {
                    // set new value in array at index iIdx
                    try {
                        arrJSON.put(iIdx, strEating);
                    } catch(JSONException e) {
                        e.printStackTrace();
                    }
                    iIdx++;
                }
            }
        }

        // place array arrJSON into object objJSON
        try {
            objJSON.put("eating", arrJSON);
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }
    //************************************************************************
    //*                 main
    //************************************************************************
    public static void main(String[] args) {
        System.out.println("main()");

        // determine the mode in which the app has to run
        if (bTestMode = getMode()) {
            System.out.println("Running in test mode.");
            strJSONFileName = TEST_JSON_FILE;
        } else {
            System.out.println("Running in operational mode.");
            strJSONFileName = getDate() + ".json";
        }
        System.out.println("File name...: " + strJSONFileName);

        // check wether or not a JSON file exists
        if (bJSONFileExist = findJSONFile()) {
            System.out.println("File exist.");
            readJSONFile();
            System.out.println(objJSON);
        }
        else {
            System.out.println("File does not exist.");
            createJSONFile();
        }

        if (bCanProceed) {
            // lock JSON file while progressing
            setLockModeAndWrite(true);

            // get user input for values
            getConsoleInput();
            getConsoleInput4Array();
System.out.println(objJSON);

            // unlock JSON file now processing is done
            setLockModeAndWrite(false);
        } else {
            System.out.println("De file is vergrendeld. Probeer het later opnieuw!");
        }
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
    private static JSONObject objJSON = null;
    private static boolean bCanProceed = true;
}
*/
/*
        JSONArray arrJSON = new JSONArray();
        String strValueEating = "";
        String strHeaderEating = "";
        if (bJSONFileExist) {
            try {
                arrJSON = objJSON.getJSONArray("eating");
                strValueEating = arrJSON.getString(0);
            } catch(JSONException e) {
                e.printStackTrace();
            } finally {
                if (!strValueEating.equals("")) {
                    strHeaderEating = "Eating...: [" + strValueEating + "] ";
                } else {
                    strHeaderEating = "Eating...: ";
                }
            }
        }
        String strEating = "";
        strEating = CONSOLE.readLine(strHeaderEating);
        if (!strEating.equals("")) {
            // set new value for "eating"
            try {
                arrJSON.put(0, strEating);
                objJSON.put("eating", arrJSON);
            } catch(JSONException e) {
                e.printStackTrace();
            }
        }
*/

/*
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.io.Console;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
//****************************************************************************
//*                     TestParseJSON
//****************************************************************************
public class TestParseJSON {
    //************************************************************************
    //*                 getDate
    //************************************************************************
    private static String getDate() {
        String strDate;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        Date currentDate = new Date();
        strDate = sdf.format(currentDate);
        return strDate;
    }
    //************************************************************************
    //*                 findCurrentJSON
    //************************************************************************
    private static boolean findCurrentJSON(String strCurrentJSON) {
        Path path = FileSystems.getDefault().getPath(JSON_FILEPATH, strCurrentJSON);
        return Files.exists(path);
    }
    //************************************************************************
    //*                 readFile
    //************************************************************************
    private static boolean readFile(String strCurrentJSON) {
        String jsonData = "";

        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(JSON_FILEPATH + "\\" + strCurrentJSON));
            while ((line = br.readLine()) != null) {
                jsonData += line + "\n";
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        try {
            JSONObject obj = new JSONObject(jsonData);
            System.out.println("Lock.....: " + obj.getBoolean("lock"));
            System.out.println("Cook.....: " + obj.getString("cook"));
            JSONArray arr = new JSONArray();
            arr = obj.getJSONArray("eating");
            for (int i = 0; i < arr.length(); i++) {
                System.out.println("Eating...: " + arr.getString(i));
            }
            if (obj.getBoolean("lock")) {
                System.out.println("File is locked. Probeer 'updaten' later!");
                // file is locked so return lock==true
                return true;
            }
        } catch(JSONException e) {
            e.printStackTrace();
        }
        // file is NOT locked so return lock==false
        return false;
    }
    //************************************************************************
    //*                 updateFile
    //************************************************************************
    private static void updateFile(String strCurrentJSON) {
        boolean bLock;
        if (!(bLock = readFile(strCurrentJSON))) {
            System.out.println("Update");
            getConsoleInput();
            writeFile(strCurrentJSON);
        }
    }
    //************************************************************************
    //*                 getConsoleInput
    //************************************************************************
    private static void getConsoleInput() {
        Console console = System.console();
        strCook = console.readLine("Cook.....: ");
        boolean bProceed = true;
        while (bProceed) {
            String strEating = "";
            strEating = console.readLine("Eating...: ");
            if (strEating.equals("")) {
                bProceed = false;
            } else {
                listEating.add(strEating);
            }
        }
    }
    //************************************************************************
    //*                 writeFile
    //************************************************************************
    private static void writeFile(String strCurrentJSON) {
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        try {
            obj.put("lock", true);
            obj.put("cook", strCook);
            for (String str : listEating) {
                arr.put(str);
            }
            obj.put("eating", arr);
        } catch(JSONException e) {
            e.printStackTrace();
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(JSON_FILEPATH + "\\" + strCurrentJSON);
            fw.write(obj.toString());
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.flush();
                    fw.close();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

    }
    //************************************************************************
    //*                 main
    //************************************************************************
    public static void main(String[] args) {
        System.out.println("TestParseJSON");
        String strCurrentDate = getDate();
        // existing file
        String strCurrentJSON = "dd-MM-yy" + ".json";
        // non existing file
        //String strCurrentJSON = strCurrentDate + ".json";
        System.out.println("File...: " + strCurrentJSON);
        if (findCurrentJSON(strCurrentJSON)) {
            System.out.println("File bestaat.");
            updateFile(strCurrentJSON);
        } else {
            System.out.println("File bestaat niet.");
            //createFile(strCurrentJSON);
        }
        System.exit(0);
    }
    //************************************************************************
    //*                 declare
    //************************************************************************
    private static final String JSON_FILEPATH = "C:\\Users\\Klasing\\Documents";
    private static String strDate;

    private static List<String> listEating = new ArrayList<String>();
    private static String strCook = "";
}
*/
/*
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
//****************************************************************************
//*                     TestParseJSON
//****************************************************************************
public class TestParseJSON {
    //************************************************************************
    //*                 main
    //************************************************************************
    public static void main(String[] args) {
        String jsonData = "";

        System.out.println("TestParseJSON");

        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader("C:\\Users\\Klasing\\Documents\\dd-MM-yy.json"));
            while ((line = br.readLine()) != null) {
                jsonData += line + "\n";
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        try {
            JSONObject obj = new JSONObject(jsonData);
            System.out.println("Lock.....: " + obj.getBoolean("lock"));
            System.out.println("Cook.....: " + obj.getString("cook"));
            JSONArray arr = new JSONArray();
            arr = obj.getJSONArray("eating");
            for (int i = 0; i < arr.length(); i++) {
                System.out.println("Eating...: " + arr.getString(i));
            }
            if (obj.getBoolean("lock")) {
                System.out.println("File is locked. Probeer 'updaten' later!");
            }
        } catch(JSONException e) {
            e.printStackTrace();
        }

    }
}
*/