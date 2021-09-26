package com.user.brayan.eltiempo.ui.detailsnews

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.user.brayan.eltiempo.AppExecutors
import com.user.brayan.eltiempo.R
import com.user.brayan.eltiempo.binding.FragmentDataBindingComponent
import com.user.brayan.eltiempo.databinding.FragmentDetailsNewsBinding
import com.user.brayan.eltiempo.di.Injectable
import com.user.brayan.eltiempo.ui.common.FavoriteCallback
import com.user.brayan.eltiempo.ui.common.RetryCallback
import com.user.brayan.eltiempo.utils.autoCleared
import javax.inject.Inject


class DetailsNewsFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<FragmentDetailsNewsBinding>()

    private val newsViewModel: DetailsNewsViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details_news,
            container,
            false,
            dataBindingComponent
        )

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner

        val params = DetailsNewsFragmentArgs.fromBundle(requireArguments())
        newsViewModel.nasaId.value = params.hrefNasaId

        newsViewModel.detailsNews.observe(viewLifecycleOwner, Observer {
            binding.news = it.data
        })

        binding.favoriteCallback = object: FavoriteCallback {
            override fun favorite() {
                newsViewModel.favorite(!binding.news!!.data.favorite, binding.news!!.data.nasaId)
            }
        }
    }
}