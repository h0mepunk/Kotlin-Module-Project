abstract class Item {
    abstract var name: String?

    abstract fun show()

    abstract fun create(): Item
}

abstract class Items<T: Item> {
    abstract var itemsList: MutableList<T>
    val userInteraction: UserInteraction = UserInteraction()

    fun show() {
        for (i in itemsList.indices) {
            println("$i. ${itemsList[i].name}")
        }
    }

    fun choose(): T{
            println("Введите номер элемента")
            val noteNumber = userInteraction.readCommand()
            if (noteNumber in itemsList.indices) {
                itemsList[noteNumber].show()
                return itemsList[noteNumber]
            } else {
                println("Неверный номер элемента")
                choose()
            }
        return itemsList[noteNumber]
    }
}


class Archive : Item() {
    override var name: String? = ""
    var notes: Notes = Notes()

    override fun create(): Archive {
        println("Введите название архива")
        name = readLine() ?: ""
        println("Архив создан")
        return this
    }

    fun addNote() {
        val note = Note()
        note.create()
        note.inputText()
        notes.itemsList.add(note)
        println("Заметка добавлена")
    }

    override fun show() {
        for (i in notes.itemsList.indices) {
            println("$i. ${notes.itemsList[i].name}")
        }
        println("Заметки показаны")
    }
}

class Note : Item() {
    override var name: String? = ""
    var text: String? = ""

    override fun create(): Note {
        println("Введите название заметки")
        name = readLine() ?: ""
        println("Заметка создана")
        return this
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

    fun showNotes() {
        super.show()
        println("Заметки показаны")
    }
}
