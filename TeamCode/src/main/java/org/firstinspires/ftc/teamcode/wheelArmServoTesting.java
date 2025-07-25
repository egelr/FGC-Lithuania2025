package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Variables;

@TeleOp(name = "TestingWheelArm")
public class wheelArmServoTesting extends LinearOpMode {

    CRServo ballIntakeServoLeft, ballIntakeServoRight;
    double power;

    @Override
    public void runOpMode() throws InterruptedException {
        ballIntakeServoLeft = hardwareMap.crservo.get("ballIntakeServoLeft");
        ballIntakeServoRight = hardwareMap.crservo.get("ballIntakeServoRight");

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.circle) {
                ballIntakeServoLeft.setPower(1);
                ballIntakeServoRight.setPower(-1);
            }
            if (gamepad1.triangle) {
                ballIntakeServoLeft.setPower(-1);
                ballIntakeServoRight.setPower(1);
            }
            // Add telemetry for debugging if needed
            telemetry.addData("Servo Power", power);
            telemetry.update();
        }
    }
}