package mathUtils.plot;

import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import mathUtils.calculus.Function;
import mathUtils.calculus.complex.Complex;
import mathUtils.calculus.complex.ComplexFunction;
import mathUtils.compiler.ComplexCompiler;
import mathUtils.compiler.RealCompiler;

public class Editor extends Group {

    ///////////////////////////////////////////////////////////
    ///// fields
    ///////////////////////////////////////////////////////////

    private TextArea textArea;
    private int tabs = 4;


    ///////////////////////////////////////////////////////////
    ///// constructor
    ///////////////////////////////////////////////////////////

    public Editor() {
        //initialize text area
        textArea = new TextArea();
        textArea.setPrefHeight(480);
        textArea.setPrefWidth(500);
        textArea.getStylesheets().add("plot_stylesheet.css");

        //add events to customize editing
        textArea.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.TAB) {
                textArea.insertText(textArea.getCaretPosition(), tab(tabs));
                e.consume();
            }
        });

        textArea.insertText( 0, startingClass() );

        this.getChildren().add(textArea);
    }

    ///////////////////////////////////////////////////////////
    ///// methods
    ///////////////////////////////////////////////////////////

    private static String startingClass() {
        return  "";
    }


    public static String tab(int spaces) {
        StringBuilder b = new StringBuilder();
        while (spaces > 0) {
            b.append(" ");
            spaces--;
        }
        return b.toString();
    }


    public ComplexFunction parseComplex() {
        String source = textArea.getText();
        return new ComplexCompiler().function(source);
    }

    public Function parseReal() {
        String source = textArea.getText();
        return new RealCompiler().function(source);
    }

}
