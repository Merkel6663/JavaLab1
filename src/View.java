/**
 * Отвечает за вывод информации пользователю: меню, метеоданных
 * и результатов анализа.
 */
public class View {

    /**
     * Выводит меню доступных действий.
     */
    public void showMenu(){
        System.out.println("1. Перегенерировать данные");
        System.out.println("2. Вывести сненерированные данные");
        System.out.println("3. Вывести проанализированные данные");
        System.out.println("0. Выход");
        System.out.println("====================================");
        System.out.println("Выберите команду (1,2,3 или 0):");
    }

    /**
     * Выводит температуры и осадки за месяц.
     *
     * @param model модель, содержащая метеоданные
     */
    public void showData(Model model){
        System.out.print("Температура:\n"+model.temp[0]); 
        for (int i = 1; i<model.temp.length ; i++){ System.out.print(", "+model.temp[i]); }
        System.out.print("\n");
        System.out.print("Осадки:\n"+model.rainfall[0]); 
        for (int i = 1; i<model.rainfall.length ; i++){ System.out.print(", "+model.rainfall[i]); }
    }

    /**
     * Выводит результаты анализа метеоданных.
     *
     * @param rainfallData массив с количеством дождя и снега
     * @param season код времени года: 1 — зима, 2 — лето,
     *               3 — весна или осень
     */
    public void showAnalysis(float[] rainfallData, int season){
        switch (season){
            case 1:
                System.out.println("Время года - зима");
                break;
            case 2:
                System.out.println("Время года - лето"); 
                break;
            case 3:
                System.out.println("Время года - весна или осень");
                break;
        }
        System.out.println("Осадки в виде дождя: "+rainfallData[0]);
        System.out.println("Осадки в виде снега: "+rainfallData[1]);
    }
}
