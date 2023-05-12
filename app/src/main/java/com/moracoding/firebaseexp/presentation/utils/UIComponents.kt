package com.moracoding.firebaseexp.presentation.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FormTextField(value: String, label: String, onValueChange: (String) -> Unit) {
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Color.Black,
            disabledLabelColor = Color.Green,
            unfocusedIndicatorColor = Color.Black,
            focusedIndicatorColor = Color.Black,
        ),
        shape = RoundedCornerShape(25.dp),
        singleLine = true,
        label = { Text(text = label) },
    )
}

@Composable
fun CustomAlert(title:String, message:String,openDialog:Boolean, onDismissDialog:()-> Unit){
    if (openDialog){
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back button
                onDismissDialog()
            },
            title = {
                Text(text = title)
            },
            text = {
                Text(message)
            },
            confirmButton = {
                Button(
                    onClick = {
                        onDismissDialog()
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                      onDismissDialog()
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )
    }
}