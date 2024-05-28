package org.aton;

import java.util.concurrent.Callable;

public class Actor implements Callable<Actor>, Comparable<Actor>{
    private String currentReplica = "Hello Everybody!";

    public void setCurrentReplica(String currentReplica) {this.currentReplica = currentReplica;}

    public void beginningTalkReplica() {
        System.out.println(currentReplica);
    }

    @Override
    public int compareTo(Actor o) {
        return 0;
    }

    @Override
    public Actor call() throws Exception {
        return null;
    }
}
