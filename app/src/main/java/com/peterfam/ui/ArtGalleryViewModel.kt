package com.peterfam.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peterfam.data.local.model.Art
import com.peterfam.data.remote.model.ImageResponse
import com.peterfam.domain.ArtGalleryRepoImpl
import com.peterfam.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ArtGalleryViewModel @Inject constructor(
    private val repository: ArtGalleryRepoImpl
): ViewModel(){


    val artList = repository.getData()

    private val images = MutableLiveData<Response<ImageResponse>>()
    val imagesList: LiveData<Response<ImageResponse>>
        get() = images


    private val selectedImage = MutableLiveData<String>()
    val selectedImageUrl: LiveData<String>
        get() = selectedImage

    private var insertArtMsg = MutableLiveData<Response<Art>>()
    val insertArtMessage: LiveData<Response<Art>>
        get() = insertArtMsg

    fun resetInsertArtMsg(){
        insertArtMsg = MutableLiveData<Response<Art>>()
    }

    fun setSelectedImage(url: String){
        selectedImage.postValue(url)
    }

    fun deletedItem(art: Art) =viewModelScope.launch {
        repository.deleteItem(art)
    }

    private fun insertArt(art: Art) = viewModelScope.launch {
        repository.insertItem(art)
    }

    fun searchForImage(searchString: String){
        if(searchString.isEmpty()){
            return
        }
        images.value = Response.loading(null)
        viewModelScope.launch {
            val response = repository.searchImage(searchString)
            images.value = response
        }
    }

    fun insertArtItem(name: String, artistName: String, year: String){
        if(name.isEmpty() || artistName.isEmpty() || year.isEmpty()){
            insertArtMsg.postValue(Response.error("Please enter art name, artist, year", null))
            return
        }
        val yearInt = try {
            year.toInt()
        } catch (e: Exception){
          insertArtMsg.postValue(Response.error("Year should be number", null))
            return
        }
        val art = Art(name, artistName, yearInt, selectedImage.value ?: "")
        insertArt(art)
        setSelectedImage("")
        insertArtMsg.postValue(Response.success(art))

    }
}

