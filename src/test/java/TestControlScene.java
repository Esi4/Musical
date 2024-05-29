import org.assertj.core.api.Assertions;
import org.aton.ControlScene;
import org.aton.ScenarioParser;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestControlScene {
    public static final String PATH_TO_SCENARIO = "src/main/resources/scenario";

    @Test
    void testScene() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        PrintStream customOut = new PrintStream(byteArrayOutputStream);
        ScenarioParser scenarioParser = new ScenarioParser(PATH_TO_SCENARIO);
        ControlScene controlScene = new ControlScene(scenarioParser);
        List<String> expectedNameActors = Files.readAllLines(Paths.get(PATH_TO_SCENARIO));

        System.setOut(customOut);

        try {
            controlScene.scene();
        } finally {
            System.setOut(originalOut);
        }

        String output = byteArrayOutputStream.toString();
        List<String> outputLines = new ArrayList<>(Arrays.asList(output.split(System.lineSeparator())));
        Assertions.assertThat(outputLines).containsExactlyElementsOf(expectedNameActors);
    }
}
