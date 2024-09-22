
class UserInteraction {
    fun readCommand(): Int {
        return readLine()?.toIntOrNull() ?: -1
    }

    fun printMenu(menu: List<MenuItem>) {
        menu.forEachIndexed { index, item ->
            println("$index. $item")
        }
    }

    fun incorrectInput(lastPosition: Int) {
        println("Incorrect input. Please enter a number from 0 to $lastPosition")
    }
}

abstract class Menu(
    val menuItems: List<MenuItem> = listOf()

) {
    fun switchCommands() {
        userInteraction.printMenu(menuItems)
        val command = userInteraction.readCommand()
        if (command in menuItems.indices ) {
            menuItems[command].command()
        } else {
            userInteraction.incorrectInput(menuItems.size)
            switchCommands()
        }
    }

    var userInteraction = UserInteraction()
}

class MenuItem(
    val title: String,
    val command: () -> Unit
)

class ArchiveMenu(items: List<MenuItem>): Menu(items)

class MainMenu(items: List<MenuItem>) : Menu(items)

class NoteMenu(items: List<MenuItem>) : Menu(items)