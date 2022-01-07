package com.clockworkshepherd.client_finder;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Document {
    private final Path path;
    PDDocument document;
    Boolean encrypted;
    String text;
    List<String> lines;

    PDFTextStripper stripper = new PDFTextStripper();


    Document(Path path) throws IOException {
        this.path = path;
        this.document = PDDocument.load(path.toFile());
        this.encrypted = document.isEncrypted();
        stripper.setSortByPosition(true);

        if (encrypted) {
            text = "";
            lines = new ArrayList<>();
        } else {
            text = stripper.getText(document);
            lines = List.of(text.split(System.lineSeparator()));
        }

        document.close();
    }

//    private get

    public Path getPath() {
        return path;
    }

    public PDDocument getDocument() {
        return document;
    }

    public Boolean isEncrypted() {
        return encrypted;
    }

    public String getText() {
        return text;
    }

    public List<String> getLines() {
        return lines;
    }
}
