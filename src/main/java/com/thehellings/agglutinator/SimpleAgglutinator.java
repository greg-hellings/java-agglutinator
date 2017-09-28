package com.thehellings.agglutinator;

import com.thehellings.agglutinator.exceptions.SourceNotFoundException;
import com.thehellings.agglutinator.interfaces.Agglutinator;
import com.thehellings.agglutinator.interfaces.Source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Striaght forward concatentation of sources.
 *
 * <p>
 *     This Agglutinator combines {@link Source} elements in the order specified by the underlying Iterable object and
 *     return them with the specified delimiter marking off the space between them.
 * </p>
 */
public class SimpleAgglutinator implements Agglutinator {
    final private String delimiter;
    final private Map<String, Source> sources = new HashMap<>();

    public SimpleAgglutinator(final String delimiter) {
        this.delimiter = delimiter;
    }

    public SimpleAgglutinator() {
        this("\n");
    }

    @Override
    public void addSource(final String identifier, final Source source) {
        this.sources.put(identifier, source);
    }

    @Override
    public String getContent(final Iterable<String> identifiers) throws SourceNotFoundException, IOException {
        List<String> contents = new ArrayList<>();
        for(String identifier : identifiers) {
            Source source = this.sources.get(identifier);
            if (source == null) {
                throw new SourceNotFoundException("Source " + identifier + " was not found in this collection.");
            }
            contents.add(source.getContent());
        }
        return String.join(this.delimiter, contents);
    }
}
