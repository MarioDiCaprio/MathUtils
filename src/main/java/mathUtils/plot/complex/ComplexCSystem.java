package mathUtils.plot.complex;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import mathUtils.calculus.complex.ComplexFunction;
import static mathUtils.calculus.complex.ComplexMath.*;

public class ComplexCSystem extends Canvas {

    //////////////////////////////////////
    ///// fields
    //////////////////////////////////////

    public static final int COMPLEX_PLANE = 0;
    public static final int DOMAIN_COLORING = 1;
    public static final int RIEMANN_SURFACE = 2;

    private ComplexFunction f = z -> square(z);
    private ComplexPlotMethod method;


    //////////////////////////////////////
    ///// constructor
    //////////////////////////////////////

    public ComplexCSystem() {
        super();
        setPlottingMethod(DOMAIN_COLORING);
    }


    public ComplexCSystem(double v, double v1) {
        super(v, v1);
        setPlottingMethod(DOMAIN_COLORING);
    }


    //////////////////////////////////////
    ///// methods
    //////////////////////////////////////

    public void plot(ComplexFunction f) {
        this.f = f;
        paintSelf();
    }

    public ComplexFunction getPlottedFunction() {
        return f;
    }


    public void setPlottingMethod(ComplexPlotMethod m) {
        method = m;
    }

    public ComplexPlotMethod setPlottingMethod(int m) {
        ComplexPlotMethod method;

        switch (m) {
            case DOMAIN_COLORING:
                method =    new ComplexDomainColoring(this)
                            .setXDomain(-2, 2)
                            .setYDomain(-2, 2)
                            .setGridTransparency(0.7);
                break;

            case COMPLEX_PLANE:
                method = new ComplexPlane(this)
                        .setXDomain(-5, 5)
                        .setYDomain(-5, 5)
                        .resetNativeColors();
                break;

            case RIEMANN_SURFACE:
                method =    new RiemannSurface(this)
                            .setXDomain(-5, 5)
                            .setYDomain(-5, 5)
                            .setZDomain(-5, 5);
                break;

            default:
                method = null;
                break;
        }

        setPlottingMethod(method);
        return method;
    }

    public ComplexPlotMethod getPlottingMethod() {
        return method;
    }


    public void paintSelf() {
        method.paintSelf();
    }



    public Pane plotSettings() {
        return method.plotSettings();
    }

}
