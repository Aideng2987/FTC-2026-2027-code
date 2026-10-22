package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "shooter", group = "Linear OpMode")
public class shooter extends Container {

    // Hardware declaration

    @Override
    public void runOpMode() {
        // Map hardware to configured device name in REV Control Hub
        waitForStart();

        while (opModeIsActive()) {
            // Left Trigger -> Intake IN (positive power)
            // Otherwise nothing happens

            double shoot = gamepad1.right_trigger;

            // left bumper is boolean, so it would say if (gamepad1.left_bumper)
            // shooter right trigger, intake left trigger, index Y, index reverse X, intake reverse A, shooter reverse B

            if (shoot > 0.1) {
                shooterMotor.setPower(shoot); // Spins when trigger is pulled
            } else {
                shooterMotor.setPower(0.0);      // Stops only when trigger is released
            }

            telemetry.addData("Shooter Power", shooterMotor.getPower());
            telemetry.update();
        }
    }
}

