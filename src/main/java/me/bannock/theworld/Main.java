package me.bannock.theworld;

import com.formdev.flatlaf.FlatLightLaf;
import me.bannock.theworld.ui.swing.SwingUserInterfaceImpl;
import me.bannock.theworld.ui.UserInterface;

public class Main {

    public static int starve = 0, parch = 0;
    private static int topPop = 0, test = 0;

    public static void main(String[] args) {
        FlatLightLaf.setup();
        UserInterface userInterface = new SwingUserInterfaceImpl();
        userInterface.createWindow();
        userInterface.setWindowVisible(true);
    }

//    private static FileOutputStream fileOutputStream;
//
//    static {
//        try {
//            fileOutputStream = new FileOutputStream(new File("%d.csv".formatted(System.currentTimeMillis())));
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static void main(String[] args) {
//        World world = new ArrayWorldImpl();
//        System.out.printf("Created world with size %dx%d\n", world.getWidth(), world.getHeight());
//        printWorldStats(world);
//
//        int day = 0;
//        try{
//            while (true){
//                System.out.printf("\n-DAY %d------------------------------\n", ++day);
//                world.simulate();
//                printWorldStats(world);
//            }
//        }catch (Throwable t){
//            printWorldStats(world);
//        }
//    }

//    private static void printWorldStats(World world){
//        float waterMl = 0;
//        float foodCals = 0;
//        for (int x = 0; x < world.getWidth(); x++){
//            for (int y = 0; y < world.getHeight(); y++){
//                LandFeature feature = world.getLand(x, y).getFeatures();
//                while(feature != null){
//                    switch (feature.getType()){
//                        case WATER:{
//                            waterMl += feature.getCount();
//                        }break;
//                        case FOOD:{
//                            foodCals += feature.getCount();
//                        }break;
//                    }
//                    feature = feature.getNext();
//                }
//            }
//        }
////        topPop = Math.max(topPop, world.getEntities().size());
////        System.out.printf("\n STATS: %fmL water; %f cals; entities remaining/top pop: %d/%d \n",
////                waterMl, foodCals, world.getEntities().size(), topPop);
////        System.out.printf(" DEATHS: %d starved; %d parched\n", starve, parch);
//        System.out.printf("DEATHS: %d starved; %d parched- Lives created: %d- current pop/total: %f%%\n",
//                starve, parch, Entity.globalId,
//                (world.getEntities().size() / (float)Entity.globalId) * 100);
//        try {
//            fileOutputStream.write("%d,%d,\n".formatted(++test, world.getEntities().size()).getBytes(StandardCharsets.UTF_8));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        starve = 0;
//        parch = 0;
//    }

}
