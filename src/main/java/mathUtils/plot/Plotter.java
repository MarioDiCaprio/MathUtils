package mathUtils.plot;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import mathUtils.calculus.Function;
import mathUtils.calculus.Polynomial;
import mathUtils.calculus.complex.ComplexFunction;
import mathUtils.linear.Point;
import mathUtils.plot.complex.ComplexCSystem;
import mathUtils.plot.real.RealCSystem;

public class Plotter extends Application {

    //////////////////////////////////////
    ///// fields
    //////////////////////////////////////

    Stage window;
    Scene plotReal, plotComplex;
    RealCSystem realCSystem;
    ComplexCSystem complexCSystem;


    //////////////////////////////////////
    ///// methods
    //////////////////////////////////////

    Menu changePlot() {
        Menu menu = new Menu("Change Plot");
        MenuItem re = new MenuItem("Real");
        MenuItem co = new MenuItem("Complex");

        re.setOnAction( e -> window.setScene(plotReal) );
        co.setOnAction( e -> window.setScene(plotComplex) );

        menu.getItems().addAll(re, co);
        return menu;
    }





    //*****************************************
    //***** create components
    //*****************************************

    void initializeReal() {
        VBox window = new VBox();
        VBox settings = new VBox();
        VBox plotter = new VBox();
        HBox top = new HBox();
        HBox center = new HBox();

        center.setSpacing(20);

        //menu bar//
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu help = new Menu("Help");
        menuBar.getMenus().addAll(file, edit, help, changePlot());
        top.getChildren().add(menuBar);

        //coordinate system//
        realCSystem = new RealCSystem(500, 500);
        center.getChildren().addAll(realCSystem, realCSystem.plotSettings());

        //editor
        Editor editor = new Editor();
        Button plot = new Button("Plot Function");
        plot.setOnAction(e -> {
            Function f = editor.parseReal();
            realCSystem.plot(f);
            realCSystem.paintSelf();
        });

        plotter.setSpacing(10);
        plotter.getChildren().addAll(editor, plot);


        settings.setMinWidth(100);
        center.getChildren().addAll( settings, plotter );

        Function f = Polynomial.fromPoints(
                new Point(2, 2),
                new Point(4, -6),
                new Point(6, 4),
                new Point(7, 1)
        ).toFunction();

        realCSystem.plot(f);

        window.getChildren().addAll(top, center);
        plotReal = new Scene(window, 1200, 600);
        realCSystem.paintSelf();
    }


    void initializeComplex() {
        VBox window = new VBox();
        VBox settings = new VBox();
        VBox plotter = new VBox();
        HBox top = new HBox();
        HBox center = new HBox();

        center.setSpacing(20);

        //menu bar//
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu help = new Menu("Help");
        menuBar.getMenus().addAll(file, edit, help, changePlot());
        top.getChildren().add(menuBar);

        //coordinate system//
        complexCSystem = new ComplexCSystem(500, 500);
        center.getChildren().addAll(complexCSystem, complexCSystem.plotSettings());

        //choice box
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Complex Plane", "Domain Coloring", "Riemann Surface");
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number val1, Number val2) -> {
                    complexCSystem.setPlottingMethod( val2.intValue() );
                    complexCSystem.paintSelf();
                    center.getChildren().set(1, complexCSystem.plotSettings());
                }
        );
        choiceBox.setValue("Domain Coloring");

        //editor
        Editor editor = new Editor();
        Button plot = new Button("Plot Function");
        plot.setOnAction(e -> {
            ComplexFunction f = editor.parseComplex();
            complexCSystem.plot(f);
        });

        plotter.setSpacing(10);
        plotter.getChildren().addAll(editor, plot);


        settings.setMinWidth(100);
        settings.getChildren().addAll(choiceBox);
        center.getChildren().addAll( settings, plotter );

        window.getChildren().addAll(top, center);
        plotComplex = new Scene(window, 1200, 600);
        complexCSystem.setPlottingMethod(ComplexCSystem.DOMAIN_COLORING);
        complexCSystem.paintSelf();
    }


    //*****************************************
    //***** actual application
    //*****************************************


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Plotter");

        initializeReal();
        initializeComplex();

        window.setScene(plotReal);
        window.show();
    }

}
