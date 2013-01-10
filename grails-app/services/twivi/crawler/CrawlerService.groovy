package twivi.crawler

class CrawlerService {

    /**
     * Make a query on wikipedia & google for images
     *
     * @param query the keyword to be searched
     * @return a Map of <ImageName, ImageByteArray>
     */
    def getImages(String query) {

        Map<String, byte[]> images =[:];
        /* instantiate the 2 available search Strategies */
//        Context googleContext = new Context(new GoogleStrategy());
//        images = googleContext.executeStrategy(query);
        Context wikiContext = new Context(new WikiStrategy());
        /* assemble results from the 2 strategies */
        images.putAll(wikiContext.executeStrategy(query));

        return images;
    }
    /**
     * Completele retarded way to get only X images.
     * It get's them all and then it picks only one of them.
     * TODO: refactor so that it queries for only X
     * @param query
     * @param noImages
     * @return
     */
    def getImages(String query, int noImages) {
        def images = getImages(query)

        if (noImages < 0 || noImages >= images.keySet().size())
            return images
        def rtnImages = [:]

        for (String imageKey : images.keySet()) {
            if (noImages <= 0)
                break
            rtnImages.put(imageKey, images.get(imageKey))
            noImages --;
        }

        return rtnImages
    }
}
