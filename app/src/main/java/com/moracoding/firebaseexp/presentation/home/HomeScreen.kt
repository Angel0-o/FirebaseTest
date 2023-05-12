package com.moracoding.firebaseexp.presentation.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moracoding.firebaseexp.domain.model.User
import com.moracoding.firebaseexp.presentation.utils.CustomAlert
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    user: User = User(),
    viewModel: UsersViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val state = viewModel.createUserState.collectAsState(initial = null)
    val fbState = viewModel.tokenState.collectAsState(initial = null)
    var openDialog by remember { mutableStateOf(false) }
    var messageDialog = remember { mutableStateOf("") }

    viewModel.getFBToken()
    viewModel.createUser(user)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, end = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (openDialog) {
            CustomAlert(title = "Alert", message = messageDialog.value, openDialog = openDialog, onDismissDialog = {
                openDialog = false
            })
        }
        Text(
            text = "Home",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 50.sp,
            color = Color.DarkGray,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                scope.launch {
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
            Text(text = "Log out", color = Color.White, modifier = Modifier.padding(7.dp))
        }
        LaunchedEffect(key1 = state.value?.isSuccess) {
            scope.launch {
            }
        }
        LaunchedEffect(key1 = state.value?.isError) {
            scope.launch {
                if (state.value?.isError?.isNotEmpty() == true){
                    openDialog =true
                    messageDialog.value = "Error: ${state.value?.isError}"
                }
            }
        }
        LaunchedEffect(key1 = fbState.value?.isSuccess) {
            scope.launch {
                Log.d("FBM", "${fbState.value?.isSuccess}")
            }
        }
    }
}