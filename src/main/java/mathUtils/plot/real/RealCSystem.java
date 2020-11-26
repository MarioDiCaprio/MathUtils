package mathUtils.plot.real;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import mathUtils.calculus.Function;
import mathUtils.calculus.MathTools;
import mathUtils.plot.PlotSettings;

import java.util.HashMap;
import java.util.Map;

public class RealCSystem extends Canvas {

    //////////////////////////////////////
    ///// fields
    //////////////////////////////////////

    private final double[] xDomain = new double[] {-10, 10};
    private final double[] yDomain = new double[] {-10, 10};
    private HashMap<String, Color> nativeColors = new HashMap<>();
    private Function function = x -> Double.NaN;

    //////////////////////////////////////
    ///// constructor
    //////////////////////////////////////

    public RealCSystem() {
        super();
        resetNativeColors();
    }

    public RealCSystem(double v, double v1) {
        super(v, v1);
        resetNativeColors();
    }


    //////////////////////////////////////
    ///// methods
    //////////////////////////////////////


    public void setXDomain(double from, double to) {
        xDomain[0] = from;
        xDomain[1] = to;
    }


    public void setYDomain(double from, double to) {
        yDomain[0] = from;
        yDomain[1] = to;
    }


    public void setNativeColor(String component, Color color) {
        if (nativeColors.get(component) == null || color == null) {
            return;
        }
        nativeColors.put(component, color);
    }


    public void resetNativeColors() {
        nativeColors.put("axes", Color.DARKGREY.darker().darker());
        nativeColors.put("plane", Color.WHITE);
        nativeColors.put("enumeration", Color.BLACK);
        nativeColors.put("grid", Color.DARKGRAY);
        nativeColors.put("subGrid", Color.LIGHTGRAY);
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
                return this.getWidth() / 10;
            case "y":
                return this.getHeight() / 10;
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
        double scaleX = getWidth() / visibleDigits("x");
        double dx = scaleX*x + getWidth()/2 + getTranslateX();

        double scaleY = getHeight() / visibleDigits("y");
        double dy = scaleY*y + getHeight()/2 + getTranslateY();

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
                this.getTranslateX(), this.getTranslateY(),
                this.getWidth(), this.getHeight()
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
                        this.getTranslateX() + j*x, this.getTranslateY() + i*y,
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
                        this.getTranslateX() + j*x, this.getTranslateY() + i*y,
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
                getTranslateX(), y,
                getTranslateX() + getWidth(), y
        );

        //y-axis
        g.strokeLine(
                x, getTranslateY(),
                x, getTranslateY() + getHeight()
        );
    }


    public void plot(Function f) {
        function = f;
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
                (org[0] < getTranslateX())? getTranslateX() :
                (org[0] > getTranslateX()+getWidth())? getTranslateX() + getWidth() :
                org[0];

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
                (org[1] < getTranslateY())? getTranslateY() :
                (org[1] > getTranslateY()+getHeight())? getTranslateY() + getHeight() :
                org[1];

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
        g.setLineWidth(1.5);
        double z;

        Function f = function;
        Function df = x -> Math.abs( f.derivative().y(x) );

        g.setStroke(Color.RED);
        double[] p0 = mapToScreen( xDomain[0], -f.y(xDomain[0]) );

        for (double x=xDomain[0]; x <= xDomain[1]; x += z) {
            z = df.y(x);
            z = Double.isNaN(z)? 0.01 : MathTools.clamp(z, 0.01, 0.1);
            double[] p1 = mapToScreen( x, -f.y(x) );
            g.strokeLine(
                    p0[0], p0[1],
                    p1[0], p1[1]
            );
            p0 = p1;
        }
    }



    public void paintSelf() {
        GraphicsContext g = this.getGraphicsContext2D();

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

        TextField domX0 = new TextField("-10");
        domX0.setMinSize(width, height);
        TextField domX1 = new TextField("10");
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

        TextField domY0 = new TextField("-10");
        domY0.setMinSize(width, height);
        TextField domY1 = new TextField("10");
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
