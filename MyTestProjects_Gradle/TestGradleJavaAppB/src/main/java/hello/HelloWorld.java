package hello;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.joda.time.LocalTime;

public class HelloWorld {
    private static void getBundle(String[] args) {
        String strLanguage, strCountry;
        if (args.length == 2) {
            strLanguage = args[0];
            strCountry = args[1];
        } else {
            strLanguage = "en";
            strCountry = "US";
        }
        currentLocale = new Locale(strLanguage, strCountry);
        try {
            messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
        } catch(MissingResourceException e) {
            e.printStackTrace();
            ;
        } finally {
            ;
        }
    }

    public static void main(String[] args) {
        getBundle(args);

        LocalTime currentTime = new LocalTime();
        System.out.println(messages.getString("message_time") + ": " + currentTime);

        Greeter greeter = new Greeter(messages.getString("hello_world"));
        System.out.println(greeter.sayHello());
    }

    private static Locale currentLocale = null;
    private static ResourceBundle messages = null;
}