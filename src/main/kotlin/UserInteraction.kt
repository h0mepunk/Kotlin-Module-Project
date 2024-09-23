class UserInteraction {
    fun readCommand(): Int {
        return readLine()?.toIntOrNull() ?: -1
    }

    fun printMenu(menu: List<MenuItem>) {
        menu.forEachIndexed { index, item ->
            println("$index. ${item.title}")
        }
    }

    fun incorrectInput(lastPosition: Int) {
        println("Incorrect input. Please enter a number from 0 to $lastPosition")
    }
}

abstract class Menu(
    val menuItems: List<MenuItem> = listOf(),
    val exitToPrev: () -> Unit
) {
    fun switchCommands() {
        while (true) {
            userInteraction.printMenu(menuItems)
            val command = userInteraction.readCommand()
            if (command == menuItems.indices.last){
                println("log? exit to prev")
                exitToPrev()
            }
            else if (command in menuItems.indices) {
               println("log? ${menuItems[command].title}")
                menuItems[command].command()
            } else {
                println("log? incorrect input + ${menuItems.size} + ${menuItems.indices} + ${menuItems.indices.last} + $command")
                userInteraction.incorrectInput(menuItems.size)
                switchCommands()
            }
        }
    }

    var userInteraction = UserInteraction()
}

class MenuItem(
    val title: String,
    val command: () -> Unit
)

class ArchiveMenu(items: List<MenuItem>, exitToPrev: () -> Unit): Menu(items, exitToPrev)

class MainMenu(items: List<MenuItem>, exitToPrev: () -> Unit) : Menu(items, exitToPrev)

class NoteMenu(items: List<MenuItem>, exitToPrev: () -> Unit) : Menu(items, exitToPrev)

class ArchivesListMenu(items: List<MenuItem>, exitToPrev: () -> Unit) : Menu(items, exitToPrev)

class NotesListMenu(items: List<MenuItem>, exitToPrev: () -> Unit) : Menu(items, exitToPrev)