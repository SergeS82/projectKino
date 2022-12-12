package ru.anudx.project_kino.study

import android.annotation.SuppressLint
import android.database.Cursor
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter
import com.redmadrobot.inputmask.MaskedTextChangedListener
import ru.anudx.project_kino.MainActivity
import ru.anudx.project_kino.R
import ru.anudx.project_kino.databinding.FragmentStudyUserInputBinding

@Suppress("CAST_NEVER_SUCCEEDS", "NAME_SHADOWING")
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
        val from = arrayOf("Items")
        val to = intArrayOf(R.id.study_text_search)
        val menuAdapter = SimpleCursorAdapter(activity,
            R.layout.study_search_items,
            null,
            from,
            to,
            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        )
        searchView.suggestionsAdapter = menuAdapter
        searchView.setOnSuggestionListener(object: SearchView.OnSuggestionListener{
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }
            @SuppressLint("Range")
            override fun onSuggestionClick(position: Int): Boolean {
                val cursor: Cursor = menuAdapter.getItem(position) as Cursor
                val txt: String = cursor.getString(cursor.getColumnIndex("items"))
                searchView.setQuery(txt, true)
                searchView.clearFocus()
                return true
            }

        })
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                val c = MatrixCursor(arrayOf(BaseColumns._ID,"items"))
                val list = activity.list
                for (i in list.indices ){
                    if (list[i].toLowerCase().contains(newText!!.toLowerCase()))
                        c.addRow(arrayOf(i, list[i]))
                }
                menuAdapter.changeCursor(c)
                return true
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
