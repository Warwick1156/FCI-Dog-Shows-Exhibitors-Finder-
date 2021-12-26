package example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) throws IOException {
//        Path input = Paths.get("./input/wykaz.pdf");
//        ExhibitorsList pdf = new ExhibitorsList(new Document(input));

        Path path = Paths.get("./input/sample.pdf");
        PDDocument document = PDDocument.load(path.toFile());
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        System.out.println(stripper.getText(document));

    }
}
