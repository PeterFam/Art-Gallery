package com.peterfam.domain

import androidx.lifecycle.LiveData
import com.peterfam.data.local.model.Art
import com.peterfam.data.remote.model.ImageResponse
import com.peterfam.utils.Response

interface ArtGalleryRepoImpl {

    suspend fun insertItem(art: Art)

    suspend fun deleteItem(art: Art)

    fun getData(): LiveData<List<Art>>

    suspend fun searchImage(imgString: String): Response<ImageResponse>

}