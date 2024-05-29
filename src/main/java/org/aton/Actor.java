package org.aton;

import java.util.Queue;
import java.util.logging.Level;

public class Actor implements Runnable{
    private String currentReplica;
    private String name;
    private ActorState currentState = ActorState.WAITING;
    private Queue<String> replicaSet;

    public ActorState getCurrentState() {return this.currentState;}

    public void setCurrentState(ActorState currentState) {this.currentState = currentState;}

    public String getName() {return this.name;}

    public Queue<String> getReplicaSet() {
        return this.replicaSet;
    }

    public Actor(String name, Queue<String> replicaSet) {
        this.replicaSet = replicaSet;
        this.name = name;
    }

    @Override
    public void run() {
        while (!replicaSet.isEmpty()) {
            if(currentState == ActorState.TALK) {
                currentReplica = replicaSet.poll();
                System.out.println(name + ": " + currentReplica);
                currentState = ActorState.WAITING;
            }
        }
    }
}
