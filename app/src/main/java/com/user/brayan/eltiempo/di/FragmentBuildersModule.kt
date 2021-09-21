package com.user.brayan.eltiempo.di

import com.user.brayan.eltiempo.ui.news.NoticeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeNoticeFragment(): NoticeFragment
}