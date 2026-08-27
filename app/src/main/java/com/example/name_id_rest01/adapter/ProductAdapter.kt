package com.example.name_id_rest01.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.name_id_rest01.databinding.ItemProductsBinding
import com.example.name_id_rest01.model.Product

class ProductAdapter(
    private var productList: List<Product>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(
        private val binding: ItemProductsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {

            // Product title
            binding.tvProductName.text = product.title

            // Product price
            binding.tvProductPrice.text = "$${product.price}"
            binding.tvProductCategory.text = product.category.name

            // Product image
            val imageUrl = product.images.firstOrNull()

            Glide.with(binding.ivProduct.context)
                .load(imageUrl)
                .into(binding.ivProduct)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {

        val binding = ItemProductsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateProducts(newProducts: List<Product>) {
        productList = newProducts
        notifyDataSetChanged()
    }
}