ThisBuild / organization := "ch.unibas.cs.gravis"
ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / crossPaths := false
ThisBuild / autoScalaLibrary := false
ThisBuild / versionScheme := Some("semver-spec")
ThisBuild / homepage :=  Some(url("https://scalismo.org"))
ThisBuild / licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))

ThisBuild / developers := List(
      Developer("marcelluethi", "marcelluethi", "marcel.luethi@unibas.ch", url("https://github.com/marcelluethi"))
    )
ThisBuild / publishMavenStyle := true
ThisBuild / publishTo := Some(Opts.resolver.sonatypeSnapshots)

/*
 * dummy package to manage itk.jar
 */   
lazy val itkJar = (project in file("itkJar"))
    .settings(
        name := "itkJar",
        Compile / packageBin := baseDirectory.value / "lib"/ "simpleitk-2.2.0rc2.post35-g8c184.jar"
    )

/*
 * Module definition and library loading logic. Does not contain any native libraries
 */
lazy val itkJavaLibraries = (project in file("itknativelibs"))
.dependsOn(itkJar)
.aggregate(itkJar)
.settings(
    javacOptions ++= Seq("--release", "8")
)


/*
 * Main module (only used for testing)
 */
lazy val root = (project in file("."))
    .aggregate(itkJavaLibraries)
    .dependsOn(itkJavaLibraries)


