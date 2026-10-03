import rendering.RasterRenderer;
import rendering.VectorRenderer;
import shapes.Circle;
import shapes.Shape;
import shapes.Square;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1 || !"--demo".equals(args[0])) {
            System.out.println("Usage: java -cp out Main --demo");
            return;
        }

        runDemo();
    }

    private static void runDemo() {
        int passed = 0;

        passed += checkResult(
                "T1",
                new Circle("circle-1", 2, new VectorRenderer()),
                "Circle + VectorRenderer",
                "VECTOR circle radius=2.0");

        passed += checkResult(
                "T2",
                new Circle("circle-1", 2, new RasterRenderer()),
                "Circle + RasterRenderer",
                "RASTER circle radius=2.0");

        passed += checkResult(
                "T3",
                new Square("square-1", 3, new VectorRenderer()),
                "Square + VectorRenderer",
                "VECTOR square side=3.0");

        passed += checkResult(
                "T4",
                new Square("square-1", 3, new RasterRenderer()),
                "Square + RasterRenderer",
                "RASTER square side=3.0");

        passed += checkRuntimeSwitch();

        System.out.println("SUMMARY: " + passed + "/5 PASS");
    }

    private static int checkResult(
            String testId,
            Shape shape,
            String participants,
            String expected) {

        String actual = shape.execute();
        boolean passed = expected.equals(actual);

        System.out.println(testId + ": " + (passed ? "PASS" : "FAIL"));
        System.out.println("Classes: " + participants);
        System.out.println("Result: " + actual);

        if (!passed) {
            System.out.println("Expected: " + expected);
        }

        System.out.println();

        return passed ? 1 : 0;
    }

    private static int checkRuntimeSwitch() {
        Circle circle = new Circle(
                "circle-switch", 2, new VectorRenderer());

        Circle original = circle;
        String originalId = circle.getId();
        double originalRadius = circle.getRadius();

        String before = circle.execute();

        circle.setImplementation(new RasterRenderer());

        String after = circle.execute();

        boolean sameObject = original == circle;

        boolean stateUnchanged =
                originalId.equals(circle.getId())
                        && Double.compare(
                        originalRadius, circle.getRadius()) == 0;

        boolean resultsCorrect =
                "VECTOR circle radius=2.0".equals(before)
                        && "RASTER circle radius=2.0".equals(after);

        boolean passed =
                sameObject && stateUnchanged && resultsCorrect;

        System.out.println("T5: " + (passed ? "PASS" : "FAIL"));
        System.out.println(
                "Classes: Circle, VectorRenderer, RasterRenderer");
        System.out.println("Same object: " + sameObject);
        System.out.println("ID and radius unchanged: " + stateUnchanged);
        System.out.println("Before: " + before);
        System.out.println("After: " + after);

        if (!passed) {
            System.out.println("Expected same object: true");
            System.out.println("Expected unchanged ID and radius: true");
            System.out.println(
                    "Expected before: VECTOR circle radius=2.0");
            System.out.println(
                    "Expected after: RASTER circle radius=2.0");
        }

        System.out.println();

        return passed ? 1 : 0;
    }
}