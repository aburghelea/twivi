package twivi.stream

import twivi.thread.TwitterSource

import javax.servlet.http.HttpServletResponse

class TwitterStreamController {
    def twitterStreamService
    static def observedResult

    def start() {
        try {
            twitterStreamService.retrieveStream(params.keyword)
        } catch (Exception e) {
            log.error e.getMessage()
        }
    }

    def doStart() {
        start()
        render status: HttpServletResponse.SC_OK
    }

    def update() {
        def results = observedResult;
        observedResult = null;
        render(results)
    }

    public static class ResponseObserver implements Observer {

        void update(Observable o, Object arg) {
            TwitterSource ts = o as TwitterSource;
            Map<String, ?> entryResult = ts.getEntry()
            observedResult = entryResult.get("text")
        }
    }

}
