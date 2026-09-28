import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Управляет работой программы: получает команды пользователя,
 * вызывает методы модели и передаёт результаты во View.
 */
public class Controller {
    private Model model;
    private View view;

    /**
     * Создаёт контроллер с указанными моделью и представлением.
     *
     * @param model модель с метеоданными
     * @param view объект, отвечающий за вывод информации
     */
    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
    }

    /**
     * Ожидает, пока пользователь нажмёт Enter.
     *
     * @param scanner объект для чтения ввода пользователя
     */
    private void waitForEnter(Scanner scanner) {
        System.out.println("\nНажмите Enter, чтобы продолжить...");
        scanner.nextLine();
        scanner.nextLine();
    }

    /**
     * Запускает главный цикл программы и обрабатывает пункты меню.
     */
    public void run(){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
         
        while (true){
            view.showMenu();
            choice = 0;
            int[] values = {1, 2, 3, 0};
            boolean isValid = false;
            try{
                choice = scanner.nextInt();
                for (int i = 0; i < values.length; i++){
                    if (choice == values[i]){isValid = true;}
                }
                if (isValid != true) {throw new InputMismatchException("");}
            } catch (InputMismatchException e) {
                System.out.println("Введите 1,2,3 или 0.");
                waitForEnter(scanner);
                continue;
            }
            switch (choice){
                case 1:
                    model.generate_new_values();
                    System.out.println("Данные перегенерированы!");
                    waitForEnter(scanner);
                    break;
                case 2:
                    view.showData(model);
                    waitForEnter(scanner);
                    break;
                case 3:
                    view.showAnalysis(model.get_rainfall_per_type(), model.get_season());
                    waitForEnter(scanner);
                    break;
                case 0:
                    return;
            }
        }
    }
}
