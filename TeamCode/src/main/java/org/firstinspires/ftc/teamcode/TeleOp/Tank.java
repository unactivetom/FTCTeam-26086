package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;


public class Tank extends Drive{


    //These variables are just to calculate the speed
    public double left_power;
    public double right_power;
    public double yaw = 0;
    private boolean debug;

    public Tank(boolean debug){
        super();
        this.debug = debug;
    }

    public void Init(HardwareMap hardwareMap){
        super.initializer((byte) 4, hardwareMap);
    }

    public void driveLoop(Gamepad gamepad1) {
        double slow_drive = -gamepad1.left_stick_y * Constants.SLOW_SPEED;
        double slow_turn = gamepad1.left_stick_x * Constants.SLOW_SPEED;
        double fast_turn  =  gamepad1.right_stick_x;
        slow_drive += Fast_drive(gamepad1); //We want the fast drive to overwrite the slow speed

        if(gamepad1.cross){
            super.toggleBrake();
        }else {
            //Make the variable stay between the boundaries the motor can handle
            left_power = Range.clip((slow_drive + slow_turn + fast_turn), -1.0, 1.0);
            right_power = Range.clip((slow_drive - slow_turn - fast_turn), -1.0, 1.0);

            //Send the power to the motors
            super.setPowers(left_power, right_power, left_power, right_power);
        }

        if(debug) super.Logger((float)left_power,(float)right_power,-gamepad1.left_stick_x,-gamepad1.left_stick_y,-gamepad1.right_stick_x);
    }

    private double Fast_drive(Gamepad gamepad1){ //this function return a value of 1 when pressed, and a value of 0 when not
        double drive_speed = 0; //every frame reset to 0
        if(gamepad1.right_trigger > 0){ //if the right trigger is pressed

            drive_speed++; //Makes the Motors go forward

        }else if(gamepad1.left_trigger > 0){ //if the left trigger is p

            drive_speed--; //Makes the Motors go backwards

        }

        return(drive_speed);
    }







}
