package shapes;

import rendering.Renderer;

public class Circle extends Shape {

    private final double radius;

    public Circle(String id, double radius, Renderer renderer) {
        super(id, renderer);

        if (!Double.isFinite(radius) || radius <= 0) {
            throw new IllegalArgumentException(
                    "Radius must be a finite positive number");
        }

        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String execute() {
        return getRenderer().renderCircle(radius);
    }
}
