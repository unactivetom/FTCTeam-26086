package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class Drive {

    protected BNO055IMU imu;
    protected DcMotor leftBack;
    protected DcMotor leftFront;
    protected DcMotor rightBack;
    protected DcMotor rightFront;


    private final MainTeleOp main = new MainTeleOp();

    private boolean brake = true;






    protected Drive(){

    }

    protected void setBrake(boolean value){
        this.brake = value;
        //switching between floating break and break break.
        if(this.brake){
            main.log("Brake setting: Brake");
            leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }else{
            main.log("Brake setting: Float");
            leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
    }
    protected void toggleBrake(){
        setBrake(!getBrake());
    }


    private boolean getBrake() {
        return brake;
    }

    protected void initializer(byte motorCount, HardwareMap hardwareMap){
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        leftBack = hardwareMap.get(DcMotor.class, "LeftBack");
        rightBack = hardwareMap.get(DcMotor.class, "RightBack");

        if(motorCount == 4){
            leftFront = hardwareMap.get(DcMotor.class, "LeftFront");
            rightFront = hardwareMap.get(DcMotor.class, "RightFront");
        }
    }

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
