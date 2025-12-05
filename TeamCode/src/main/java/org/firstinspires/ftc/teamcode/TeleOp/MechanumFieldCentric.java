package org.firstinspires.ftc.teamcode.TeleOp;


import static org.firstinspires.ftc.teamcode.TeleOp.Constants.*;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class MechanumFieldCentric extends Drive{
    private boolean debug;



    public MechanumFieldCentric(boolean debugMode) {
        super();
        debug = debugMode;
    }
    public void init(HardwareMap hardwareMap){
        super.initializer((byte) 4, hardwareMap);
        setBrake(true);
    }


    public void driveLoop(Gamepad gamepad1) {
        // Get IMU heading in radians

        Orientation angelsDegree = imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
        Orientation anglesRadians = imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.RADIANS);
        double heading = anglesRadians.firstAngle;

        // Get joystick inputs for movement
        float inputLY = -gamepad1.left_stick_y; // forward/back
        float inputLX = -gamepad1.left_stick_x; // strafe, with slight adjustment
        float inputRX = -gamepad1.right_stick_x; // rotation


        // Rotate the input coordinates based on the field-centric angle
        float rotatedX = (float) (inputLX * Math.cos(-heading) + inputLY * Math.sin(-heading));
        float rotatedY = (float) (-inputLX * Math.sin(-heading) + inputLY * Math.cos(-heading));

        // Calculate motor powers for mecanum drive
        float leftFrontPower = rotatedY + rotatedX + inputRX;
        float rightFrontPower = rotatedY - rotatedX - inputRX;
        float leftBackPower = rotatedY - rotatedX + inputRX;
        float rightBackPower = rotatedY + rotatedX - inputRX;

        // Normalize motor powers if any exceeds 1.0
        double max = Math.max(1.0, Math.abs(leftFrontPower));


        rightBackPower /= (float) max;

        // Set motor powers
//        leftFront.setPower((debug ? leftFrontPower / 4 : leftFrontPower)*LFTUNE.value);
//        rightFront.setPower((debug ? rightFrontPower / 4 : rightFrontPower)*RFTUNE.value);
//        leftBack.setPower((debug ? leftBackPower / 4 : leftBackPower)*LBTUNE.value);
//        rightBack.setPower((debug ? rightBackPower / 4 : rightBackPower)*RBTUNE.value);
        super.setPowers((debug ? leftBackPower / 4 : leftBackPower)*LBTUNE.value, (debug ? rightBackPower / 4 : rightBackPower)*RBTUNE.value, (debug ? leftFrontPower / 4 : leftFrontPower)*LFTUNE.value, (debug ? rightFrontPower / 4 : rightFrontPower)*RFTUNE.value);
        if(gamepad1.cross) super.toggleBrake(); //to break


        //debugger:
        if (debug) super.Logger(leftFrontPower,rightFrontPower,leftBackPower,rightBackPower,angelsDegree,inputLX,inputLY,inputRX);
    }
}