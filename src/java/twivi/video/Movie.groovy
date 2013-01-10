package twivi.video

import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import twivi.util.MovieUtils

class Movie {
    public List<BufferedImage> images

    private Movie(ImagesBuilder builder) {
        this.images = builder.images;
    }

    /* BULDER */

    public static class ImagesBuilder {
        List<BufferedImage> images


        public Movie build() {
            return new Movie(this);
        }


        public ImagesBuilder buildImages(final Map<String, byte[]> imgList) {
            images = new ArrayList<BufferedImage>()
            imgList.each {v ->
                // convert byte array back to BufferedImage
                InputStream inputStream = new ByteArrayInputStream(v.value);
                BufferedImage rawImage = ImageIO.read(inputStream);
                // convert to the right image type
                BufferedImage goodImage = MovieUtils.convertToType(rawImage, BufferedImage.TYPE_3BYTE_BGR);
                images.add(goodImage)
            }
            return this;
        }
    }

}
