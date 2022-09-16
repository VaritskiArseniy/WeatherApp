package com.example.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ListItemBinding
import com.squareup.picasso.Picasso

class WeatherAdapter(val listener: Listener?): ListAdapter<WeatherModel,WeatherAdapter.Holder>(Comparetor()) {

    class Holder(view: View,val listener: Listener?): RecyclerView.ViewHolder(view){
        val binding = ListItemBinding.bind(view)
        var itemTemp: WeatherModel? = null

        init {
            itemView.setOnClickListener {
                itemTemp?.let { it1 -> listener?.onClick(it1) }

            }
        }

        fun bind(item: WeatherModel) = with(binding){
            itemTemp = item
            tvData.text = item.time
            tvTemp.text = item.currentTemp.ifEmpty {"${item.maxTemp}°C/${item.minTemp}°C"}
            tvCondition.text = item.condition
            Picasso.get().load("https:" + item.imageUrl).into(im)

        }
    }
    class Comparetor : DiffUtil.ItemCallback<WeatherModel>(){
        override fun areItemsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return Holder(view , listener)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        return holder.bind(getItem(position))
    }
    interface Listener{
        fun onClick(item: WeatherModel)
    }
}