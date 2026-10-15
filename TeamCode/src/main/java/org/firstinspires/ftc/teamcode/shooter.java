package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "shooter", group = "Linear OpMode")
public class shooter extends LinearOpMode {

    // Hardware declaration
    private DcMotor shooterMotor = null;

    @Override
    public void runOpMode() {
        // Map hardware to configured device name in REV Control Hub
        shooterMotor = hardwareMap.get(DcMotor.class, "shooterMotor");

        // Set direction (REVERSE if the intake runs backwards relative to controller input)
        shooterMotor.setDirection(DcMotor.Direction.FORWARD);

        // Set motor to brake when no power is applied
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized. Ready for Start.");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // Left Trigger -> Intake IN (positive power)
            // Otherwise nothing happens

            double intakeIn = gamepad1.right_trigger;

            // left bumper is boolean, so it would say if (gamepad1.left_bumper)
            // shooter right trigger, intake left trigger, index Y, index reverse X, intake reverse A, shooter reverse B

            if (intakeIn > 0.1) {
                shooterMotor.setPower(intakeIn); // Spins when trigger is pulled
            } else {
                shooterMotor.setPower(0.0);      // Stops only when trigger is released
            }

            telemetry.addData("Shooter Power", shooterMotor.getPower());
            telemetry.update();
        }
    }
}

