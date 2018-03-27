package src;
//****************************************************************************
//*                     import
//****************************************************************************
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//****************************************************************************
//*                     Panel4Dinner
//****************************************************************************
public class Panel4Dinner {
    public Panel4Dinner(int _iIdx) {
        System.out.println("<<constructor>> Panel4Dinner()");

        iIdx = _iIdx;
        // search Google Drive if file exist for current day
        // if file exist, check whether file is locked
        try {
            bCanProceed = readJsonFile();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            ;
        }
        if (bCanProceed) {
            lockAndWrite(true);

            createPanel4Dinner();
            // clear inputs
            jtfCook.setText("");
            jtaEating.setText("");
            if (bExist) setJsonContent();

        } else {
            createLockMessage();
        }
    }

    //************************************************************************
    //*                 getLock
    //************************************************************************
    protected static boolean getLock() {
        System.out.println("Panel4Dinner.getLock()");
        if (jsonObject == null) return false;
        return jsonObject.getLock();
    }

    //************************************************************************
    //*                 lockAndWrite
    //************************************************************************
    protected static void lockAndWrite(boolean bLock) {
        System.out.println("Panel4Dinner.lockAndWrite()");

        if (bLock) {
            System.out.println("File is being locked.");
        } else {
            System.out.println("File is being unlocked.");
        }
        jsonObject.setLock(bLock);
        try {
            writeJsonFile();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            ;
        }
    }

    //************************************************************************
    //*                 setJsonContent
    //************************************************************************
    private void setJsonContent() {
        System.out.println("Panel4Dinner.setJsonContent()");

        jtfCook.setText(jsonObject.getCook());

        for (String strElement : jsonObject.getEating()) {
            jtaEating.append(strElement + '\n');
        }
    }

    //************************************************************************
    //*                 readJsonFile
    //************************************************************************
    private boolean readJsonFile() throws IOException {
        System.out.println("Panel4Dinner.readJsonFile()");

        //strJSONFileName = "dd-MM-yy.json";
        strJSONFileName = Frame4Week.aDayOfWeek[iIdx].strDate + ".json";
        // search for file on Google Drive
        if (bExist = GoogleDrive.search(strJSONFileName)) {
            System.out.println("File exists: " + strJSONFileName);
            // download the file from Google Drive
            // the file is found by its fileId, stored inside the
            // Google Drive object
            String jsonData = GoogleDrive.download();
            // save jsonData string into jsonObject
            jsonObject = mapper.readValue(jsonData, JsonObject.class);
            // show jsonObject content
            System.out.println(jsonObject.toString());
            // save jsonObject on local file system
            mapper.writeValue(new File(
                Frame4Week.JSON_FILE_PATH + "\\" + strJSONFileName), jsonObject);
            // check whether file is locked
            // bCanProceed is inverse bLock
            return !jsonObject.getLock();
        }
        System.out.println("File does not exist.");
        jsonObject = new JsonObject();
        return true;
    }

    //************************************************************************
    //*                 getGuiContent
    //************************************************************************
    protected static void getGuiContent() {
        System.out.println("Panel4Dinner.getGuiContent()");

        jsonObject.setCook(jtfCook.getText());
        // get JTextArea content and set into jsonObject listEating
        String strJtaEating = jtaEating.getText();
        if (strJtaEating.length() > 0) {
            if (strJtaEating.charAt(strJtaEating.length() - 1) != '\n') strJtaEating += "\n";
            List<String> listEating = new ArrayList<String>();
            // use regex to split strJtaEating
            String regex = "\n";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(strJtaEating);
            int i = 0;
            int iStartSubstr = 0;
            while (m.find()) {
                String strSubstr = strJtaEating.substring(iStartSubstr, m.start());
                listEating.add(strSubstr);
                System.out.println(i++ + ": " + strSubstr);
                iStartSubstr = m.end();
            }
            jsonObject.setEating((ArrayList<String>)listEating);
        }
    }

