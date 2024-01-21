package jp.gcreate.sample.multiprojectgradlesample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Text(text = "Compose sample")
                Button(
                    onClick = {
                        startActivity(Intent(this@MainActivity, OssLicensesMenuActivity::class.java))
                    },
                ) {
                    Text(text = "LAUNCH")
                }
                Text(text = "This is compose")
            }
        }
    }
}