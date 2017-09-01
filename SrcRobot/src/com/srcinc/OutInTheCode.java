package com.srcinc;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Relay;
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
    private SpeedController mRightMotor;
    private SpeedController mLeftMotor;
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

    private Relay mRelay;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit()
    {
        // PWM's
        mRightMotor = new Talon(0);
        mLeftMotor = new Jaguar(1);
        mServo = new Servo(2);

        // Digital IO
        mDigitalOutput = new DigitalOutput(0);
        mRightEncoder = new Encoder(4, 5);
        mLeftEncoder = new Encoder(1, 2);

        // Analog IO
        mAnalogGryo = new AnalogGyro(1);

        // Solenoid
        mSolenoid = new Solenoid(0);

        // Joysticks
        mJoystick1 = new Joystick(0);
        mJoystick2 = new XboxController(1);

        // Utilities
        mTimer = new Timer();
        mPDP = new PowerDistributionPanel();

        mRelay = new Relay(0);
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
            mRightMotor.set(1);
        }
        else
        {
            mRightMotor.set(0);
        }

        updateSmardDashboard();
    }

    protected void updateSmardDashboard()
    {

        SmartDashboard.putNumber("Motor 1", mRightMotor.get());
        SmartDashboard.putNumber("Analog Angle", mAnalogGryo.getAngle());
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic()
    {

    }
}
