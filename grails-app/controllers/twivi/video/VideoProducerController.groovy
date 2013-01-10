package twivi.video

import groovy.util.logging.Log4j
import org.javatuples.Pair

import javax.activation.MimetypesFileTypeMap
import javax.servlet.ServletOutputStream

@Log4j
class VideoProducerController {
    def videoProducerService
    def crawlerService
    def burningImageService

    private static final int CHUNK_SIZE = 1024 * 1024  //1M
    private static final String IMAGE_EXTENSIONS = "(?i).*\\.(jpg|gif|png|bmp)*"

    def generateVideo() {
        def token = params.token;
        def fileName = crawlAndGenerate(token)
        render(template: '/video/videoModal', model: [token: fileName])
    }

    def private final crawlAndGenerate(def token) {
        def imagesMap = crawlerService.getImages(token);
        log.info "Crawled images for token " + token

        def imagesResized = [:]
        def resizedImage;
        for (def imageKey : imagesMap.keySet()) {
            if (imageKey.matches(IMAGE_EXTENSIONS)) {
                def imagePair = new Pair<String, byte[]>(imageKey, imagesMap.get(imageKey))
                resizedImage = burningImageService.doWith(imagePair).execute { it.scaleAccurate(520, 390) }.getProcessedImage()
                imagesResized.put(imageKey, resizedImage.source)
            }
        }
        log.info "Resized Images for token " + token
        if (imagesResized?.size() > 0) {

            token = videoProducerService.buildVideo(imagesResized, token)
            log.info "Video Generated " + token
        } else {
            log.info "Video not generated for " + token
            token = null
        }

        token
    }

    def stream() {
        ServletOutputStream out = response.getOutputStream();
        def filePath = videoProducerService.getFilePath(params.query);

        File file = null;
        if (filePath != null) {
            file = new File(filePath)
        }

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file))
        String mimeType = new MimetypesFileTypeMap().getContentType(file)
        byte[] bytes = new byte[CHUNK_SIZE];
        int bytesRead;
        response.setContentType(mimeType);
        while ((bytesRead = inputStream.read(bytes)) != -1) {
            out.write(bytes, 0, bytesRead);
        }
        inputStream.close();
        out.flush();
        out.close();
    }
}
