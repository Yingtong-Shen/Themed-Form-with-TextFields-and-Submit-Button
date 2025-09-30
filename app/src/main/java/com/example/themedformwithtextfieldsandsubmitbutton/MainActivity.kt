package com.example.themedformwithtextfieldsandsubmitbutton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.themedformwithtextfieldsandsubmitbutton.ui.theme.ThemedFormWithTextFieldsAndSubmitButtonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThemedFormWithTextFieldsAndSubmitButtonTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    LoginFormScreen()
                }
            }
        }
    }
}

@Composable
fun LoginFormScreen() {
    // 使用 rememberSaveable 防止旋转后丢失
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var usernameError by rememberSaveable { mutableStateOf(false) }
    var passwordError by rememberSaveable { mutableStateOf(false) }
    var submitted by rememberSaveable { mutableStateOf(false) }

    val titleStyle = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("CS501 · Q5 Login Form", style = titleStyle, color = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.height(24.dp))

        // Username
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                if (usernameError && it.isNotBlank()) usernameError = false
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Username", style = MaterialTheme.typography.labelLarge
            ) },
            singleLine = true,
            isError = usernameError,
            supportingText = {
                if (usernameError) Text("Username can't be empty", color = MaterialTheme.colorScheme.error)
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )

        Spacer(Modifier.height(16.dp))

        // Password
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                if (passwordError && it.isNotBlank()) passwordError = false
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Password", style = MaterialTheme.typography.labelLarge) },
            singleLine = true,
            isError = passwordError,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            supportingText = {
                if (passwordError) Text("Password can't be empty", color = MaterialTheme.colorScheme.error)
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                submitted = true
                usernameError = username.isBlank()
                passwordError = password.isBlank()
                // 如需提交成功后的逻辑，可在两个都不报错时执行
                // if (!usernameError && !passwordError) { /* do login */ }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = username.isNotBlank() || password.isNotBlank()
        ) {
            Text("Submit", style = MaterialTheme.typography.labelLarge)
        }

        if (submitted && !usernameError && !passwordError) {
            Spacer(Modifier.height(12.dp))
            Text(
                "Submitted ✓",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun GreetingPreview() {
    ThemedFormWithTextFieldsAndSubmitButtonTheme {
        LoginFormScreen()
    }
}