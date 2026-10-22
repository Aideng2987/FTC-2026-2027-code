package org.firstinspires.ftc.teamcode;


public class intake extends Container {


    @Override
    public void runOpMode() {
        // Map hardware to configured device name in REV Control Hub

        telemetry.addData("Status", "Initialized. Ready for Start.");
        telemetry.update();
        waitForStart();


        while (opModeIsActive()) {
            // Left Trigger -> Intake IN (positive power)
            // Otherwise nothing happens
            double intakeIn = gamepad1.left_trigger;


            if (intakeIn > 0.1) {
                intakeMotor.setPower(intakeIn); // Spins when trigger is pulled
            } else {
                intakeMotor.setPower(0.0);      // Stops only when trigger is released
            }
            //Servo
            if (gamepad1.left_trigger > 0.1)
            {
                leftIntakeServo.setPosition(1);
                rightIntakeServo.setPosition(1);
            }
            else
            {
                leftIntakeServo.setPosition(0.5);
                rightIntakeServo.setPosition(0.5);
            }


            telemetry.addData("Intake Power", intakeMotor.getPower());
            telemetry.update();

            // add reverse for each motor
        }
    }
}
