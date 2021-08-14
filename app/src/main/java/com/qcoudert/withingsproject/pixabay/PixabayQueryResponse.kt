package com.qcoudert.withingsproject.pixabay

data class PixabayQueryResponse (
    val total: Int,
    val totalHits: Int,
    val hits: List<PixabayHit>
)