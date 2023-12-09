package com.ahmed.transactionsfilterapp.data

import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate
import java.time.format.DateTimeFormatter


//Filtering the transactions by date
fun filterByDate(context: Context, startDate: String, endDate: String) {

    val firestore = FirebaseFirestore.getInstance()
    val email = "ahmed.mohamed.makawi@gmail.com"

    val formatter = DateTimeFormatter.ofPattern("d-M-yyyy")

// Parse the date strings into LocalDate objects
    val date1 = LocalDate.parse(startDate, formatter)
    val date2 = LocalDate.parse(endDate, formatter)
    val result = mutableListOf<String>()

    firestore.collection("transactions")
        .get()
        .addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot) {

                val currentEmail = document.getString("email")
                if (currentEmail == email) {
                    val currentDate = document.getString("date")
                    val givenDate = LocalDate.parse(currentDate, formatter)

                    if ((givenDate.isAfter(date1) || givenDate.isEqual(date1)) && (givenDate.isBefore(
                            date2
                        ) || givenDate.isEqual(date2))
                    ) {
                        result.add("${document.getString("date")}  : ${document.getString("amount")} ")
                    }
                }
            }
            if (result.isEmpty()) {
                Toast.makeText(
                    context,
                    "there is no transactions",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                // create and send email
                createPDF(result, context)
            }
        }
}