package com.example.dogbreedapp.data.model

data class DogBreed(
    val id: Int,
    val name: String,
    val temperament: String?,
    val life_span: String?,
    val image: Image?
) {
    data class Image(val url: String?)
}