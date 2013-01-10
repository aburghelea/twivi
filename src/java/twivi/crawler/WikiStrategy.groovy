package twivi.crawler

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONElement
import groovy.util.logging.Log4j

/**
 * Created with IntelliJ IDEA.
 * User: cristian
 * Date: 6/16/12
 * Time: 6:14 PM
 * To change this template use File | Settings | File Templates.
 */

@Log4j
class WikiStrategy implements Strategy {
    private static String WIKI_API_URL = "http://en.wikipedia.org/w/api.php?" +
            "action=query&prop=%s&rvprop=content&format=json&titles=%s"

    /**
     * Read & Parse a JSON from an URL
     *
     * @param url an URL from where to read a JSON
     * @return the parsed json
     */
    private JSONElement getPageContent(URL url) {

        try {
            return JSON.parse(new InputStreamReader(url.openStream()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Obtain text from a wikipedia page.
     *
     * @param querry the keyword/page to be searched for
     * @return a JSON which contains the text
     */
    private wikiText(String querry) {

        URL wikiURL = new URL(String.format(WIKI_API_URL, "revisions", querry));

        //TODO: get relevant info out of JSONElement?
        this.getPageContent(wikiURL);
    }

    /**
     * Download an image from the designated URL
     *
     * @param url the location of the image
     * @param size size of the image
     * @return an array of size "size" containing the image read from the url
     */
    private byte[] getImage(String url, int size) {
        if (size <= 0)
            return null;

        byte[] byteArray = new byte[size];
        URL imageURL = new URL(url);
        InputStream is = imageURL.openStream();

        for (int i = 0; i < size; i++) {
            byteArray[i] = is.read();
        }

        return byteArray;
    }

    /**
     * Make  a querry on wikipedia and return a Map with images.
     *
     * @param query the keyword used in our search
     * @return a Map of <ImageName, ImageBytes>
     */
    public Map<String, byte[]> getImages(String querry) {
        Map<String, byte[]> mapImages = new HashMap<String, byte[]>();

        /* original query for image names */
        URL imagesURL = new URL(String.format(WIKI_API_URL, "images", querry));
        URL imageInfoURL;
        String url;
        int size, no_images = 0;
        JSONElement jsonImages = this.getPageContent(imagesURL);

        /* wikipedia API enables mutiple searches - called pages - in a single request
        *  for the moment, the code above makes a single search a time */
        for (def page : jsonImages.getAt("query").getAt("pages")) {
//        jsonImages.getAt("query").getAt("pages").each { page ->
            /* obtain all the images for current page */
            for (img in page.value.getAt("images")) {
                /* for each image found, determine it's URL */
                log.info "getting... " + img.title;
                /* construct image information URL */
                imageInfoURL = new URL("http://en.wikipedia.org/w/api.php?action=query&titles=" + URLEncoder.encode(img.title) + "&prop=imageinfo&iiprop=url|size&format=json");
                /* download image information */
                for (def page2 : getPageContent(imageInfoURL).getAt("query").getAt("pages")) {
//                this.getPageContent(imageInfoURL).getAt("query").getAt("pages").each { page2 ->
                    url = page2.value.getAt("imageinfo").getAt("url")[0];
                    size = page2.value.getAt("imageinfo").getAt("size")[0] as Integer;
                    /* download image */
                    def image = getImage(url, size)
                    mapImages.put(img.title, image);
                }
                /* image limit for avoiding long download times */
                no_images++;
//                if (no_images >= 2)
//                    return mapImages;
            }
        }
        return mapImages;
    }

}
