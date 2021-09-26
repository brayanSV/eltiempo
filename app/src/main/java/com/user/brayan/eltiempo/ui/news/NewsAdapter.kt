package com.user.brayan.eltiempo.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.user.brayan.eltiempo.AppExecutors
import com.user.brayan.eltiempo.R
import com.user.brayan.eltiempo.databinding.NewsItemBinding
import com.user.brayan.eltiempo.model.News
import com.user.brayan.eltiempo.model.NewsCollections
import com.user.brayan.eltiempo.ui.common.DataBoundListAdapter

class NewsAdapter (
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val callback: ((News) -> Unit)?
): DataBoundListAdapter<News, NewsItemBinding> (
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.data.nasaId == newItem.data.nasaId
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.data.nasaId == newItem.data.nasaId && oldItem.data.title == newItem.data.title
        }
    }
) {
    override fun createBinding(parent: ViewGroup): NewsItemBinding {
        val binding = DataBindingUtil.inflate<NewsItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.news_item,
            parent,
            false,
            dataBindingComponent
        )

        binding.root.setOnClickListener {
            binding.news?.let {
                callback?.invoke(it)
            }
        }

        return binding
    }

    override fun bind(binding: NewsItemBinding, item: News) {
        binding.news = item
    }

}