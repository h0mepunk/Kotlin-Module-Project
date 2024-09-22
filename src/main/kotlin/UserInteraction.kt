
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
            if (command in menuItems.indices - 1 ) {
                menuItems[command].command()
            } else if (command == menuItems.indices.last) {
                exitToPrev()
            }
            else {
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