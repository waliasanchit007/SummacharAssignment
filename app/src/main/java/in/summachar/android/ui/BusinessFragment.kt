package `in`.summachar.android.ui

import `in`.summachar.android.MainActivityViewModel
import `in`.summachar.android.R
import `in`.summachar.android.databinding.FragmentBusinessBinding
import `in`.summachar.android.databinding.FragmentTopStoriesBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import java.lang.Exception

class BusinessFragment : Fragment() {

    private lateinit var viewModel: MainActivityViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:FragmentBusinessBinding = DataBindingUtil.inflate(
            inflater,R.layout.fragment_business,container,false
        )
        binding.lifecycleOwner = viewLifecycleOwner

        //ViewModel
        viewModel = activity?.run {
            ViewModelProvider(this, MainActivityViewModel.Factory(this.application))
                .get(MainActivityViewModel::class.java)
        }?:throw Exception("invalid Activity")

        //recyclerViewAdapter
        val adapter = TopStoriesAdapterClickable(
            ArticleListener {
                    url: String? -> this
                    .findNavController()
                    .navigate(BusinessFragmentDirections
                    .actionBusinessFragmentToWebViewFragment(url))
            }
        )
        binding.recyclerView.adapter = adapter


        //Observing List of Articles connected to database
        viewModel.businessArticles.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }

        })
        return binding.root
    }
}