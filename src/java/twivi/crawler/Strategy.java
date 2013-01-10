package twivi.crawler;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cristian
 * Date: 6/16/12
 * Time: 6:00 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Strategy {
    /* interface for search strategies */
    public Map<String, byte[]> getImages(String query);

}
