package org.aton;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class ScenarioParser {

    private List<Actor> actors;
    private Queue<String> scenario;

    public List<Actor> getActors() {return this.actors;}

    public Queue<String> getScenario() {return this.scenario;}

    public ScenarioParser(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            parseScript(lines);
        } catch (IOException e) {
            e.printStackTrace();
            this.actors = Collections.emptyList();
        }
    }

    private void parseScript(List<String> lines) {
        Map<String, Queue<String>> actorsMap = new HashMap<>();
        String currentActor;
        scenario = new LinkedBlockingQueue<>();

        for (String line : lines) {
            String[] s = line.split(":");
            currentActor = s[0].trim();
            scenario.add(currentActor);
            actorsMap.putIfAbsent(currentActor, new LinkedBlockingQueue<>());
            actorsMap.get(currentActor).add(s[1].trim());
        }

        actors = new ArrayList<>();
        for (Map.Entry<String, Queue<String>> entry : actorsMap.entrySet()) {
            actors.add(new Actor(entry.getKey(), entry.getValue()));
        }
    }
}
