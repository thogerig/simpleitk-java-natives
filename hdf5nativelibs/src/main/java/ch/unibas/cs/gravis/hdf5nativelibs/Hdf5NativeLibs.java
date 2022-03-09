/*
 * Copyright 2022 University of Basel, Graphics and Vision Research Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.unibas.cs.gravis.hdf5nativelibs;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class Hdf5NativeLibs {
   
    public static final String MAJOR_VERSION = "0";
    public static final String MINOR_VERSION = "1";


    /**
      * Extracts the hdf5 native libraries to nativeLibDir (if necessary) and loads them. 
      *
      * @param nativeLibraryBaseDirectory A directory into which the nativelibs will be extracted
      */
    public static void initialize(File nativeLibraryBaseDirectory) throws Hdf5NativeLibsException {        

        String platform =  System.getProperty("os.name").trim().toLowerCase();
        File directory = Util.createNativeDirectory(nativeLibraryBaseDirectory);
        String nativeName = System.mapLibraryName("jhdf5");
        URL url = Hdf5NativeLibs.class.getResource(nativeName);
        
        if (url == null) {
            throw new Hdf5NativeLibsException("Unable to load resource "
                    + nativeName + " for platform " + platform);
        }
        
        File file = new File(directory, nativeName.substring(nativeName.lastIndexOf('/') + 1, nativeName.length()));
        try {
            Util.copyUrlToFile(url, file);
        } catch (IOException e) {
            throw new Hdf5NativeLibsException("Error while copying library " + nativeName, e);
        }
        
        Runtime.getRuntime().load(file.getAbsolutePath());
     
        System.setProperty("ncsa.hdf.hdf5lib.H5.hdf5lib", file.getAbsolutePath());

    }
}
