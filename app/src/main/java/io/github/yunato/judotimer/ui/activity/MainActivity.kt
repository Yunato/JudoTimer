package io.github.yunato.judotimer.ui.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.yunato.judotimer.R
import io.github.yunato.judotimer.model.dto.Game
import io.github.yunato.judotimer.model.dto.Player
import io.github.yunato.judotimer.ui.fragment.InputGameInfoFragment

class MainActivity : AppCompatActivity() {
    val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(resources.getBoolean(R.bool.portrait_only)){
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }else{
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        val transition = fragmentManager.beginTransaction()
        val fragment = InputGameInfoFragment.newInstance()
        fragment.setOnStartListener(
                object: InputGameInfoFragment.OnStartListener{
                    override fun onStart(game: Game, first: Player, second: Player){
                        TimerActivity.intent(applicationContext).let { startActivity(it) }
                    }
                }
        )
        transition.replace(R.id.container_fragment, fragment).commit()
    }
}
