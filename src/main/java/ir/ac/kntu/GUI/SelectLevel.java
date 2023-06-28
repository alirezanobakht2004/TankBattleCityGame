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
        col1.setPercentWidth(35);
        col2.setPercentWidth(50);
        col3.setPercentWidth(15);
        row1.setPercentHeight(10);
        row2.setPercentHeight(80);
        row3.setPercentHeight(10);
        setLevelsTexts();
        levelsList.getChildren().addAll(level1, level2, level3, level4, level5, level6, level7, level8, level9, level10);
        selectLevel.setStyle("-fx-background-color: black;");
        selectLevel.add(levelsList, 1, 1);
    }

    public void setLevelsTexts() {
        Font font=new Font(35);
        String text = "LEVEL: ";
        Color color = Color.GOLD;
        level1.setFont(font);
        level1.setText(text + 1);
        level1.setFill(color);
        level2.setFont(font);
        level2.setText(text + 2);
        level2.setFill(color);
        level3.setFont(font);
        level3.setText(text + 3);
        level3.setFill(color);
        level4.setFont(font);
        level4.setText(text + 4);
        level4.setFill(color);
        level5.setFont(font);
        level5.setText(text + 5);
        level5.setFill(color);
        level6.setFont(font);
        level6.setText(text + 6);
        level6.setFill(color);
        level7.setFont(font);
        level7.setText(text + 7);
        level7.setFill(color);
        level8.setFont(font);
        level8.setText(text + 8);
        level8.setFill(color);
        level9.setFont(font);
        level9.setText(text + 9);
        level9.setFill(color);
        level10.setFont(font);
        level10.setText(text + 10);
        level10.setFill(color);
    }

}
