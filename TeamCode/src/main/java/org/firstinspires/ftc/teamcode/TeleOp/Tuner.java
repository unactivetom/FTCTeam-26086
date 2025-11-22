package org.firstinspires.ftc.teamcode.TeleOp;


import static org.firstinspires.ftc.teamcode.TeleOp.Constants.*;

import com.qualcomm.robotcore.hardware.Gamepad;

public class Tuner {


    String current = LFTUNE.name;

    public Tuner(Gamepad gamepad){

    }
    public void Tune(Gamepad gamepad){
        MainTeleOp main = new MainTeleOp();



        main.log("###Tuner:###");
        main.log("LFTUNE", LFTUNE.value);
        main.log("RFTUNE", RFTUNE.value);
        main.log("LBTUNE", LBTUNE.value);
        main.log("RBTUNE", LBTUNE.value);
        main.log("changing: ", current);
        main.log("###Buttons:###");
        main.log("LeftBumper=-1     rightBumper=+1");
        main.log("     x --------> x      x       ");
        main.log("   x   x ---------------^       ");
        main.log("   | x---------{ x }--> x       ");
        main.log("   |-------------^              ");

        if(gamepad.dpad_up) current = LFTUNE.name; gamepad.rumble(100);
        if(gamepad.dpad_right) current = RFTUNE.name; gamepad.rumble(100);
        if(gamepad.dpad_down) current = LBTUNE.name; gamepad.rumble(100);
        if(gamepad.dpad_left) current = RBTUNE.name; gamepad.rumble(100);

        if(gamepad.left_bumper){
            switch(current){
                case "LFTUNE": LFTUNE.value -= 0.05; break;
                case "RFTUNE": RFTUNE.value -= 0.05; break;
                case "LBTUNE": LBTUNE.value -= 0.05; break;
                case "RBTUNE": RBTUNE.value -= 0.05; break;
            }
            while(gamepad.left_bumper){}
        }
        if(gamepad.right_bumper){
            switch(current){
                case "LFTUNE": LFTUNE.value += 0.05; break;
                case "RFTUNE": RFTUNE.value += 0.05; break;
                case "LBTUNE": LBTUNE.value += 0.05; break;
                case "RBTUNE": RBTUNE.value += 0.05; break;
            }
            while(gamepad.right_bumper){}
        }
    }


}
