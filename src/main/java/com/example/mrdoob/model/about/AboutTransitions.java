package com.example.mrdoob.model.about;

import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.manageStates.StateTransitions;
import io.github.jspinak.brobot.services.StateTransitionsRepository;
import org.springframework.stereotype.Component;

import static io.github.jspinak.brobot.actions.actionOptions.ActionOptions.Action.FIND;

@Component
public class AboutTransitions {

    private final Action action;
    private final About about;

    public AboutTransitions(StateTransitionsRepository stateTransitionsRepository,
                              Action action, About about) {
        this.action = action;
        this.about = about;
        StateTransitions transitions = new StateTransitions.Builder("about")
                .addTransitionFinish(this::finishTransition)
                .build();
        stateTransitionsRepository.add(transitions);
    }

    private boolean finishTransition() {
        return action.perform(FIND, about.getAboutText()).isSuccess();
    }

}
