package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DcMotor;



/**
 * Created by slistudent on 11/1/17.
 */
@TeleOp(name="Pushbot: Teleop POV", group="Pushbot")

public class HardwarePushbot extends LinearOpMode {

    /* Declare OpMode members. */

    HardwarePushbot robot = new HardwarePushbot();
    // Use a Pushbot's hardware
    // could also use HardwarePushbotMatrix class if wanted.

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

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
            /*if (max > 1.0)
            {
                left /= max;
                right /= max;
            }
            */

            // Output the safe vales to the motor drives.

        }

    }

    /**
     * This is NOT an opmode.
     *
     * This class can be used to define all the specific hardware for a single robot.
     * In this case that robot is a Pushbot.
     * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
     *
     * This hardware class assumes the following device names have been configured on the robot:
     * Note:  All names are lower case and some have single spaces between words.
     *
     Motor channel:  Left  drive motor:        "left_drive"
     Motor channel:  Right drive motor:        "right_drive"
     Motor channel:  Manipulator drive motor:  "left_arm"
     Servo channel:  Servo to open left claw:  "left_hand"
     Servo channel:  Servo to open right claw: "right_hand"
     */


        public static class HardwarePushbot {

        // Public OpMode members.

            public DcMotor  frontLeft    = null;
            public DcMotor  frontRight   = null;
            public DcMotor  backLeft     = null;
            public DcMotor  backRight    = null;

       /* public static final double MID_SERVO     =  0.5 ;
        public static final double ARM_UP_POWER    =  0.45 ;
        public static final double ARM_DOWN_POWER  = -0.45 ;: */

        /* local OpMode members. */
        HardwareMap hwMap           =  null;
        private ElapsedTime period  = new ElapsedTime();

        /* Constructor */
        public HardwarePushbot(){

        }

        /* Initialize standard Hardware interfaces */
        public void init (HardwareMap ahwMap) {
            // Save reference to Hardware map
            hwMap = ahwMap;

            // Define and Initialize Motors
            frontLeft  = hwMap.get(DcMotor.class, "front_left");
            frontRight = hwMap.get(DcMotor.class, "front_right");
            backLeft  = hwMap.get(DcMotor.class, "back_left");
            backRight = hwMap.get(DcMotor.class, "back_right");


            frontLeft.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
            frontRight.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
            backRight.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
            backLeft.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

            // Set all motors to zero power
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);

            // Set all motors to run without encoders.
            // May want to use RUN_USING_ENCODERS if encoders are installed.
            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
     }
}



