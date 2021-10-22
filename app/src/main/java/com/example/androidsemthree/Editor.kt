package com.example.androidsemthree

class Editor(
    email: String,
    password: String
) : User(email, password), EditorTool {

    override fun editText() {
        println("Text was edited")
    }

    override fun sendChanges() {
        println("Changes were sent")
    }
}

