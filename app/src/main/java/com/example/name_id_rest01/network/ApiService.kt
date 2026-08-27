package com.example.name_id_rest01.network

import com.example.name_id_rest01.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}