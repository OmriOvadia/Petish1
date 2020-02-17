package com.example.petish.Activities

import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle

import com.example.petish.R

import java.util.concurrent.TimeUnit

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class SplashScreen : AppCompatActivity() {


    private val disposables: CompositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


    }

    override fun onResume() {
        super.onResume()
        disposables.add(Observable.timer(3, TimeUnit.SECONDS).subscribe {
            run {
                val intent = Intent(this@SplashScreen, SignInActivity::class.java)
                startActivity(intent)
            }
        })

    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }


}
