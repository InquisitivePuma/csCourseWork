import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CardC {
    private static Stage myStage;
    public static Pane launchCardC(){
        Pane root = new Pane();

        //making a toolbar for text formatting tools
        ToolBar textFormatting = new ToolBar();
        Button bold = new Button("Bold");
        Button italic = new Button("Italic");
        Button underline = new Button("Underline");
        ObservableList<String> options = FXCollections.observableArrayList(
                "10","11","12","14","16","18","20","22","24","26","30","36","42","48","56","64","72"
        );
        ComboBox fontSize = new ComboBox(options);
        textFormatting.getItems().addAll(bold, italic, underline);
        options = FXCollections.observableArrayList(
                "Arial","Courier","Courier New","Helvetica","Times","Times New Roman"
        );
        ComboBox font = new ComboBox(options);
        textFormatting.getItems().add(font);
        textFormatting.getItems().add(fontSize);
        root.getChildren().add(textFormatting);
        return root;
    }
}
