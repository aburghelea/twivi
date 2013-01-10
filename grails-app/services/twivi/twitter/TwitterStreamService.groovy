package twivi.twitter

import org.apache.commons.httpclient.methods.GetMethod
import twivi.stream.TwitterStreamController
import twivi.thread.TwitterSource

class TwitterStreamService {
    static transactional = false

    def retrieveStream(String keyword) {
        final TwitterSource eventSource = new TwitterSource.Builder().withHttpMethod(new GetMethod("https://stream.twitter.com/1/statuses/filter.json?track=Bucharest,Berlin,Moscow,France,Paris,London,SUA,USA,Washington")).build()
        final TwitterStreamController.ResponseObserver observer = new TwitterStreamController.ResponseObserver()
        eventSource.addObserver(observer);
        eventSource.run()
    }
}
