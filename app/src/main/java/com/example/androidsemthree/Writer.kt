package com.example.androidsemthree

class Writer(
    email: String,
    password: String
) : User(email, password), WriterTool {

    override fun addText() {
        println("Text was added")
    }

    override fun sendText() {
        println("Text was sent")
    }
}
