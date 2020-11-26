package mathUtils.plot;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class PlotSettings extends VBox {

    ///////////////////////////////////////////////////////////
    ///// fields
    ///////////////////////////////////////////////////////////

    private Label title = new Label("Settings");
    private Map<String, Node> settings = new HashMap<>();


    ///////////////////////////////////////////////////////////
    ///// constructor
    ///////////////////////////////////////////////////////////

    public PlotSettings() {
        super();
        this.getChildren().addAll(title);
    }


    ///////////////////////////////////////////////////////////
    ///// methods
    ///////////////////////////////////////////////////////////

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public String getTitle() {
        return title.getText();
    }

    public Node getSetting(String setting) {
        return settings.get(setting);
    }

    public void removeSetting(String setting) {
        Node node = settings.remove(setting);
        this.getChildren().remove(node);
    }

    public void addSetting(String name, Node node) {
        settings.put(name, node);
        this.getChildren().add(node);
    }

}
