package ru.anudx.project_kino.fragments

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.anudx.project_kino.App
import ru.anudx.project_kino.R
import ru.anudx.project_kino.databinding.FragmentDetailBinding
import ru.anudx.project_kino.model.FilmsData


class DetailFragment() : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val b: FragmentDetailBinding by lazy {
        FragmentDetailBinding.bind(this.requireView())
    }
    private val mainContext = App.mainContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move).apply { duration = 1000 }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onStart() {
        super.onStart()
        val data = arguments?.getParcelable<FilmsData>("FilmsData")
        b.toolbarLayout.title = data?.title
        data?.image?.let { b.image.setImageResource(it) }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailFragment().apply {
                }
            }
    }
