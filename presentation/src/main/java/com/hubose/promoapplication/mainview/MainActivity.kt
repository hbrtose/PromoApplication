package com.hubose.promoapplication.mainview

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.hubose.promoapplication.R
import com.hubose.promoapplication.common.ImageLoader
import com.hubose.promoapplication.settings.SettingsActivity
import com.hubose.promoapplication.views.PagerCrossfadeTransformer
import com.hubose.promoapplication.views.PagerSlowScroller
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val imageLoader: ImageLoader by inject()
    private val mainViewModel: MainViewModel by viewModel()
    private val disposable: CompositeDisposable = CompositeDisposable()
    private var adapter: MediaPagerAdapter? = null

    companion object {
        const val PERMISSIONS_REQUEST = 1519
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpClock()
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSIONS_REQUEST)
        } else {
            setUpViewModel()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.menu_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            PERMISSIONS_REQUEST -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    setUpViewModel()
                }
            }
        }
    }

    private fun setUpClock() {
        disposable.add(Observable.interval(1, TimeUnit.SECONDS).subscribe { analog_clock.refreshTime() })
    }

    private fun setUpViewModel() {
        mainViewModel.error.observe(this, Observer { showDialog(it.localizedMessage) })
        mainViewModel.getItems().observe(this, Observer { setUpPager(it) })
        mainViewModel.ticker.observe(this, Observer { pager.currentItem = it })
    }

    private fun setUpPager(items: List<String>) {
        if (adapter == null) {
            adapter = MediaPagerAdapter(imageLoader, items, supportFragmentManager)
            pager.adapter = adapter
            pager.offscreenPageLimit = 1
            pager.setPageTransformer(false, PagerCrossfadeTransformer())
            val scroller = ViewPager::class.java.getDeclaredField("mScroller")
            scroller.isAccessible = true
            scroller.set(
                pager,
                PagerSlowScroller(
                    this,
                    DecelerateInterpolator(),
                    resources.getInteger(R.integer.crossfade_length_seconds)
                )
            )
        } else {
            adapter?.setUris(items)
        }
    }

    private fun showDialog(msg: String){
        AlertDialog.Builder(this)
            .setMessage(msg)
            .setPositiveButton(android.R.string.ok) {
                    dialogInterface, _ ->  dialogInterface.dismiss()
            }.show()
    }
}
