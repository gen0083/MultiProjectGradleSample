package jp.gcreate.sample.multiprojectgradlesample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dagger.hilt.android.AndroidEntryPoint
import jp.gcreate.sample.multiplatformsample.share.Greeting
import java.lang.RuntimeException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val greeting = Greeting()
        setContent {
            val state by viewModel.state.collectAsState()
            val book by viewModel.book.collectAsState()

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
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
                Button(onClick = { throw RuntimeException("test crash") }) {
                    Text(text = "Test Crash")
                }
                Text(text = greeting.greet())
                Button(onClick = { viewModel.increment() }) {
                    Text(text = "value is $state")
                }
                Button(onClick = { viewModel.getBook() }) {
                    Text(text = "get from database")
                }
                Text(text = book)
            }
        }
    }
}