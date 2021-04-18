package `in`.summachar.android.ui

import `in`.summachar.android.R
import `in`.summachar.android.databinding.FragmentWebViewBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil


class WebViewFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding:FragmentWebViewBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_web_view,container,false
            )

        val url = WebViewFragmentArgs.fromBundle(arguments!!).url
        binding.webview.webViewClient = WebViewClient()
        binding.webview.loadUrl(url!!)
        return binding.root
    }
}