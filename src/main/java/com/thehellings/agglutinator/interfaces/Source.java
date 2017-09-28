package com.thehellings.agglutinator.interfaces;

import java.io.IOException;

/**
 * A "Source" is any type of backend that supports returning a {@link String} object that represents it.
 *
 * <p>
 *     A "Source" is abstracted away so that agglutination can happen across multiple back ends, all provided by their
 *     own implementation. A few, simple, {@link java.io.File}-backed implementations are included in this package as
 *     examples. However, any type of Source could be implemented and backed by a database, a cache mechanism, or other
 *     types of resources as per your need.
 * </p>
 */
public interface Source {
    /**
     * Creates, fetches, or calls up the content represented by this Source.
     *
     * @return A String representation of this particular Source
     * @throws IOException If there is an error reading the content
     */
    String getContent() throws IOException;
}
