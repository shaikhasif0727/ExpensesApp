import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependecy:String){
    add("implementation",dependecy)
}

fun DependencyHandler.implementation(dependency: Dependency){
    add("implementation",dependency)
}

fun DependencyHandler.test(dependecy:String){
    add("test",dependecy)
}

fun DependencyHandler.androidTest(dependecy:String){
    add("androidTest",dependecy)
}

fun DependencyHandler.debugImplementation(dependecy:String){
    add("debugImplementation",dependecy)
}

fun DependencyHandler.kapt(dependecy:String){
    add("kapt",dependecy)
}