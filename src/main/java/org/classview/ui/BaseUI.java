package org.classview.ui;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.classview.main.ClassView;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author jjr
 * @version 1.0
 * @date 2019/7/14 19:18
 * @Description ui界面入口
 */
public class BaseUI extends Application {

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
        BorderPane root = new BorderPane();

        VBox leftVBox = new VBox();

        VBox centerVBox = new VBox();

        VBox rightVBox = new VBox();

        Scene scene = new Scene(root,300,250, Color.WHITE);

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

        classItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                //FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CLASS files(.class)");
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    ClassView classView = new ClassView();
                    try {
                       result[0] = classView.getMessage(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                System.out.println(file);
                stringProperty.set(result[0]);
            }
        });
        leftVBox.getChildren().addAll(status);
        root.setLeft(leftVBox);



        fileMenu.getItems().addAll(openMenuItem);

        Menu WindowMenu = new Menu("Window");
        MenuItem newItem = new MenuItem("new Window");
        WindowMenu.getItems().addAll(newItem);

        Menu Help = new Menu("Help");
        MenuItem aboutItem = new MenuItem("About");
        Help.getItems().addAll(aboutItem);

        menuBar.getMenus().addAll(fileMenu,WindowMenu,Help);


        primaryStage.setScene(scene);
        primaryStage.show();


    }

}




















