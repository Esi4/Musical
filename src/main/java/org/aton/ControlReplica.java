package org.aton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class ControlReplica {
    public static final String PATH_TO_SCENARIO = "C:\\Users\\Tyauch\\Desktop\\Aton quests\\Musical\\Musical\\src\\main\\resources\\scenario";

    private final ExecutorService executorService = Executors.newFixedThreadPool(6);

    public void scene() {
        ScenarioParser scenarioParser = new ScenarioParser(PATH_TO_SCENARIO);

        try {
            for(Actor actor: scenarioParser.getActors()) {
                executorService.execute(actor);
            }

            Queue<String> scenario = scenarioParser.getScenario();
            while(!scenario.isEmpty()) {
                Thread.sleep(1000);
               String currentPerformingActor = scenario.poll();
                for(Actor actor: scenarioParser.getActors()) {
                    if(Objects.equals(actor.getName(), currentPerformingActor)) {
                        actor.setCurrentState(ActorState.TALK);
                        break;
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

}
