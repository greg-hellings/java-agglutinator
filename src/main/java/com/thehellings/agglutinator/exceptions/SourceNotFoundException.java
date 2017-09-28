package com.thehellings.agglutinator.exceptions;

import com.thehellings.agglutinator.interfaces.Source;

/**
 * Indicates that a requested {@link Source} was not found in the current set.
 */
public class SourceNotFoundException extends Exception {
    public SourceNotFoundException(final String message) {
        super(message);
    }
}
