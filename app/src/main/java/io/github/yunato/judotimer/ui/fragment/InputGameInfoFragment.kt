package io.github.yunato.judotimer.ui.fragment

import android.app.AlertDialog
import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TextInputEditText
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.yunato.judotimer.R
import io.github.yunato.judotimer.model.dto.Game
import io.github.yunato.judotimer.model.dto.Player

class InputGameInfoFragment : Fragment() {

    companion object {

        fun newInstance(): InputGameInfoFragment {
            return InputGameInfoFragment()
        }
    }

    var mListener: OnStartListener? = null

    val game: Game = Game(0, "", "", "", "")
    val firstPlayer: Player = Player("", "")
    val secondPlayer: Player = Player("", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_input_game_info, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (view != null) {
            val firstPlayerLayout = view.findViewById(R.id.input_player_info_layout)
                    .findViewById(R.id.first_input_layout)
                    .findViewById(R.id.color_layout)
            firstPlayerLayout.setBackgroundColor(
                    ContextCompat.getColor(activity, R.color.colorPlayerRed))

            val secondPlayerLayout = view.findViewById(R.id.input_player_info_layout)
                    .findViewById(R.id.second_input_layout)
                    .findViewById(R.id.color_layout)
            secondPlayerLayout.setBackgroundColor(
                    ContextCompat.getColor(activity, R.color.colorPlayerWhite))

            val selectGenderView = view.findViewById(R.id.input_game_info_layout)
                    .findViewById(R.id.select_gender_text_view) as TextInputEditText
            selectGenderView.setOnClickListener {
                val items: Array<String> = activity.resources.getStringArray(R.array.gender)
                AlertDialog.Builder(activity)
                        .setTitle(activity.getString(R.string.gender_title))
                        .setItems(items, { _, which ->
                            selectGenderView.setText(items[which])
                        })
                        .show()
            }

            val selectGradeView = view.findViewById(R.id.input_game_info_layout)
                    .findViewById(R.id.select_grade_text_view) as TextInputEditText
            selectGradeView.setOnClickListener {
                val resourceId = if (activity.getString(R.string.gender_man).
                        contentEquals(selectGenderView.text)) R.array.man_grade else R.array.woman_grade
                val items: Array<String> = activity.resources.getStringArray(resourceId)
                AlertDialog.Builder(activity)
                        .setTitle(activity.getString(R.string.grade_title))
                        .setItems(items, { _, which ->
                            selectGradeView.setText(items[which])
                        })
                        .show()
            }

            val selectRankView = view.findViewById(R.id.input_game_info_layout)
                    .findViewById(R.id.select_rank_text_view) as TextInputEditText
            selectRankView.setOnClickListener {
                val items: Array<String> = activity.resources.getStringArray(R.array.rank)
                AlertDialog.Builder(activity)
                        .setTitle(activity.getString(R.string.rank_title))
                        .setItems(items, { _, which ->
                            selectRankView.setText(items[which])
                        })
                        .show()
            }

            val faButton = view.findViewById(R.id.fab) as FloatingActionButton
            faButton.setOnClickListener {
                mListener?.onStart(
                        Game(0, "", "", "", ""),
                        Player("", ""),
                        Player("", ""))
            }
        }
    }

    fun setOnStartListener(listener: OnStartListener) {
        mListener = listener
    }

    interface OnStartListener {
        fun onStart(game: Game, first: Player, second: Player)
    }
}
