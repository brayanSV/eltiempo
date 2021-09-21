package com.user.brayan.eltiempo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.user.brayan.eltiempo.ui.detailsnews.DetailsNewsViewModel
import com.user.brayan.eltiempo.ui.news.NoticeViewModel
import com.user.brayan.eltiempo.view_model.AppViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NoticeViewModel::class)
    abstract fun bindNoticeViewModel(noticeViewModel: NoticeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NoticeViewModel::class)
    abstract fun bindNoticeViewModel(detailNewsViewModel: DetailsNewsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}