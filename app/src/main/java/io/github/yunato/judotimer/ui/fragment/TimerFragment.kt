package io.github.yunato.judotimer.ui.fragment

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.yunato.judotimer.R
import io.github.yunato.judotimer.model.timer.MyCountDownTimer
import io.github.yunato.judotimer.ui.view.ResizeTextView

class TimerFragment : Fragment() {

    companion object {

        fun newInstance(): TimerFragment {
            return TimerFragment()
        }
    }

    private var mListener: OnFragmentInteractionListener? = null
    private var timer: MyCountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                     savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_timer_style1, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var countNumber: Long = 180000
        val interval: Long = 10

        val timerTextView = view?.findViewById(R.id.timer_text_view) as ResizeTextView
        timerTextView.text = "03:00"

        timerTextView.setOnClickListener {
            timer?.cancel()
            if (timer?.isRunning ?: false){
                timer?.isRunning = false
            }else{
                timer = MyCountDownTimer(countNumber, interval)
                timer?.setOnProgressListener(object: MyCountDownTimer.OnProgressListener{
                    override fun onProgress(millisUntilFinished: Long, timeText: String) {
                        countNumber = millisUntilFinished
                        timerTextView.text = timeText
                    }
                })
                timer?.start()
                timer?.isRunning = true
            }
        }
        timerTextView.setOnLongClickListener {
            timer?.cancel()
            timer?.isRunning = false
            countNumber = 180000
            timerTextView.text = "03:00"
            true
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
