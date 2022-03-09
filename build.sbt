ThisBuild / organization := "ch.unibas.cs.gravis"
ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / crossPaths := false
ThisBuild / autoScalaLibrary := false
ThisBuild / versionScheme := Some("semver-spec")
ThisBuild / homepage :=  Some(url("https://scalismo.org"))
ThisBuild / licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
ThisBuild / scmInfo := Some(
      ScmInfo(url("https://github.com/unibas-gravis/hdf5-java-natives"), "git@github.com:unibas-gravis/hdf5-java-natives.git")
    )
ThisBuild / developers := List(
      Developer("marcelluethi", "marcelluethi", "marcel.luethi@unibas.ch", url("https://github.com/marcelluethi"))
    )
ThisBuild / publishMavenStyle := true
ThisBuild / publishTo := Some(Opts.resolver.sonatypeSnapshots)


/*
 * dummy package to manage hdf5.jar
 */   
lazy val hdf5Jar = (project in file("hdf5Jar"))
    .settings(
        name := "hdf5Jar",
        Compile / packageBin := baseDirectory.value / "lib"/ "jhdf.jar"
    )

/*
 * Module definition and library loading logic. Does not contain any native libraries
 */
lazy val hdf5JavaNatives = (project in file("hdf5nativelibs"))
.dependsOn(hdf5Jar)
.aggregate(hdf5Jar)
.settings(
    javacOptions ++= Seq("--release", "8") 
)


/*
 * Main module (only used for testing) 
 */ 
lazy val root = (project in file("."))
    .aggregate(hdf5JavaNatives)
    .dependsOn(hdf5JavaNatives)


