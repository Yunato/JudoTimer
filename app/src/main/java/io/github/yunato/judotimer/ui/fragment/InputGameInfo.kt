package io.github.yunato.judotimer.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import io.github.yunato.judotimer.R
import io.github.yunato.judotimer.model.Game
import io.github.yunato.judotimer.model.Player

class InputGameInfo : Fragment() {

    companion object {

        fun newInstance(): InputGameInfo {
            return InputGameInfo()
        }
    }

    val game: Game          = Game(0, "", "", "")
    val redPlayer: Player   = Player("", "")
    val whitePlayer: Player = Player("", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                     savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_input_game_info, container, false)
    }
}
