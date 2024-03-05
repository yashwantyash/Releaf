package com.releaf.releaf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.releaf.releaf.database.ReleafDatabase
import com.releaf.releaf.models.User
import com.releaf.releaf.navigation.SetupNavigation
import com.releaf.releaf.ui.theme.ReLeafTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    lateinit var database: ReleafDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = ReleafDatabase.getDatabase(this)

        GlobalScope.launch {
            database.userDao().insertUser(
                User(
                    fullName = "John Doe",
                    phone = "1234567890",
                    email = "email"
                )
            )
        }
        setContent {
            ReLeafTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupNavigation(database)
                }
            }
        }
    }
}

