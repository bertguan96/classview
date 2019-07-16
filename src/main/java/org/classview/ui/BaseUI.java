package org.classview.ui;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.classview.core.entity.ClassFile;
import org.classview.main.ClassView;
import org.classview.main.Message;
import org.classview.main.MessageFactory;
import org.classview.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

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

        VBox centerVBox = new VBox();
        centerVBox.setPadding(new Insets(10, 0, 0, 15));
        centerVBox.setSpacing(10);

        Scene scene = new Scene(root,800,600, Color.WHITE);

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
                        // 使用简单工厂模式
                        MessageFactory message = new MessageFactory();
                        ClassView classView1 = (ClassView)message.getMessage("ClassView");
                        ClassFile classFile = classView1.getMessage(file);

                        List<String> bytes =  FileUtils.readClassFile(filepath);
                        final TreeItem item = classFile.getFileMsg();

                        //16进制
                        String stringBytes = classView.getStrings(bytes);
                        String result = DisplayAll(stringBytes);
                        ScrollPane sp = new ScrollPane();
                        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
                        Text t = new Text( );
                        t.setWrappingWidth(900);
                        t.setText(result);
                        t.setFont(new Font(12));
                        sp.setContent(t);


                        //创建一个列
                        TreeTableColumn<String,String> column = new TreeTableColumn<>("Column");
                        column.setPrefWidth(400);

                        //定义列的单元格内容
                        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<String, String> p) ->
                                new ReadOnlyStringWrapper(p.getValue().getValue()));
                        final TreeTableView<String> treeTableView = new TreeTableView<String>(item);

                        //必须加这个，不然不显示！！
                        treeTableView.getColumns().add(column);
                        treeTableView.setMinWidth(400);
                        treeTableView.setMinHeight(600);
                        treeTableView.setShowRoot(true);

                        centerVBox.getChildren().addAll(sp,t);
                        root.setLeft(treeTableView);
                        root.setCenter(centerVBox);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                System.out.println(file);
            }
        });


        fileMenu.getItems().addAll(openMenuItem);

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

    private String DisplayAll(String value){


        StringBuffer stringBuilder1=new StringBuffer(value);

        for(int i = stringBuilder1.length() ;i > 0;i = i - 2){
            stringBuilder1.insert(i,"\t");
        }
        String newValue = new String(stringBuilder1);
        return newValue;
    }


}




















