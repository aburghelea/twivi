package twivi.crawler;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cristian
 * Date: 6/16/12
 * Time: 6:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class Context {
    private Strategy strategy;
    /* containing class for search strategies */
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public Map<String, byte[]> executeStrategy(String query) {
        return strategy.getImages(query);
    }
}
