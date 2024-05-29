package org.aton;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ControlScene {
    private final ExecutorService executorService = Executors.newFixedThreadPool(6);
    private final ScenarioParser scenarioParser;

    public ControlScene(ScenarioParser scenarioParser) {
        this.scenarioParser = scenarioParser;
    }

    public void scene() {
        try {
            executeActors(scenarioParser);
            processScenario(scenarioParser);
        } catch (InterruptedException e) {
            handleInterruptedException(e);
        } finally {
            shutdownExecutorService();
        }
    }

    private void executeActors(ScenarioParser scenarioParser) {
        for (Actor actor : scenarioParser.getActors()) {
            executorService.execute(actor);
        }
    }

    private void processScenario(ScenarioParser scenarioParser) throws InterruptedException {
        Queue<String> scenario = scenarioParser.getScenario();
        while (!scenario.isEmpty()) {
            Thread.sleep(1000);
            String currentPerformingActor = scenario.poll();
            updateActorState(scenarioParser, currentPerformingActor);
        }
    }

    private void updateActorState(ScenarioParser scenarioParser, String currentPerformingActor) {
        for (Actor actor : scenarioParser.getActors()) {
            if (Objects.equals(actor.getName(), currentPerformingActor)) {
                actor.setCurrentState(ActorState.TALK);
                break;
            }
        }
    }

    private void handleInterruptedException(InterruptedException e) {
        throw new RuntimeException(e);
    }

    private void shutdownExecutorService() {
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

