import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

class TachiyomiExtensionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.create("tachiyomiExtension", TachiyomiExtensionExtension::class.java)
    }
}
