package xyz.pixelatedw.MineMineNoMi3.api.math;

import java.util.Random;

public class WyMathHelper
{	
	public static double percentage(double i, double j) { return (i / 100) * j; }
	
    public static double randomWithRange (int min, int max) { return new Random().nextInt(max + 1 - min) + min;}

    public static float clampf(float val, float min, float max) { return Math.max(min, Math.min(max, val)); }
    
    public static double clampd(double val, double min, double max) { return Math.max(min, Math.min(max, val)); }
    
    public static int clampi(int val, int min, int max) { return Math.max(min, Math.min(max, val)); }
    
    public static double lerp(double a, double b, double f) { return a + (b - a) * f; }
}
