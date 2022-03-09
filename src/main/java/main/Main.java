package main;

import java.io.File;

import ch.unibas.cs.gravis.hdf5nativelibs.*;
import ncsa.hdf.object.*;
import ncsa.hdf.object.h5.*;

public class Main {
    
    


    public static void main(String[] args) {

        File nativeDir = new File(System.getProperty("user.home") + File.separator +".nativelibs");
        try {
            Hdf5NativeLibs.initialize(nativeDir);
        } catch (Hdf5NativeLibsException e) {
            System.out.println("could not initialize hdf5 library. " +e.getMessage());
        }

        try {
            // If these calls works, the library was properly loaded
            FileFormat fileFormat = FileFormat.getFileFormat(FileFormat.FILE_TYPE_HDF5);
            FileFormat h5file = fileFormat.createInstance("x.h5", FileFormat.READ);
        } catch (Exception e) {
            System.out.println("Something has gone wrong " +e.getMessage());
        }
    }
}
