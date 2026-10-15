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
        ;

        // Wait for the driver to press PLAY
        waitForStart();

        // Run until the driver presses STOP
        while (opModeIsActive()) {
            double y = gamepad1.left_stick_y; // Y stick value is reversed by default
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // -1 to 1
            // rotation rx = 1
            // front left set power rx * 100
            // back left set power rx * 100
            // front right set power rx * -100
            // back right set power rx * -100

            // Mecanum kinematic formulas
            double frontLeftPower = (y + x)/2 ;
            double backLeftPower = (y - x)/2 ;
            double frontRightPower = (y - x)/2 ;
            double backRightPower = (y + x)/2 ;
            if(Math.abs(frontLeftPower) > 1){
                if(frontLeftPower < 0){
                    frontLeftPower= -1;
                }
                else{
                    frontLeftPower = 1;
                }
                frontLeftPower = backRightPower;
            }
            if(Math.abs(backLeftPower) > 1){
                if(backLeftPower < 0){
                    backLeftPower = -1;
                }
                else{
                    backLeftPower = 1;
                }
                backLeftPower = frontRightPower;
            }

            // if motor power >= 100
            // make motor power = 100
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
