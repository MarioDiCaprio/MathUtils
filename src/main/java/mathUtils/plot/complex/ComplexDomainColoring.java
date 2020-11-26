package mathUtils.plot.complex;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import mathUtils.calculus.MathTools;
import mathUtils.calculus.complex.Complex;
import mathUtils.calculus.complex.ComplexFunction;
import mathUtils.calculus.complex.ComplexMath;
import mathUtils.calculus.complex.annot.ComplexComplex;
import mathUtils.calculus.complex.annot.ComplexReal;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static mathUtils.calculus.complex.ComplexMath.*;

/**
 * This class represents complex domain coloring for complex
 * functions. This class is supposed to be a setting to pass
 * in the {@link ComplexCSystem}{@code .setPlottingMethod()}.
 *
 */
public class ComplexDomainColoring extends ComplexPlotMethod {

    //////////////////////////////////////
    ///// fields
    //////////////////////////////////////

    private double[] xDomain = new double[] {-10, 10};
    private double[] yDomain = new double[] {-10, 10};

    @ComplexReal
    private ComplexFunction gradientFunction;

    private double gradientTolerance = 0.725;

    @ComplexComplex
    private ComplexFunction gridFunction;

    private double gridErrorTolerance = 0.1;

    private double gridTransparency = 0.5;

    public WritableImage cache;


    //////////////////////////////////////
    ///// constructor
    //////////////////////////////////////

    public ComplexDomainColoring(ComplexCSystem c) {
        super(c);

        setGradientFunction(z -> {
            double x = MathTools.log(ComplexMath.abs(z), 2);
            return x - Math.floor(x);
        });

        setGridFunction(z -> {
            ComplexFunction f = getPlottedFunction();
            Complex complex = new Complex( f.z(z) );
            Number t = 0.1;

            return multiply(
                    abs(
                        pow(
                            sin( multiply(Math.PI, complex.real) ), t
                        )
                    ),

                    abs(
                        pow(
                            sin( multiply(Math.PI, complex.imaginary) ), t
                        )
                    )
            );
        });
    }

    //////////////////////////////////////
    ///// methods
    //////////////////////////////////////

    public WritableImage getCache() {
        return cache;
    }


    public ComplexDomainColoring setXDomain(double from, double to) {
        xDomain[0] = from;
        xDomain[1] = to;
        return this;
    }


    public ComplexDomainColoring setYDomain(double from, double to) {
        yDomain[0] = from;
        yDomain[1] = to;
        return this;
    }


    public ComplexDomainColoring setGradientFunction(@ComplexReal ComplexFunction f) {
        gradientFunction = f;
        return this;
    }


    public ComplexFunction getGradientFunction() {
        return gradientFunction;
    }


    public double getGradientTolerance() {
        return gradientTolerance;
    }

    public ComplexDomainColoring setGradientTolerance(double gradientTolerance) {
        this.gradientTolerance = gradientTolerance;
        return this;
    }

    public ComplexDomainColoring setGridFunction(@ComplexComplex ComplexFunction f) {
        gridFunction = f;
        return this;
    }


    public ComplexFunction getGridFunction() {
        return gridFunction;
    }


    public double getGridErrorTolerance() {
        return gridErrorTolerance;
    }

    public ComplexDomainColoring setGridErrorTolerance(double gridErrorTolerance) {
        this.gridErrorTolerance = gridErrorTolerance;
        return this;
    }


    public double getGridTransparency() {
        return gridTransparency;
    }

    public ComplexDomainColoring setGridTransparency(double gridTransparency) {
        this.gridTransparency = MathTools.clamp(gridTransparency, 0, 1);
        return this;
    }


    public void save(File file, String format, boolean repaint) throws IOException {
        if (repaint) {
            paintSelf();
        }

        BufferedImage img = SwingFXUtils.fromFXImage(cache, null);
        ImageIO.write(img, format, file);
    }


    private double squarePixels(String domain) {
        domain = domain.toLowerCase();
        switch (domain) {
            case "x":
                return getCanvas().getWidth() / 10;
            case "y":
                return getCanvas().getHeight() / 10;
            default:
                return 0;
        }
    }


    private double visibleDigits(String domain) {
        domain = domain.toLowerCase();
        switch (domain) {
            case "x":
                return Math.abs(xDomain[0] - xDomain[1]);
            case "y":
                return Math.abs(yDomain[0] - yDomain[1]);
            default:
                return 0;
        }
    }


    private double getScale(String domain) {
        domain = domain.toLowerCase();
        switch (domain) {
            case "x":
                return visibleDigits("x") / getCanvas().getWidth();
            case "y":
                return visibleDigits("y") / getCanvas().getHeight();
            default:
                return 0;
        }
    }


    private double squareDigits(String domain) {
        return visibleDigits(domain) / 10;
    }


    private double[] mapToScreen(double x, double y) {
        double scaleX = getCanvas().getWidth() / visibleDigits("x");
        double dx = scaleX*x + getCanvas().getWidth()/2 + getCanvas().getTranslateX();

        double scaleY = getCanvas().getHeight() / visibleDigits("y");
        double dy = scaleY*y + getCanvas().getHeight()/2 + getCanvas().getTranslateY();

        return new double[] {dx, dy};
    }


    private double[] origin() {
        return mapToScreen(0, 0);
    }


