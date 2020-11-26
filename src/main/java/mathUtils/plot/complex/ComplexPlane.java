package mathUtils.plot.complex;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import mathUtils.calculus.MathTools;
import mathUtils.calculus.complex.Complex;
import mathUtils.calculus.complex.ComplexFunction;
import mathUtils.calculus.complex.ComplexMath;

import java.util.*;

public class ComplexPlane extends ComplexPlotMethod {

    ////////////////////////////////////////////////
    ///// fields
    ////////////////////////////////////////////////

    private final double[] xDomain = new double[] {-10, 10};
    private final double[] yDomain = new double[] {-10, 10};
    private HashMap<String, Color> nativeColors = new HashMap<>();


    ////////////////////////////////////////////////
    ///// constructor
    ////////////////////////////////////////////////

    public ComplexPlane(ComplexCSystem c) {
        super(c);
        resetNativeColors();
    }


    ////////////////////////////////////////////////
    ///// methods
    ////////////////////////////////////////////////


    public ComplexPlane setXDomain(double from, double to) {
        xDomain[0] = from;
        xDomain[1] = to;
        return this;
    }


    public ComplexPlane setYDomain(double from, double to) {
        yDomain[0] = from;
        yDomain[1] = to;
        return this;
    }

    public double[] getXDomain() {
        return xDomain;
    }

    public double[] getYDomain() {
        return yDomain;
    }


    public ComplexPlane setNativeColor(String component, Color color) {
        if (nativeColors.get(component) != null && color != null) {
            nativeColors.put(component, color);
        }
        return this;
    }


    public ComplexPlane resetNativeColors() {
        nativeColors.put("axes", Color.DARKGREY.darker().darker());
        nativeColors.put("plane", Color.WHITE);
        nativeColors.put("enumeration", Color.BLACK);
        nativeColors.put("grid", Color.DARKGRAY);
        nativeColors.put("subGrid", Color.LIGHTGRAY);
        return this;
    }


    public Map<String, Color> getNativeColors() {
        return nativeColors;
    }


    public Color getNativeColor(String component) {
        return nativeColors.get(component);
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


    //*********************************
    //***** painting
    //*********************************

    private void paintPlane(GraphicsContext g) {
        Color color = getNativeColor("plane");
        g.setFill(color);
        g.fillRect(
                getCanvas().getTranslateX(), getCanvas().getTranslateY(),
                getCanvas().getWidth(), getCanvas().getHeight()
        );
    }


    private void paintGrid(GraphicsContext g) {
        Color color = getNativeColor("grid");
        g.setStroke(color);
        g.setLineWidth(1);
        double x = squarePixels("x");
        double y = squarePixels("y");
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                g.strokeRect(
                        getCanvas().getTranslateX() + j*x, getCanvas().getTranslateY() + i*y,
                        x, y
                );
            }
        }
    }



    public void paintSubGrid(GraphicsContext g) {
        Color color = getNativeColor("subGrid");
        g.setStroke(color);
        g.setLineWidth(0.5);
        double x = squarePixels("x") / 4;
        double y = squarePixels("y") / 4;
        for (int i=0; i<40; i++) {
            for (int j=0; j<40; j++) {
                g.strokeRect(
                        getCanvas().getTranslateX() + j*x, getCanvas().getTranslateY() + i*y,
                        x, y
                );
            }
        }
    }


    private void paintAxes(GraphicsContext g) {
        Color color = getNativeColor("axes");
        g.setStroke(color);
        g.setLineWidth(2);
        double[] org = origin();
        double x = org[0];
        double y = org[1];

        //x-axis
        g.strokeLine(
                getCanvas().getTranslateX(), y,
                getCanvas().getTranslateX() + getCanvas().getWidth(), y
        );

        //y-axis
        g.strokeLine(
                x, getCanvas().getTranslateY(),
                x, getCanvas().getTranslateY() + getCanvas().getHeight()
        );
    }


    public void paintEnumeration(GraphicsContext g) {
        Color color = getNativeColor("enumeration");
        g.setStroke(color);
        g.setLineWidth(0.5);
        double x = squarePixels("x");
        double y = squarePixels("y");
        double cx = squareDigits("x");
        double cy = squareDigits("y");
        double[] org = origin();

        //y
        double dx =
                (org[0] < getCanvas().getTranslateX())? getCanvas().getTranslateX() :
                        Math.min(org[0], getCanvas().getTranslateX() + getCanvas().getWidth());

        for (int i=0; i<10; i++) {
            double val = MathTools.round(-(yDomain[0] + i*cy), 2);
            if (val == 0)
                continue;
            String txt = (val % 1 == 0)? String.valueOf((int) val) : String.valueOf(val);
            g.strokeText(
                    txt, dx + 2, i*y + 4
            );
        }

        //x
        double dy =
                (org[1] < getCanvas().getTranslateY())? getCanvas().getTranslateY() :
                        Math.min(org[1], getCanvas().getTranslateY() + getCanvas().getHeight());

        for (int i=0; i<10; i++) {
            double val = MathTools.round(xDomain[0] + i*cx, 2);
            if (val == 0)
                continue;
            String txt = (val % 1 == 0)? String.valueOf((int) val) : String.valueOf(val);
            g.strokeText(
                    txt, i*x, dy
            );
        }
    }



    private void paintFunctions(GraphicsContext g) {
        ComplexFunction f = getPlottedFunction();
        g.setLineWidth(1.5);
        g.setStroke(Color.RED);
        Complex c = new Complex( f.z(xDomain[0]) );
        double[] p0 = mapToScreen( c.real, c.imaginary.doubleValue() );

        for (double x=xDomain[0]; x <= xDomain[1]; x += 0.001) {
            c = new Complex( f.z(x) );
            double[] p1 = mapToScreen( c.real, c.imaginary.doubleValue() );
            g.strokeLine(
                    p0[0], p0[1],
                    p1[0], p1[1]
            );
            p0 = p1;
        }
    }

    @Override
    void paintSelf() {
        GraphicsContext g = getCanvas().getGraphicsContext2D();

        paintPlane(g);
        paintSubGrid(g);
        paintGrid(g);
        paintAxes(g);
        paintEnumeration(g);
        paintFunctions(g);
    }



    public Pane plotSettings() {
        VBox root = new VBox();
        double width = 50;
        double height = 25;

        HBox domX = new HBox();

        TextField domX0 = new TextField( "" + getXDomain()[0] );
        domX0.setMinSize(width, height);
        TextField domX1 = new TextField( "" + getXDomain()[1] );
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
        root.getChildren().addAll(new Label("X-Domain"), domX);


        HBox domY = new HBox();

        TextField domY0 = new TextField( "" + getYDomain()[0] );
        domY0.setMinSize(width, height);
        TextField domY1 = new TextField( "" + getYDomain()[1] );
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
