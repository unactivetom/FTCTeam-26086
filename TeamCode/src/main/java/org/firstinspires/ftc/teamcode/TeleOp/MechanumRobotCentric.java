package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class MechanumRobotCentric extends Drive{

    private boolean debug;

    public MechanumRobotCentric(boolean debugMode){
        super();
        this.debug = debugMode;
    }

    public void init(HardwareMap hardwareMap){
        super.initializer((byte) 4, hardwareMap);
        super.setBrake(true);
    }

    public void driveLoop(Gamepad gamepad1) {
        // Get IMU heading in radians


        Orientation anglesDegree = imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        // Get joystick inputs for movement
        float inputLY = -gamepad1.left_stick_y; // forward/back
        float inputLX = -gamepad1.left_stick_x; // strafe, with slight adjustment
        float inputRX = -gamepad1.right_stick_x; // rotation


        // Calculate motor powers for mecanum drive
        float leftFrontPower = inputLY + inputLX + inputRX;
        float rightFrontPower = inputLY - inputLX - inputRX;
        float leftBackPower = inputLY - inputLX + inputRX;
        float rightBackPower = inputLY + inputLX - inputRX;

        // Normalize motor powers if any exceeds 1.0
        double max = Math.max(1.0, Math.abs(leftFrontPower));


        rightBackPower /= (float) max;

        // Set motor powers
        leftFront.setPower(debug ? leftFrontPower / 4 : leftFrontPower);
        rightFront.setPower(debug ? rightFrontPower / 4 : rightFrontPower);
        leftBack.setPower(debug ? leftBackPower / 4 : leftBackPower);
        rightBack.setPower(debug ? rightBackPower / 4 : rightBackPower);

        if(gamepad1.cross) super.toggleBrake(); //to break


        //debugger:
        if (debug) super.Logger(leftFrontPower,rightFrontPower,leftBackPower,rightBackPower,anglesDegree,inputLX,inputLY,inputRX);
    }

}
