package twivi.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 16/06/12
 * Time: 12:37
 * To change this template use File | Settings | File Templates.
 */
public class TwiviException extends RuntimeException {
    public TwiviException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public TwiviException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public TwiviException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public TwiviException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    protected TwiviException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
