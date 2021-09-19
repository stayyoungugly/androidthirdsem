package com.example.androidsemthree

interface EditorTool {
    fun editText()

    fun sendChanges()

    fun deleteTextDefault() {
        println("Successful delete")
    }
}
