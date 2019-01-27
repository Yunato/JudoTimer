package io.github.yunato.judotimer.ui.activity

import io.github.yunato.judotimer.R
import io.github.yunato.judotimer.ui.fragment.TimerFragment

class TimerActivity : android.support.v7.app.AppCompatActivity(),
        TimerFragment.OnFragmentInteractionListener {

    companion object {
        fun intent(context: android.content.Context): android.content.Intent =
                android.content.Intent(context, TimerActivity::class.java)
    }

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(io.github.yunato.judotimer.R.layout.activity_timer)

        val transition = fragmentManager.beginTransaction()
        val fragment = TimerFragment.newInstance()
        transition.replace(R.id.activity_timer, fragment).commit()
    }

    override fun onResume() {
        super.onResume()

        val decor: android.view.View = window.decorView
        decor.systemUiVisibility =
                android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                        android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        android.view.View.SYSTEM_UI_FLAG_FULLSCREEN or
                        android.view.View.SYSTEM_UI_FLAG_LOW_PROFILE or
                        android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }

    override fun onFragmentInteraction() {}
}
