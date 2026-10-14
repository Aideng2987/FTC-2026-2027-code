package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "index", group = "Linear OpMode")
public class index extends LinearOpMode {

    // Hardware declaration
    private DcMotor indexMotor = null;

    @Override
    public void runOpMode() {
        // Map hardware to configured device name in REV Control Hub
        indexMotor = hardwareMap.get(DcMotor.class, "indexMotor");

        // Set direction (REVERSE if the intake runs backwards relative to controller input)
        indexMotor.setDirection(DcMotor.Direction.FORWARD);

        // Set motor to brake when no power is applied
        indexMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized. Ready for Start.");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // Left Trigger -> Intake IN (positive power)
            // Otherwise nothing happens
            double intakeIn = gamepad1.a;

            if (intakeIn > 0.1) {
                indexMotor.setPower(intakeIn); // Spins when trigger is pulled
            } else {
                indexMotor.setPower(0.0);      // Stops only when trigger is released
            }

            telemetry.addData("Index Power", indexMotor.getPower());
            telemetry.update();
        }
    }
}

