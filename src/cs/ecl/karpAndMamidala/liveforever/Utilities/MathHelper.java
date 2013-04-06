package cs.ecl.karpAndMamidala.liveforever.Utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dale
 * Date: 2013-04-05
 * Time: 11:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class MathHelper {
    static public float getMax(List<Float> wl) {
        return Collections.max(wl);
    }

    static public float getMin(List<Float> wl) {
        return Collections.min(wl);
    }

    static public float getAvg(List<Float> wl) {
        float rv = 0;
        for (float i: wl) {
            rv += i;
        }
        return rv / wl.size();
    }

    static public float getMedian(List<Float> wl) {
        int middleIdx = wl.size() / 2;
        if (middleIdx % 2 == 1) {
            return wl.get(middleIdx);
        }
        else {
            float rv = wl.get(middleIdx) + wl.get(middleIdx - 1) / 2;
            return rv;
        }
    }

    static public float getMode(List<Float> wl) {
        float rv = 0;
        float highestFreq = 0;

        int size = wl.size();
        for (int i = 0; i < size; ++i) {
            int c = 0;
            for (int x = 0; x < size; ++x) {
                if (wl.get(i) == wl.get(x)) c++;
            }
            if (c > highestFreq) {
                highestFreq = c;
                rv = wl.get(i);
            }
        } // end for
        return rv;
    } // end getMode()

    static public float getRange(List<Float> wl) {
        float min = MathHelper.getMin(wl);
        float max = MathHelper.getMax(wl);
        return max - min;
    }

    static public float getVariance(List<Float> wl) {
        float avg = MathHelper.getAvg(wl);

        List<Float> subMean = new ArrayList<Float>();
        for (float i: wl) {
            subMean.add(i - avg);
        }

        List<Float> squared = new ArrayList<Float>();
        for(float i: subMean) {
            squared.add(i * i);
        }

        float addedSquares = 0;
        for(float i: squared) {
            addedSquares += i;
        }
        return (addedSquares / (wl.size() - 1));
    }

    static public double getStandardDeviation(List<Float> wl) {
        float variance = MathHelper.getVariance(wl);
        return Math.sqrt(variance);
    }


}
