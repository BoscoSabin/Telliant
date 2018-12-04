package com.tellient.tellianttask.ui.fragment

import android.graphics.Paint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tellient.tellianttask.R
import com.tellient.tellianttask.constant.Constants
import com.tellient.tellianttask.data.AppData
import kotlinx.android.synthetic.main.fragment_patient_info_tab.view.*
import android.graphics.Paint.UNDERLINE_TEXT_FLAG



class PatientDetailsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_patient_info_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val patientDetails = arguments?.getSerializable(Constants.PATIENTDETAILS) as AppData.PatientDetails
            view.ivPatientProfile.setImageResource(patientDetails.profilepIc)
            view.tvPatientName.text = patientDetails.name
            view.tvPatientGender.text = getString(R.string.gender_age, patientDetails.gender, patientDetails.age)
            view.tvPatientCountry.text = patientDetails.country
            view.tvPatientName.setPaintFlags(view.tvPatientName.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        }

    }

    companion object {
        fun getInstance(patientDetails: AppData.PatientDetails): PatientDetailsFragment {

            val fragment = PatientDetailsFragment()
            var bundle = Bundle()
            bundle.putSerializable(Constants.PATIENTDETAILS, patientDetails)
            fragment.arguments = bundle
            return fragment

        }
    }
}