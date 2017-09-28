package com.thehellings.agglutinator;

import com.thehellings.agglutinator.exceptions.SourceNotFoundException;
import com.thehellings.agglutinator.interfaces.Source;
import com.thehellings.config.Mode;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SimpleAgglutinatorTest {
    public static SimpleAgglutinator simpleAgglutinator;

    @BeforeClass
    public static void setUp() throws Exception {
        simpleAgglutinator = new SimpleAgglutinator("\n---\n");
        final Source a = SourceFactory.getSource(Mode.DEVELOPMENT,
                new File(SimpleAgglutinatorTest.class.getClassLoader().getResource("simple/a.txt").getFile())
        );
        final Source b = SourceFactory.getSource(Mode.PRODUCTION,
                new File(SimpleAgglutinatorTest.class.getClassLoader().getResource("simple/b.txt").getFile())
        );
        final Source multiline = SourceFactory.getSource(Mode.PRODUCTION,
                new File(SimpleAgglutinatorTest.class.getClassLoader().getResource("simple/multiline.txt").getFile())
        );
        simpleAgglutinator.addSource("a", a);
        simpleAgglutinator.addSource("b", b);
        simpleAgglutinator.addSource("multiline", multiline);
    }

    @Test
    public void testSingleFile() throws Exception {
        final String expected = "Test a";
        final List<String> list = new ArrayList<String>() {{ add("a"); }};
        assertEquals(expected, simpleAgglutinator.getContent(list));
    }

    @Test
    public void testMultipleFile() throws Exception {
        final String expected = "Test b\n---\nFirst line\nSecond line";
        final List<String> list = new ArrayList<String>() {{ add("b"); add("multiline"); }};
        assertEquals(expected, simpleAgglutinator.getContent(list));
    }

    @Test
    public void testRepeatedContent() throws Exception {
        final String expected = "Test a\n---\nTest b\n---\nTest a";
        final List<String> list = new ArrayList<String>() {{ add("a"); add("b"); add("a"); }};
        assertEquals(expected, simpleAgglutinator.getContent(list));
    }

    @Test(expected = SourceNotFoundException.class)
    public void unknownSource() throws Exception {
        final List<String> list = new ArrayList<String>() {{ add("derp"); }};
        simpleAgglutinator.getContent(list);
    }
}
