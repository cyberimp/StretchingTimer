package com.kalinasoft.stretchtimer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.delay

class TimerViewModel(time: Float): ViewModel() {
    private var _startTime: Long = 0L

    val timer = liveData {
        emit(time)
        _startTime = System.currentTimeMillis()
        while (true) {
            delay(16)
            emit((time - (System.currentTimeMillis()-_startTime)/1000f).coerceAtLeast(0f))
        }
    }
}