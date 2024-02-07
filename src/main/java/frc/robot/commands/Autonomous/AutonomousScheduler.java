package frc.robot.commands.Autonomous;
import java.util.LinkedList;
import java.util.Queue;

public class AutonomousScheduler {
    //When we add an action into our actionQueue, all previous actions must be completed before we can run the current action
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