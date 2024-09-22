import javax.swing.text.Position
import kotlin.system.exitProcess

class UserInteraction {
    fun readCommand(): Int {
        return readLine()?.toIntOrNull() ?: -1
    }

    fun printMenu(menu: List<Action>) {
        menu.forEachIndexed { index, item ->
            println("$index. $item")
        }
    }

    fun incorrectInput(lastPosition: Int) {
        println("Incorrect input. Please enter a number from 0 to $lastPosition")
    }
}

enum class Action {
    CREATE,
    VIEW,
    EXIT
}

abstract class Menu {
    abstract val items: List<Action>

    abstract fun exitToPrev()

    abstract fun switchCommands()
}

class ArchiveMenu : Menu() {
    override val items = listOf(Action.CREATE, Action.VIEW, Action.EXIT)

    override fun exitToPrev() {

    }

    override fun switchCommands() {
        UserInteraction().printMenu(items)
        when (UserInteraction().readCommand()) {
            0 -> Note.create()
            1 -> Notes.show()
            2 -> exitToPrev()
            else -> {
                UserInteraction().incorrectInput(items.size)
                switchCommands()
            }
        }
    }
}

class MainMenu : Menu() {
    override val items = listOf(Action.CREATE, Action.VIEW, Action.EXIT)

    override fun exitToPrev() {
        exit()
    }

    override fun switchCommands() {
        UserInteraction().printMenu(items)
        when (UserInteraction().readCommand()) {
            0 -> Archive.create()
            1 -> Archives.show()
            2 -> exitToPrev()
            else -> {
                UserInteraction().incorrectInput(items.size)
                switchCommands()
            }
        }
    }
}

class NoteMenu : Menu() {
    override val items = listOf(Action.VIEW, Action.EXIT)

    override fun exitToPrev() {
        archiveMenu()
    }

    override fun switchCommands() {
        UserInteraction().printMenu(items)
        when (UserInteraction().readCommand()) {
            0 -> viewNote()
            1 -> exitToPrev()
            else -> {
                UserInteraction().incorrectInput(items.size)
                switchCommands()
            }
        }
    }
}