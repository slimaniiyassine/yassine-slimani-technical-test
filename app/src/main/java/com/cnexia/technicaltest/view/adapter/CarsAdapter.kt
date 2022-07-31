package com.cnexia.technicaltest.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cnexia.technicaltest.databinding.CarRecyclerViewItemBinding
import com.cnexia.technicaltest.view.data.RecyclerViewItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CarsAdapter @Inject constructor() : RecyclerView.Adapter<MainViewHolder>() {

    private var cars = listOf<RecyclerViewItem>()

    fun setLocationList(cars: List<RecyclerViewItem>) {
        this.cars = cars
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = CarRecyclerViewItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val car = cars[position]
        holder.binding.recyclerViewItem = car
    }

    override fun getItemCount(): Int {
        return cars.size
    }
}

class MainViewHolder(val binding: CarRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root)