package edu.rice.comp504.model.strategy.behavior;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;
import edu.rice.comp504.util.RandUtil;

public class BehaviorStrategyFac implements IStrategyFac {

    private  static IUpdateStrategy[] strategies = {HorizontalStrategy.make(), CircleStrategy.make(), SquareStrategy.make(), TransformStrategy.make(),
            TeleportStrategy.make()};
    /**
     * Constructor.
     */
    public BehaviorStrategyFac() {

    }

    private static int maxDistance = 3;


    @Override
    public IUpdateStrategy make(String type) {

        switch (type) {
            case "horizontal":
                return HorizontalStrategy.make();
            case "circle":
                return CircleStrategy.make();
            case "square":
                return SquareStrategy.make();
            case "teleport":
                return TeleportStrategy.make();
            case "transform":
                return TransformStrategy.make();
            case "stopAndGo":
                return StopAndGoStrategy.make();
            case "acceleration":
                return AccelerationStrategy.make();
            case "rectangle":
                return RectangleStrategy.make();
            case "triangle":
                return TriangleStrategy.make();
            case "outAndBack":
                return OutAndBackStrategy.make();
            default:
                return null;
        }
    }

    /**
     * Switch the strategy.
     * @param context The current line.
     * @return The new line strategy for the line
     */
    public  static void switchStrategy(APaintObject context, IUpdateStrategy strategy) {
        context.setStrategy(strategy);
    }

    public static int getMaxDistance() {
        return maxDistance;
    }
}
