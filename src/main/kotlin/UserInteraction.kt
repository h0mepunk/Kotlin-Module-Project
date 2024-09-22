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

abstract class Menu(
    var exitToPrev: () -> Unit,

) {
    abstract val items: List<Action>

    abstract fun switchCommands()
}

class ArchiveMenu(
    exitToPrev: () -> Unit
) : Menu(exitToPrev) {
    override val items = listOf(Action.CREATE, Action.VIEW, Action.EXIT)


    override fun switchCommands() {
        UserInteraction().printMenu(items)
        when (UserInteraction().readCommand()) {
            0 -> Note().create()
            1 -> Archive().showNotes()
            2 -> exitToPrev()
            else -> {
                UserInteraction().incorrectInput(items.size)
                switchCommands()
            }
        }
    }
}

class MainMenu(
    exitToPrev: () -> Unit
) : Menu(exitToPrev) {
    override val items = listOf(Action.CREATE, Action.VIEW, Action.EXIT)

    var archives: MutableList<Archive> = mutableListOf()

    fun showArchives() {
        for (i in archives.indices) {
            println("$i. ${archives[i].name}")
        }
        println("Архивы показаны")
    }

    override fun switchCommands() {
        UserInteraction().printMenu(items)
        when (UserInteraction().readCommand()) {
            0 -> Archive().create()
            1 -> showArchives()
            2 -> exitToPrev()
            else -> {
                UserInteraction().incorrectInput(items.size)
                switchCommands()
            }
        }
    }
}

class NoteMenu(
    exitToPrev: () -> Unit
) : Menu(exitToPrev) {
    override val items = listOf(Action.VIEW, Action.EXIT)


    override fun switchCommands() {
        UserInteraction().printMenu(items)
        when (UserInteraction().readCommand()) {
            0 -> Note().show()
            1 -> exitToPrev()
            else -> {
                UserInteraction().incorrectInput(items.size)
                switchCommands()
            }
        }
    }
}