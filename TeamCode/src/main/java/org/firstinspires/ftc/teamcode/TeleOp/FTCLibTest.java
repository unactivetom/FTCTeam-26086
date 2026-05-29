package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.rev.Rev9AxisImuOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;

import org.firstinspires.ftc.ftccommon.internal.manualcontrol.parameters.ImuParameters;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "FTCLibTest", group = "Test")
public class FTCLibTest extends OpMode {
    private Motor fL, fR, bL, bR;
    private MecanumDrive drive;
    private GamepadEx driverOp;
    private IMU imu;


    @Override
    public void init() {
        /* instantiate motors */

        drive = new MecanumDrive(fL, fR, bL, bR);
        imu = hardwareMap.get(IMU.class, "imu");
        driverOp = new GamepadEx(gamepad1);
        imu.initialize(new IMU.Parameters(new Rev9AxisImuOrientationOnRobot(Rev9AxisImuOrientationOnRobot.LogoFacingDirection.LEFT, Rev9AxisImuOrientationOnRobot.I2cPortFacingDirection.DOWN)));
    }

    @Override
    public void loop() {
        drive.driveFieldCentric(
                driverOp.getLeftX(),
                driverOp.getLeftY(),
                driverOp.getRightY(),
                imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS)

        );

    }
}
