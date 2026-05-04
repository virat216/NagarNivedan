package com.example.nagarnivedan.data

object ComplaintRepository {

    private val complaints = mutableListOf<Complaint>()

    fun addComplaint(complaint: Complaint) {
        complaints.add(complaint)
    }

    fun getComplaints(): List<Complaint> {
        return complaints
    }
}