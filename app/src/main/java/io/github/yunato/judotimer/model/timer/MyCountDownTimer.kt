package io.github.yunato.judotimer.model.timer

import android.os.CountDownTimer
import java.text.SimpleDateFormat
import java.util.*

class MyCountDownTimer(millisUntilFinished: Long, countDownInterval: Long)
        : CountDownTimer(millisUntilFinished, countDownInterval) {

    private var mListener: OnProgressListener? = null
    var isRunning: Boolean = true

    private val dataFormat: SimpleDateFormat = SimpleDateFormat("mm:ss", Locale.JAPAN)

    fun setOnProgressListener (listener: OnProgressListener){
        mListener = listener
    }

    override fun onFinish() {
        mListener?.onProgress(0, dataFormat.format(0))
    }

    override fun onTick(millisUntilFinished: Long) {
        mListener?.onProgress(millisUntilFinished, dataFormat.format(millisUntilFinished))
    }

    interface OnProgressListener{
        fun onProgress(millisUntilFinished: Long, timeText: String)
    }
}
