package com.tellient.tellianttask.data

import java.io.Serializable

class AppData {

    data class PatientDetails(var profilepIc: Int, var name: String, var gender: String, var age: String, var country: String):Serializable
    data class ReportDetails(var statusIcon: Int, var status: String, var type: String, var timimg: String, var reportedBy: String, var createdBy: String)
}