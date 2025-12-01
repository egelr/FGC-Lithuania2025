package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.SerialNumber;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "TeleOpModeFieldCentric")
public class OpModeField extends LinearOpMode {


    private Motor fL, fR, bL, bR, hangFront, hangBack, spin;

    private IMU m_imu;

    double power;

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

        Servo armServoLeft = hardwareMap.get(Servo.class, "armServoLeft");
        Servo armServoRight = hardwareMap.get(Servo.class, "armServoRight");
        Servo miniArmServoRight = hardwareMap.get(Servo.class, "miniArmServoRight");
        Servo miniArmServoLeft = hardwareMap.get(Servo.class, "miniArmServoLeft");


        //Creating the Mecanum Drivetrain
        MecanumDrive drive = new MecanumDrive(fL, fR, bL, bR);

        m_imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters= new IMU.Parameters(new RevHubOrientationOnRobot(
             RevHubOrientationOnRobot.LogoFacingDirection.DOWN,
             RevHubOrientationOnRobot.UsbFacingDirection.LEFT
        ));
        m_imu.initialize(parameters);
        m_imu.resetYaw();

        //Creating timer variables
        ElapsedTime timer = new ElapsedTime();

        waitForStart();

        while (!isStopRequested()) {
            double heading = m_imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

            //Drivetrain controls
            drive.driveFieldCentric(
                    gamepad1.left_stick_x * drive_speed,
                    -gamepad1.left_stick_y * drive_speed,
                    gamepad1.right_stick_x * drive_speed,
                    heading

            );
            //Drivetrain Motors speed Change controls
            if (gamepad1.right_trigger > 0.5) {
                drive_speed = 0.35;
            } else {
                drive_speed = 1;
            }

            if(gamepad1.dpad_up){
                hangBack.set(1);
                hangFront.set(1);
            }
            if(gamepad1.dpad_down){
                hangBack.set(-0.1);
                hangFront.set(-0.1);
            }
            if(gamepad1.square){
                spin.set(1);
            }

            if(gamepad1.dpad_left){
                armServoLeft.setPosition(0.82);
                armServoRight.setPosition(0.20);
            }

            if(gamepad1.dpad_right){
                armServoLeft.setPosition(0.05);
                armServoRight.setPosition(1);
            }
            //reset arba isjungt viska
            if(gamepad1.guide && gamepad1.left_trigger < 0.8){
                armServoLeft.setPosition(0.82);
                armServoRight.setPosition(0.20);
                hangBack.set(0);
                hangFront.set(0);
                spin.set(0);
                miniArmServoLeft.setPosition(0.90);
                miniArmServoRight.setPosition(0.1);
            }
            if(gamepad1.guide && gamepad1.left_trigger > 0.8){
                armServoLeft.setPosition(0.8);
                armServoRight.setPosition(0.2);
                miniArmServoLeft.setPosition(0.90);
                miniArmServoRight.setPosition(0.1);
                hangBack.set(0.1);
                hangFront.set(0.1);
                spin.set(0);
            }
            if(gamepad1.share){
                armServoRight.setPosition(0.85);
            }
            if(gamepad1.options){
                armServoLeft.setPosition(0.15);
            }
            if(gamepad1.circle){
                miniArmServoLeft.setPosition(0.12);
                miniArmServoRight.setPosition(0.88);
            }
            if(gamepad1.triangle){
                miniArmServoLeft.setPosition(0.25);
                miniArmServoRight.setPosition(0.75);
            }


        }

    }
}
