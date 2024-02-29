package com.releaf.releaf.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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
fun MyInputField(
    label: Int,
    leadIcon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit
) {
    var textValue by remember { mutableStateOf("") }
    OutlinedTextField(
        value = textValue,
        onValueChange = {
            textValue = it
            onValueChange(it)
        },
        modifier = Modifier
            .fillMaxWidth(),
        label = {
            Text(text = stringResource(label))
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            capitalization = KeyboardCapitalization.None
        ),
        leadingIcon = {
            Icon(
                imageVector = leadIcon,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (textValue.isNotBlank()) {
                IconButton(
                    onClick = {
                        textValue = ""
                    }
                ) {
                    Icon(Icons.Default.Clear, contentDescription = null)
                }
            }
        },
    )
}

@Composable
fun SigninBtn(
    btnTxtId: Int,
    navController: NavController,
    destinationScreen: String,
    onClick: () -> Unit
) {
    Button(
        onClick = {
            onClick()
//            navController.navigate(destinationScreen)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp),
        shape = RoundedCornerShape(10.dp)
    )
    {
        Text(
            text = stringResource(id = btnTxtId),
            fontSize = 18.sp
        )
    }
}

@Composable
fun SignupBtn(
    btnTxtId: Int,
    navController: NavController,
    destinationScreen: String,
    onClick: () -> Unit
) {
    Button(
        onClick = {
            onClick()
//            navController.navigate(destinationScreen)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp),
        shape = RoundedCornerShape(10.dp)
    )
    {
        Text(
            text = stringResource(id = btnTxtId),
            fontSize = 18.sp
        )
    }
}

@Composable
fun UnderlinedText(
    txtSize: TextUnit = 16.sp,
    value: String,
    navController: NavController,
    desScreen: String
) {
    Text(
        text = value,
        fontSize = txtSize,
        textDecoration = TextDecoration.Underline,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .clickable {
                navController.navigate(desScreen)
            }
    )
}

@Composable
fun MyNormalText(valueId: Int, txtSize: TextUnit = 16.sp) {
    Text(
        text = stringResource(id = valueId),
        fontSize = txtSize
    )
}

