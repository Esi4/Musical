package org.aton;

import java.util.Queue;

public class Actor implements Runnable{
    private final String name;
    private ActorState currentState = ActorState.WAITING;
    private final Queue<String> replicaSet;

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
                String currentReplica = replicaSet.poll();
                System.out.println(name + ": " + currentReplica);
                currentState = ActorState.WAITING;
            }
        }
    }
}
