import java.util.Random;

/**
 * Хранит метеоданные за месяц и выполняет их обработку:
 * генерирует температуры и осадки, определяет тип осадков
 * и время года.
 */
public class Model {

    int[] temp = new int[30];
    float[] rainfall = new float[30];

    /**
     * Создаёт модель и генерирует начальные метеоданные.
     */
    public Model(){
        generate_new_values();
    }

    /**
     * Генерирует новые случайные значения температуры и осадков
     * для 30 дней месяца.
     */
    public void generate_new_values(){
        Random rand = new Random();
        for (int i = 0; i < temp.length; i++){
            temp[i] = rand.nextInt(60) - 30;
        }
        for (int i = 0; i < rainfall.length; i++){
            rainfall[i] = rand.nextInt(200)/10.0f;
        }
    }

    /**
     * Подсчитывает количество осадков в виде дождя и снега.
     * При температуре 0 градусов и выше осадки считаются дождём.
     *
     * @return массив из двух элементов: первый — количество дождя,
     *         второй — количество снега
     */
    public float[] get_rainfall_per_type(){ // result[0] - sum rain | result[1] sum snow
        float snow = 0;
        float rain = 0;
        for (int i = 0; i < temp.length && i < rainfall.length; i++){
            if (temp[i] < 0) {
                snow += rainfall[i];
            } else {rain += rainfall[i];}
        }
        float[] result = new float[2];
        result[0] = rain;
        result[1] = snow;
        return result;
    }

    /**
     * Определяет время года по температуре за месяц.
     *
     * @return 1 — зима, 2 — лето, 3 — весна или осень
     */
    public int get_season(){ // 1 - winter | 2 - summer | 3 other
        int TwoWeekFlag = 0;
        boolean MaxWinterFlag = false, MinWinterFlag = false, MaxSummerFlag = false, MinSummerFlag = false;

//winter check
        for (int i = 0; i < temp.length; i++){
            if (temp[i] < 5){
                 TwoWeekFlag++;
            } else { TwoWeekFlag = 0; }
            if (TwoWeekFlag == 14){
                MaxWinterFlag = true;
            }
        }
        TwoWeekFlag = 0;
        int MaxValueCheck = temp[0];
        for (int i = 0; i < temp.length; i++){
            if (MaxValueCheck > temp[i] ){
                MaxValueCheck = temp[i];
            }
        }
        if (MaxValueCheck < -7){ MinWinterFlag = true; }

        if (MaxWinterFlag && MinWinterFlag) { return 1; }


//winter avg check
        int SumWinterTemp = 0;
        for (int i = 0; i < temp.length; i++){
            SumWinterTemp += temp[i];
        }
        if ((double)SumWinterTemp/temp.length < 0) { return 1; }


//summer check
        for (int i = 0; i < temp.length; i++){
            if (temp[i] > 0){
                 TwoWeekFlag++;
            } else { TwoWeekFlag = 0; }
            if (TwoWeekFlag == 14){
                MinSummerFlag = true;
            }
        }
        TwoWeekFlag = 0;
        MaxValueCheck = temp[0];
        for (int i = 0; i < temp.length; i++){
            if (MaxValueCheck < temp[i] ){
                MaxValueCheck = temp[i];
            }
        }
        if (MaxValueCheck > 20){ MaxSummerFlag = true; }

        if (MaxSummerFlag && MinSummerFlag) { return 2; }

//summer avg check
        int SumSummerTemp = 0;
        for (int i = 0; i < temp.length; i++){
            SumSummerTemp += temp[i];
        }
        if ((double)SumSummerTemp/temp.length > 15) { return 2; }
        
        return 3;
    }
}
