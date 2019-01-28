package GUI.Views;


import GUI.Components.BottomBar;
import GUI.Components.NotificationView;
import GUI.Components.TopBar;
import GUI.Styler;
import Server.KumoSettings;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javax.crypto.Cipher;
import java.security.NoSuchAlgorithmException;

class SettingsView {

    BorderPane getSettingsView() {
        BorderPane borderPane = new BorderPane();
        borderPane.getStylesheets().add(getClass().getResource("/css/global.css").toExternalForm());
        borderPane.setTop(new TopBar().getTopBar(KUMO.Kumo.getPrimaryStage()));
        borderPane.setLeft(settingsViewLeft());
        borderPane.setCenter(settingsViewCenter());
        borderPane.setBottom(new BottomBar().getBottomBar());
        return borderPane;
    }

    private HBox settingsViewLeft() {
        HBox hBox = Styler.hContainer(20);
        hBox.getStylesheets().add(getClass().getResource("/css/global.css").toExternalForm());
        hBox.setId("clientBuilder");
        hBox.setPadding(new Insets(20, 20, 20, 20));
        Label title = (Label) Styler.styleAdd(new Label("Settings"), "title");
        hBox.getChildren().add(Styler.vContainer(20, title));
        return hBox;
    }

    private HBox settingsViewCenter() {
        HBox hBox = Styler.hContainer(20);
        hBox.getStylesheets().add(getClass().getResource("/css/global.css").toExternalForm());
        hBox.setId("settingsView");
        hBox.setPadding(new Insets(20, 20, 20, 20));
        Label title = (Label) Styler.styleAdd(new Label(" "), "title");

        Label listeningPortLabel = (Label) Styler.styleAdd(new Label("Listening Port: "), "label-bright");
        TextField listeningPort = new TextField("" + KumoSettings.PORT);
        HBox listeningPortBox = Styler.hContainer(listeningPortLabel, listeningPort);
        listeningPort.setEditable(true);
        listeningPort.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                listeningPort.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }));

        Label maxConnectionsLabel = (Label) Styler.styleAdd(new Label("Max Connections: "), "label-bright");
        TextField maxConnections = new TextField("" + KumoSettings.MAX_CONNECTIONS);
        HBox maxConnectionsBox = Styler.hContainer(maxConnectionsLabel, maxConnections);
        maxConnections.setEditable(true);
        maxConnections.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                maxConnections.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }));

        Label aesLabel = (Label) Styler.styleAdd(new Label("Encryption Key: "), "label-bright");
        TextField aesKey = new TextField("" + KumoSettings.AES_KEY);
        HBox aesBox = Styler.hContainer(aesLabel, aesKey);
        aesKey.setEditable(false);
        aesKey.setDisable(true);


        CheckBox soundToggle = new CheckBox();
        soundToggle.setSelected(KumoSettings.SOUND);
        if (soundToggle.isSelected()) {
            soundToggle.setText("Sound (on) ");
        } else {
            soundToggle.setText("Sound (off) ");
        }
        soundToggle.setOnAction(event -> {
            if (soundToggle.isSelected()) {
                KumoSettings.SOUND = true;
                soundToggle.setText("Sound (on) ");
            } else {
                KumoSettings.SOUND = false;
                soundToggle.setText("Sound (off) ");
            }
        });

        CheckBox notificaitonToggle = new CheckBox();
        notificaitonToggle.setSelected(KumoSettings.SHOW_NOTIFICATIONS);
        if (notificaitonToggle.isSelected()) {
            notificaitonToggle.setText("Notifications (on) ");
        } else {
            notificaitonToggle.setText("Notifications (off) ");
        }
        notificaitonToggle.setOnAction(event -> {
            if (notificaitonToggle.isSelected()) {
                KumoSettings.SHOW_NOTIFICATIONS = true;
                notificaitonToggle.setText("Notifications (on) ");
            } else {
                KumoSettings.SHOW_NOTIFICATIONS = false;
                notificaitonToggle.setText("Notifications (off) ");
            }
        });

        CheckBox backgroundPersistentTogle = new CheckBox();
        backgroundPersistentTogle.setSelected(KumoSettings.BACKGROUND_PERSISTENT);
        if (backgroundPersistentTogle.isSelected()) {
            backgroundPersistentTogle.setText("Background Persistent (on) ");
        } else {
            backgroundPersistentTogle.setText("Background Persistent (off) ");
        }
        backgroundPersistentTogle.setOnAction(event -> {
            if (backgroundPersistentTogle.isSelected()) {
                KumoSettings.BACKGROUND_PERSISTENT = true;
                backgroundPersistentTogle.setText("Background Persistent (on) ");
            } else {
                KumoSettings.BACKGROUND_PERSISTENT = false;
                backgroundPersistentTogle.setText("Background Persistent (off) ");
            }
        });

        Button applySettings = new Button("Apply Settings");
        applySettings.setPrefWidth(150);
        applySettings.setPrefHeight(50);
        applySettings.setOnAction(event -> {
            if (Integer.parseInt(listeningPort.getText()) != KumoSettings.PORT) {
                KumoSettings.PORT = (Integer.parseInt(listeningPort.getText()));
            }
            if (Integer.parseInt(maxConnections.getText()) != KumoSettings.MAX_CONNECTIONS) {
                KumoSettings.MAX_CONNECTIONS = (Integer.parseInt(maxConnections.getText()));
            }
            if (!aesKey.getText().equals(KumoSettings.AES_KEY)){
                int len = 16;
                try{
                    len = Cipher.getMaxAllowedKeyLength("AES");
                } catch (NoSuchAlgorithmException e){
                    e.printStackTrace();
                }
                if (aesKey.getText().length() > len){ // Keys larger than 16 will cause an error on most JRE's
                } else {
                    KumoSettings.AES_KEY = aesKey.getText();
                }
            }
                Platform.runLater(() -> NotificationView.openNotification("Settings Applied"));
        });
        hBox.getChildren().add(Styler.vContainer(20, title, listeningPortBox, maxConnectionsBox, aesBox, soundToggle,notificaitonToggle,backgroundPersistentTogle, applySettings));
        return hBox;
    }
}