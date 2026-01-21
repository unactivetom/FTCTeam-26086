package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class MechanumRobotCentric extends Drive{

    private boolean debug;


    private double leftFrontPower;
    private double rightFrontPower;
    private double leftBackPower;
    private double rightBackPower;

    private double speedModifier = 1;
    MainTeleOp mainTeleOp = new MainTeleOp();



    public MechanumRobotCentric(boolean debugMode){
        super();
        this.debug = debugMode;
    }

    public void init(HardwareMap hardwareMap){
        super.initializer((byte) 4, hardwareMap);
        super.setBrake(true);
    }

    public void driveLoop(Gamepad gamepad1, Telemetry telemetry) {
        // Get IMU heading in radians

        if(gamepad1.dpadUpWasPressed() && speedModifier < 1) speedModifier += 0.1;
        if(gamepad1.dpadDownWasPressed() && speedModifier > -1) speedModifier -= 0.1;

        Orientation anglesDegree = imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        // Get joystick inputs for movement
        double inputLY = -gamepad1.left_stick_y; // forward/back
        double inputLX = gamepad1.left_stick_x; // strafe, with slight adjustment
        double inputRX = gamepad1.right_stick_x; // rotation

        // Calculate motor powers for mecanum drive
        leftFrontPower = inputLY + inputLX + inputRX;
        rightFrontPower = inputLY - inputLX - inputRX;
        leftBackPower = inputLY - inputLX + inputRX;
        rightBackPower = inputLY + inputLX - inputRX;

        double max = Math.abs(leftFrontPower);
        max = Math.max(max, Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if(max > 1) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }

        // Set motor powers
        leftFront.setPower(leftFrontPower * speedModifier);
        rightFront.setPower(rightFrontPower * speedModifier);
        leftBack.setPower((leftBackPower * speedModifier)/1.5);
        rightBack.setPower(rightBackPower * speedModifier);


        if(gamepad1.cross) super.toggleBrake(); //to break

        telemetry.addLine("Motor telemetry");
        mainTeleOp.motorPacket.addTimestamp();
        mainTeleOp.motorPacket.addLine("Motor Telemetry: ");
        telemetry.addData("leftFront power: ", leftFrontPower);
        mainTeleOp.motorPacket.put("leftFront power:", leftFrontPower);
        telemetry.addData("rightFront power: ", rightFrontPower);
        mainTeleOp.motorPacket.put("rightFront power:", rightFrontPower);
        telemetry.addData("leftBack power: ", leftBackPower);
        mainTeleOp.motorPacket.put("leftBack power:", leftBackPower);
        telemetry.addData("rightBack power: ", rightBackPower);
        mainTeleOp.motorPacket.put("rightBack power:", rightBackPower);
        telemetry.addData("Modifier: ", speedModifier);
        mainTeleOp.motorPacket.put("Modifier:", speedModifier);
        telemetry.addData("Yaw: ", anglesDegree.firstAngle);
        mainTeleOp.motorPacket.put("Yaw:", anglesDegree.firstAngle);

        mainTeleOp.dashboard.sendTelemetryPacket(mainTeleOp.motorPacket);



    }

}
