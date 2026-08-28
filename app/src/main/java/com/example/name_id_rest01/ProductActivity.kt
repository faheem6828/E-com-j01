package com.example.name_id_rest01

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.name_id_rest01.adapter.ProductAdapter
import com.example.name_id_rest01.databinding.ActivityProductBinding
import com.example.name_id_rest01.network.ApiClient
import kotlinx.coroutines.launch

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        loadProducts()

        binding.btnRetry.setOnClickListener {
            loadProducts()
        }

        binding.btnTopRetry.setOnClickListener {
            loadProducts()
        }
    }

    private fun setupRecyclerView() {

        productAdapter = ProductAdapter(emptyList())

        binding.recyclerViewProducts.apply {
            layoutManager = LinearLayoutManager(this@ProductActivity)
            adapter = productAdapter
        }
    }

    private fun loadProducts() {

        lifecycleScope.launch {

            binding.progressBar.visibility = View.VISIBLE
            binding.errorContainer.visibility = View.GONE

            try {

                val products = ApiClient.api.getProducts()

                productAdapter.updateProducts(products)

            } catch (e: Exception) {

                e.printStackTrace()

                binding.errorContainer.visibility = View.VISIBLE

            } finally {

                binding.progressBar.visibility = View.GONE
            }
        }
    }
}