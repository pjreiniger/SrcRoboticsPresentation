/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016-2017. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.hal;

import java.io.File;

import com.snobot.simulator.JniLibraryResourceLoader;

/**
 * Base class for all JNI wrappers.
 */
public class JNIWrapper {
  static boolean libraryLoaded = false;
  static File jniLibrary = null;

  static {
        System.out.println("Hello There");
        JniLibraryResourceLoader.loadLibrary("wpilibJNI");
  }

  public static native int getPortWithModule(byte module, byte channel);

  public static native int getPort(byte channel);
}
