package GUI.Components;


import GUI.Controller;
import GUI.Styler;
import GUI.Views.ClientBuilderView;
import GUI.Views.ClientView;
import GUI.Views.SettingsView;
import GUI.Views.UpdatesView;
import KUMO.Kumo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TopBar {

    public VBox getTopBar(Stage stage) {
        Image image = new Image(getClass().getResourceAsStream("/Images/logo.png"));
        ImageView imageView = new ImageView(image);

        VBox homeVbox = new VBox();
        homeVbox.setAlignment(Pos.CENTER);
        Label label = (Label) Styler.styleAdd(new Label("HOME"), "label-light");
        label.setStyle("-fx-font-family: \"Roboto\"");
        Image homeImage = new Image(getClass().getResourceAsStream(Styler.getCurrentIcon("/Images/Icons/clients.svg")), 32, 32, false, true);
        homeVbox.getChildren().addAll(new ImageView(homeImage), label);
        homeVbox.setPadding(new Insets(5, 10, 0, 10));
        homeVbox.setId("homeButton");

        VBox buildVbox = new VBox();
        buildVbox.setAlignment(Pos.CENTER);
        Label buildLabel = (Label) Styler.styleAdd(new Label("BUILD"), "label-light");
        label.setStyle("-fx-font-family: \"Roboto\"");
        Image buildImg = new Image(getClass().getResourceAsStream(Styler.getCurrentIcon("/Images/Icons/build.svg")), 32, 32, false, true);
        buildVbox.getChildren().addAll(new ImageView(buildImg), buildLabel);
        buildVbox.setPadding(new Insets(5, 10, 0, 10));
        buildVbox.setId("homeButton"); // Use the same style as the home button

        VBox settingsVbox = new VBox();
        settingsVbox.setAlignment(Pos.CENTER);
        Label settingsLabel = (Label) Styler.styleAdd(new Label("SETTINGS"), "label-light");
        label.setStyle("-fx-font-family: \"Roboto\"");
        Image settingsImg = new Image(getClass().getResourceAsStream(Styler.getCurrentIcon("/Images/Icons/settings.svg")), 32, 32, false, true);
        settingsVbox.getChildren().addAll(new ImageView(settingsImg), settingsLabel);
        settingsVbox.setPadding(new Insets(5, 10, 0, 10));
        settingsVbox.setId("homeButton");

        VBox infoVbox = new VBox();
        infoVbox.setAlignment(Pos.CENTER);
        Label infoLabel = (Label) Styler.styleAdd(new Label("INFO"), "label-light");
        label.setStyle("-fx-font-family: \"Roboto\"");
        Image infoImg = new Image(getClass().getResourceAsStream(Styler.getCurrentIcon("/Images/Icons/info.svg")), 32, 32, false, true);
        infoVbox.getChildren().addAll(new ImageView(infoImg), infoLabel);
        infoVbox.setPadding(new Insets(5, 10, 0, 10));
        infoVbox.setId("homeButton");


        VBox vBox1 = new VBox();
        vBox1.setAlignment(Pos.CENTER);
        vBox1.getChildren().add(new ImageView(new Image(getClass().getResourceAsStream("/Images/logo.png"))));
        vBox1.setPadding(new Insets(5, 10, 5, 160));

        HBox hBox = Styler.hContainer(new HBox(), homeVbox, buildVbox, settingsVbox, infoVbox, vBox1);
        homeVbox.setOnMouseClicked(event -> {
            Kumo.getPrimaryStage().setHeight(500);
            Controller.changePrimaryStage(new ClientView().getClientView());
        });
        buildVbox.setOnMouseClicked(event -> {
            Kumo.getPrimaryStage().setHeight(500);
            Controller.changePrimaryStage(new ClientBuilderView().getClientBuilderView());
        });
        settingsVbox.setOnMouseClicked(event -> {
            Controller.changePrimaryStage(new SettingsView().getSettingsView(), 900, 600);
            Kumo.getPrimaryStage().setHeight(600);
        });
        infoVbox.setOnMouseClicked(event -> {
            Controller.changePrimaryStage(new UpdatesView().getUpdatesView(), 900, 600);
            Kumo.getPrimaryStage().setHeight(600);
        });
        imageView.setFitWidth(100);
        imageView.setFitHeight(50);
        return Styler.vContainer(new VBox(), new TitleBar().getMenuBar(stage), hBox);
    }

    public VBox getTopBarSansOptions(Stage stage) {
        Image image = new Image(getClass().getResourceAsStream("/Images/logo.png"));
        ImageView imageView = new ImageView(image);

        VBox vBox1 = new VBox();
        vBox1.setAlignment(Pos.CENTER);
        vBox1.getChildren().add(new ImageView(new Image(getClass().getResourceAsStream("/Images/logo.png"))));
        vBox1.setPadding(new Insets(5, 10, 5, 5));

        HBox hBox = Styler.hContainer(new HBox(), vBox1);
        imageView.setFitWidth(100);
        imageView.setFitHeight(50);
        return Styler.vContainer(new VBox(), new TitleBar().getMenuBar(stage), hBox);
    }

    public VBox getStrippedTopBar(Stage stage){
        return Styler.vContainer(new VBox(), new TitleBar().getMenuBar(stage));
    }
}
