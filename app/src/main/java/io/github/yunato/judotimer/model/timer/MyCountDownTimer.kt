package io.github.yunato.judotimer.model.timer

import android.os.CountDownTimer
import java.text.SimpleDateFormat
import java.util.*

class MyCountDownTimer : CountDownTimer {
    private var mListener: OnProgressListener? = null

    private val dataFormat: SimpleDateFormat = SimpleDateFormat("mm:ss", Locale.JAPAN)

    constructor(millisInFuture: Long,
                countDownInterval: Long) : super(millisInFuture, countDownInterval)

    fun setOnProgressListener (listener: OnProgressListener){
        mListener = listener
    }

    override fun onFinish() {
        mListener?.onProgress(dataFormat.format(0))
    }

    override fun onTick(millisUntilFinished: Long) {
        mListener?.onProgress(dataFormat.format(millisUntilFinished))
    }

    interface OnProgressListener{
        fun onProgress(timeText: String)
    }
}
