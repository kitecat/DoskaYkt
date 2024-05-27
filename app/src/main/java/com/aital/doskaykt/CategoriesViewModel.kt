package com.aital.doskaykt

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aital.doskaykt.models.CategoriesSimpleResponse
import com.aital.doskaykt.models.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesViewModel : ViewModel() {
    private var categoriesLiveData = MutableLiveData<List<Category>>()
    fun getCategories() {
        // стоит scope = simple для получения простого списка категорий
        RetrofitInstance.api.getCategories("simple").enqueue(object  : Callback<CategoriesSimpleResponse> {
            override fun onResponse(call: Call<CategoriesSimpleResponse>, response: Response<CategoriesSimpleResponse>) {
                if (response.body()!=null){
                    categoriesLiveData.value = response.body()!!.data
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<CategoriesSimpleResponse>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }
    fun observeCategoriesLiveData() : LiveData<List<Category>> {
        return categoriesLiveData
    }
}