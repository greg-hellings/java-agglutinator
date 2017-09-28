package com.thehellings.agglutinator.file;

import com.thehellings.agglutinator.interfaces.Source;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * A cached {@link Source} that is useful in high-performance environments
 *
 * <p>
 *     This type of Source will read the contents entirely into memory and store the {@link File} as a {@link String}
 *     in memory. This can be exceptionally fast for very small objects, but obviously should not be used for
 *     exceptionally large files.
 * </p>
 */
public class PrimedSource implements Source {
    private String contents;

    /**
     * Reads the File contents into memory.
     *
     * @param file The file whose contents will be referenced
     * @throws IOException If reading the file causes an error
     */
    public PrimedSource(final File file) throws IOException {
        this.contents = FileUtils.readFileToString(file);
    }

    /**
     * Returns the contents at the time of object creation
     *
     * <p>
     *     If the underlying {@link File} object has changed, that will not affect the results of calling this method.
     *     The contents of the File were read upon the creation of this object and will not be referenced or read again.
     * </p>
     *
     * @return The cached contents of the file
     * @throws IOException Does not actually throw an exception
     */
    @Override
    public String getContent() throws IOException {
        return this.contents;
    }
}
