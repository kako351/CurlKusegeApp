package birth.h3.app.curl_kusegeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import birth.h3.app.curl_kusegeapp.databinding.ActivityMainBinding
import birth.h3.app.curl_kusegeapp.model.enums.HairStatus
import birth.h3.app.curl_kusegeapp.ui.chart.ChartFragment
import birth.h3.app.curl_kusegeapp.ui.mydata.MyDataFragment
import birth.h3.app.curl_kusegeapp.ui.news.NewsFragment
import birth.h3.app.curl_kusegeapp.ui.setting.SettingFragment
import birth.h3.app.curl_kusegeapp.ui.signup.SignUpActivity
import birth.h3.app.curl_kusegeapp.ui.top.TopFragment
import birth.h3.app.curl_kusegeapp.ui.util.BottomNavigationViewHelper
import birth.h3.app.curl_kusegeapp.ui.util.UtilDateTime
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as CurlApp).component.inject(this)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        supportFragmentManager.beginTransaction()
                .add(R.id.container, TopFragment(), TopFragment().TAG)
                .commit()
        setBottomNavigtionOption()

        floatingActionButton.setOnClickListener { animator.showNext() }

        onSubmitClickLister()

        findViewById<Button>(R.id.btn_signup).setOnClickListener {
            val intent = Intent(this.application, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    fun setBottomNavigtionOption() {
        val bottomNavigationViewHelper = BottomNavigationViewHelper()
        bottomNavigationViewHelper.disableShiftMode(bottom_navigation)
        bottom_navigation.setOnNavigationItemSelectedListener  {
            val fgManager = supportFragmentManager
            val fgTransaction = supportFragmentManager.beginTransaction()
            val fragments: List<androidx.fragment.app.Fragment>? = fgManager?.fragments
            var fragment: androidx.fragment.app.Fragment? = null
            var fragmentTag: String = ""

            when (it.itemId) {
                R.id.nav_top -> {
                    fragment    = TopFragment()
                    fragmentTag = TopFragment().TAG
                }
                R.id.nav_mydata -> {
                    fragment    = MyDataFragment()
                    fragmentTag = MyDataFragment().TAG
                }
                R.id.nav_news -> {
                    fragment    = NewsFragment()
                    fragmentTag = NewsFragment().TAG
                }
                R.id.nav_setting -> {
                    fragment    = SettingFragment()
                    fragmentTag = SettingFragment().TAG
                }
                else -> null
            }

            var alreadyFragment = fgManager.findFragmentByTag(fragmentTag)

            if( fragments!!.size > 0 ) {
                fragments.forEach {
                    fgTransaction.hide(it)
                }
            }

            if( alreadyFragment == null ){
                fgTransaction.add(R.id.container, fragment!!, fragmentTag)
            }else{
                fgTransaction.show(alreadyFragment)
            }

            fgTransaction.commit()

            true
        }
    }

    private fun onSubmitClickLister() {
        // サラサラ
        iv_submit_straight.setOnClickListener {
            viewModel.insertStatus(HairStatus.STRAIGHT.rawValue)
            animator.showNext()
            floatingActionButton.setImageResource(R.drawable.men_streat)
        }
        iv_submit_curl.setOnClickListener {
            viewModel.insertStatus(HairStatus.CURL.rawValue)
            animator.showNext()
            floatingActionButton.setImageResource(R.drawable.men_curl)
        }
        iv_submit_very_curl.setOnClickListener {
            viewModel.insertStatus(HairStatus.VERY_CURL.rawValue)
            animator.showNext()
            floatingActionButton.setImageResource(R.drawable.men_very_curl)
        }
    }
}
