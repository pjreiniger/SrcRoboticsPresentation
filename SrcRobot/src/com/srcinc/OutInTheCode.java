package com.srcinc;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OutInTheCode extends IterativeRobot
{
    //////////////////////////////////////
    // Outputs
    //////////////////////////////////////

    // PWM
    private SpeedController mTestMotor1;
    private SpeedController mTestMotor2;
    private Servo mServo;

    // Digital IO
    private DigitalOutput mDigitalOutput;

    // Solenoid
    private Solenoid mSolenoid;

    //////////////////////////////////////
    // Inputs
    //////////////////////////////////////

    // Joysticks
    private Joystick mJoystick1;
    private XboxController mJoystick2;

    // Digital IO
    private Encoder mRightEncoder;
    private Encoder mLeftEncoder;

    // Analog IO
    private Gyro mAnalogGryo;
    private Potentiometer mPotentiometer;

    // Utilities
    private PowerDistributionPanel mPDP;
    private Timer mTimer;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit()
    {
        // PWM's
        mTestMotor1 = new Talon(0);
        mTestMotor2 = new Jaguar(1);
        mServo = new Servo(2);

        // Digital IO
        mDigitalOutput = new DigitalOutput(0);
        mRightEncoder = new Encoder(4, 5);
        mLeftEncoder = new Encoder(1, 2);

        // Analog IO
        mAnalogGryo = new AnalogGyro(0);
        mPotentiometer = new AnalogPotentiometer(1);

        // Solenoid
        mSolenoid = new Solenoid(0);

        // Joysticks
        mJoystick1 = new Joystick(0);
        mJoystick2 = new XboxController(1);

        // // SPI
        // mSpiGryo = new ADXRS450_Gyro();

        // Utilities
        mTimer = new Timer();
        mPDP = new PowerDistributionPanel();
    }

    public void autonomousInit()
    {
        mTimer.start();
        mRightEncoder.reset();
        mLeftEncoder.reset();
    }

    public void autonomousPeriodic()
    {
        if (mTimer.get() < 2)
        {
            mSolenoid.set(true);
        }
        else
        {
            mSolenoid.set(false);
        }

        updateSmardDashboard();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic()
    {
        if (mJoystick1.getRawButton(1))
        {
            mTestMotor1.set(1);
        }
        else
        {
            mTestMotor1.set(0);
        }

        updateSmardDashboard();
    }

    protected void updateSmardDashboard()
    {

        SmartDashboard.putNumber("Motor 1", mTestMotor1.get());
        SmartDashboard.putNumber("Analog Angle", mAnalogGryo.getAngle());
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic()
    {

    }
}
