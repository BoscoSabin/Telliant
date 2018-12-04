package com.tellient.tellianttask.util

import android.content.Context
import com.tellient.tellianttask.R
import com.tellient.tellianttask.data.AppData

class ResourceLoader {

    companion object {

        fun initPatientData(context: Context): List<AppData.PatientDetails> {
            val patientDetails = ArrayList<AppData.PatientDetails>()
            val profileImages = arrayOf(R.drawable.profile_1, R.drawable.profile_2, R.drawable.profile_5, R.drawable.profile_4, R.drawable.profile_3)
            val name = context.resources.getStringArray(R.array.patient_name)
            val gender = context.resources.getStringArray(R.array.gender)
            val age = context.resources.getStringArray(R.array.age)
            val country = context.resources.getStringArray(R.array.country)
            for (i in name.indices) {
                patientDetails.add(AppData.PatientDetails(profileImages.get(i), name.get(i), gender.get(i), age.get(i), country.get(i)))
            }
//            profileImages.recycle()
            return patientDetails
        }

        fun getReportData(context: Context): ArrayList<AppData.ReportDetails> {
            val reportDetails = ArrayList<AppData.ReportDetails>()
            val statusImage = arrayOf(R.drawable.ic_videocam_black_24dp, R.drawable.ic_local_pharmacy_black_24dp)
            val status = context.resources.getStringArray(R.array.status)
            val type = context.resources.getStringArray(R.array.type)
            val timing = context.resources.getStringArray(R.array.timing)
            val reported = context.resources.getStringArray(R.array.reported)
            val created = context.resources.getStringArray(R.array.created)
            for (i in status.indices) {
                reportDetails.add(AppData.ReportDetails(statusImage.get(if (i % 2 == 0) 0 else 1), status.get(i), type.get(i), timing.get(i), reported.get(i), created.get(i)))
            }
//            profileImages.recycle()
            return reportDetails
        }

        fun getCategoryList(context: Context): Array<String> {
            return context.resources.getStringArray(R.array.categories)
        }
    }
}