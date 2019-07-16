package org.classview.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class AboutDialog {
    public static void showDialog() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        BorderPane aboutPane = createAboutPane(stage);
        Scene scene = new Scene(aboutPane, 300, 180);

        stage.setScene(scene);
        stage.setTitle("About");
        stage.show();
    }

    private static BorderPane createAboutPane(Stage dialogStage) {
        BorderPane pane = new BorderPane();
        //pane.setPadding(new Insets(10, 20, 30, 40));
       // String author = "author: guanjiangtao  liujianrongcqut";
        //pane.setBottom(new Label(author));
        pane.setCenter(createHomeLink());
        pane.setOnMouseClicked(e -> dialogStage.close());

        return pane;
    }

    private static Hyperlink createHomeLink() {
        String homeUrl = "https://github.com/guanjiangtao/classview.git";
        Hyperlink link = new Hyperlink(homeUrl);
        link.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(URI.create(homeUrl));
            } catch (IOException x) {
                x.printStackTrace(System.err);
            }
        });

        BorderPane.setAlignment(link, Pos.CENTER);
        BorderPane.setMargin(link, new Insets(8));
        return link;
    }
}
