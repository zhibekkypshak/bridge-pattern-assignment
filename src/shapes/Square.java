package shapes;

import rendering.Renderer;

public class Square extends Shape {

    private final double side;

    public Square(String id, double side, Renderer renderer) {
        super(id, renderer);

        if (!Double.isFinite(side) || side <= 0) {
            throw new IllegalArgumentException(
                    "Side must be a finite positive number");
        }

        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public String execute() {
        return getRenderer().renderSquare(side);
    }
}
