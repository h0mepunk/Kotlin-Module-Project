class Archives: Items<Archive>() {
    override var itemsList: List<Archive> = listOf()

    fun showArchives() {
        this.show()
        println("Archives shown")
    }
}