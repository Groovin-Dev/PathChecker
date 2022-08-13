import java.io.File

fun main() {
    // Get the system environment
    val env = System.getenv()

    // Try to get the "path" environment variable
    val path = env["Path"]

    // If the "path" environment variable is not set, print an error message
    if (path == null) {
        println("The \"path\" environment variable is not set")
        return
    }

    // Split the "path" environment variable into a list of paths
    // Split by the path separator (; on Windows, : on Unix)
    val paths = path.split(File.pathSeparatorChar)

    // Check all the paths
    val checkedPaths = paths.map { checkFolder(it) }

    // Remove all entries where the path is empty
    val filteredPaths = checkedPaths.filter { it.path.isNotEmpty() }

    // If any of the paths is not a folder, print an error message
    if (filteredPaths.any { !it.exists }) {
        println("Some of the paths are not folders")

        // Print the paths that are not folders
        filteredPaths.forEach {
            if (!it.exists) {
                println(it.path)
            }
        }

        return
    }

    // Print a congratulations message
    println("Congratulations! Your path is clean!")
}

/**
 * CheckFolder
 *
 * This function takes in a path as a parameter and checks if it
 * is a valid folder.
 *
 * @param p The path to check
 * @return a PathValue object containing the path and whether it is a valid folder
 */
fun checkFolder(p: String): PathValue {
    // Create a File object for the path
    val file = File(p)

    return PathValue(p, file.isDirectory)
}

/**
 * PathValue
 *
 * This class is used to store the path and whether it is a valid folder.
 *
 * @param path The path
 * @param exists Whether the path is a valid folder
 */
class PathValue(val path: String, val exists: Boolean)
{
    override fun toString(): String {
        return "Path: $path, Exists: $exists"
    }
}