package birth.h3.app.curl_kusegeapp.ui.weather

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.model.entity.Weather
import birth.h3.app.curl_kusegeapp.model.entity.Icon
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
) : ViewModel() {
    val weather: MutableLiveData<Weather> = MutableLiveData()
    val icon: MutableLiveData<Icon> = MutableLiveData()
    val submitImages : MutableLiveData<List<Int>> = MutableLiveData<List<Int>>()
    val statusImage: MutableLiveData<Int> = MutableLiveData()
    val kusegeColor: MutableLiveData<Int> = MutableLiveData()

    init {
        weather.value = Weather(0,
                "-",
                0,
                0,
                0,
                0,
                0,
                1,
                "よみこみ中...")
        icon.value = Icon(3)
        submitImages.value = listOf(R.drawable.men_streat,
                                    R.drawable.men_curl,
                                    R.drawable.men_very_curl)
        statusImage.value = R.drawable.men_curl
    }

    fun setWeather(weather: Weather){
        this.weather.value = weather
        this.icon.value = Icon(weather.weather)
        this.statusImage.value = when(weather.kusege){
            1 -> R.drawable.men_streat
            2 -> R.drawable.men_curl
            3 -> R.drawable.men_very_curl
            else -> R.drawable.men_curl
        }
    }

    fun setColorHex(context:Context, colorID: Int){
        this.kusegeColor.value = Color.parseColor("#" + Integer.toHexString(ContextCompat.getColor(context, colorID)))
    }
}