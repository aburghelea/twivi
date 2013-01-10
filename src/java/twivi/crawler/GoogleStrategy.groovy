package twivi.crawler

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONElement
import groovy.util.logging.Log4j

/**
 * Created with IntelliJ IDEA.
 * User: cristian
 * Date: 6/16/12
 * Time: 6:34 PM
 * To change this template use File | Settings | File Templates.
 */
@Log4j
class GoogleStrategy implements Strategy {

    private String userip = "AIzaSyDpIV0aMfltjJl-x4z3MdHBjMaLhYEa67Q";

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
            e.printStackTrace()
        }
        return null;
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
     * Make  a querry on google and return a Map with images.
     *
     * @param query the keyword used in our search
     * @return a Map of <ImageName, ImageBytes>
     */
    public Map<String, Byte[]> getImages(String query) {

        String title, imageURL;
        int width, height;
        Map<String, byte[]> mapImages = new HashMap<String, byte[]>();
        URL imagesURL = new URL("https://ajax.googleapis.com/ajax/services/search/images?" +
                "v=1.0&q=" + query + "&userip=" + userip);

        JSONElement jsonImages = this.getPageContent(imagesURL);

        for (image in jsonImages.getAt("responseData").getAt("results")) {
            title = image.getAt("titleNoFormatting");
            width = Integer.parseInt(image.getAt("width") as String);
            height = Integer.parseInt(image.getAt("height") as String);
            imageURL = image.getAt("unescapedUrl");
            log.info "getting... " + title
            mapImages.put(title, this.getImage(imageURL, width * height));
        }

        return mapImages as Map<String, Byte[]>;

    }
}
