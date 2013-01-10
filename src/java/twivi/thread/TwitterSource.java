package twivi.thread;

import org.apache.commons.httpclient.HttpMethod;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 16/06/12
 * Time: 13:18
 * To change this template use File | Settings | File Templates.
 */
public class TwitterSource extends StreamThread {
    String keyword;
    HttpMethod method;
    Map<String, ?> entry;

    private TwitterSource(Builder builder) {
        this.keyword = builder.keyword;
        this.method = builder.method;
    }

    @Override
    public String getKeyword() {
        return keyword;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return method;
    }

    @Override
    protected synchronized void handle(Map<String, ?> entry) {
        this.entry = entry;
        setChanged();
        notifyObservers(entry);
    }

    public static class Builder {
        String keyword;
        HttpMethod method;

        public Builder withKeyword(final String _keyword) {
            this.keyword = _keyword;
            return this;
        }

        public Builder withHttpMethod(final HttpMethod _method) {
            this.method = _method;
            return this;
        }

        public TwitterSource build() {
            return new TwitterSource(this);
        }
    }

    public Map<String, ?> getEntry() {
        return entry;
    }
}
