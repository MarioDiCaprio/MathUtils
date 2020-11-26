package mathUtils.plot.complex;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import mathUtils.calculus.complex.ComplexFunction;

abstract class ComplexPlotMethod {

    //////////////////////////////////////
    ///// fields
    //////////////////////////////////////

    private ComplexCSystem canvas;

    //////////////////////////////////////
    ///// constructor
    //////////////////////////////////////

    public ComplexPlotMethod(ComplexCSystem c) {
        setCanvas(c);
    }


    //////////////////////////////////////
    ///// methods
    //////////////////////////////////////


    public ComplexCSystem getCanvas() {
        return canvas;
    }

    public ComplexPlotMethod setCanvas(ComplexCSystem canvas) {
        this.canvas = canvas;
        return this;
    }

    public ComplexFunction getPlottedFunction() {
        return canvas.getPlottedFunction();
    }

    abstract void paintSelf();
    abstract Pane plotSettings();

}
