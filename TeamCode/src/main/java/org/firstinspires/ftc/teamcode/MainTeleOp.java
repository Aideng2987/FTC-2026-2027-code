package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Main Full TeleOp", group = "Main")
public class MainTeleOp extends Container {

    @Override
    public void runOpMode() {
        // 1. Initialize all motors/hardware mapped in Container
        initHardware();

        telemetry.addData("Status", "Robot Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // 2. Drive using the helper method in Container
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;
            driveRobot(y,x,rx);

            // 3. Intake using the helper method in Container
            if (gamepad1.left_trigger > 0.1) {
                Intake(gamepad1.left_trigger);
            } else if (gamepad1.dpad_down) {
                Intake(-0.8);
            } else {
                Intake(0.0);
            }

            // 4. Shooter using the helper method in Container
            if (gamepad1.right_trigger > 0.1) {
                shoot(gamepad1.right_trigger);
            } else if (gamepad1.dpad_up) {
                shoot(-0.5);
            } else {
                shoot(0.0);
            }

            telemetry.update();
        }
    }
}