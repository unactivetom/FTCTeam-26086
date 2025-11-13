package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class MotorTest extends OpMode {

    DcMotor motor;

    @Override
    public void init() {
        motor = hardwareMap.get(DcMotor.class, "Motor");
    }

    @Override
    public void loop() {
        if(gamepad1.cross){
            motor.setPower(1);
        }
        else if(gamepad1.circle){
            motor.setPower(-1);
        } else if (gamepad1.square) {
            motor.setPower(0);
        }
    }
}
