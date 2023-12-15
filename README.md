
# bank transactions filter

 ### What this app has:
- A basic API that accepts a period of time (two dates) and the user's email address ID.
- The database can be a CSV/json file.
- A database service that collects the relevant transactions.
- A PDF generation service that takes in the above data and generates a PDF of the transaction list
- An email service that sends the above PDF to the user's email address as an attachment


[video](https://github.com/Ahmed-makawi/bank-transactions-filter/assets/119809534/51851c01-8531-436a-bb60-2cd73710a465)


### What this app uses:
- javax.mail
- Firebase
- PdfDocument


### javax.mail
javax.mail is a Java API that provides classes and interfaces for sending, receiving, and manipulating email messages. It is part of the JavaMail API, which is included in the Java EE (Enterprise Edition) platform and can also be used in Java SE (Standard Edition) applications.

The javax.mail API allows you to perform various email-related tasks, such as sending email messages, reading email from a mail server, managing mail folders, and working with attachments. It provides a high-level abstraction for working with email, hiding the complexities of the underlying protocols (such as SMTP, POP3, and IMAP) and providing a consistent interface across different mail servers.


### Firebase
Firebase is a comprehensive mobile and web application development platform provided by Google. It offers a wide range of services and tools that help developers build, improve, and scale their applications quickly and efficiently. Firebase provides both backend and frontend services, allowing developers to focus on building great user experiences without worrying about infrastructure management.

it offers: 
- Real-time Database
- Authentication
- Cloud Firestore
- Hosting
- Cloud Messaging


### PdfDocument
PdfDocument is a class provided by the Android framework that allows you to create and manipulate PDF documents programmatically. It is part of the Android PDF Rendering Library, which was introduced in Android API level 21 (Android 5.0 Lollipop) and is available in the android.graphics.pdf package.

With PdfDocument, you can generate PDF files dynamically by adding text, images, shapes, and other graphical elements to the document. You can also set properties such as page size, margins, and compression options.

