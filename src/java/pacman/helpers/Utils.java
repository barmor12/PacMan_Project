package pacman.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Utility class providing various helper methods.
 */
public class Utils {
    private static Map<Integer, Double> directionConverterMap = new HashMap<>();

    static {
        directionConverterMap.put(0, 0d);
        directionConverterMap.put(1, Math.PI);
        directionConverterMap.put(2, Math.PI / 2);
        directionConverterMap.put(3, Math.PI * (3 / 2));
    }

    /**
     * Calculate the distance between two points (xA, yA) and (xB, yB).
     *
     * @param xA The x-coordinate of the first point.
     * @param yA The y-coordinate of the first point.
     * @param xB The x-coordinate of the second point.
     * @param yB The y-coordinate of the second point.
     * @return The distance between the two points.
     */
    public static double getDistance(double xA, double yA, double xB, double yB) {
        return Math.sqrt(Math.pow(xB - xA, 2) + Math.pow(yB - yA, 2));
    }

    /**
     * Calculate the direction from point (xA, yA) to point (xB, yB).
     *
     * @param xA The x-coordinate of the starting point.
     * @param yA The y-coordinate of the starting point.
     * @param xB The x-coordinate of the target point.
     * @param yB The y-coordinate of the target point.
     * @return The direction in radians from the starting point to the target point.
     */
    public static double getDirection(double xA, double yA, double xB, double yB) {
        return Math.atan2((yB - yA), (xB - xA));
    }

    /**
     * Get the point at a certain distance and direction from a given point (x, y).
     *
     * @param x The x-coordinate of the starting point.
     * @param y The y-coordinate of the starting point.
     * @param distance The distance to the target point.
     * @param direction The direction in radians to the target point.
     * @return An array containing the x and y coordinates of the target point.
     */
    public static int[] getPointDistanceDirection(int x, int y, double distance, double direction) {
        int[] point = new int[2];
        point[0] = x + (int) (Math.cos(direction) * distance);
        point[1] = y + (int) (Math.sin(direction) * distance);
        return point;
    }

    /**
     * Convert a sprite direction to radians.
     *
     * @param spriteDirection The direction of the sprite.
     * @return The direction in radians.
     */
    public static double directionConverter(int spriteDirection) {
        return directionConverterMap.get(spriteDirection);
    }

    /**
     * Generate a random integer between 0 (inclusive) and n (exclusive).
     *
     * @param n The upper bound (exclusive).
     * @return A random integer between 0 and n-1.
     */
    public static int randomInt(int n) {
        Random r = new Random();
        return r.nextInt(n);
    }

    /**
     * Generate a random integer between min (inclusive) and max (exclusive).
     *
     * @param min The lower bound (inclusive).
     * @param max The upper bound (exclusive).
     * @return A random integer between min and max-1.
     */
    public static int randomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min) + min;
    }

    /**
     * Generate a random boolean value.
     *
     * @return A random boolean value.
     */
    public static boolean randomBool() {
        return (randomInt(2) == 1);
    }
}
