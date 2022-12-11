package ru.anudx.project_kino.study

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.redmadrobot.inputmask.MaskedTextChangedListener
import ru.anudx.project_kino.R
import ru.anudx.project_kino.databinding.FragmentStudyUserInputBinding

class StudyUserInputFragment : Fragment() {
    val b by lazy {
        FragmentStudyUserInputBinding.bind(this.requireView())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_study_user_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listener = MaskedTextChangedListener("+7 ([000]) [000]-[00]-[00]", b.phoneNumber)
        b.phoneNumber.addTextChangedListener(listener)
        b.phoneNumber.onFocusChangeListener = listener

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudyUserInputFragment().apply {
                }
            }
    }
