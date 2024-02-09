package com.releaf.releaf.composables

import android.graphics.Outline
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.releaf.releaf.R


@Composable
fun LogoAndName(name: String) {
    Column(
//        verticalArrangement = Arrangement.Top
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .width(124.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = name,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
//            modifier = Modifier
        )
    }
}

@Composable
fun LoginForm() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
            },
            label = {
                Text(text = stringResource(id = R.string.outline_text1))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.None
            ),
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.person_black),
                    contentDescription = null
                )
            },

            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = stringResource(id = R.string.outline_text2))
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                capitalization = KeyboardCapitalization.None
            ),
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.lock_svgrepo_com),
                    contentDescription = null
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp),
            shape = RoundedCornerShape(10.dp)
        )
        {
            Text(
                text = stringResource(id = R.string.login_button),
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
//                    .align(Alignment.Bottom)
        ) {
            Text(
                text = stringResource(id = R.string.ask_signup)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Register Now!",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clickable {
//                        val navigate = Intent(this@LoginActivity, SignupActivity::class.java)
//                        startActivity(navigate)
                    }
            )
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}