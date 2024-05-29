package org.aton;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        testControlReplica();
        //testScenario();
    }

    public static void testControlReplica() {
        ControlReplica controlReplica = new ControlReplica();
        controlReplica.scene();
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