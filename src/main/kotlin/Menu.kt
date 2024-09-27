import kotlin.system.exitProcess

abstract class Menu(
    private val menuItems: List<MenuItem> = listOf(),
    val exitToPrev: () -> Unit
) {
    fun switchCommands() {
        while (true) {
            userInteraction.printMenu(menuItems)
            val command = userInteraction.readCommand()
            if (command == menuItems.indices.last){
                exitToPrev()
                return
            }
            else if (command in menuItems.indices) {
                menuItems[command].command()
                return
            } else {
                userInteraction.incorrectInput(menuItems.size)
                switchCommands()
            }
        }
    }

    private var userInteraction = UserInteraction()
}

class MenuItem(
    val title: String,
    val command: () -> Unit
)

class MenuItems(
    private var currentNote: Note? = null,
    private var currentArchive: Archive = Archive(),
    private val archives: Archives = Archives(),
) {
    private val noteMenuItems: List<MenuItem> = listOf(
        MenuItem("Add text") {
            currentNote?.inputText() ?: println("Note is not created")
            noteMenu.switchCommands()
        },
        MenuItem(
            "Exit to archive"
        ) {
            archiveMenu.switchCommands()
        }
    )

    private val mainMenuItems: List<MenuItem> = listOf(
        MenuItem(
            "Create archive"
        ) {
            archives.itemsList += (Archive().create())
            archiveMenu.switchCommands()
        },
        MenuItem(
            "Show archives"
        ) {
            archives.showArchives()
            archivesListMenu.switchCommands()
        },
        MenuItem(
            "Exit to desktop"
        ) {
            exitProcess(0)
        }
    )

    private val archiveMenuItems: List<MenuItem> = listOf(
        MenuItem(
            "Create note"
        ) {
            currentNote = Note().create()
            currentArchive.addNote(currentNote!!)
            noteMenu.switchCommands()
        },
        MenuItem(
            "Show notes"
        ) {
            currentArchive.showNotes()
            notesListMenu.switchCommands()
        },
        MenuItem(
            "Exit to archives list"
        ) {
            archivesListMenu.switchCommands()
        }
    )

    private val archivesListMenuItems: List<MenuItem> = listOf(
        MenuItem("Choose archive") {
            currentArchive = archives.choose()
            archiveMenu.switchCommands()
        },
        MenuItem("Exit to main menu") {
            mainMenu.switchCommands()
        }
    )


    private val notesListMenuItems: List<MenuItem> = listOf(
        MenuItem("Choose note") {
            currentNote = currentArchive.chooseNote()
            noteMenu.switchCommands()
        },
        MenuItem("Exit to archive menu") {
            archiveMenu.switchCommands()
        }
    )

    val mainMenu = MainMenu(mainMenuItems) { exitProcess(0) }

    private val archiveMenu = ArchiveMenu( archiveMenuItems) { mainMenu.switchCommands() }
    private val noteMenu = NoteMenu(noteMenuItems) { archiveMenu.switchCommands() }
    private val notesListMenu = NotesListMenu( notesListMenuItems) { noteMenu.switchCommands() }
    private val archivesListMenu = ArchivesListMenu( archivesListMenuItems) { archiveMenu.switchCommands() }
}

class ArchiveMenu(items: List<MenuItem>, exitToPrev: () -> Unit): Menu(items, exitToPrev)

class MainMenu(items: List<MenuItem>, exitToPrev: () -> Unit) : Menu(items, exitToPrev)

class NoteMenu(items: List<MenuItem>, exitToPrev: () -> Unit) : Menu(items, exitToPrev)

class ArchivesListMenu(items: List<MenuItem>, exitToPrev: () -> Unit) : Menu(items, exitToPrev)

class NotesListMenu(items: List<MenuItem>, exitToPrev: () -> Unit) : Menu(items, exitToPrev)