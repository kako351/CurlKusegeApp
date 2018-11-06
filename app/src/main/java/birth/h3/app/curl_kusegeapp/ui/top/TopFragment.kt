package birth.h3.app.curl_kusegeapp.ui.top


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import birth.h3.app.curl_kusegeapp.R
import kotlinx.android.synthetic.main.fragment_top.*

/**
 * A simple [Fragment] subclass.
 *
 */
class TopFragment : Fragment() {

    val TAG = "top"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // タイトル設定
        app_toolbar.title = resources.getString(R.string.app_name)
        (activity as AppCompatActivity).setSupportActionBar(app_toolbar)

        val fragmentManager: android.support.v4.app.FragmentManager = childFragmentManager
        vp_weather.adapter = TopPagerAdapter(fragmentManager)

        tl_area.setupWithViewPager(vp_weather)
    }
}
