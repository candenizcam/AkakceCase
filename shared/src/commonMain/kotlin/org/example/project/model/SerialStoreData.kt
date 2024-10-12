package org.example.project.model

import kotlinx.serialization.Serializable

/** Model for a single data
 * I use kotlinx.Serializable to represent Json, this implementation is name sensitive
 * I implemented rating as a subclass because only SerialStoreData refers to it
 */
@Serializable
data class SerialStoreData (val id: String,
                            val title: String,
                            val price: Float,
                            val description: String,
                            val category: String,
                            val image: String,
                            val rating: SimpleRating
){
    @Serializable
    class SimpleRating(val rate: Float, val count: Int)


}