package com.ahmed.transactionsfilterapp.data

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.ahmed.transactionsfilterapp.R
import java.io.File
import java.io.FileOutputStream


// create pdf
fun createPDF(filteredData: MutableList<String>, context: Context) {

    val pageHeight = 1120
    val pageWidth = 792


    val pdfDocument = PdfDocument()
    val title = Paint()


    val myPageInfo: PdfDocument.PageInfo? =
        PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()


    val myPage: PdfDocument.Page = pdfDocument.startPage(myPageInfo)
    val canvas: android.graphics.Canvas? = myPage.canvas


    title.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)

    title.textSize = 45F


    title.color = ContextCompat.getColor(context, R.color.black)


    canvas?.drawText("Filtered Transactions", 209F, 80F, title)
    title.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
    title.color = ContextCompat.getColor(context, R.color.black)
    title.textSize = 30F

    var yPos = 260F // Initial vertical position

    for (data in filteredData) {
        title.textAlign = Paint.Align.CENTER
        canvas?.drawText(data, 396F, yPos, title)

        yPos += title.textSize + 10F // Adjust the vertical position for the next line
    }


    pdfDocument.finishPage(myPage)


    val file = File(
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
        "filtered_transactions.pdf"
    )

    try {
        pdfDocument.writeTo(FileOutputStream(file))
        // Passing the pdf to email sender function
        sendEmailWithPdf(file)

        Toast.makeText(
            context,
            "PDF file generated",
            Toast.LENGTH_SHORT
        ).show()
    } catch (e: Exception) {

        e.printStackTrace()

        Toast.makeText(
            context,
            "Fail to generate PDF file",
            Toast.LENGTH_SHORT
        )
            .show()
    }
    pdfDocument.close()
}