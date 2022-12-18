package ru.anudx.project_kino.study

import android.content.ClipData
import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ru.anudx.project_kino.MainActivity
import ru.anudx.project_kino.R
import ru.anudx.project_kino.databinding.FragmentStudyClipBoardBinding

class StudyClipBoardFragment : Fragment() {
    private val b: FragmentStudyClipBoardBinding  by lazy {
        FragmentStudyClipBoardBinding.bind(requireView())
    }
    private val mainContext by lazy {  requireActivity() as MainActivity }
    val clipboardManager by lazy {
        mainContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }
    val clipDescription = ClipDescription("Text1", arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN))
//    MIMETYPE_TEXT_PLAIN — передаем текст;
//    MIMETYPE_TEXT_HTML — передаем HTML;
//    MIMETYPE_TEXT_INTENT — передаем интент;
//    MIMETYPE_TEXT_URILIST — передаем URI.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_study_clip_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b.editTextTest.setOnLongClickListener {
            val clipboardData = ClipData(clipDescription, ClipData.Item(b.editTextTest.text))
            clipboardData.addItem(ClipData.Item("123456"))
            clipboardManager.setPrimaryClip(clipboardData)
            Toast.makeText(mainContext, "Text put to buffer", Toast.LENGTH_SHORT).show()
            true
        }
        b.textView2.setOnLongClickListener {
            b.textView2.text = clipboardManager.primaryClip?.getItemAt(1)?.text
            true
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudyClipBoardFragment().apply {
            }
    }
}