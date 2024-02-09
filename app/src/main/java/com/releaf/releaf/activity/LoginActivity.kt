package com.releaf.releaf.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.releaf.releaf.R
import com.releaf.releaf.composables.LoginForm
import com.releaf.releaf.composables.LogoAndName
import com.releaf.releaf.ui.theme.ReLeafTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReLeafTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(stringResource(id = R.string.app_name))
                }
            }
        }
    }
}

@Composable
fun LoginScreen(name: String, modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoAndName(name)
        Spacer(modifier = Modifier.height(32.dp))
        LoginForm()
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview() {
    ReLeafTheme {
        LoginScreen(stringResource(id = R.string.app_name))
    }
}