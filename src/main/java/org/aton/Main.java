package org.aton;

import java.util.List;

public class Main {
    public static final String PATH_TO_SCENARIO = "C:\\Users\\Tyauch\\Desktop\\Aton quests\\Musical\\Musical\\src\\main\\resources\\scenario";

    public static void main(String[] args) throws InterruptedException {
        ScenarioParser scenarioParser = new ScenarioParser(PATH_TO_SCENARIO);
        ControlScene controlScene = new ControlScene(scenarioParser);
        controlScene.scene();
    }

    public static void testScenario() {
        ScenarioParser scenarioParser = new ScenarioParser("C:\\Users\\Tyauch\\Desktop\\Aton quests\\Musical\\Musical\\src\\main\\resources\\scenario");
        List<Actor> actors = scenarioParser.getActors();

        // Выводим результаты
        for (Actor actor : actors) {
            System.out.println("Actor: " + actor.getName());
            System.out.println("Lines:");
            for (String line : actor.getReplicaSet()) {
                System.out.println(line);
            }
            System.out.println();
        }

        for(String actor: scenarioParser.getScenario()) {
            System.out.println(actor);
        }
    }
}