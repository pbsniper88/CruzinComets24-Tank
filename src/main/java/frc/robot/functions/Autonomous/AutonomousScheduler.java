package frc.robot.functions.Autonomous;
import java.util.LinkedList;
import java.util.Queue;

public class AutonomousScheduler {
    private Queue<Action> actionQueue = new LinkedList<>();

    public void addAction(Action action) {
        actionQueue.add(action);
    }

    public void run() {
        if (!actionQueue.isEmpty()) {
            Action currentAction = actionQueue.peek();

            if (!currentAction.isFinished()) {
                currentAction.update();
            } else {
                currentAction.end();
                actionQueue.remove();
                if (!actionQueue.isEmpty()) {
                    actionQueue.peek().start();
                }
            }
        }
    }

    public void init() {
        if (!actionQueue.isEmpty()) {
            actionQueue.peek().start();
        }
    }
}
