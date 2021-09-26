package com.user.brayan.eltiempo.ui.news

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.user.brayan.eltiempo.AppExecutors
import com.user.brayan.eltiempo.R
import com.user.brayan.eltiempo.binding.FragmentDataBindingComponent
import com.user.brayan.eltiempo.databinding.FragmentNewsBinding
import com.user.brayan.eltiempo.di.Injectable
import com.user.brayan.eltiempo.ui.common.RetryCallback
import com.user.brayan.eltiempo.utils.autoCleared
import com.user.brayan.eltiempo.view_model.AppViewModelFactory
import javax.inject.Inject


class NewsFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<FragmentNewsBinding>()
    var adapter by autoCleared<NewsAdapter>()

    val newsViewModel: NewsViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false, dataBindingComponent)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        initSearchView()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val rvAdapter = NewsAdapter(
            dataBindingComponent = dataBindingComponent,
            appExecutors = appExecutors,
            viewModel = newsViewModel
        ) { news ->
             findNavController().navigate(
                NewsFragmentDirections.actionNewsFragmentToDetailsNewsFragment(news.data.nasaId)
             )
        }

        binding.query = newsViewModel.queryLD
        binding.newsList.adapter = rvAdapter
        adapter = rvAdapter

        initSearchInputListener()
        initNewsList(newsViewModel)

        binding.callback = object: RetryCallback {
            override fun retry() {
                newsViewModel.refresh()
            }
        }
    }

    private fun initSearchView() {
        binding.searchResult = newsViewModel.result
        newsViewModel.result.observe(viewLifecycleOwner, Observer { result ->
            adapter.submitList(result?.data)
        })
    }

    private fun initNewsList(viewModel: NewsViewModel) {
        viewModel.repositories.observe(viewLifecycleOwner, Observer { listResource ->
            if(listResource?.data != null) {
                adapter.submitList(listResource.data)
            } else {
                adapter.submitList(emptyList())
            }
        })
    }

    private fun initSearchInputListener() {
        binding.input.setOnEditorActionListener {view: View, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearch(view)
                true
            } else {
                false
            }
        }

        binding.input.setOnKeyListener{view: View, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                doSearch(view)
                true
            } else {
                false
            }
        }
    }

    private fun doSearch(view: View) {
        val query = binding.input.text.toString()

        dismissKeyboard(view.windowToken)
        newsViewModel.setQuery(query)
    }

    private fun dismissKeyboard(windowToken: IBinder) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken,0)
    }
}