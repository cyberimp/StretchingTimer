package com.kalinasoft.stretchtimer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.kalinasoft.stretchtimer.databinding.FragmentSecondBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.subscribe
import java.util.Locale
import kotlin.math.floor

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TimerFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var timerViewModel: TimerViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.circleTimer.maxTime = 60f
        timerViewModel = TimerViewModel(60f)
        val timeObserver = Observer<Float>  {
            val seconds: String = floor(it).toInt().toString()
            val millis: String = String.format(Locale.US,"%03d", floor((it-floor(it))*1000).toInt())

            binding.secondsText.text = seconds
            binding.millisecondsText.text = millis
            binding.circleTimer.curTime = it
        }
        view.findViewTreeLifecycleOwner()?.let { timerViewModel?.timer!!.observe(it, timeObserver) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}