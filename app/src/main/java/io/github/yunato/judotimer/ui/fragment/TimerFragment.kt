package io.github.yunato.judotimer.ui.fragment

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import io.github.yunato.judotimer.R
import io.github.yunato.judotimer.model.dto.Game
import io.github.yunato.judotimer.model.dto.Player
import io.github.yunato.judotimer.model.timer.MyCountDownTimer
import io.github.yunato.judotimer.ui.view.ResizeTextView

class TimerFragment : Fragment() {

    companion object {
        private val GAME_ARG = "GAME_ARG"
        private val FIRST_ARG = "FIRST_ARG"
        private val SECOND_ARG = "SECOND_ARG"

        fun newInstance(game: Game, first: Player, second: Player): TimerFragment {
            val fragment = TimerFragment()
            val args = Bundle()
            args.putParcelable(GAME_ARG, game)
            args.putParcelable(FIRST_ARG, first)
            args.putParcelable(SECOND_ARG, second)
            fragment.arguments = args
            return fragment
        }
    }

    private var mListener: OnFragmentInteractionListener? = null
    private var timer: MyCountDownTimer? = null
    private var game: Game? = null
    private var first: Player? = null
    private var second: Player? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            game = arguments.getParcelable(GAME_ARG)
            first = arguments.getParcelable(FIRST_ARG)
            second = arguments.getParcelable(SECOND_ARG)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_timer_style1, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (view != null) {
            var countNumber: Long = 180000
            val interval: Long = 10
            var firstScore: Int = 0
            var secondScore: Int = 0
            var firstShidoCount: Int = 0
            var secondShidoCount: Int = 0

            val timerTextView = view.findViewById(R.id.timer_text_view) as ResizeTextView
            val firstOsaekomiButton = view.findViewById(R.id.first_input_score)
                    .findViewById(R.id.osaekomi_button) as Button
            val firstMinusIpponButton = view.findViewById(R.id.first_input_score)
                    .findViewById(R.id.minus_ippon_button) as Button
            val firstPlusIpponButton = view.findViewById(R.id.first_input_score)
                    .findViewById(R.id.plus_ippon_button) as Button
            val firstMinusWazaariButton = view.findViewById(R.id.first_input_score)
                    .findViewById(R.id.minus_wazaari_button) as Button
            val firstPlusWazaariButton = view.findViewById(R.id.first_input_score)
                    .findViewById(R.id.plus_wazaari_button) as Button
            val firstMinusShidoButton = view.findViewById(R.id.first_input_score)
                    .findViewById(R.id.minus_shido_button) as Button
            val firstPlusShidoButton = view.findViewById(R.id.first_input_score)
                    .findViewById(R.id.plus_shido_button) as Button
            val secondOsaekomiButton = view.findViewById(R.id.second_input_score)
                    .findViewById(R.id.osaekomi_button) as Button
            val secondMinusIpponButton = view.findViewById(R.id.second_input_score)
                    .findViewById(R.id.minus_ippon_button) as Button
            val secondPlusIpponButton = view.findViewById(R.id.second_input_score)
                    .findViewById(R.id.plus_ippon_button) as Button
            val secondMinusWazaariButton = view.findViewById(R.id.second_input_score)
                    .findViewById(R.id.minus_wazaari_button) as Button
            val secondPlusWazaariButton = view.findViewById(R.id.second_input_score)
                    .findViewById(R.id.plus_wazaari_button) as Button
            val secondMinusShidoButton = view.findViewById(R.id.second_input_score)
                    .findViewById(R.id.minus_shido_button) as Button
            val secondPlusShidoButton = view.findViewById(R.id.second_input_score)
                    .findViewById(R.id.plus_shido_button) as Button
            val firstColorLayout = view.findViewById(R.id.first_color) as FrameLayout
            val secondColorLayout = view.findViewById(R.id.second_color) as FrameLayout
            val stateTextView = view.findViewById(R.id.state_text_view) as TextView
            val firstBelongTextView = view.findViewById(R.id.first_player_belong_text_view) as TextView
            val firstNameTextView = view.findViewById(R.id.first_player_name_text_view) as TextView
            val secondBelongTextView = view.findViewById(R.id.second_player_belong_text_view) as TextView
            val secondNameTextView = view.findViewById(R.id.second_player_name_text_view) as TextView
            val firstScoreFirstTextView = view.findViewById(R.id.first_score_first_text_view) as ResizeTextView
            val firstScoreSecondTextView = view.findViewById(R.id.first_score_second_text_view) as ResizeTextView
            val secondScoreFirstTextView = view.findViewById(R.id.second_score_first_text_view) as ResizeTextView
            val secondScoreSecondTextView = view.findViewById(R.id.second_score_second_text_view) as ResizeTextView
            val firstOneShidoCard = view.findViewById(R.id.first_one_shido_view) as CardView
            val firstTwoShidoCard = view.findViewById(R.id.first_two_shido_view) as CardView
            val firstThreeShidoCard = view.findViewById(R.id.first_three_shido_view) as CardView
            val secondOneShidoCard = view.findViewById(R.id.second_one_shido_view) as CardView
            val secondTwoShidoCard = view.findViewById(R.id.second_two_shido_view) as CardView
            val secondThreeShidoCard = view.findViewById(R.id.second_three_shido_view) as CardView

            timerTextView.text = "03:00"
            firstColorLayout.setBackgroundColor(
                    ContextCompat.getColor(activity, R.color.colorPlayerRed))
            secondColorLayout.setBackgroundColor(
                    ContextCompat.getColor(activity, R.color.colorPlayerWhite))
            firstScoreFirstTextView.text = firstScore.toString()
            secondScoreFirstTextView.text = secondScore.toString()
            firstNameTextView.text = first?.name
            firstBelongTextView.text = first?.belongs
            secondNameTextView.text = second?.name
            secondBelongTextView.text = second?.belongs

            timerTextView.setOnClickListener {
                timer?.cancel()
                if (timer?.isRunning ?: false) {
                    timer?.isRunning = false
                    timerTextView.setTextColor(
                            ContextCompat.getColor(activity, R.color.colorPlayerBlue))
                    stateTextView.text = "待て"
                } else {
                    timer = MyCountDownTimer(countNumber, interval)
                    timer?.setOnProgressListener(object : MyCountDownTimer.OnProgressListener {
                        override fun onProgress(millisUntilFinished: Long, timeText: String) {
                            countNumber = millisUntilFinished
                            timerTextView.text = timeText
                        }
                    })
                    timer?.start()
                    timer?.isRunning = true
                    timerTextView.setTextColor(
                            ContextCompat.getColor(activity, R.color.colorStroke))
                    stateTextView.text = ""
                }
            }
            timerTextView.setOnLongClickListener {
                timer?.cancel()
                timer?.isRunning = false
                countNumber = 180000
                timerTextView.text = "03:00"
                true
            }
            firstMinusIpponButton.setOnClickListener {
                if (firstScore >= 10) {
                    firstScore -= 10
                    firstScoreSecondTextView.visibility = View.INVISIBLE
                }
            }
            firstPlusIpponButton.setOnClickListener {
                if (firstScore < 10) {
                    firstScore += 10
                    firstScoreSecondTextView.visibility = View.VISIBLE
                    timer?.cancel()
                    timer?.isRunning = false
                }
            }
            firstMinusWazaariButton.setOnClickListener {
                val first: Int = firstScore % 10
                if (first > 0) {
                    firstScore -= 1
                    firstScoreFirstTextView.text = (first - 1).toString()
                }
            }
            firstPlusWazaariButton.setOnClickListener {
                val first: Int = firstScore % 10
                val second: Int = firstScore / 10
                if (first < 1 && second < 1) {
                    firstScore += 1
                    firstScoreFirstTextView.text = firstScore.toString()
                } else if (second < 1) {
                    firstScore += 10
                    firstScoreSecondTextView.visibility = View.VISIBLE
                    timer?.cancel()
                    timer?.isRunning = false
                }
            }
            firstMinusShidoButton.setOnClickListener {
                if (firstShidoCount > 0) {
                    val CardView: CardView? = when (firstShidoCount) {
                        1 -> firstOneShidoCard
                        2 -> firstTwoShidoCard
                        3 -> firstThreeShidoCard
                        else -> null
                    }
                    if (CardView != null) {
                        CardView.visibility = View.INVISIBLE
                        firstShidoCount -= 1
                    }
                }
            }
            firstPlusShidoButton.setOnClickListener {
                if (firstShidoCount < 3) {
                    val CardView: CardView? = when (firstShidoCount) {
                        0 -> firstOneShidoCard
                        1 -> firstTwoShidoCard
                        2 -> {
                            timer?.cancel()
                            timer?.isRunning = false
                            firstThreeShidoCard
                        }
                        else -> null
                    }
                    if (CardView != null) {
                        CardView.visibility = View.VISIBLE
                        firstShidoCount += 1
                    }
                }
            }
            secondMinusIpponButton.setOnClickListener {
                if (secondScore >= 10) {
                    secondScore -= 10
                    secondScoreSecondTextView.visibility = View.INVISIBLE
                }
            }
            secondPlusIpponButton.setOnClickListener {
                if (secondScore < 10) {
                    secondScore += 10
                    secondScoreSecondTextView.visibility = View.VISIBLE
                    timer?.cancel()
                    timer?.isRunning = false
                }
            }
            secondMinusWazaariButton.setOnClickListener {
                val first: Int = secondScore % 10
                if (first > 0) {
                    secondScore -= 1
                    secondScoreFirstTextView.text = (first - 1).toString()
                }
            }
            secondPlusWazaariButton.setOnClickListener {
                val first: Int = secondScore % 10
                val second: Int = secondScore / 10
                if (first < 1 && second < 1) {
                    secondScore += 1
                    secondScoreFirstTextView.text = secondScore.toString()
                } else if (second < 1) {
                    secondScore += 10
                    secondScoreSecondTextView.visibility = View.VISIBLE
                    timer?.cancel()
                    timer?.isRunning = false
                }
            }
            secondMinusShidoButton.setOnClickListener {
                if (secondShidoCount > 0) {
                    val CardView: CardView? = when (secondShidoCount) {
                        1 -> secondOneShidoCard
                        2 -> secondTwoShidoCard
                        3 -> secondThreeShidoCard
                        else -> null
                    }
                    if (CardView != null) {
                        CardView.visibility = View.INVISIBLE
                        secondShidoCount -= 1
                    }
                }
            }
            secondPlusShidoButton.setOnClickListener {
                if (secondShidoCount < 3) {
                    val CardView: CardView? = when (secondShidoCount) {
                        0 -> secondOneShidoCard
                        1 -> secondTwoShidoCard
                        2 -> {
                            timer?.cancel()
                            timer?.isRunning = false
                            secondThreeShidoCard
                        }
                        else -> null
                    }
                    if (CardView != null) {
                        CardView.visibility = View.VISIBLE
                        secondShidoCount += 1
                    }
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction()
    }
}
