module MathUtils {
    requires java.desktop;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.swing;


    requires org.antlr.antlr4.runtime;

    exports mathUtils.calculus;
    exports mathUtils.dev;
    exports mathUtils.plot;
    exports mathUtils.linear;
    exports mathUtils.numbers;
    exports mathUtils.calculus.complex;
    exports mathUtils.compiler;
}