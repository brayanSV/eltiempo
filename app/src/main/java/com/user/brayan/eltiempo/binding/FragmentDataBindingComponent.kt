package com.user.brayan.eltiempo.binding

import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment
import com.user.brayan.eltiempo.binding.FragmentBindingAdapters

class FragmentDataBindingComponent (fragment: Fragment): DataBindingComponent {
    private val adapter = FragmentBindingAdapters(fragment)

    override fun getFragmentBindingAdapters(): FragmentBindingAdapters = adapter
}