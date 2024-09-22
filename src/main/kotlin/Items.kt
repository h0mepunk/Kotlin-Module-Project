class Archive {
    var name: String? = ""
    var notes: MutableList<Note> = mutableListOf()

    fun create() {
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

    fun showNotes() {
        for (i in notes.indices) {
            println("$i. ${notes[i].name}")
        }
        println("Заметки показаны")
    }
}

class Note {
    var name: String? = ""
    var text: String? = ""

    fun create() {
        println("Введите название заметки")
        name = readLine() ?: ""
        println("Заметка создана")
    }

    fun inputText() {
        println("Введите текст заметки")
        text = readLine() ?: ""
        println("Текст заметки введён")
    }

    fun show() {
        println(text)
        println("Заметка показана")
    }
}