package com.wanda.idc;


import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        IdCardGenerator g = new IdCardGenerator();
        Date randomDate = DateRandom.randomDate("2011-07-25", "2016-07-25");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int money = 100 + (int)(Math.random()*400);
        String[] orgId = {"B003003H8000", "B003003H8001", "B003003H8002", "B003003H8003",
            "B003003H8004", "B003003H8005", "B003003H8006", "B003003H8007", "B003003H8008",
            "B003003H8009"};
        String[] type1 = {"1","2","3","4","5","6","7","8","9"};
        String[] type2 = {"1","21","22","3","4","51","52"};

        for (int k = 0; k < 10; ++k) {
            try {
                //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
                FileWriter writer = new FileWriter("blacklist" + k, true);
                for (int j = 0; j < 1000; ++j) {
                    String uid = g.generate();
                    String data = "\"" + uid + "\",\"" + orgId[k] + "," + RandomValue.getChineseName() + "," +
                            uid + "," + RandomValue.getTel() + "," + RandomValue.getRoad() + "," +
                            format.format(randomDate) + "," +
                            money + "," + type1[(int) (Math.random() * 9)] + "," +
                            type2[(int) (Math.random() * 7)] + "\"";
                    for (int i = 1; i < 500; ++i) {
                        uid = g.generate();
                        data += ",\"" + uid + "\",\"" + orgId[k] + "," + RandomValue.getChineseName() + "," +
                                uid + "," + RandomValue.getTel() + "," + RandomValue.getRoad() + "," +
                                format.format(randomDate) + "," +
                                money + "," + type1[(int) (Math.random() * 9)] + "," +
                                type2[(int) (Math.random() * 7)] + "\"";
                    }
                    data += "\n";
                    writer.write(data);
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
