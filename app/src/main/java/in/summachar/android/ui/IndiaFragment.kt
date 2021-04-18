package `in`.summachar.android.ui

import `in`.summachar.android.MainActivityViewModel
import `in`.summachar.android.R
import `in`.summachar.android.databinding.FragmentBusinessBinding
import `in`.summachar.android.databinding.FragmentIndiaBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import java.lang.Exception

class IndiaFragment : Fragment() {

    private lateinit var viewModel: MainActivityViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:FragmentIndiaBinding = DataBindingUtil.inflate(
            inflater,R.layout.fragment_india,container,false
        )
        binding.lifecycleOwner = viewLifecycleOwner

        //ViewModel
        viewModel = activity?.run {
            ViewModelProvider(this, MainActivityViewModel.Factory(this.application)).get(MainActivityViewModel::class.java)
        }?:throw Exception("invalid Activity")

        //recyclerViewAdapter
        val adapter = TopStoriesAdapterClickable(
            ArticleListener {
                url: String? -> this
                    .findNavController()
                    .navigate(IndiaFragmentDirections
                        .actionIndiaFragmentToWebViewFragment(url)
                    )
            }
        )
        binding.recyclerView.adapter = adapter



        //Observing List of Articles connected to database
        viewModel.indianArticles.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })


        return binding.root
    }
}