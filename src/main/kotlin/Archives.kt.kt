class Archives: Items<Archive>() {
    override var itemsList: MutableList<Archive> = mutableListOf()

    fun showArchives() {
        this.show()
        println("Archives shown")
    }
}