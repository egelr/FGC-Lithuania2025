package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Variables;

@TeleOp(name = "TeleOpMode")
public class OpMode extends LinearOpMode {


    private Motor fL, fR, bL, bR, hangFront, hangBack, spin;
    //Creating drive speed variable
    public double drive_speed = 1;

    @Override
    public void runOpMode() throws InterruptedException {

        //Creating Drivetrain Motors and Setting their behaviour to "brake"
        fL = new Motor(hardwareMap, "leftFront", 28, 500);
        fR = new Motor(hardwareMap, "rightFront", 28, 500);
        bL = new Motor(hardwareMap, "leftBack", 28, 500);
        bR = new Motor(hardwareMap, "rightBack", 28, 500);

        hangFront = new Motor(hardwareMap, "hangFront", 28, 300);
        hangBack = new Motor(hardwareMap, "hangBack", 28, 300);

        spin = new Motor(hardwareMap, "spin", 28, 300);

        hangBack.setInverted(true);
        
        fL.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        bR.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        hangFront.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        hangBack.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        //Creating the Mecanum Drivetrain
        MecanumDrive drive = new MecanumDrive(fL, fR, bL, bR);

        //Creating timer variables
        ElapsedTime timer = new ElapsedTime();

        waitForStart();

        while (!isStopRequested()) {

            //Drivetrain controls
            drive.driveRobotCentric(
                    gamepad1.left_stick_x * drive_speed,
                    -gamepad1.left_stick_y * drive_speed,
                    gamepad1.right_stick_x * drive_speed,
                    false

            );
            //Drivetrain Motors speed Change controls
            if (gamepad1.right_trigger > 0.5) {
                drive_speed = 0.45;
            } else {
                drive_speed = 1;
            }

            if(gamepad1.dpad_up){
                hangBack.set(1);
                hangFront.set(1);
            }
            if(gamepad1.dpad_down){
                hangBack.set(-1);
                hangFront.set(-1);
            }
            if(gamepad1.circle){
                spin.set(1);
            }

        }

    }
}
