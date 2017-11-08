package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by slistudent on 11/1/17.
 */
@TeleOp(name="HoloTele", group="Pushbot")

public class Holocodo extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbot robot = new HardwarePushbot();   // Use a Pushbot's hardware
    // could also use HardwarePushbotMatrix class.

    @Override
    public void runOpMode() {
        float R;
        float X;
        float Y;
        float FLvalue;
        float FRvalue;
        float BLvalue;
        float BRvalue;
        double max;


        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Run wheels in POV mode (note: The joystick goes negative when pushed forwards, so negate it)
            // In this mode the Left stick moves the robot fwd and back, the Right stick turns left and right.
            // This way it's also easy to just drive straight, or just turn.
            R = gamepad1.right_stick_x;
            X = gamepad1.left_stick_x;
            Y = gamepad1.left_stick_y;

            // Combine drive and turn for blended motion.
            FLvalue = -Y + X + R;
            FRvalue = +Y + X + R;
            BLvalue = -Y - X + R;
            BRvalue = +Y - X + R;

            //maxes the values at 1
            FLvalue = Range.clip(FLvalue, -1, 1);
            FRvalue = Range.clip(FRvalue, -1, 1);
            BLvalue = Range.clip(BLvalue, -1, 1);
            BRvalue = Range.clip(BRvalue, -1, 1);

            robot.frontLeft.setPower(FLvalue);
            robot.frontRight.setPower(FRvalue);
            robot.backLeft.setPower(BLvalue);
            robot.backRight.setPower(BRvalue);


            //Normalize the values so neither exceed +/- 1.0
            //max = Math.max(Math.abs(left), Math.abs(right));
            /*=if (max > 1.0)
            {
                left /= max;
                right /= max;
            }
            */

            // Output the safe vales to the motor drives.

        }

    }

}



