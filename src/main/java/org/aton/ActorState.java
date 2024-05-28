package org.aton;

import java.util.Set;

public enum ActorState {
    TALK {
        @Override
        Set<ActorState> nextStates() {
            return Set.of(WAITING, READY);
        }
    }, WAITING {
        @Override
        Set<ActorState> nextStates() {
            return Set.of(READY);
        }
    }, READY {
        @Override
        Set<ActorState> nextStates() {
            return Set.of(TALK);
        }
    };

    Set<ActorState> nextStates() {
        return Set.of();
    }

}

