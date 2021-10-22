package com.example.androidsemthree

interface WriterTool {
    fun addText()

    fun sendText()

    fun applyChangesDefault() {
        println("Successful apply")
    }
}
