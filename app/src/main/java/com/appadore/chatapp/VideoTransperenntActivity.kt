package com.appadore.chatapp

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.alphamovie.lib.AlphaMovieView


class VideoTransperenntActivity: AppCompatActivity(),View.OnClickListener {
    lateinit var alphaMovieView: AlphaMovieView
    lateinit var rvRoot:RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_video)
        val videoPath = "android.resource://${packageName}/${R.raw.appadore_video}"
        var uri=Uri.parse(videoPath)
        alphaMovieView = findViewById(R.id.video_player)
        rvRoot = findViewById(R.id.rvRoot)
        rvRoot.setOnClickListener(this)
        try {
            alphaMovieView.setVideoFromUri(this,uri);
            alphaMovieView.setLooping(true)

        }catch (e:Exception){
            Log.d("Exception",e.toString())
        }


    }
    override fun onResume() {
        super.onResume()
        alphaMovieView.onResume()
    }

    override fun onPause() {
        super.onPause()
        alphaMovieView.onPause()
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.rvRoot->{
                finish()
            }
        }

    }
}