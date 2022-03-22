package main;

import java.io.File;

import ch.unibas.cs.gravis.itknativelibs.*;
import org.itk.simple.Image;
import org.itk.simple.SimpleITK;

public class Main {

    public static void main(String[] args) {

        File nativeDir = new File(System.getProperty("user.home") + File.separator +".nativelibs");
        try {
            ITKNativeLibs.initialize(nativeDir);
        } catch (ITKNativeLibsException e) {
            System.out.println("could not initialize itk library. " +e.getMessage());
        }

        System.out.println(System.getProperty("java.library.path"));

        try {
            // If these calls works, the library was properly loaded
            // Grab a file
            Image image = SimpleITK.readImage("/home/gerith00/Downloads/_R7B3351.JPG");
            Image output = SimpleITK.discreteGaussian(image);
            SimpleITK.writeImage(output, "dot.dcm");
            System.out.println("Test");
        } catch (Exception e) {
            System.out.println("Something has gone wrong " +e.getMessage());
        }
    }
}
