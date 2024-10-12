package org.example.project

/** This class, and related functions are created with project
 * they are not used but i kept them for future reference
 *
 */
class Greeting {

    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}