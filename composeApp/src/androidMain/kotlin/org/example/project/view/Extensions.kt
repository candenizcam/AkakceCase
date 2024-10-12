package org.example.project.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import java.util.concurrent.Executors

/** I copied the content directly from web but i implemented it as an extension,
 * it loads an image by url for the relevant file
 */
fun ImageView.load(imageURL: String){

    val executor = Executors.newSingleThreadExecutor()
    // Once the executor parses the URL
    // and receives the image, handler will load it
    // in the ImageView
    val handler = Handler(Looper.getMainLooper())
    // Initializing the image
    var image: Bitmap? = null
    // Only for Background process (can take time depending on the Internet speed)
    executor.execute {
        // Image URL
        // Tries to get the image and post it in the ImageView
        // with the help of Handler
        try {
            val `in` = java.net.URL(imageURL).openStream()
            image = BitmapFactory.decodeStream(`in`)
            // Only for making changes in UI
            handler.post {
                this.setImageBitmap(image)
            }
        }
        // If the URL does not point to
        // image or any other kind of failure
        catch (e: Exception) {
            e.printStackTrace()
        }
    }
}