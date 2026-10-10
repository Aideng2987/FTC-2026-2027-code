package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "Mecanum Drive Control", group = "Drivetrain")
public class drivetrain extends LinearOpMode {


    @Override
    public void runOpMode() {
        // Initialize motors from the hardware map
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "topLeftWheel");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "bottomLeftWheel");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "topRightWheel");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "bottomRightWheel");


        // Reverse left side motors so positive power moves the robot forward
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);


        telemetry.addData("Status", "Mecanum Drive Initialized");
        telemetry.update();


        // Wait for the driver to press PLAY
        waitForStart();


        // Run until the driver presses STOP
        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Y stick value is reversed by default
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;


            // Mecanum kinematic formulas
            double frontLeftPower = y + x + rx;
            double backLeftPower = y - x + rx;
            double frontRightPower = y - x - rx;
            double backRightPower = y + x - rx;


            // Denominator is the largest motor power (absolute value) or 1.0
            double max = Math.max(Math.abs(frontLeftPower), Math.abs(backLeftPower));
            max = Math.max(max, Math.abs(frontRightPower));
            max = Math.max(max, Math.abs(backRightPower));


            if (max > 1.0) {
                frontLeftPower /= max;
                backLeftPower /= max;
                frontRightPower /= max;
                backRightPower /= max;
            }


            // Send calculated power to wheels
            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);


            telemetry.addData("Drive Vectors", "Y: %.2f, X: %.2f, RX: %.2f", y, x, rx);
            telemetry.update();
        }
    }
}
