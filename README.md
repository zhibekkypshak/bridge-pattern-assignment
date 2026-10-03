# Assignment 3 | Bridge Pattern

Name: Zhibek Kypshak
Group: SE-2528
Topic: A — Drawing
Repository: https://github.com/zhibekkypshak/bridge-pattern-assignment
Base commit: 4465fab7c1b5753b66c49ee29571fff74fe8d911

## Project description

This application uses the Bridge pattern to separate shapes from
their rendering methods.

Circle and Square store shape data. VectorRenderer, RasterRenderer,
and AsciiRenderer provide different rendering descriptions.

The two hierarchies are connected through the Renderer interface.
Rendering is simulated locally using strings.

## Role map

| Role | Class | Source path |
|------|-------|-------------|
| Abstraction | Shape | src/shapes/Shape.java |
| A1 | Circle | src/shapes/Circle.java |
| A2 | Square | src/shapes/Square.java |
| Implementor | Renderer | src/rendering/Renderer.java |
| I1 | VectorRenderer | src/rendering/VectorRenderer.java |
| I2 | RasterRenderer | src/rendering/RasterRenderer.java |
| I3 | AsciiRenderer | src/rendering/AsciiRenderer.java |
| Client | Main | src/Main.java |

## Important code locations

- Bridge field: `private Renderer renderer` in Shape.
- Constructor injection: `Shape(String id, Renderer renderer)`.
- Common operation: `execute()` in Shape, implemented in Circle and Square.
- Runtime replacement: `setImplementation(Renderer renderer)` in Shape.
- Delegation: Circle calls `renderCircle(radius)` through `getRenderer()`.
  Square calls `renderSquare(side)` through `getRenderer()`.
- Runtime switch check: `checkRuntimeSwitch()` in Main.

## Build and run

Requirements: JDK 17. No external dependencies are required.

Run these commands from the project root:

```text
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main --demo
```

The demo runs without interactive input.

## Expected results

| Check | Classes | Expected result |
|-------|---------|-----------------|
| T1 | Circle + VectorRenderer | VECTOR circle radius=2.0 |
| T2 | Circle + RasterRenderer | RASTER circle radius=2.0 |
| T3 | Square + VectorRenderer | VECTOR square side=3.0 |
| T4 | Square + RasterRenderer | RASTER square side=3.0 |
| T5 | Circle + VectorRenderer → RasterRenderer | Same object; unchanged ID and radius |
| T6 | Circle + AsciiRenderer | ASCII circle radius=2.0 |
| T7 | Square + AsciiRenderer | ASCII square side=3.0 |

T5 expected values:

- `sameObject`: true, checked using `original == circle`.
- `stateUnchanged`: true.
- ID remains `circle-switch`.
- Radius remains `2.0`.
- Before: `VECTOR circle radius=2.0`.
- After: `RASTER circle radius=2.0`.

Each check compares actual results with expected values.
The summary is calculated from the number of successful checks.

Expected summary: `SUMMARY: 7/7 PASS`.

Actual console output is saved in `demo-output.txt`.

## Independent extension

The base commit contains VectorRenderer, RasterRenderer,
and checks T1–T5.

AsciiRenderer was added as a third implementation of Renderer.
Main was updated to demonstrate it in T6 and T7.
Its source path was also added to sources.txt.

Shape, Circle, Square, Renderer, VectorRenderer, and RasterRenderer
were not changed during this extension.

The source changes are recorded in `extension.diff`.

## Bridge and Adapter

Bridge separates two dimensions so they can develop independently.
In this project, those dimensions are shapes and rendering methods.

Adapter makes an existing incompatible interface usable by a client.
This project uses a shared Renderer contract from the beginning,
so no interface conversion is needed.

## Trade-off

Bridge introduces additional classes and delegation.
For a small application with only one rendering method,
this structure may add unnecessary complexity.