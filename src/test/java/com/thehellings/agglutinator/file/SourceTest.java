package com.thehellings.agglutinator.file;

import com.thehellings.agglutinator.interfaces.Source;
import com.thehellings.agglutinator.SourceFactory;
import com.thehellings.config.Mode;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SourceTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private String firstContent = "First content";
    private String secondContent = "Second content";

    private String temporaryFilename = SourceTest.class.getSimpleName() + ".txt";
    private File temporaryFile;

    @Before
    public void initialize() {
        try {
            this.temporaryFile = this.temporaryFolder.newFile(this.temporaryFilename);
            this.writeFile(this.firstContent);
        } catch(Exception ex) {
            assertTrue(false);
        }
    }

    /**
     * Tests that the file is read properly, after it is changed.
     *
     * @throws Exception Shouldn't throw this, unless the filesystem changes underneath
     */
    @Test
    public void testDevSource() throws Exception {
        Source source = SourceFactory.getSource(Mode.DEVELOPMENT, this.temporaryFile);
        assertEquals(this.firstContent, source.getContent());
        // Now, make sure it picks up the new version
        this.writeFile(this.secondContent);
        assertEquals(this.secondContent, source.getContent());
        assertEquals(DevSource.class, source.getClass());
    }

    /**
     * Ensures that the source never changes, regardless of what happens to the file underneath of it.
     *
     * @throws Exception Shouldn't throw this, unless the filesystem changes underneath
     */
    @Test
    public void testPrimedSource() throws Exception {
        assertNotNull(this.temporaryFile);
        Source source = SourceFactory.getSource(Mode.PRODUCTION, this.temporaryFile);
        assertEquals(source.getContent(), this.firstContent);
        this.writeFile(this.secondContent);
        assertEquals(source.getContent(), this.firstContent);
        assertEquals(source.getClass(), PrimedSource.class);
    }

    protected void writeFile(final String content) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.temporaryFile));
        writer.write(content);
        writer.close();
    }
}
