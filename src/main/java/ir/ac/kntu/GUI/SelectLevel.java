package ir.ac.kntu.GUI;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SelectLevel {
    private GridPane selectLevel = new GridPane();
    private ColumnConstraints col1 = new ColumnConstraints();
    private ColumnConstraints col2 = new ColumnConstraints();
    private ColumnConstraints col3 = new ColumnConstraints();
    private RowConstraints row1 = new RowConstraints();
    private RowConstraints row2 = new RowConstraints();
    private RowConstraints row3 = new RowConstraints();
    private VBox levelsList = new VBox(15);
    private Text level1 = new Text("level1");
    private Text level2 = new Text("level2");
    private Text level3 = new Text("level3");
    private Text level4 = new Text("level4");
    private Text level5 = new Text("level5");
    private Text level6 = new Text("level6");
    private Text level7 = new Text("level7");
    private Text level8 = new Text("level8");
    private Text level9 = new Text("level9");
    private Text level10 = new Text("level10");

    public GridPane getSelectLevel() {
        return selectLevel;
    }

    public Text getLevel1() {
        return level1;
    }

    public Text getLevel2() {
        return level2;
    }

    public Text getLevel3() {
        return level3;
    }

    public Text getLevel4() {
        return level4;
    }

    public Text getLevel5() {
        return level5;
    }

    public Text getLevel6() {
        return level6;
    }

    public Text getLevel7() {
        return level7;
    }

    public Text getLevel8() {
        return level8;
    }

    public Text getLevel9() {
        return level9;
    }

    public Text getLevel10() {
        return level10;
    }

    public void setSelectLevelStart() {
        selectLevel.setPrefSize(450, 500);
        selectLevel.getColumnConstraints().addAll(col1, col2, col3);
        selectLevel.getRowConstraints().addAll(row1, row2, row3);
        col1.setPercentWidth(5);
        col2.setPercentWidth(90);
        col3.setPercentWidth(5);
        row1.setPercentHeight(10);
        row2.setPercentHeight(80);
        row3.setPercentHeight(10);
        levelsList.getChildren().addAll(level1,level2,level3,level4,level5,level6,level7,level8,level9,level10);
        int i=0;
        for (Node node : levelsList.getChildren()) {
            if (node instanceof Label) {
                Label label = (Label) node;
                label.setTextFill(Color.GOLD);
                label.setFont(new Font(40));
                label.setText("LEVEL: "+i);
                i++;
            }
        }
        selectLevel.setStyle("-fx-background-color: black;");
        selectLevel.add(levelsList,1,1);
    }


}
