package org.example.project.viewmodel

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.example.project.model.SerialStoreData

class KtorViewModel {
    /** Fetches from fakestorepi using native kotlin Ktor functions
     * no error handling included due to the scope of the project
     * runs coroutines in MainScope()
     */

    /** Fetches a single data with id
     * i note that, id starts from 1 not 0
     */
    fun oneData(id: String, onPull: (SerialStoreData)->Unit){
        val url = "https://fakestoreapi.com/products/${id}"

        val client = HttpClient() {
            install(ContentNegotiation) {
                json()
            }
        }

        // This part is async, hence we return nothing and apply output via function
        MainScope().launch {
            val response: HttpResponse = client.get(url)
            val ssd: SerialStoreData = response.body()
            onPull(ssd)
            client.close()
        }
    }

    /** fetches many data, either with or without limit
     * if limit is larger than one, it is used, if not, it fetches all the data
     */
    fun manyData(limit: Int = 0, onPull: (ArrayList<SerialStoreData>)->Unit){
        // I build url manually because it is simpler for this scope
        val url= if (limit<=0){
            "https://fakestoreapi.com/products"
        }else{
            "https://fakestoreapi.com/products?limit=${limit}"
        }

        val client = HttpClient() {
            install(ContentNegotiation) {
                json()
            }
        }

        // Same as oneData
        MainScope().launch {
            val response: HttpResponse = client.get(url)
            val ssd: ArrayList<SerialStoreData> = response.body()
            onPull(ssd)
            client.close()
        }

    }



}