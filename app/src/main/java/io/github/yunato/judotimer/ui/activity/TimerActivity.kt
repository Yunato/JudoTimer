package io.github.yunato.judotimer.ui.activity

import android.content.Intent
import io.github.yunato.judotimer.R
import io.github.yunato.judotimer.model.dto.Game
import io.github.yunato.judotimer.model.dto.Player
import io.github.yunato.judotimer.ui.fragment.TimerFragment

class TimerActivity : android.support.v7.app.AppCompatActivity(),
        TimerFragment.OnFragmentInteractionListener {

    companion object {

        private const val GAME_EXTRA: String = "GAME_EXTRA"
        private const val FIRST_EXTRA: String = "FIRST_EXTRA"
        private const val SECOND_EXTRA: String = "SECOND_EXTRA"

        fun intent(context: android.content.Context,
                   game: Game,
                   first: Player,
                   second: Player): Intent =
                Intent(context, TimerActivity::class.java)
                        .putExtra(GAME_EXTRA, game)
                        .putExtra(FIRST_EXTRA, first)
                        .putExtra(SECOND_EXTRA, second)
    }

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(io.github.yunato.judotimer.R.layout.activity_timer)

        val game:  Game = intent.getParcelableExtra(GAME_EXTRA)
        val first:  Player = intent.getParcelableExtra(FIRST_EXTRA)
        val second:  Player = intent.getParcelableExtra(SECOND_EXTRA)

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
