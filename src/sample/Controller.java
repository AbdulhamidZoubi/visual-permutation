package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Controller {
    @FXML private TextField nText, rText;
    @FXML private VBox labelsBox, keysBox;
    private final IntegerProperty count = new SimpleIntegerProperty(1);

    @FXML
    private void initialize(){
        rText.setDisable(true);
        rText.textProperty().bind(nText.textProperty());
    }

    @FXML
    private void showPermuting(){

        for (int i = 0; i < keysBox.getChildren().size(); i++) {
            if(i != 0)
                keysBox.getChildren().remove(keysBox.getChildren().get(i));
        }

        count.set(1);

        labelsBox.getChildren().clear();
        int n = Integer.parseInt(nText.getText());
        int r = Integer.parseInt(rText.getText());

        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++)
            list.add(i+1);

        permutingArray(list, 0);
    }

    private void addPerLabel(int count ,List<Integer> list){

        HBox itemBox = new HBox();
        itemBox.setSpacing(7);
        Label countLabel = new Label(String.format("%03d",count));

        itemBox.getChildren().add(countLabel);

        for (int p: list){

            Circle circle = new Circle(12, getColorForNumber(p));

            circle.setStroke(Color.WHITE);
            circle.setStrokeWidth(1.5);
            circle.setStrokeType(StrokeType.INSIDE);
            itemBox.getChildren().add(circle);

            if(keysBox.getChildren().size() < list.size() +1){
                HBox key = new HBox();
                key.setSpacing(4);
                Label keyLabel = new Label("العنصر "+p+ ": ");
                keyLabel.setFont(Font.font(13));
                Circle keyCircle = new Circle(8, getColorForNumber(p));

                keyCircle.setStroke(Color.WHITE);
                keyCircle.setStrokeWidth(1.5);
                keyCircle.setStrokeType(StrokeType.INSIDE);
                key.getChildren().addAll(keyCircle, keyLabel);
                key.setAlignment(Pos.CENTER_RIGHT);
                keysBox.getChildren().add(key);
            }
        }

        labelsBox.getChildren().add(itemBox);
    }

    private void permutingArray(List<Integer> arrayList, int element) {
        for (int i = element; i < arrayList.size(); i++) {
            Collections.swap(arrayList, i, element);
            permutingArray(arrayList, element + 1);
            Collections.swap(arrayList, element, i);
        }

        if (element == arrayList.size() - 1) {
            addPerLabel(count.get(), arrayList);
            count.set(count.get() + 1);

        }
    }

    private Color getColorForNumber(int n)
    {
        switch (n){
            case 1:
                return Color.RED;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.GREEN;
            case 4:
                return Color.ORANGE;
            case 5:
                return Color.BLACK;
            case 6:
                return Color.YELLOW;
            case 7:
                return Color.PURPLE;
            case 8:
                return Color.SKYBLUE;
            default:
                return Color.WHEAT;
        }
    }

}
