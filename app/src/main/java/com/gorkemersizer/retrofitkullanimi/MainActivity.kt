package com.gorkemersizer.retrofitkullanimi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gorkemersizer.retrofitkullanimi.ui.theme.RetrofitKullanimiTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitKullanimiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Sayfa()
                }
            }
        }
    }
}

@Composable
fun Sayfa() {
    LaunchedEffect(key1 = true){
        //tumKisiler()
        arama()
    }
}

fun tumKisiler(){
    val kisilerDaoInterface = ApiUtils.getKisilerDaoInterface()

    kisilerDaoInterface.tumKisiler().enqueue(object : Callback<KisilerCevap>{
        override fun onResponse(call: Call<KisilerCevap>, response: Response<KisilerCevap>) {
            val liste = response.body()!!.kisiler

            for (k in liste){
                Log.e("Kişi Bilgi","***********")
                Log.e("Kişi id",k.kisi_id.toString())
                Log.e("Kişi ad",k.kisi_ad)
                Log.e("Kişi tel",k.kisi_tel)
            }
        }

        override fun onFailure(call: Call<KisilerCevap>, t: Throwable) {}
    })
}

fun arama(){
    val kisilerDaoInterface = ApiUtils.getKisilerDaoInterface()

    kisilerDaoInterface.kisiAra("r").enqueue(object : Callback<KisilerCevap>{
        override fun onResponse(call: Call<KisilerCevap>, response: Response<KisilerCevap>) {
            val liste = response.body()!!.kisiler

            for (k in liste){
                Log.e("Kişi Bilgi","***********")
                Log.e("Kişi id",k.kisi_id.toString())
                Log.e("Kişi ad",k.kisi_ad)
                Log.e("Kişi tel",k.kisi_tel)
            }
        }
        override fun onFailure(call: Call<KisilerCevap>, t: Throwable) {}
    })
}

fun sil(){
    val kisilerDaoInterface = ApiUtils.getKisilerDaoInterface()

    kisilerDaoInterface.kisiSil(2892).enqueue(object : Callback<CRUDCevap>{
        override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {
            val mesaj = response.body()!!.message
            val basari = response.body()!!.success
            Log.e("Kişi Sil","$basari - $mesaj")
        }
        override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {}
    })
}

fun ekle(){
    val kisilerDaoInterface = ApiUtils.getKisilerDaoInterface()

    kisilerDaoInterface.kisiEkle("test_ad","test_tel").enqueue(object : Callback<CRUDCevap>{
        override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {
            val mesaj = response.body()!!.message
            val basari = response.body()!!.success
            Log.e("Kişi ekle","$basari - $mesaj")
        }
        override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {}
    })
}

fun guncelle(){
    val kisilerDaoInterface = ApiUtils.getKisilerDaoInterface()

    kisilerDaoInterface.kisiGuncelle(2,"test_adx","test_telx").enqueue(object : Callback<CRUDCevap>{
        override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {
            val mesaj = response.body()!!.message
            val basari = response.body()!!.success
            Log.e("Kişi güncelle","$basari - $mesaj")
        }
        override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {}
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RetrofitKullanimiTheme {
        Sayfa()
    }
}