import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import org.assertj.core.api.Assertions;
import org.aton.Actor;
import org.aton.ScenarioParser;
import org.junit.jupiter.api.Test;


public class TestScenarioParser {
    public static final String PATH_TO_SCENARIO = "src/main/resources/scenario";
    public static final String PATH_TO_TEST_ACTORS = "src/main/resources/Test_Actors";
    public static final String PATH_TO_TEST_SCENARIO = "src/main/resources/Test_Scenario";
    private List<String> expectedNameActors;
    private Queue<String> expectedScenario;

    public void preparingExpectedScenario() throws IOException {
        expectedScenario = new LinkedBlockingDeque<>();
        List<String> lines = Files.readAllLines(Paths.get(PATH_TO_TEST_SCENARIO));
        expectedScenario.addAll(lines);

    }

    public void preparingExpectedActorsName() throws IOException {
        expectedNameActors = Files.readAllLines(Paths.get(PATH_TO_TEST_ACTORS));
    }

    @Test
    void parseActorsNameTest() throws IOException {
        ScenarioParser scenarioParser = new ScenarioParser(PATH_TO_SCENARIO);
        List<Actor> resultActors = scenarioParser.getActors();
        List<String> resultNameActors = new ArrayList<>();
        for(Actor actor: resultActors) {resultNameActors.add(actor.getName());}

        preparingExpectedActorsName();

        Assertions.assertThat(resultNameActors).containsExactlyElementsOf(expectedNameActors);
    }

    @Test
    void parseScenarioTest() throws IOException {
        ScenarioParser scenarioParser = new ScenarioParser(PATH_TO_SCENARIO);
        Queue<String> resultScenario = scenarioParser.getScenario();
        preparingExpectedScenario();

        Assertions.assertThat(resultScenario).containsExactlyElementsOf(expectedScenario);
    }
}
