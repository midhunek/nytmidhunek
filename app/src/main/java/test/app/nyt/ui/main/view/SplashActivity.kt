package test.app.nyt.ui.main.view

import android.annotation.SuppressLint
import android.content.Intent
import android.media.AudioManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject

class SplashActivity :  AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        moveToSignUp()

    }

    private  fun moveToSignUp() {
        Handler().postDelayed({
            if (!isFinishing) {
                var intent = Intent(this, NewsListActivity :: class.java)
                startActivity(intent)
                finish()
            }
        }, 2000)
    }

}
