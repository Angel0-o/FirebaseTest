package com.moracoding.firebaseexp.presentation.signIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.moracoding.firebaseexp.R
import com.moracoding.firebaseexp.domain.model.User
import com.moracoding.firebaseexp.presentation.navigation.Screens
import com.moracoding.firebaseexp.presentation.utils.CustomAlert
import com.moracoding.firebaseexp.presentation.utils.FormTextField
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    navController: NavController, viewModel: SignInViewModel = hiltViewModel()
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signInState.collectAsState(initial = null)
    var openDialog by remember { mutableStateOf(false) }
    var messageDialog = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, end = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (openDialog) {
            CustomAlert(
                title = "Alert",
                message = messageDialog.value,
                openDialog = openDialog,
                onDismissDialog = {
                    openDialog = false
                })
        }
        Image(
            painter = painterResource(id = R.drawable.ic_lynx),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Sign In",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 50.sp,
            color = Color.DarkGray,
            fontFamily = FontFamily.Serif
        )
        FormTextField(value = email, label = "Email", onValueChange = { email = it })
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                disabledLabelColor = Color.Green,
                unfocusedIndicatorColor = Color.Black,
                focusedIndicatorColor = Color.Black
            ),
            shape = RoundedCornerShape(25.dp),
            singleLine = true,
            label = { Text(text = "Password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Please provide localized description for accessibility services
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description)
                }
            }
        )
        Button(
            onClick = {
                scope.launch {
                    viewModel.signIn(email, password)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text(text = "Sign In", color = Color.White, modifier = Modifier.padding(7.dp))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (state.value?.isLoading == true) CircularProgressIndicator()
        }
        LaunchedEffect(key1 = state.value?.isSuccess) {
            scope.launch {
                if (!state.value?.isSuccess?.email.isNullOrEmpty()) {
                    val email = state.value?.isSuccess?.email
                    val access = state.value?.isSuccess?.tsLogIn
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "user",
                        value = User(
                            name = "",
                            email = email,
                            tsLogIn = access,
                        )
                    )
                    navController.navigate(Screens.Home.route)
                }
            }
        }
        LaunchedEffect(key1 = state.value?.isError) {
            scope.launch {
                if (state.value?.isError?.isNotEmpty() == true) {
                    openDialog = true
                    messageDialog.value = "Error: ${state.value?.isError}"
                }
            }
        }
    }
}