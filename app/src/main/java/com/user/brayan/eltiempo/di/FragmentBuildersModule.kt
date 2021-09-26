package com.user.brayan.eltiempo.di

import com.user.brayan.eltiempo.ui.detailsnews.DetailsNewsFragment
import com.user.brayan.eltiempo.ui.news.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeNewsFragment(): NewsFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailsNewsFragment(): DetailsNewsFragment
}