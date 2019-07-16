package org.classview.ui;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.classview.core.entity.ClassFile;
import org.classview.main.ClassView;
import org.classview.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * @author jjr
 * @version 1.0
 * @date 2019/7/14 19:18
 * @Description ui界面入口
 */
public class BaseUI extends Application {

    final static String TITLE = "classview";
    Stage primaryStage;
    BorderPane root;
    ClassFile file1;

    public static void main(String[] args){
        launch(args);
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        BorderPane root = new BorderPane();
        this.root = root;
        VBox leftVBox = new VBox();

        VBox centerVBox = new VBox();

        VBox rightVBox = new VBox();

        Scene scene = new Scene(root,600,500, Color.WHITE);

        //菜单栏
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root.setTop(menuBar);
        Menu fileMenu = new Menu("File");
        Menu openMenuItem = new Menu("Open");
        MenuItem classItem = new MenuItem("Java Class");
        openMenuItem.getItems().addAll(classItem);
        //文本换行
        StringProperty stringProperty = new SimpleStringProperty();
        Text status = TextBuilder.create().x(100).y(50).build();
        status.textProperty().bind(stringProperty);
        final String[] result = {""};

        //获取信息
        Stage finalPrimaryStage = primaryStage;

        classItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                //FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CLASS files(.class)");
                File file = fileChooser.showOpenDialog(finalPrimaryStage);
                if (file != null) {
                    ClassView classView = new ClassView();
                    try {
                        String filepath = file.getPath();
                        ClassFile classFile = classView.getMessage(file);
                        List<String> bytes =  FileUtils.readClassFile(filepath);
                        final TreeItem item = classFile.getFileMsg();

                        String stringBytes = classView.getStrings(bytes);
                        ScrollPane sp = new ScrollPane();
                        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
                        Text t = new Text( );
                        t.setWrappingWidth(210);
                        t.setText(stringBytes);
                        t.setFont(new Font(14));
                        sp.setContent(t);

                        result[0] = classFile.toString();

                        //创建一个列
                        TreeTableColumn<String,String> column = new TreeTableColumn<>("Column");
                        column.setPrefWidth(150);

                        //定义列的单元格内容
                        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<String, String> p) ->
                                new ReadOnlyStringWrapper(p.getValue().getValue()));
                        final TreeTableView<String> treeTableView = new TreeTableView<String>(item);

                        //必须加这个，不然不显示！！
                        treeTableView.getColumns().add(column);
                        treeTableView.setMinWidth(200);
                        treeTableView.setMinHeight(480);
                        treeTableView.setShowRoot(true);
                        leftVBox.getChildren().addAll(treeTableView);
                        centerVBox.getChildren().addAll(sp,t);
                        root.setLeft(leftVBox);
                        root.setCenter(centerVBox);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                System.out.println(file);
                stringProperty.set(result[0]);
            }
        });

//        TreeTableView<String> treeTableView = new TreeTableView<String>(item);
//        treeTableView.setPrefWidth(180);
//        treeTableView.setShowRoot(true);
//        leftVBox.getChildren().addAll(treeTableView);
//        root.setLeft(leftVBox);

        fileMenu.getItems().addAll(openMenuItem);

//        Menu WindowMenu = new Menu("Window");
//        MenuItem newItem = new MenuItem("new Window");
//        WindowMenu.getItems().addAll(newItem);

//        Menu Help = new Menu("Help");
//        MenuItem aboutItem = new MenuItem("About");
//        aboutItem.setOnAction(e -> AboutDialog.showDialog());
//        aboutItem.setMnemonicParsing(true);
//        Help.getItems().addAll(aboutItem);

        menuBar.getMenus().addAll(fileMenu,createHelpMenu());



        primaryStage.setScene(scene);
        primaryStage.setTitle(TITLE);
        primaryStage.show();


    }



    //关于我们
    private Menu createHelpMenu() {
        MenuItem aboutMenuItem = new MenuItem("About");
        aboutMenuItem.setOnAction(e -> AboutDialog.showDialog());
        aboutMenuItem.setMnemonicParsing(true);
        Menu helpMenu = new Menu("Help");
        helpMenu.getItems().add(aboutMenuItem);
        helpMenu.setMnemonicParsing(true);
        return helpMenu;
    }

    private Menu getAllMessage(ClassFile classFile){
        Menu menu = new Menu(classFile.getClass().getName());
        for(Field field : classFile.getClass().getDeclaredFields()){
            Menu menu1 = new Menu(field.getName());
            menu.getItems().addAll(menu1);
        }

        return menu;
    }


}




















