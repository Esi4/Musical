package org.aton;

import java.util.List;

public class Main {
    public static final String PATH_TO_SCENARIO = "src/main/resources/scenario";

    public static void main(String[] args) {
        ScenarioParser scenarioParser = new ScenarioParser(PATH_TO_SCENARIO);
        ControlScene controlScene = new ControlScene(scenarioParser);
        controlScene.scene();

    }
}