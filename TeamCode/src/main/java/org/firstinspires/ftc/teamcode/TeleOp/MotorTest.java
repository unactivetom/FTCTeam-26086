package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "MotorTest", group = "Test")
public class MotorTest extends OpMode {

    DcMotorEx motor;
    double speed = 1.0;

    @Override
    public void init() {
        motor = hardwareMap.get(DcMotorEx.class, "Motor");
    }

    @Override
    public void loop() {
        if(gamepad1.cross){
            motor.setPower(speed);
        } else if(gamepad1.circle){
            motor.setPower(-speed);
        } else if (gamepad1.square) {
            motor.setPower(0);
        }

        telemetry.addData("Speed: ", speed);
        telemetry.update();


    }
}
