package pl.burningice.plugins.image.file

import org.javatuples.Pair

/**
 * Representing files stored in memory
 *
 * @author Alex Burghelea (aburghelea@rosedu.org)
 */
class BytemapImageFile extends ImageFile {

    /**
     * Class constructor
     *
     * @param source Byte [] that is source image for this class
     * @return BytematImageFile
     */
    BytemapImageFile(String sourceFileName = "DEFAULT" + source.hashCode(), byte[] source) {
        super(sourceFileName, source)
    }

    BytemapImageFile(Pair<String, byte[]> source){
        super(source.getValue0(), source.getValue1())
    }
}
