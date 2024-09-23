class Note : Item() {
    override var name: String? = ""
    var text: String? = ""

    override fun create(): Note {
        println("Input note name")
        name = readlnOrNull() ?: ""
        println("Note created")
        return this
    }

    fun inputText() {
        println("Enter note text")
        text = (text +  readlnOrNull())
        println("Text added")
    }

    override fun show() {
        println(text)
        println("Note shown")
    }
}