    //************************************************************************
    //*                 writeJsonFile
    //************************************************************************
    private static void writeJsonFile() throws IOException {
        System.out.println("Panel4Dinner.writeJsonFile()");

        // save jsonObject on local file system
        mapper.writeValue(new File(Frame4Week.JSON_FILE_PATH + "\\" + strJSONFileName), jsonObject);
        // write file on local file system to Google Drive
        GoogleDrive.upload(Frame4Week.JSON_FILE_PATH, strJSONFileName);
    }

    //************************************************************************
    //*                 createPanel4Dinner
    //************************************************************************
    private void createPanel4Dinner() {
        System.out.println("Panel4Dinner.createPanel4Dinner()");

        // create a JPanel
        mainPane = new JPanel();
        mainPane.setOpaque(true);
        mainPane.setBackground(Color.RED);

        // set a gridbaglayout
        mainPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // create a header
        jlHeader = new JLabel("BLA dd-MM-yy");
        jlHeader.setFont(jlHeader.getFont().deriveFont(18.0f));
        jlHeader.setForeground(Color.WHITE);
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPane.add(jlHeader, constraints);

        // create label for cook
        jlCook = new JLabel("Cook");
        jlCook.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPane.add(jlCook, constraints);

        // create text field for cook
        jtfCook = new JTextField("BLA", 15);
        constraints.gridx = 0;
        constraints.gridy = 2;
        mainPane.add(jtfCook, constraints);

        //create label for eating
        jlEating = new JLabel("Eating");
        jlEating.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 3;
        mainPane.add(jlEating, constraints);

        // create text area for eating
        jtaEating = new JTextArea(5, 15);
        // set some content into the text area
        jtaEating.append("A_BLA\nB_BLA\nC_BLA\nD_BLA\nE_BLA");
        // create scroll pane for eating, containing text area
        jspEating = new JScrollPane(jtaEating,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        constraints.gridx = 0;
        constraints.gridy = 4;
        mainPane.add(jspEating, constraints);

        // create OK button
        jbOK = new JButton("Bla");
        jbOK.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 5;
        mainPane.add(jbOK, constraints);
        jbOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Panel4Dinner.actionPerformed()");
                Frame4Week.releasePanel4Dinner(e.getActionCommand(), iIdx);
            }
        });

        // create Save button
        jbSave = new JButton("Bla");
        jbSave.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 6;
        mainPane.add(jbSave, constraints);
        jbSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Panel4Dinner.actionPerformed()");
                Frame4Week.releasePanel4Dinner(e.getActionCommand(), iIdx);
            }
        });

        // create Cancel button
        jbCancel = new JButton("Bla");
        jbCancel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 7;
        mainPane.add(jbCancel, constraints);
        jbCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Panel4Dinner.actionPerformed()");
                Frame4Week.releasePanel4Dinner(e.getActionCommand(), iIdx);
            }
        });
    }

    //************************************************************************
    //*                 createLockMessage
    //************************************************************************
    private void createLockMessage() {
        System.out.println("Panel4Dinner.createLockMessage()");

        // create a JPanel
        mainPane = new JPanel();
        mainPane.setOpaque(true);
        mainPane.setBackground(Color.RED);

        // set a gridbaglayout
        mainPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // create a header
        jlHeader = new JLabel("BLA dd-MM-yy");
        jlHeader.setFont(jlHeader.getFont().deriveFont(18.0f));
        jlHeader.setForeground(Color.WHITE);
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPane.add(jlHeader, constraints);

        // create message part one: file is locked
        jlLockMessage1 = new JLabel("BLA");
        jlLockMessage1.setFont(jlLockMessage1.getFont().deriveFont(14.0f));
        jlLockMessage1.setForeground(Color.WHITE);
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPane.add(jlLockMessage1, constraints);

        // create message part two: try again later
        jlLockMessage2 = new JLabel("BLA BLA");
        jlLockMessage2.setFont(jlLockMessage2.getFont().deriveFont(14.0f));
        jlLockMessage2.setForeground(Color.WHITE);
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 2;
        mainPane.add(jlLockMessage2, constraints);

        // create OK button
        jbOK = new JButton("Bla");
        jbOK.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 3;
        mainPane.add(jbOK, constraints);
        jbOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Panel4Dinner.actionPerformed()");
                Frame4Week.releasePanel4Dinner(e.getActionCommand(), iIdx);
            }
        });
    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    private static ObjectMapper mapper = new ObjectMapper();
    private static int iIdx;
    private static JsonObject jsonObject = null;

    protected static boolean bExist = true;
    protected static boolean bCanProceed = true;
    protected static String strJSONFileName;

    protected static JPanel mainPane;
    protected static JLabel jlHeader, jlCook, jlEating;
    protected static JTextField jtfCook;
    protected static JTextArea jtaEating;
    protected static JScrollPane jspEating;
    protected static JButton jbOK, jbSave, jbCancel;

    protected static JLabel jlLockMessage1, jlLockMessage2;
}
/*
package src;
//****************************************************************************
//*                     import
//****************************************************************************
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

//****************************************************************************
//*                     Panel4Dinner
//****************************************************************************
public class Panel4Dinner {
    public Panel4Dinner(int _iIdx) {
        System.out.println("<<constructor>> Panel4Dinner()");

        iIdx = _iIdx;
        // search for file on Google Drive and check if it is unlocked
        //strJSONFileName = TEST_JSON_FILE;
        strJSONFileName = Frame4Week.aDayOfWeek[iIdx].strDate + ".json";
        try {
            // search for file on Google Drive
            if (GoogleDrive.search(strJSONFileName)) {
                System.out.println("File exists: " + strJSONFileName);
                // download the file from Google Drive
                // the file is found by its fileId, stored inside the
                // Google Drive object
                String jsonData = GoogleDrive.download();
                // save jsonData string into jsonObject
                jsonObject = mapper.readValue(jsonData, JsonObject.class);
                // show jsonObject content
                System.out.println(jsonObject.toString());
                // save jsonObject on local file system
                mapper.writeValue(new File(Frame4Week.JSON_FILE_PATH + "\\" + strJSONFileName), jsonObject);
// set jsonObject content on panel4Dinner
//jtfCook.setText(jsonObject.getCook());
//jtaEating.setText("");
//for (String strElement : jsonObject.getEating()) {
//    jtaEating.append(strElement);
//}
            } else {
                System.out.println("File does not exists.");
                jsonObject = new JsonObject();
// clear panel4Dinner
//jtfCook.setText("");
//jtaEating.setText("");
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            ;
        }

        if (bCanProceed) createPanel4Dinner(); else createLockMessage();
    }

    //************************************************************************
    //*                 createPanel4Dinner
    //************************************************************************
    private void createPanel4Dinner() {
        System.out.println("Panel4Dinner.createPanel4Dinner()");

        // create a JPanel
        mainPane = new JPanel();
        mainPane.setOpaque(true);
        mainPane.setBackground(Color.RED);

        // set a gridbaglayout
        mainPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // create a header
        jlHeader = new JLabel("BLA dd-MM-yy");
        jlHeader.setFont(jlHeader.getFont().deriveFont(18.0f));
        jlHeader.setForeground(Color.WHITE);
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPane.add(jlHeader, constraints);

        // create label for cook
        jlCook = new JLabel("Cook");
        jlCook.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPane.add(jlCook, constraints);

        // create text field for cook
        jtfCook = new JTextField("BLA", 15);
        constraints.gridx = 0;
        constraints.gridy = 2;
        mainPane.add(jtfCook, constraints);

        //create label for eating
        jlEating = new JLabel("Eating");
        jlEating.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 3;
        mainPane.add(jlEating, constraints);

        // create text area for eating
        jtaEating = new JTextArea(5, 15);
        // set some content into the text area
        jtaEating.append("A_BLA\nB_BLA\nC_BLA\nD_BLA\nE_BLA");
        // create scroll pane for eating, containing text area
        jspEating = new JScrollPane(jtaEating,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        constraints.gridx = 0;
        constraints.gridy = 4;
        mainPane.add(jspEating, constraints);

        // create OK button
        jbOK = new JButton("Bla");
        jbOK.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 5;
        mainPane.add(jbOK, constraints);
        jbOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Panel4Dinner.actionPerformed()");
                Frame4Week.releasePanel4Dinner(e.getActionCommand(), iIdx);
            }
        });

        // create Save button
        jbSave = new JButton("Bla");
        jbSave.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 6;
        mainPane.add(jbSave, constraints);
        jbSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Panel4Dinner.actionPerformed()");
                save();
                Frame4Week.releasePanel4Dinner(e.getActionCommand(), iIdx);
            }
        });

        // create Cancel button
        jbCancel = new JButton("Bla");
        jbCancel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 7;
        mainPane.add(jbCancel, constraints);
        jbCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Panel4Dinner.actionPerformed()");
                Frame4Week.releasePanel4Dinner(e.getActionCommand(), iIdx);
            }
        });

    }

    //************************************************************************
    //*                 save
    //************************************************************************
    private void save() {
        System.out.println("Panel4Dinner.save()");

        jsonObject.setCook(jtfCook.getText());
        try {
            // TEXT AREA SUCKS!
            // get JTextArea content and set into jsonObject listEating
            String strJtaEating = jtaEating.getText();
            List<String> listEating = new ArrayList<String>();
            for (int i = 0; i < jtaEating.getLineCount(); i++) {
                int iStart = jtaEating.getLineStartOffset(i);
                int iEnd = jtaEating.getLineEndOffset(i);
                String strOneLine = strJtaEating.substring(iStart, iEnd);
                if (!strOneLine.equals("") && !strOneLine.equals("\n")) {
                    if (strOneLine.charAt(strOneLine.length() - 1) == '\n') {
                        strOneLine = strOneLine.substring(0, strOneLine.length() - 1);
                    }
                    listEating.add(strOneLine);
                }
            }
            jsonObject.setEating((ArrayList<String>)listEating);
        } catch(BadLocationException e) {
            e.printStackTrace();
        } finally {
            ;
        }
        // show jsonObject content
        System.out.println(jsonObject.toString());
        try {
            // save jsonObject on local file system
            mapper.writeValue(new File(Frame4Week.JSON_FILE_PATH + "\\" + strJSONFileName), jsonObject);
            // write file on local file system to Google Drive
            GoogleDrive.upload(Frame4Week.JSON_FILE_PATH, strJSONFileName);
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            ;
        }
    }

    //************************************************************************
    //*                 createLockMessage
    //************************************************************************
    private void createLockMessage() {
        System.out.println("Panel4Dinner.createLockMessage()");

        // create a JPanel
        mainPane = new JPanel();
        mainPane.setOpaque(true);
        mainPane.setBackground(Color.RED);

        // set a gridbaglayout
        mainPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // create a header
        jlHeader = new JLabel("BLA dd-MM-yy");
        jlHeader.setFont(jlHeader.getFont().deriveFont(18.0f));
        jlHeader.setForeground(Color.WHITE);
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPane.add(jlHeader, constraints);

        // create message part one: file is locked
        jlLockMessage1 = new JLabel("BLA");
        jlLockMessage1.setFont(jlLockMessage1.getFont().deriveFont(14.0f));
        jlLockMessage1.setForeground(Color.WHITE);
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPane.add(jlLockMessage1, constraints);

        // create message part one: try again later
        jlLockMessage2 = new JLabel("BLA BLA");
        jlLockMessage2.setFont(jlLockMessage2.getFont().deriveFont(14.0f));
        jlLockMessage2.setForeground(Color.WHITE);
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 2;
        mainPane.add(jlLockMessage2, constraints);

        // create OK button
        jbOK = new JButton("Bla");
        jbOK.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 3;
        mainPane.add(jbOK, constraints);
        jbOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Panel4Dinner.actionPerformed()");
                Frame4Week.releasePanel4Dinner(e.getActionCommand(), iIdx);
            }
        });

    }

    //************************************************************************
    //*                 declare
    //************************************************************************
    protected JPanel mainPane;
    protected JLabel jlHeader, jlCook, jlEating;
    protected JTextField jtfCook;
    protected JTextArea jtaEating;
    protected JScrollPane jspEating;
    protected JButton jbOK, jbSave, jbCancel;

    protected JLabel jlLockMessage1, jlLockMessage2;

    protected int iIdx;
    protected String strJSONFileName;
    protected boolean bCanProceed = true;

    private static ObjectMapper mapper = new ObjectMapper();
    private static JsonObject jsonObject;
}
*/