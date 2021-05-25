package QueG1.controller;

import java.beans.EventHandler;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class viewController implements Initializable {

    // @FXML Label lbl1;
    @FXML 
    private ImageView pokemonMImageView;
    @FXML 
    private ImageView pokemonFImageView;
    @FXML 
    private TextField pokemonSearch;
    @FXML 
    private Label errorLbl;

    private String pathm;
    private String pathf;
    private Image imagem;
    private Image imagef;



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) 
    {
        pokemonSearch.textProperty().addListener(new ChangeListener<String>()
        {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
            {
                // System.out.println("Clicked");
                // errorLbl.setText(pokemonSearch.getText());
            
                pathm = String.format("https://img.pokemondb.net/sprites/home/normal/%s.png", pokemonSearch.getText().toLowerCase());
                pathf = String.format("https://img.pokemondb.net/sprites/home/normal/%s-f.png", pokemonSearch.getText().toLowerCase());
                
                int responseCodeM = 0;
                int responseCodeF = 0;
                try 
                {
                    URL urlM = new URL(pathm);
                    URL urlF = new URL(pathf);

                    HttpURLConnection hucM = (HttpURLConnection) urlM.openConnection();
                    HttpURLConnection hucF = (HttpURLConnection) urlF.openConnection();

                    hucM.setRequestMethod("HEAD");
                    hucF.setRequestMethod("HEAD");

                    responseCodeM = hucM.getResponseCode();
                    responseCodeF = hucF.getResponseCode();
                } 
                catch (MalformedURLException e) {} 
                catch (IOException e) {}

                if(responseCodeM == 200 || responseCodeF == 200)
                {
                    
                    imagem = new Image(pathm);
                    imagef = new Image(pathf);
                    System.out.println();
                    if(imagem.getHeight() <= 0.0)
                    {
                        pokemonMImageView.setImage(null);
                        pokemonFImageView.setImage(imagef);
                    }
                    else if(imagef.getHeight() <= 0.0)
                    {
                        pokemonFImageView.setImage(null);
                        pokemonMImageView.setScaleX(-1.0);
                        pokemonMImageView.setImage(imagem);
                    }
                    else
                    {
                        // pokemonMImageView.setScaleX(-1.0);
                        pokemonMImageView.setImage(imagem);
                        pokemonFImageView.setImage(imagef);
                    }
                }            
            }
        });
    }

    @FXML
    void btn1OnAction(ActionEvent event)
    {
        System.out.println("Clicked");
        // lbl1.setText("arg0");
    }
}
