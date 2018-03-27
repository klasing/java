<<<<<<< HEAD
package src;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.MissingResourceException;
public class HelloWorld {
    public HelloWorld() {
        System.out.println("Class name.......: " + getClass().getName());
        System.out.println("Language.........: " + strLanguage);
        System.out.println("Country..........: " + strCountry);
        System.out.println("Message..........: " + strHelloWorld);
    }
    private static void setLocale(String[] args) {
        if (args.length == 2) {
            strLanguage = args[0];
            strCountry = args[1];
        } else {
            strLanguage = "en";
            strCountry = "US";
        }
        Locale currentLocale;
        ResourceBundle messages = null;
        currentLocale = new Locale(strLanguage, strCountry);
        try {
            messages = ResourceBundle.getBundle("src/resource/MessagesBundle", currentLocale);
            strHelloWorld = messages.getString("hello_world");
        } catch(MissingResourceException e) {
            //e.printStackTrace();
            System.out.println("Can't find resource.");
        } finally {
            if (strHelloWorld == null) strHelloWorld = "???";
        }
    }
    public static void main(String[] args) {
        setLocale(args);
        HelloWorld helloWorld = new HelloWorld();
    }
    private static String strLanguage, strCountry, strHelloWorld;
=======
package src;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.MissingResourceException;
public class HelloWorld {
    public HelloWorld() {
        System.out.println("Class name.......: " + getClass().getName());
        System.out.println("Language.........: " + strLanguage);
        System.out.println("Country..........: " + strCountry);
        System.out.println("Message..........: " + strHelloWorld);
    }
    private static void setLocale(String[] args) {
        if (args.length == 2) {
            strLanguage = args[0];
            strCountry = args[1];
        } else {
            strLanguage = "en";
            strCountry = "US";
        }
        Locale currentLocale;
        ResourceBundle messages = null;
        currentLocale = new Locale(strLanguage, strCountry);
        try {
            messages = ResourceBundle.getBundle("src/resource/MessagesBundle", currentLocale);
            strHelloWorld = messages.getString("hello_world");
        } catch(MissingResourceException e) {
            //e.printStackTrace();
            System.out.println("Can't find resource.");
        } finally {
            if (strHelloWorld == null) strHelloWorld = "???";
        }
    }
    public static void main(String[] args) {
        setLocale(args);
        HelloWorld helloWorld = new HelloWorld();
    }
    private static String strLanguage, strCountry, strHelloWorld;
>>>>>>> 5839fb13e8eecee60acee4bb332b91b2b147bac9
}