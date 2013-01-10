package twivi.twitter

import grails.test.mixin.TestFor
import twivi.crawler.CrawlerService
import java.awt.image.BufferedImage
import java.awt.image.WritableRaster
import java.awt.image.DataBufferByte

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(VideoProducerService)
class VideoProducerServiceUnitTests {
    def videoProducerService = new VideoProducerService()
    def crawlerService = new CrawlerService()
    private static final String query = "Bucharest"




}
