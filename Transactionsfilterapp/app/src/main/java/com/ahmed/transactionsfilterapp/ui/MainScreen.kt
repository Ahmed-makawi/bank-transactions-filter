package com.ahmed.transactionsfilterapp.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmed.transactionsfilterapp.data.filterByDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(20.dp)
        ) {

            var startDate by remember {
                mutableStateOf("")
            }
            var endDate by remember {
                mutableStateOf("")
            }


            Text(
                text = "Transactions Filter",
                fontSize = 24.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = startDate,
                onValueChange = { startDate = it },
                label = { Text(text = "start date") },
                placeholder = { Text(text = "30-12-2023") },
            )
            OutlinedTextField(
                value = endDate,
                onValueChange = { endDate = it },
                label = { Text(text = "end date") },
                placeholder = { Text(text = "30-12-2023") },

                )

            Spacer(modifier = Modifier.height(30.dp))

            Button(onClick = {
                if (!isDateFormatValid(startDate)) {
                    Toast.makeText(context, "wrong format in start date", Toast.LENGTH_SHORT).show()
                } else if (!isDateFormatValid(endDate)) {
                    Toast.makeText(context, "wrong format in end date", Toast.LENGTH_SHORT).show()
                } else {
                    filterByDate(
                        context = context,
                        startDate = startDate,
                        endDate = endDate
                    )
                }
            }) {
                Text(text = "Send To Me Via Email")
            }
        }
    }
}

fun isDateFormatValid(dateString: String): Boolean {
    val pattern = Regex("""\d{2}-\d{2}-\d{4}""") // Regex pattern for "dd-mm-yyyy" format

    return pattern.matches(dateString)
}


