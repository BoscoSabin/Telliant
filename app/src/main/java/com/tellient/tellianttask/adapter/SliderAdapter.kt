package com.tellient.tellianttask.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.tellient.tellianttask.data.AppData
import com.tellient.tellianttask.ui.fragment.PatientDetailsFragment

class SliderAdapter(fm: FragmentManager, val patientDetails: List<AppData.PatientDetails>) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return PatientDetailsFragment.getInstance(patientDetails.get(position))
    }

    override fun getCount(): Int {
        return patientDetails.size
    }
}