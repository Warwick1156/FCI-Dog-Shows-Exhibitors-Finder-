package com.clockworkshepherd.client_finder;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

class DocumentTest {

    @Test
    void getPath() throws IOException {
        Path input = Paths.get("./input/sample.pdf");
        Document document = new Document(input);
        assertEquals(document.getPath(), input);
    }

    @Test
    void getDocument() throws IOException {
        Document document = getDummyDocument();
        assertThat(document.getDocument(), instanceOf(PDDocument.class));
    }

    @Test
    void getEncrypted() throws IOException {
        Document notEncryptedDocument = getDummyDocument();
        Document encryptedDocument = getEncryptedDummy();

        assertEquals(false, notEncryptedDocument.isEncrypted());
        assertEquals(true, encryptedDocument.isEncrypted());
    }

    @Test
    void getText() throws IOException {
        String EOL = System.getProperty("line.separator");
        String expected = "Placeholder\tDocumentation\t" + EOL +
                "This document is a placeholder for the appropriate documentation. " + EOL +
                " " + EOL;
        Document document = getDummyDocument();
        assertEquals(expected, document.getText());

    }

    @Test
    void getLines() throws IOException {
        List<String> expected = Arrays.asList(
                "Placeholder\tDocumentation\t", "This document is a placeholder for the appropriate documentation.\s", "\s");
        Document document = getDummyDocument();
        assertEquals(expected, document.getLines());
    }

    Document getDummyDocument() throws IOException {
        Path input = Paths.get("./input/sample.pdf");
        return new Document(input);
    }

    Document getEncryptedDummy() throws IOException {
        Path path = Paths.get("./input/encrypted.pdf");
        return new Document(path);
    }
}