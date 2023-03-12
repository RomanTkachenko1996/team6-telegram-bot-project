package edu.goit.feature.service.statemachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StateMachine {
    private State state = State.idle;
    public int time;
    private final List<StateMachineUsers> users = new ArrayList<>();

    public void addUser (StateMachineUsers user){
        users.add(user);
    }

    public void handle (String message){
        switchState(State.waitForTime);

        List<String> times = List.of(
                "9:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00"
        );

        boolean coincidence = times.stream()
                .anyMatch(time -> Objects.equals(time, message));


        if(coincidence) {
            onTextReceived(message);
            return;
        }

        if(message.equals("Вимкнути повідомлення")){
            switchState(State.idle);
            System.out.println("Повідомлення вимкнені");
        }
    }

    public void onTextReceived (String message){
        time = Integer.parseInt(message.split(":")[0]);
        System.out.println("Повідомлення увімкненні на час: " + time);
        onTimeReceived(time);
    }

    public void onTimeReceived (int time){
        switchState(State.idle);

        for (StateMachineUsers user : users) {
            user.onTimeReceived(time);
        }
    }

    private void switchState(State newState) {
        System.out.println("Switch state " + state + " => " + newState);

        this.state = newState;
    }
}
