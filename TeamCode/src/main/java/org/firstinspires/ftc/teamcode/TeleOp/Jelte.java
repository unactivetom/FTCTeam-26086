package org.firstinspires.ftc.teamcode.TeleOp;

//import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
//
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DistanceSensor;
//import com.qualcomm.robotcore.util.Range;
//
//import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
//
//public class Jelte extends OpMode {
//
//    DcMotor left;
//    DcMotor right;
//    DcMotor flaps;
//    double SpeedFoward;
//    double SpeedTurn;
//    DistanceSensor distanceSensor;
//
//
//    @Override
//    public void init() {
//        telemetry.addLine("INIT");
//
//        left = hardwareMap.get(DcMotor.class, "left");
//        right = hardwareMap.get(DcMotor.class, "right");
//        flaps = hardwareMap.get(DcMotor.class, "flaps");
//        distanceSensor = hardwareMap.get(DistanceSensor.class, "distanceSensor");
//
//
//
//
//        telemetry.addLine("is done");
//        telemetry.update();
//    }
//
//    @Override
//    public void loop() {
//        SpeedFoward = -gamepad1.left_stick_y;
//        SpeedTurn = gamepad1.left_stick_x;
//
//        double leftSpeed = Range.clip(SpeedFoward + SpeedTurn, -1, 1);
//        double rightSpeed = Range.clip(SpeedFoward - SpeedTurn, -1, 1);
//
//        left.setPower(leftSpeed);
//        right.setPower(rightSpeed);
//
//        if (gamepad1.aWasPressed()) flaps.setPower(1);
//
//        if (gamepad1.aWasReleased()) flaps.setPower(0);
//
//        if (gamepad1.bWasPressed()) {
//            while ((distanceSensor.getDistance(DistanceUnit.CM) < 19)){
//                left.setPower(-0.5);
//                right.setPower(-0.5);
//            while ((distanceSensor.getDistance(DistanceUnit.CM) > 20)){
//                left.setPower(0.5);
//                right.setPower(0.5);
//            }
//            left.setPower(0);
//            right.setPower(0);
//
//        }
//
//        telemetry.addData("distance: ", distanceSensor.getDistance(DistanceUnit.CM));
//    }
//}
