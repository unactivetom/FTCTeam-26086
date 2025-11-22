package org.firstinspires.ftc.teamcode.Autonomous.DECODE;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Tank {

    DcMotor leftBack;
    DcMotor rightBack;

    public Tank(){

    }

    public void init(HardwareMap hardwareMap){
        leftBack = hardwareMap.get(DcMotor.class, "LeftBack");
        rightBack = hardwareMap.get(DcMotor.class, "RightBack");
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void driveOn(){
        leftBack.setPower(0.3);
        rightBack.setPower(0.3);

    }

    public void driveOff(){
        leftBack.setPower(0.0);
        rightBack.setPower(0.0);
    }


}
