package example;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;


public class ExhibitorsList {
    public static final char CHAR = '-';
    public static final String WHITESPACE = " ";

    PDDocument document;
    List<Exhibitor> exhibitors = new ArrayList<>();

    ExhibitorsList(Path path) throws IOException {
        document = PDDocument.load(path.toFile());

        if (!document.isEncrypted()) {
            PDFTextStripper stripper = new PDFTextStripper();
            String content = stripper.getText(document);

            Arrays.stream(content.split(System.lineSeparator())).forEach(this::addExhibitor);


            List<String> keys = new ArrayList<>();
            exhibitors.forEach(instance -> keys.add(instance.getSummary()));
            System.out.println(keys);
        }
        document.close();
    }

    private void addExhibitor(String input) {
        try {
            HashMap<String, String> exhibitorInfo = extractExhibitorInfo(input);
            String key = exhibitorInfo.get("key");
            if (exhibitorExists(key)) {
                exhibitors.stream()
                        .filter(exhibitor -> Objects.equals(exhibitor.getKey(), key))
                        .forEach(exhibitor -> exhibitor.addStartNumber(exhibitorInfo.get("startNumber")));
            } else {
                exhibitors.add(new Exhibitor(
                        exhibitorInfo.get("firstName"),
                        exhibitorInfo.get("surname"),
                        exhibitorInfo.get("lastName"),
                        exhibitorInfo.get("country"),
                        exhibitorInfo.get("startNumber"),
                        exhibitorInfo.get("key")
                ));
            }
        } catch (Exception e) {
            String massage = String.format("Exception %s, on line %s. Skipping.", e, input);
            System.out.println(massage);
        }
    }

    private HashMap<String, String> extractExhibitorInfo(String input) {
        Long charCount = input.chars().filter(ch -> ch == CHAR).count();
        String info = input.split("-")[charCount.intValue()];
        String fullName = input.substring(0, input.indexOf(info));
        fullName = fullName.replace("-", "");
        fullName = String.join(WHITESPACE, Arrays.stream(fullName.split(WHITESPACE)).filter(element -> !element.isEmpty()).toArray(String[]::new));

        String lastName = fullName.split(WHITESPACE)[0];

        String[] splitFullName = fullName.split(WHITESPACE);
        String firstName = splitFullName[splitFullName.length - 1];
        String surname = String.join(WHITESPACE, new ArrayList<>(Arrays.asList(splitFullName).subList(1, splitFullName.length - 1)));

        String[] splitInfo = Arrays.stream(info.split(WHITESPACE)).filter(element -> !element.isEmpty()).toArray(String[]::new);
        String country = splitInfo[0];
        String startNumber = splitInfo[1];

        HashMap<String, String> exhibitorInfo = new HashMap<>();
        exhibitorInfo.put("firstName", firstName);
        exhibitorInfo.put("surname", surname);
        exhibitorInfo.put("lastName", lastName);
        exhibitorInfo.put("country", country);
        exhibitorInfo.put("startNumber", startNumber);
        exhibitorInfo.put("key", String.join(WHITESPACE, firstName, lastName));

        return exhibitorInfo;
    }

    private Boolean exhibitorExists(String key) {
        return exhibitors
                .stream().anyMatch(exhibitor -> Objects.equals(exhibitor.key, key));
    }
}

