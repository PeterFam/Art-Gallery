package com.peterfam.domain

import androidx.lifecycle.LiveData
import com.peterfam.data.local.ArtGalleryDao
import com.peterfam.data.local.model.Art
import com.peterfam.data.remote.ApiService
import com.peterfam.data.remote.model.ImageResponse
import com.peterfam.utils.Response
import javax.inject.Inject

class ArtGalleryRepo @Inject constructor(
    private val artGalleryDao: ArtGalleryDao,
    private val apiService: ApiService
) : ArtGalleryRepoImpl {
    override suspend fun insertItem(art: Art) {
        artGalleryDao.insertArt(art)
    }

    override suspend fun deleteItem(art: Art) {
        artGalleryDao.deleteArt(art)
    }

    override fun getData(): LiveData<List<Art>> {
       return artGalleryDao.observeArts()
    }

    override suspend fun searchImage(imgString: String): Response<ImageResponse> {
        return try {
            val response = apiService.searchImage(imgString)
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Response.success(it)
                } ?: Response.error("request error", null)
            }else {
                Response.error("error", null)
            }
        }catch (e: Exception){
            Response.error("No data!", null)
        }
    }
}