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

        // Set motor to brake when no power is applied
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Mecanum Drive Initialized");
        telemetry.update();


        // Wait for the driver to press PLAY
        waitForStart();

        // Run until the driver presses STOP
        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Y stick value is reversed by default
            double x = gamepad1.left_stick_x; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // -1 to 1
            // rotation rx = 1
            // front left set power rx * 100
            // back left set power rx * 100
            // front right set power rx * -100
            // back right set power rx * -100

            // Mecanum kinematic formulas
            double frontLeftPower ;
            double backLeftPower ;
            double frontRightPower ;
            double backRightPower ;

            frontLeftPower = (y+x) + rx;
            backRightPower = (y+x) + rx;
            frontRightPower = (y-x) - rx;
            backLeftPower = (y-x) - rx ;

            //This code just makes sure there  is a maximum value for our wheels
            double m1 = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
            double m2 = Math.max(Math.abs(backLeftPower), Math.abs(backRightPower));
            double normalize = Math.max(m1,m2);
            if(normalize > 1){
            frontLeftPower /= normalize;
            backRightPower /=normalize;
            frontRightPower /= normalize;
            backLeftPower /= normalize;}

            // if motor power >= 100
            // make motor power = 100
            // Send calculated power to wheels

            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(-backLeftPower);
            frontRight.setPower(-frontRightPower);
            backRight.setPower(-backRightPower);


            //This is basically a print statement easier for testing the robot
            telemetry.addData("Drive Vectors", "Y: %.2f, X: %.2f, RX: %.2f", y, x, rx);
            telemetry.addData("Motor Powers", "FrontL: %.2f, FrontR: %.2f, BackL: %.2f, BackR: %.2f",
                    frontLeftPower, frontRightPower, backLeftPower,
                    backRightPower);
            telemetry.addData("other buttons", "LT" , gamepad1.left_trigger_pressed);

            telemetry.update();
        }
    }
}

