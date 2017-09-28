package com.thehellings.agglutinator.interfaces;

import com.thehellings.agglutinator.exceptions.SourceNotFoundException;

import java.io.IOException;

/**
 * This is the primary type that interfaces with the user.
 *
 * <p>
 *     There are many types of Agglutinators that one might choose to implement. Some will cache the results of the
 *     getContent method, some might not. Implementing that is up to the user. A few of them are provided in this
 *     package, based on the needs of the users.
 * </p>
 * <p>
 *     At its base, an Agglutinator will take a specified collection of identifiers, concatenate the contents of them
 *     together, with some sort of delimiter between them. Then return the results. Obviously, to handle this, the
 *     Agglutinator needs to know its set of sources, which can dynamically be added to or removed.
 * </p>
 * <p>
 *     In a production environment it is entirely reasonable that an Agglutinator will cache the results of calls into
 *     a caching environment. These could be many and varied, depending on your environment or your particular caching
 *     needs. This library is in no way attempting to provide an exhaustive set of caching or otherwise Agglutinators.
 *     However, a few of them are provided for the sake of showing examples.
 * </p>
 */
public interface Agglutinator {
    /**
     * Adds a {@link Source} to this Agglutinator's collection of known sources.
     *
     * <p>
     *     Every Source must be identified by a unique identifier. This is the method by which the source will be fetched
     *     back from the Aggluatinator at retrieval time. To replace an existing Source, simply call this method a
     *     second time with the same identifier.
     * </p>
     *
     * @param identifier The unique identifier for this source
     * @param source The source of the content for this particular identifier
     */
    void addSource(final String identifier, final Source source);

    /**
     * Collects the content from each {@link Source} in this collection, concatenates them, and returns the result.
     *
     * <p>
     *     Results will be ordered in the iterator order of the underlying collection. If the underlying collection
     *     allows multiple entries, then the content from those entries will be included in the output multiple times.
     *     Some implementations of Agglutinator may intentionally manipulate the underlying set for the purpose of
     *     simplifying, speeding, deduplicating, or sorting the results.
     * </p>
     *
     * @param identifiers A collection of {@link String} identifiers to be coalesced and returned
     * @return The combined results of each identified {@link Source} in this set
     * @throws SourceNotFoundException If one of the identifiers does not match any {@link Source} in the set
     * @throws IOException If attempting to read the content causes an exception
     */
    String getContent(final Iterable<String> identifiers) throws SourceNotFoundException, IOException;
}
