package birth.h3.app.curl_kusegeapp.ui.weather

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.BR
import birth.h3.app.curl_kusegeapp.model.entity.TimeWeather

class TimeWeatherViewHolder(
        private val binding: ViewDataBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(timeWeather: TimeWeather) {
        binding.setVariable(BR.timeWeather, timeWeather)
    }

    companion object {
        fun create(parent: ViewGroup) = TimeWeatherViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_time_weather, parent,
                        false
                )
        )
    }
}