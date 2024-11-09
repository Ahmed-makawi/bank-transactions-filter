package com.ahmed.transactionsfilterapp.data

import java.io.File
import javax.activation.DataHandler
import javax.activation.FileDataSource
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart


//Sending the pdf through the email
fun sendEmailWithPdf(file: File) {

    try {

        val stringSenderEmail = "YOUR_EMAIL@gmail.com"
        val stringReceiverEmail = "ahmed.mohamed.makawi@gmail.com"
        val stringPasswordSenderEmail = ""// the 16 chars from google 2-step Verification in your account setting

        val stringHost = "smtp.gmail.com"

        val properties = System.getProperties()

        properties.put("mail.smtp.host", stringHost)
        properties.put("mail.smtp.port", "465")
        properties.put("mail.smtp.ssl.enable", "true")
        properties.put("mail.smtp.auth", "true")

        val session = Session.getInstance(properties, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail)
            }
        })

        val mimeMessage = MimeMessage(session)
        mimeMessage.addRecipient(Message.RecipientType.TO, InternetAddress(stringReceiverEmail))

        mimeMessage.subject = "Filtered Transactions"
        mimeMessage.setText("This the the Filtered Transactions")

        val multipart = MimeMultipart()

        val messageBodyPart = MimeBodyPart()
        val source = FileDataSource(file)
        messageBodyPart.dataHandler = DataHandler(source)
        messageBodyPart.fileName = file.absolutePath
        multipart.addBodyPart(messageBodyPart)


        multipart.addBodyPart(messageBodyPart)

        mimeMessage.setContent(multipart)

        val thread = Thread {
            try {
                Transport.send(mimeMessage)
            } catch (e: MessagingException) {
                e.printStackTrace()
            }
        }
        thread.start()


    } catch (e: AddressException) {
        e.printStackTrace()
    } catch (e: MessagingException) {
        e.printStackTrace()
    }
}
