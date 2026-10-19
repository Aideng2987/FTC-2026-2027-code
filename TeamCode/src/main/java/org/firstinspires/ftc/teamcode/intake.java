package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "intake", group = "Linear OpMode")
public class intake extends LinearOpMode {

    // Hardware declaration
    private DcMotor intakeMotor = null;

    @Override
    public void runOpMode() {
        // Map hardware to configured device name in REV Control Hub
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        //servo


        // Set direction (REVERSE if the intake runs backwards relative to controller input)
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);

        // Set motor to brake when no power is applied
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized. Ready for Start.");
        telemetry.update();

        waitForStart();
/*
        while (opModeIsActive()) {
            // Left Trigger -> Intake IN (positive power)
            // Otherwise nothing happens
            double intakeIn;

            if (intakeIn > 0.1) {
                intakeMotor.setPower(intakeIn); // Spins when trigger is pulled
            } else {
                intakeMotor.setPower(0.0);      // Stops only when trigger is released
            }

            telemetry.addData("Intake Power", intakeMotor.getPower());
            telemetry.update();

            // add reverse for each motor
        }*/
    }
}

