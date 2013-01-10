package twivi.thread;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import twivi.exceptions.TwiviException;
import twivi.reader.TwiviResultReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 16/06/12
 * Time: 12:36
 * To change this template use File | Settings | File Templates.
 */
public abstract class StreamThread extends Observable implements Runnable {
    boolean runnable = true;

    public abstract String getKeyword();

    public abstract HttpMethod getHttpMethod();

    private HttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();

    public void run() {

        try {
            while (runnable) {
                readStream();
            }
        } catch (Throwable t) {
            throw new TwiviException(t);
        }
    }


    private void readStream() throws IOException {
        HttpClient client = new HttpClient(connectionManager);
        String userName = "hakaton2";
        String password = "1qaz2wsx";
        HttpMethod method = getHttpMethod();
        method.addRequestHeader("Authorization", "Basic " + new String(Base64.encodeBase64((userName + ":" + password).getBytes("UTF-8"))));
        int res = client.executeMethod(method);
        if (res == 200) {//operatia a reusit
            InputStream stream = method.getResponseBodyAsStream();
            Reader reader = new InputStreamReader(stream, "UTF-8");
            TwiviResultReader resultReader = new TwiviResultReader(reader);
            while (runnable) {
                try {
                    String resultLine = resultReader.readLine();
                    if (resultLine == null) break;
                    if (StringUtils.isBlank(resultLine)) continue;
                    parse(resultLine);
                } catch (Exception e) {
                    throw new TwiviException(e);
                }
            }
        }
    }

    private void parse(String line) throws IOException {
        Map<String, ?> entry = new ObjectMapper().readValue(line, HashMap.class);
        handle(entry);
    }

    protected abstract void handle(Map<String, ?> entry);

}
