package com.example.nagarnivedan.data

object ComplaintDraft {
    var category: String? = null
    var description: String? = null
    var location: String? = null
    var photoAdded: Boolean = false

    fun reset() {
        category = null
        description = null
        location = null
        photoAdded = false
    }
}