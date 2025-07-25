package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class testingSensor extends LinearOpMode {
    // Define variables for our touch sensor and motor
    TouchSensor test_magnetic;

    public boolean STATE;
    public int COUNT, RPM, COUNTRPM;

    ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() {
        // Get the touch sensor and motor from hardwareMap
        test_magnetic = hardwareMap.get(TouchSensor.class, "test_magnetic");

        // Wait for the play button to be pressed
        waitForStart();

        timer.reset();

        // Loop while the Op Mode is running
        while (opModeIsActive()) {
            RPM = COUNT*12;
            if (test_magnetic.isPressed()) {
                STATE = true;
                COUNT = COUNT + 1;
                sleep(50);
            } else {
                STATE = false;
            }

            if (timer.seconds() > 5)
            {
                timer.reset();
                COUNT = 0;
            }


            telemetry.addData("STATE: ", STATE);
            telemetry.addData("RPM: ", RPM);
            telemetry.addData("COUNT: ", COUNT);
            telemetry.update();
        }
    }
}