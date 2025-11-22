package org.firstinspires.ftc.teamcode.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.TeleOp.DECODESEASON.Mechanism;

@TeleOp(name = "TankTestDrive", group = "Test")
public class TankTestDrive extends OpMode {



    protected DcMotor leftBack;
    protected DcMotor rightBack;

    Mechanism mechanism = new Mechanism();


    public double left_power;
    public double right_power;

    @Override
    public void init() {
        leftBack = hardwareMap.get(DcMotor.class, "LeftBack");
        rightBack = hardwareMap.get(DcMotor.class, "RightBack");
        mechanism.init(hardwareMap);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        telemetry.addData("TELEOP", "");
        telemetry.addData("time: ", getRuntime());
        driveLoop(gamepad1);
        mechanism.loop(gamepad1);

        telemetry.update();
    }



    private double fastDrive(Gamepad gamepad1){ //this function return a value of 1 when pressed, and a value of 0 when not
        double drive_speed = 0; //every frame reset to 0
        if(gamepad1.right_trigger > 0){ //if the right trigger is pressed

            drive_speed++; //Makes the Motors go forward

        }else if(gamepad1.left_trigger > 0){ //if the left trigger is p

            drive_speed--; //Makes the Motors go backwards

        }

        return(drive_speed);
    }

    private void driveLoop(Gamepad gamepad1){
        double slow_drive = -gamepad1.left_stick_y * Constants.SLOW_SPEED;
        double slow_turn = gamepad1.left_stick_x * 0.6;
        //double fast_turn  =  gamepad1.right_stick_x;
        slow_drive += fastDrive(gamepad1); //We want the fast drive to overwrite the slow speed

        if(gamepad1.cross){
            //Send the zero to the motors
            leftBack.setPower(0.0);
            rightBack.setPower(0.0);
        }else {
            //Make the variable stay between the boundaries the motor can handle
            left_power = Range.clip((slow_drive + slow_turn), -1.0, 1.0);
            right_power = Range.clip((slow_drive - slow_turn), -1.0, 1.0);

            //Send the power to the motors
            leftBack.setPower(left_power*0.85);
            rightBack.setPower(right_power);
        }
    }




















}
