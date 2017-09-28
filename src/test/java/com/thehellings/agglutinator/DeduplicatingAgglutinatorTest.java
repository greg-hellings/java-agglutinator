package com.thehellings.agglutinator;

import com.thehellings.config.Mode;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DeduplicatingAgglutinatorTest {
    public static DeduplicatingAgglutinator deduplicatingAgglutinator = new DeduplicatingAgglutinator();

    @BeforeClass
    public static void setUp() throws Exception {
        final String[] sources = {"a", "b", "multiline"};
        for(String source : sources) {
            deduplicatingAgglutinator.addSource(source,
                    SourceFactory.getSource(
                            Mode.DEVELOPMENT,
                            new File(DeduplicatingAgglutinatorTest.class.getClassLoader()
                                    .getResource("simple/" + source + ".txt").getFile())
                    )
            );
        }
    }

    @Test
    public void oneSource() throws Exception {
        final String expected = "Test a";
        final List<String> list = new ArrayList<String>() {{ add("a"); }};
        final List<String> multipleEntries = new ArrayList<String>() {{ add("a"); add("a"); add("a"); }};
        assertEquals(expected, deduplicatingAgglutinator.getContent(list));
        assertEquals(expected, deduplicatingAgglutinator.getContent(multipleEntries));
    }
}
