package com.example.jsoup2_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.jsoup2_kotlin.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityMainBinding.inflate(layoutInflater)
        val rootLayout=binding.root
        super.onCreate(savedInstanceState)
        setContentView(rootLayout)

        retrieveWebInfo()
    }

    private fun retrieveWebInfo() {
        thread{
            val doc= Jsoup.connect("https://www.tutorialspoint.com/android/index.htm").timeout(60000).validateTLSCertificates(false)
                .get()
            val imageElement=doc.getElementsByClass("img-responsive")
            val textElements=doc.getElementsByTag("h1")
            val imageUrl=imageElement[0].absUrl("src")

            this.runOnUiThread {
                binding.txtTitle.text=textElements[0].text()
              //  Picasso.get().load(imageUrl).into(binding.imgTitle)
                Glide.with(this).load(imageUrl).error(R.drawable.ic_launcher_background).into(binding.imgTitle)
            }
        }
    }
}