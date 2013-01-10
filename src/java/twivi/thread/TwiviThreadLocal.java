package twivi.thread;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 16/06/12
 * Time: 20:13
 * To change this template use File | Settings | File Templates.
 */
public class TwiviThreadLocal {
    public static final ThreadLocal<String> userThreadLocal = new ThreadLocal<String>();

    public static void set(String text) {
        userThreadLocal.set(text);
    }

    public static void unset() {
        userThreadLocal.remove();
    }

    public static String get() {
        return userThreadLocal.get();
    }

}
