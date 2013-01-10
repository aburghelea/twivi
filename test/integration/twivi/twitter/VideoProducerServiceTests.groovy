package twivi.twitter

import twivi.crawler.CrawlerService
import org.javatuples.Pair

class VideoProducerServiceTests extends GroovyTestCase {

    private static final String query = "Bucharest"

    def videoProducerService = new VideoProducerService()
    def crawlerService = new CrawlerService()
    def burningImageService

    void testGenerateFileName() {
        def generated = videoProducerService.getFilePath("Gigi");
        assertEquals(generated, "D:\\twivi-video\\Gigi.mp4");
    }

    void testConvertImages() {
        def IMG_DIR = "D:\\test_images";
        for (i in 1..3) {
            def imgName = IMG_DIR + File.separator + i
            burningImageService.doWith(imgName + ".jpg", IMG_DIR).execute { it ->
                it.scaleAccurate(260, 190);
            }
            burningImageService.doWith(imgName + ".png", IMG_DIR).execute { it ->
                it.scaleAccurate(260, 190);
            }
        }
        def imagesList = [:]
        for (i in 1..3) {
            def imgName = IMG_DIR + File.separator + i
            FileInputStream fileInputStream = new FileInputStream(imgName+".jpg");
            imagesList.put(i+".jpg",fileInputStream.getBytes());
            fileInputStream = new FileInputStream(imgName+".png");
            imagesList.put(i+".png",fileInputStream.getBytes());
        }
        videoProducerService.buildVideo(imagesList, "demo-burning")
    }

    void testConvertCollectedImages(){
        def images = crawlerService.getImages(query)
        def resized_images = [:]
        for (def imageKey : images.keySet()){
            def pairImage = new Pair<String, byte[]>(imageKey,images.get(imageKey))
            def new_img = burningImageService.doWith(pairImage).execute({
                it.scaleAccurate(260, 190);
            }).getProcessedImage()
            resized_images.put(imageKey, new_img.source)
        }
        videoProducerService.buildVideo(resized_images)
    }

    void testGeneratingVideoFromUniformPictures() {
        def fs = File.separator;
        def dir = "D:" + fs + "source" + fs + "twivi" + fs + "twivi" + fs + "web-app" + fs + "test_images"
        Map<String, byte[]> images = [:]
        for (int i = 1; i <= 2; i++) {
            FileInputStream fileInputStream = new FileInputStream(dir + fs + i + "_mod.jpg");
            images.put(i + ".jpg", fileInputStream.getBytes())
        }
        videoProducerService.buildVideo(images, "tests");
    }

    void testGenereteOneMoviePerPicture() {
        def images = crawlerService.getImages(query)
        videoProducerService.buildOneVideoPerPicture(images)
    }

    void testGenenratingVideoFromAllPictures() {
        def images = crawlerService.getImages(query)
        videoProducerService.buildVideo(images)
    }
}
