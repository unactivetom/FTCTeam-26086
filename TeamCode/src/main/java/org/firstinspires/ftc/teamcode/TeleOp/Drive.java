package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class Drive {

    protected BHI260IMU imu;
    protected BHI260IMU.Parameters imuParameters;
    protected DcMotor leftBack;
    protected DcMotor leftFront;
    protected DcMotor rightBack;
    protected DcMotor rightFront;
    protected DistanceSensor distanceSensor;


    protected MainTeleOp main;
    private byte numberOfWheels = 0;
    public boolean brake = true;




    public Drive(){

    }

    protected void setBrake(boolean value){
        this.brake = value;
        //switching between floating break and break break.
        if(this.brake){
            //main.log("Brake setting: Brake");
            if(numberOfWheels == 4){
                leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }
            leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }else{
            //main.log("Brake setting: Float");
            if(numberOfWheels == 4){
                leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

            }
            leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
    }
    protected void toggleBrake(){
        setBrake(!getBrake());
    }


    private boolean getBrake() {
        return brake;
    }

    public void initializer(byte motorCount, HardwareMap hardwareMap){
        main = new MainTeleOp();
        imu = hardwareMap.get(BHI260IMU.class, "imu");
        imuParameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.DOWN
        )
        );
        //distanceSensor = hardwareMap.get(DistanceSensor.class, "DistanceSensor");
        imu.initialize(imuParameters);
        numberOfWheels = motorCount;

        leftBack = hardwareMap.get(DcMotor.class, "LeftBack");
        rightBack = hardwareMap.get(DcMotor.class, "RightBack");
        rightBack.setDirection(DcMotor.Direction.REVERSE);


        if(numberOfWheels == 4){
            leftFront = hardwareMap.get(DcMotor.class, "LeftFront");
            rightFront = hardwareMap.get(DcMotor.class, "RightFront");
            rightFront.setDirection(DcMotor.Direction.REVERSE);
        }

        //main.motorPacket.addTimestamp();
        //main.motorPacket.addLine("Drive init ready");
    }

    public void setPowers(double leftBackPower, double rightBackPower, double leftFrontPower, double rightFrontPower){

        leftFront.setPower(leftFrontPower);
        rightFront.setPower(rightFrontPower);
        leftBack.setPower(leftBackPower);
        rightBack.setPower(rightBackPower);


    }

//    protected void goToPosDisFromGoal(double distanceCM, double numberOfWheels){
//        double distance = distanceSensor.getDistance(DistanceUnit.CM);
//        double difference = distance - distanceCM;
//        double leftPower = Math.min(difference, .425);
//        leftPower = Math.max(difference, -.425);
//        double rightPower = Math.min(difference, .5);
//        rightPower = Math.min(difference, -.5);
//
//    }

    protected void Logger(float leftFrontPower, float rightFrontPower, float leftBackPower, float rightBackPower, Orientation angles, float inputLX, float inputLY, float inputRX){
        main.log();
        main.log("########DEBUG########");
        main.log("MOTORS:");
        main.log("Left Front: ", leftFrontPower);
        main.log("Right Front: ", rightFrontPower);
        main.log("Left Back: ", leftBackPower);
        main.log("Right Back: ", rightBackPower);
        main.log();
        main.log("ORIENTATION:");
        main.log("Angles: ", angles.toString());
        main.log("Heading: ", angles.firstAngle);
        main.log();
        main.log("INPUT:");
        main.log("Input Left X: ", inputLX);
        main.log("Input Left Y: ", inputLY);
        main.log("Input Right X: ", inputRX);
        main.log("#########END#########");
    }
    protected void Logger(float leftBackPower, float rightBackPower, float inputLX, float inputLY, float inputRX){
        main.log();
        main.log("########DEBUG########");
        main.log("MOTORS:");
        main.log("Left Back: ", leftBackPower);
        main.log("Right Back: ", rightBackPower);
        main.log();
        main.log("INPUT:");
        main.log("Input Left X: ", inputLX);
        main.log("Input Left Y: ", inputLY);
        main.log("Input Right X: ", inputRX);
        main.log("#########END#########");
    }

}
