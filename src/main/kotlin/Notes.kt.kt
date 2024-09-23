class Notes: Items<Note>() {
    override var itemsList: MutableList<Note> = mutableListOf()

    fun showNotes() {
        this.show()
        println("Notes shown")
    }
}