package com.thehellings.agglutinator.file;

import com.thehellings.agglutinator.interfaces.Source;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * A {@link File}-backed {@link Source} useful in Development.
 *
 * <p>
 *     See implementation of {@link #getContent()} for more information.
 * </p>
 */
public class DevSource implements Source {
    private File file;

    /**
     * Creates a development-worthy source.
     *
     * <p>
     *     This should probably not be used on binary data, as the functionality utilized operates on String objects
     *     instead of some type of {@link Byte} array.
     * </p>
     *
     * @param file The file to be read and returned by this source
     */
    public DevSource(File file) {
        this.file = file;
    }

    /**
     * Every time this method is called, the file will be read anew and returned.
     *
     * <p>
     *     While useful in a {@link com.thehellings.config.Mode#DEVELOPMENT} environment, this is not very performant
     *     and should probably not be used in production on a source that changes only infrequently.
     * </p>
     *
     * @return The String contents of the file
     * @throws IOException If reading the file causes an exception
     */
    @Override
    public String getContent() throws IOException {
        return FileUtils.readFileToString(this.file);
    }
}
