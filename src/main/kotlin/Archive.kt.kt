class Archive : Item() {
    override var name: String? = ""
    var notes: Notes = Notes()

    override fun create(): Archive {
        println("Enter archive name")
        name = readLine() ?: ""
        println("Archive created")
        return this
    }

    override fun show() {
        for (i in notes.itemsList.indices) {
            println("$i. ${notes.itemsList[i].name}")
        }
        println("Notes shown")
    }
}