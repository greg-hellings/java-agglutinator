package com.thehellings.agglutinator;

import com.thehellings.agglutinator.exceptions.SourceNotFoundException;
import com.thehellings.agglutinator.interfaces.Source;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Returns each item from the identifiers only once.
 *
 * <p>
 *     All the elements in the identifiers collection are coalesced into a single {@link Set} before being concatenated,
 *     thereby guaranteeing that each {@link Source} in the underlying collection will be contained only one time in
 *     the underlying collection.
 * </p>
 */
public class DeduplicatingAgglutinator extends SimpleAgglutinator {
    @Override
    public String getContent(Iterable<String> identifiers) throws SourceNotFoundException, IOException {
        Set<String> set = new HashSet<>();
        for (String identifier : identifiers) {
            set.add(identifier);
        }
        return super.getContent(set);
    }
}
