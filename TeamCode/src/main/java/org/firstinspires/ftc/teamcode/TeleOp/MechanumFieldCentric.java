package org.firstinspires.ftc.teamcode.TeleOp;


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

        Orientation angelsDegree = super.imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
        Orientation anglesRadians = super.imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.RADIANS);
        double heading = anglesRadians.thirdAngle;

        // Get joystick inputs for movement
        float inputLY = -gamepad1.left_stick_y; // forward/back
        float inputLX = gamepad1.left_stick_x; // strafe, with slight adjustment
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






        // Set motor powers
        leftFront.setPower(debug ? leftFrontPower / 4 : leftFrontPower / 1.5);
        rightFront.setPower(debug ? rightFrontPower / 4 : rightFrontPower / 1.5);
        leftBack.setPower(debug ? leftBackPower / 4 : leftBackPower / 1.5);
        rightBack.setPower(debug ? rightBackPower / 4 : rightBackPower / 1.5);

//        super.setPowers((debug ? leftBackPower / 4 : leftBackPower / 2), (debug ? rightBackPower / 4 : rightBackPower / 2), (debug ? leftFrontPower / 4 : leftFrontPower / 2), (debug ? rightFrontPower / 4 : rightFrontPower / 2));
        if(gamepad1.cross) super.toggleBrake(); //to break


        //debugger:
        if (debug) super.Logger(leftFrontPower,rightFrontPower,leftBackPower,rightBackPower,angelsDegree,inputLX,inputLY,inputRX);
    }
}