package edu.rice.comp504.model.strategy.interaction;

import edu.rice.comp504.model.strategy.behavior.*;

public class InteractionStrategyFac implements IInteractionStrategyFac {

    /**
     * Constructor.
     */
    public InteractionStrategyFac() {

    }

    private IInteractionStrategy[] strategies = {};

    @Override
    public IInteractionStrategy make(String type) {

        switch (type) {
            case "changeDirection":
                return ChangeDirectionStrategy.make();
            case "destroy":
                return DestroyStrategy.make();
            case "changeColor":
                return ChangeColorStrategy.make();
            case "changeBehavior":
                return ChangeBehaviorStrategy.make();
            case "eat":
                return EatStrategy.make();
            case "changeLoc":
                return ChangeLocStrategy.make();
            case "spawn":
                return SpawnStrategy.make();
            case "transformFish":
                return TransformFishStrategy.make();
            case "stop":
                return StopStrategy.make();
            case "explosion":
                return ExplosionStrategy.make();
            default:
                return null;
        }
    }
}
