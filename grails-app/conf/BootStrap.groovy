class BootStrap {
    def grailsApplication
    def init = { servletContext ->
        def final VIDEO_DIR = grailsApplication.config.video.storage.windows
        File dir = new File(VIDEO_DIR)
        if ( !dir.exists())
            dir.mkdirs()
    }
    def destroy = {
    }
}
