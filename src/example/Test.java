package example;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) throws IOException {

        System.out.println("Hello World");
        Path input = Paths.get("./input/wykaz.pdf");
        ExhibitorsList pdf = new ExhibitorsList(input);

    }
}
