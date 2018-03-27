package hello;

public class Greeter {
    public Greeter(String strMessage) {
        this.strMessage = strMessage;
    }
    public String sayHello() {
        return strMessage;
    }
    private String strMessage;
}