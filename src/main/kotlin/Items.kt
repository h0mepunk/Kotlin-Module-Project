abstract class Item {
    abstract var name: String?

    abstract fun show()

    abstract fun create()
}

abstract class Items<T: Item> {
    abstract var itemsList: MutableList<T>

    fun show() {
        for (i in itemsList.indices) {
            println("$i. ${itemsList[i].name}")
        }
    }
}


class Archive : Item() {
    override var name: String? = ""
    private var notes: MutableList<Note> = mutableListOf()

    override fun create() {
        println("Введите название архива")
        name = readLine() ?: ""
        println("Архив создан")
    }

    fun addNote() {
        val note = Note()
        note.create()
        note.inputText()
        notes.add(note)
        println("Заметка добавлена")
    }

    override fun show() {
        for (i in notes.indices) {
            println("$i. ${notes[i].name}")
        }
        println("Заметки показаны")
    }
}

class Note : Item() {
    override var name: String? = ""
    var text: String? = ""

    override fun create() {
        println("Введите название заметки")
        name = readLine() ?: ""
        println("Заметка создана")
    }

    fun inputText() {
        println("Введите текст заметки")
        text = readLine() ?: ""
        println("Текст заметки введён")
    }

    override fun show() {
        println(text)
        println("Заметка показана")
    }
}

class Archives: Items<Archive>() {
    override var itemsList: MutableList<Archive> = mutableListOf()

    fun showArchives() {
        super.show()
        println("Архивы показаны")
    }
}

class Notes: Items<Note>() {
    override var itemsList: MutableList<Note> = mutableListOf()

    fun showNote() {
        super.show()
        println("Заметки показаны")
    }
}
