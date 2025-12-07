package me.bannock.theworld.data.land.factory;

import com.github.czyzby.noise4j.map.Grid;
import com.github.czyzby.noise4j.map.generator.noise.NoiseGenerator;
import com.github.czyzby.noise4j.map.generator.util.Generators;
import me.bannock.theworld.data.land.BasicLandLotImpl;
import me.bannock.theworld.data.land.LandLot;
import me.bannock.theworld.data.land.feature.impl.StaticFoodFeature;
import me.bannock.theworld.data.land.feature.impl.StaticWaterFeature;

import java.util.concurrent.ThreadLocalRandom;

public class NoiseLandLotFactoryImpl implements LandLotFactory {

    public NoiseLandLotFactoryImpl(){
        NoiseGenerator noiseGenerator = new NoiseGenerator();
        int seed = Generators.rollSeed();
        noiseStage(seed, waterGrid, noiseGenerator, 48, 1f);
        noiseStage(seed, waterGrid, noiseGenerator, 64, 1.2f);
        noiseStage(seed, waterGrid, noiseGenerator, 128, 1.3f);
        noiseStage(seed, waterGrid, noiseGenerator, 20  , 1.5f);

//        noiseStage(seed, waterGrid, noiseGenerator, 16, 1f);
        noiseStage(seed, foodGrid, noiseGenerator, 15, 1f);
    }

    private void noiseStage(int seed, final Grid grid, final NoiseGenerator noiseGenerator, final int radius,
                                   final float modifier) {
        noiseGenerator.setRadius(radius);
        noiseGenerator.setModifier(modifier);
        // Seed ensures randomness, can be saved if you feel the need to
        // generate the same map in the future.
        noiseGenerator.setSeed(seed);
        noiseGenerator.generate(grid);
    }

    private final Grid waterGrid = new Grid(1000, 1000);
    private final Grid foodGrid = new Grid(1000, 1000);

    @Override
    public LandLot generateLandLot(int x, int y) {
        LandLot landLot = new BasicLandLotImpl();
        if (x < 0 || x > foodGrid.getWidth() || y < 0 || y > foodGrid.getHeight())
            return landLot;

        landLot.addFeature(new StaticWaterFeature(300));
        landLot.addFeature(new StaticFoodFeature(300));

        float resourceAmount = 10_000;
        float food = foodGrid.get(x, y);
        if (food < 0.5f)
            landLot.addFeature(new StaticFoodFeature(resourceAmount * food * 30 * (x % 100 == 0 && y % 100 == 0 ? 2 : 1)));

        float water = waterGrid.get(x, y);
        if (water > 0.5f)
            landLot.addFeature(new StaticWaterFeature(resourceAmount * water));

        return landLot;
    }

}
