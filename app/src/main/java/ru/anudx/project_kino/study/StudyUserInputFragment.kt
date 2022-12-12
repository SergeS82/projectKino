package ru.anudx.project_kino.study

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import com.redmadrobot.inputmask.MaskedTextChangedListener
import ru.anudx.project_kino.App
import ru.anudx.project_kino.MainActivity
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
        //
        val activity = requireActivity() as MainActivity
        val menuItem = activity.menu.findItem(R.id.study_search)
        val searchView = menuItem?.actionView as SearchView
        val list = activity.list
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (list.contains(query)) {
                    b.textViewRes.text = "In list"
                    b.textViewRes.setTextColor(ContextCompat.getColor(activity, R.color.Green))
                } else {
                    b.textViewRes.text = "Not in list"
                    b.textViewRes.setTextColor(ContextCompat.getColor(activity, R.color.Red))
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudyUserInputFragment().apply {
                }
            }
    }
