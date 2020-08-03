package com.jobin.weatherapp.presentation.ui.landing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jobin.weatherapp.R
import com.jobin.weatherapp.domain.model.WeatherForcast
import com.jobin.weatherapp.presentation.ui.util.Utility
import kotlinx.android.synthetic.main.item_forcast_item.view.*
import java.util.*

class WeatherForcastAdapter  : RecyclerView.Adapter<WeatherForcastAdapter.WeatherForecastViewHolder>() {

    private var items: List<WeatherForcast> = ArrayList()
    private var listener: ((WeatherForcast) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forcast_item, parent, false)
        return WeatherForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherForecastViewHolder, position: Int) {
        val weather = items[position]
        holder.bind(weather)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setWeatherClickListener(listener: (WeatherForcast) -> Unit) {
        this.listener = listener
    }

    fun setItems(items: List<WeatherForcast>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class WeatherForecastViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(weatherForcast: WeatherForcast) {
            itemView.apply {
                tv_date.text = weatherForcast.dtTxt
                iv_weather_icon.setImageResource(Utility.getWeatherConditionImage(weatherForcast.id))
                tv_weather_description.text = weatherForcast.main
                tv_high_temperature.text = Utility.convertKelvinToCelsius(context, weatherForcast.tempMax)
                tv_low_temperature.text = Utility.convertKelvinToCelsius(context, weatherForcast.tempMin)
                setOnClickListener { listener?.invoke(weatherForcast) }
            }
        }
    }
}