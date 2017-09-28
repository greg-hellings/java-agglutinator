package com.thehellings.agglutinator;

import java.io.File;
import java.io.IOException;

import com.thehellings.agglutinator.interfaces.Source;
import com.thehellings.agglutinator.file.DevSource;
import com.thehellings.agglutinator.file.PrimedSource;
import com.thehellings.config.Mode;

/**
 * Factory for creating sources from a given {@link File} object.
 */
public class SourceFactory {
    /**
     * Creates the proper type of {@link Source} for a given {@link File}, based on the type of {@link Mode} that the
     * project is running in.
     *
     * <p>
     *     See the various implementations of {@link Source} for more information on the types of Source and how they
     *     each operate.
     * </p>
     *
     * @param mode The current operational mode
     * @param file The file object to be read as the basis of watching the contents
     * @return A {@link Source} object, or null if an unsupported Mode is specified
     * @throws IOException If an underlying {@link Source} object throws an exception during creation
     */
    public static Source getSource(Mode mode, File file) throws IOException {
        switch (mode) {
            case PRODUCTION:
                return new PrimedSource(file);
            case DEVELOPMENT:
            default:
                return new DevSource(file);
        }
    }
}
