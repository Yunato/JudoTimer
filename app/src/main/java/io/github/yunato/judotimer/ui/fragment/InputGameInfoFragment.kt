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
    var mGenderState: String = ""
    var mGradeItems: Array<String> = arrayOf("")

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
            val secondPlayerLayout = view.findViewById(R.id.input_player_info_layout)
                    .findViewById(R.id.second_input_layout)
                    .findViewById(R.id.color_layout)
            val gameNameView = view.findViewById(R.id.input_game_info_layout)
                    .findViewById(R.id.name_edit_text) as TextInputEditText
            val selectGenderView = view.findViewById(R.id.input_game_info_layout)
                    .findViewById(R.id.select_gender_text_view) as TextInputEditText
            val selectPartView = view.findViewById(R.id.input_game_info_layout)
                    .findViewById(R.id.select_part_text_view) as TextInputEditText
            val selectGradeView = view.findViewById(R.id.input_game_info_layout)
                    .findViewById(R.id.select_grade_text_view) as TextInputEditText
            val selectRankView = view.findViewById(R.id.input_game_info_layout)
                    .findViewById(R.id.select_rank_text_view) as TextInputEditText
            val numberView = view.findViewById(R.id.input_game_info_layout)
                    .findViewById(R.id.num_edit_text) as TextInputEditText
            val firstPlayerNameView = view.findViewById(R.id.input_player_info_layout)
                    .findViewById(R.id.first_input_layout)
                    .findViewById(R.id.player_name_edit_text) as TextInputEditText
            val firstPlayerBelongView = view.findViewById(R.id.input_player_info_layout)
                    .findViewById(R.id.first_input_layout)
                    .findViewById(R.id.player_belong_edit_text) as TextInputEditText
            val secondPlayerNameView = view.findViewById(R.id.input_player_info_layout)
                    .findViewById(R.id.second_input_layout)
                    .findViewById(R.id.player_name_edit_text) as TextInputEditText
            val secondPlayerBelongView = view.findViewById(R.id.input_player_info_layout)
                    .findViewById(R.id.second_input_layout)
                    .findViewById(R.id.player_belong_edit_text) as TextInputEditText
            val faButton = view.findViewById(R.id.fab) as FloatingActionButton

            val switchGradeItems = {
                val pair = Pair(selectGenderView.text.toString(), selectPartView.text.toString())
                val resourceId = when (pair) {
                    Pair(activity.getString(R.string.gender_man), activity.getString(R.string.part_es)) -> R.array.man_grade_es
                    Pair(activity.getString(R.string.gender_man), activity.getString(R.string.part_jhs)) -> R.array.man_grade_jhs
                    Pair(activity.getString(R.string.gender_man), activity.getString(R.string.part_hs)) -> R.array.man_grade_hs
                    Pair(activity.getString(R.string.gender_man), activity.getString(R.string.part_univ)) -> R.array.man_grade
                    Pair(activity.getString(R.string.gender_man), activity.getString(R.string.part_general)) -> R.array.man_grade
                    Pair(activity.getString(R.string.gender_woman), activity.getString(R.string.part_es)) -> R.array.woman_grade_es
                    Pair(activity.getString(R.string.gender_woman), activity.getString(R.string.part_jhs)) -> R.array.woman_grade_jhs
                    Pair(activity.getString(R.string.gender_woman), activity.getString(R.string.part_hs)) -> R.array.woman_grade_hs
                    Pair(activity.getString(R.string.gender_woman), activity.getString(R.string.part_univ)) -> R.array.woman_grade
                    Pair(activity.getString(R.string.gender_woman), activity.getString(R.string.part_general)) -> R.array.woman_grade
                    else -> {
                        R.string.grade_other
                    }
                }
                mGradeItems = activity.resources.getStringArray(resourceId)
            }

            firstPlayerLayout.setBackgroundColor(
                    ContextCompat.getColor(activity, R.color.colorPlayerRed))
            secondPlayerLayout.setBackgroundColor(
                    ContextCompat.getColor(activity, R.color.colorPlayerWhite))
            selectGenderView.setOnClickListener {
                val items: Array<String> = activity.resources.getStringArray(R.array.gender)
                AlertDialog.Builder(activity)
                        .setTitle(activity.getString(R.string.gender_title))
                        .setItems(items, { _, which ->
                            selectGenderView.setText(items[which])
                            if (!mGenderState.contentEquals(selectGenderView.text)) {
                                mGenderState = selectGenderView.text.toString()
                                switchGradeItems()
                                if (!mGradeItems.contains(selectGradeView.text.toString())) {
                                    selectGradeView.setText("")
                                }
                            }
                        })
                        .show()
            }
            selectPartView.setOnClickListener {
                val items: Array<String> = activity.resources.getStringArray(R.array.part)
                AlertDialog.Builder(activity)
                        .setTitle(activity.getString(R.string.part_title))
                        .setItems(items, { _, which ->
                            selectPartView.setText(items[which])
                            switchGradeItems()
                            if (!mGradeItems.contains(selectGradeView.text.toString())) {
                                selectGradeView.setText("")
                            }
                        })
                        .show()
            }
            selectGradeView.setOnClickListener {
                val items = mGradeItems
                AlertDialog.Builder(activity)
                        .setTitle(activity.getString(R.string.grade_title))
                        .setItems(items, { _, which ->
                            selectGradeView.setText(items[which])
                        })
                        .show()
            }
            selectRankView.setOnClickListener {
                val items: Array<String> = activity.resources.getStringArray(R.array.rank)
                AlertDialog.Builder(activity)
                        .setTitle(activity.getString(R.string.rank_title))
                        .setItems(items, { _, which ->
                            selectRankView.setText(items[which])
                        })
                        .show()
            }
            faButton.setOnClickListener {
                mListener?.onStart(
                        Game(0, gameNameView.text.toString(),
                                selectGradeView.text.toString(),
                                selectRankView.text.toString(),
                                numberView.text.toString()),
                        Player(firstPlayerNameView.text.toString(),
                                firstPlayerBelongView.text.toString()),
                        Player(secondPlayerNameView.text.toString(),
                                secondPlayerBelongView.text.toString()))
            }

            mGenderState = activity.getString(R.string.gender_man)
            switchGradeItems()
        }
    }

    fun setOnStartListener(listener: OnStartListener) {
        mListener = listener
    }

    interface OnStartListener {
        fun onStart(game: Game, first: Player, second: Player)
    }
}
