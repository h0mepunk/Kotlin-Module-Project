import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val menuItems = MenuItems(Note(), Archive(), Archives())


    menuItems.mainMenu.switchCommands()

}

class MenuItems(
    var currentNote: Note = Note(),
    var currentArchive: Archive = Archive(),
    val archives: Archives = Archives(),
) {
    val noteMenuItems: List<MenuItem> = listOf(
        MenuItem("Add text") {
            currentNote.inputText()
            noteMenu.switchCommands()
        },
        MenuItem("Show notes") {
            currentArchive.notes.showNotes()
            notesListMenu.switchCommands()
        },
        MenuItem(
            "Exit", {archiveMenu.switchCommands() })
    )

    val mainMenuItems: List<MenuItem> = listOf(
        MenuItem(
            "Create archive"
        ) {
            archives.itemsList.add(Archive().create())
            archiveMenu.switchCommands()
        },
        MenuItem(
            "Show archives"
        ) {
            archives.showArchives()
            archivesListMenu.switchCommands()
        },
        MenuItem(
            "Exit"
        ) { exitProcess(0) }
    )

    val archiveMenuItems: List<MenuItem> = listOf(
        MenuItem(
            "Create archive"
        ) {
            archives.itemsList.add(Archive().create())
            archiveMenu.switchCommands()
        },
        MenuItem(
            "Show archives"
        ) {
            archives.showArchives()
            archivesListMenu.switchCommands()
        },
        MenuItem(
            "Exit to prev"
        ) { mainMenu.switchCommands() }
    )

    val archivesListMenuItems: List<MenuItem> = listOf(
        MenuItem(
            "Show notes"
        ) {
            currentArchive = archives.choose()
            currentArchive.notes.showNotes()
            notesListMenu.switchCommands()
        },
        MenuItem("Create note") {
            currentArchive.notes.itemsList.add(Note().create())
            noteMenu.switchCommands()
                                },
        MenuItem(
            "Exit to prev", { archiveMenu.switchCommands() })
    )


    val notesListMenuItems: List<MenuItem> = listOf(
        MenuItem("Show notes") {
            currentArchive.notes.showNotes()
            currentNote = currentArchive.notes.choose()
            notesListMenu.switchCommands()
        },
        MenuItem("Exit to prev", { archiveMenu.switchCommands() })
    )

    val mainMenu = MainMenu(mainMenuItems, { exitProcess(0) })

    val archiveMenu = ArchiveMenu( archiveMenuItems, {mainMenu.switchCommands()})
    val noteMenu = NoteMenu(noteMenuItems, {archiveMenu.switchCommands()})
    val notesListMenu = NotesListMenu( notesListMenuItems, {noteMenu.switchCommands()})
    val archivesListMenu = ArchivesListMenu( archivesListMenuItems, {archiveMenu.switchCommands()})
}


//Приложение «Заметки» должно иметь возможность:
//Добавлять архивы (наборы заметок);
//Добавлять заметки в архивы;
//Просматривать заметки.
//Важно, чтобы пользователь мог в любой момент выйти с экрана выбора архива, заметок или заметки и попасть на предыдущий список.


//Итого получится пять экранов:
    //Выбор архива.
    //Создание архива.
    //Выбор заметки.
    //Создания заметки.
    //Экран заметки. На экране заметки должна быть возможность вывода текста заметки.


//Эти пять экранов условно разделим на две группы:

    //Выбор элемента из списка и создание объекта.
    //Выбор архива, выбор заметки, экран заметки — это меню выбора.

//Например, выбор архива выглядит так:
    //Список архивов:
    //0. Создать архив
    //1. Это мой уже созданный архив
    //2. Выход

//Такое количество экранов похоже на навигацию в реальном приложении — у пользователя всегда есть возможность выйти с любого экрана или пойти дальше.
//Наша схема навигации выглядит так:

//image

//Важно: выход из программы есть только один.
// Во всех остальных случаях мы возвращаемся на предыдущий экран.

//Выбор пункта меню на каждом экране должен корректно реагировать на неправильный ввод:
//Если человек ввёл не цифру, то программа должна сказать ему, что следует вводить цифру,
// и ещё раз показать пункты меню.

//Если человек ввёл цифру, но такого элемента нет на экране,
// то программа должна сказать, что такой цифры нет, снова показать пункты меню и предложить
//ввести корректный символ.

//Для каждого экрана нужно написать всю эту логику.
// Но мы знаем, как избежать повторов в коде, поэтому все экраны с выбором элементов в меню сделаем через общий код.
//Можно заметить, что у экранов выбора общая навигация и ввод. Именно это и надо вынести в отдельный класс:


//Критерии успешного выполнения задания:
    //Есть меню с возможностью добавления и просмотра архивов.
    //Есть меню с возможностью добавления и просмотра заметок.
    //Есть возможность добавлять и просматривать текст заметок.
    //Не должно быть повторений одного и того же кода.
    // Вся логика по считыванию ввода пользователя и вывода пунктов на экран должна переиспользовать один и тот же код.
    //Ошибочный ввод пользователя должен корректно обрабатываться.
    //Приложение не позволяет создавать архив или заметку без имени (с пустым именем).
    //Приложение не позволяет создавать заметку без содержания (с пустым текстом).
//Из любого меню можно выйти и попасть на предыдущее меню или выйти из программы, если это просмотр архива.
//Приложение успешно компилируется и выполняется без ошибок.
//Весь код не написан в одном файле Main.
//Весь код приложения написан на Kotlin.
//Подсказки
//Рекомендуем начать с самого первого меню — архивы.
//
// Затем переместить общую логику в отдельный класс и свериться с требованиями к заданию.
//Далее оставшиеся меню будет написать намного легче.
//Каждое меню советуем делать в отдельном файле, чтобы проще было ориентироваться.
//Для переиспользования общего кода рекомендуем использовать отдельный класс, который содержит код:
//По выводу пунктов меню;
//По чтению пользовательского ввода;
//По выполнению определённых операций на выбор пункта меню.
//Для всего этого советуем использовать изменяемый список, который содержит в себе название пункта и лямбду, которая вызовется при выборе этого пункта.
//Для ввода стоит использовать бесконечный цикл, который повторяется до тех пор, пока пользователь не выберет выход.
//Все данные должны храниться в памяти и удаляться после завершения программы.
//Само чтение из консоли можно реализовать через Scanner. Для этого добавьте в начало файла import java.util.Scanner и в месте кода, где хотите прочитать строчку из консоли, введите Scanner(System.`in`).nextLine().
//Сдача задания на проверку
//Если у вас получилось написать код приложения «Заметки», прикрепляйте ссылку на ваш Pull Request через кнопку «Сдать работу» ниже. Ревьюер проверит задание и обязательно вернётся с обратной связью.
//Если вам нужна помощь, напишите нам в Пачку!