    private Color getColor(Number n) {
        Complex c = new Complex(n);
        double r = ComplexMath.abs(c);

        if (r == 0) {
            return Color.BLACK;
        } else if ( Double.isInfinite(r) ) {
            return Color.WHITE;
        }

        double theta = ComplexMath.arg(c);

        //gradient
        double factor = (double) gradientFunction.z(n) + gradientTolerance;

        //colors
        Color gridColor = Color.color(0, 0, 0);
        Color mainColor = Color.hsb(
                (theta + Math.PI) / Math.PI / 2 * 360,
                1,
                r * MathTools.clamp(factor, 0, 1) / (r + 1)
        );

        boolean gridReal = MathTools.isWithinBounds(c.real % 1, -gridErrorTolerance, gridErrorTolerance);
        boolean gridImaginary = MathTools.isWithinBounds(c.imaginary.doubleValue() % 1, -gridErrorTolerance, gridErrorTolerance);

        return (gridReal || gridImaginary)?
                mainColor.interpolate(gridColor, 1 - gridTransparency) : mainColor;
    }



    public void paintEnumeration(GraphicsContext g) {
        Canvas c = new Canvas( getCanvas().getWidth(), getCanvas().getHeight() );
        g.setStroke(Color.BLACK);
        g.setLineWidth(0.5);
        double x = squarePixels("x");
        double y = squarePixels("y");
        double cx = squareDigits("x");
        double cy = squareDigits("y");
        double[] org = origin();

        //y
        double dx = c.getTranslateX() + c.getWidth() * 0.95;

        for (int i=0; i<10; i++) {
            double val = -(yDomain[0] + i*cy);
            String txt = (val % 1 == 0)? "" + (int) val : "" + MathTools.round(val, 2);
            g.strokeText(
                    txt, dx + 2, i*y + 9
            );
        }

        //x
        double dy = c.getTranslateY() + c.getHeight() * 0.975;

        for (int i=0; i<10; i++) {
            double val = xDomain[0] + i*cx;
            String txt = (val % 1 == 0)? "" + (int) val : "" + MathTools.round(val, 2);
            g.strokeText(
                    txt, i*x, dy
            );
        }
    }


    @Override
    void paintSelf() {

        ComplexFunction f = getPlottedFunction();

        double width = getCanvas().getWidth();
        double height = getCanvas().getHeight();
        double scaleX = getScale("x");
        double scaleY = getScale("y");
        double scaleGraphic = 0.945;

        getCanvas().setWidth(width * scaleGraphic);
        getCanvas().setHeight(height * scaleGraphic);

        WritableImage img = new WritableImage(
                (int) width + 1,
                (int) height + 1
        );

        PixelWriter writer = img.getPixelWriter();

        for (int r = 0; r <= width*scaleGraphic; r++) {
            for (int i = 0; i <= height*scaleGraphic; i++) {
                Complex c = new Complex(
                        r * scaleX - visibleDigits("x") / 2,
                        i * scaleY - visibleDigits("y") / 2
                );
                Color col = getColor( f.z(c) );
                writer.setColor(r, i, col);
            }
        }

        double x = getCanvas().getTranslateX();
        double y = getCanvas().getTranslateY();

        getCanvas().getGraphicsContext2D().drawImage(img, x, y);

        getCanvas().setWidth(width);
        getCanvas().setHeight(height);

        paintEnumeration( getCanvas().getGraphicsContext2D() );

        cache = getCanvas().snapshot(new SnapshotParameters(), null);
    }


    public Pane plotSettings() {
        VBox root = new VBox();
        double width = 50;
        double height = 25;

        HBox domX = new HBox();

        javafx.scene.control.TextField domX0 = new javafx.scene.control.TextField("-2");
        domX0.setMinSize(width, height);
        javafx.scene.control.TextField domX1 = new javafx.scene.control.TextField("2");
        domX1.setMinSize(width, height);

        domX0.setOnKeyTyped(e -> {
            double x0 = Double.parseDouble( domX0.getText() );
            double x1 = Double.parseDouble( domX1.getText() );
            setXDomain(x0, x1);
            paintSelf();
        });
        domX1.setOnKeyTyped(e -> {
            double x0 = Double.parseDouble( domX0.getText() );
            double x1 = Double.parseDouble( domX1.getText() );
            setXDomain(x0, x1);
            paintSelf();
        });

        domX.getChildren().addAll(domX0, domX1);
        root.getChildren().addAll(new javafx.scene.control.Label("X-Domain"), domX);


        HBox domY = new HBox();

        javafx.scene.control.TextField domY0 = new javafx.scene.control.TextField("-2");
        domY0.setMinSize(width, height);
        javafx.scene.control.TextField domY1 = new javafx.scene.control.TextField("2");
        domY1.setMinSize(width, height);

        domY0.setOnKeyTyped(e -> {
            double y0 = Double.parseDouble( domY0.getText() );
            double y1 = Double.parseDouble( domY1.getText() );
            setYDomain(y0, y1);
            paintSelf();
        });
        domY1.setOnKeyTyped(e -> {
            double y0 = Double.parseDouble( domY0.getText() );
            double y1 = Double.parseDouble( domY1.getText() );
            setYDomain(y0, y1);
            paintSelf();
        });

        domY.getChildren().addAll(domY0, domY1);
        root.getChildren().addAll(new Label("Y-Domain"), domY);


        return root;
    }

}
