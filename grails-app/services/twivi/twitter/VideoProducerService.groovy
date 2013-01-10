package twivi.twitter

import com.xuggle.mediatool.IMediaWriter
import com.xuggle.mediatool.ToolFactory
import twivi.video.Movie

import java.awt.Dimension
import java.awt.Toolkit
import java.awt.image.BufferedImage
import java.util.concurrent.TimeUnit

class VideoProducerService {
    def grailsApplication

    private static Dimension screenBounds;
    private static final double FRAME_RATE = 50;
    private static final int SECONDS_TO_RUN_FOR = 2;
    private static final String EXTENSION = ".mp4"

    public String getFilePath(String fileName) {
        def final VIDEO_DIR = grailsApplication.config.video.storage.windows

        return VIDEO_DIR + File.separator + fileName
    }

    public String getFileName(String token) {
        return token + EXTENSION
    }

    public String buildVideo(Map<String, byte[]> imgList, String token = "default") {
        //build images for movie
        Movie movie = new Movie.ImagesBuilder().buildImages(imgList).build()

        // let's make a IMediaWriter to write the file.
        def outputFileName = getFileName(token)
        def outputAbsoluteFileName = getFilePath(outputFileName)

        final IMediaWriter writer = ToolFactory.makeWriter(outputAbsoluteFileName);
        screenBounds = Toolkit.getDefaultToolkit().getScreenSize();

        // We tell it we're going to add one video stream, with id 0,
        // at position 0, and that it will have a fixed frame rate of FRAME_RATE.
        int w = screenBounds.width / 2
        int h = screenBounds.height / 2
        int streamIndex = writer.addVideoStream(0, 0, w, h);

        long startTime = System.nanoTime();

        for (int i = 0; i < movie?.images?.size(); i++) {
            BufferedImage v = movie?.images?.get(i)
//        movie?.images?.each {v ->
            for (int index = 0; index < SECONDS_TO_RUN_FOR * FRAME_RATE; index++) {

                // encode the image to stream #0
                writer.encodeVideo(streamIndex, v, System.nanoTime() - startTime, TimeUnit.NANOSECONDS);

                try {
                    Thread.sleep((long) (1000 / FRAME_RATE));
                }
                catch (InterruptedException e) {
                    e.printStackTrace()
                }
            }
        }
        writer.flush();
        writer.close();
        writer = null;

        return outputFileName;
    }
    /**
     * Generate a video from every image.
     *
     * @deprecated As of TwiVi0.1, call {@link #buildVideo} with a map with a single entry.
     */
    @Deprecated
    public void buildOneVideoPerPicture(Map<String, byte[]> imgList) {
        def oneImageMap = [:]
        for (String imageKey : imgList.keySet()) {
            oneImageMap.clear();
            oneImageMap.put(imageKey, imgList.get(imageKey));
            buildVideo(oneImageMap)
        }
    }
}
