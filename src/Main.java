/**
 * Точка входа в программу анализа метеоданных.
 */
public class Main {
    /**
     * Создаёт части MVC-программы и запускает контроллер.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {

        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        controller.run();
    }
}
