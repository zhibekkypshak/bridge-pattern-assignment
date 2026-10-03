package shapes;

import rendering.Renderer;
import java.util.Objects;

public abstract class Shape {

    private final String id;
    private Renderer renderer;

    protected Shape(String id, Renderer renderer) {
        this.id = Objects.requireNonNull(id, "ID must not be null");
        this.renderer = Objects.requireNonNull(
                renderer, "Renderer must not be null");
    }

    public String getId() {
        return id;
    }

    public void setImplementation(Renderer renderer) {
        this.renderer = Objects.requireNonNull(
                renderer, "Renderer must not be null");
    }

    protected Renderer getRenderer() {
        return renderer;
    }

    public abstract String execute();
}