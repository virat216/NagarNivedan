package com.example.nagarnivedan.data

import android.net.Uri

object ComplaintDraft {
    var category: String? = null
    var description: String? = null
    var location: String? = null
    var photoAdded: Boolean = false
    var imageUris: MutableList<Uri> = mutableListOf()

    fun reset() {
        category = null
        description = null
        location = null
        photoAdded = false
        imageUris.clear()
    }
}