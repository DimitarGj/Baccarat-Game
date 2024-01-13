//Dimitar Gjorgievski, dgjor2@uic.edu
//CS 342
//Project 2 - Baccarat Game



import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;


public class BaccaratGame extends Application {
	Stage primaryStage;
	private final IntegerProperty betChoice = new SimpleIntegerProperty();
	private final DoubleProperty betAmount = new SimpleDoubleProperty();
	private final DoubleProperty totalWinnings = new SimpleDoubleProperty(0.0);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Baccarat Game");
		
		Scene mainScene = createMainScene();
	     	     
		//Setting Scene
		this.primaryStage.setScene(mainScene);
		this.primaryStage.setResizable(false);
		this.primaryStage.show();
		
	}

	//Creating main menu scene
	Scene createMainScene(){
	    BorderPane canvas = new BorderPane(); //Initializing the canvas
		BorderPane textPane = new BorderPane(); //Border Pane to allign text

		//Setting menu buttons
		Button play = new Button("Play");
		play.setMinWidth(200);
		play.setMinHeight(80);
		play.setStyle("-fx-border-color: #8B4513; -fx-border-width: 5px; -fx-background-color:#808000; -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';");
		
		Button rules = new Button("Rules");
		rules.setMinWidth(200);
		rules.setMinHeight(80);
		rules.setStyle("-fx-border-color: #8B4513; -fx-border-width: 5px; -fx-background-color:#808000; -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';");
		
		Button exit = new Button("Exit");
		exit.setMinWidth(200);
		exit.setMinHeight(80);
		exit.setStyle("-fx-border-color: #8B4513; -fx-border-width: 5px; -fx-background-color:#808000; -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';");

		//Setting Title
        Text title = new Text("Baccarat");
		title.setFont(Font.font ("Bodoni MT Black", 70));
		textPane.setCenter(title);
		canvas.setTop(textPane);

		//Adding functionality to play button
		play.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				Scene betScene = createBetScene();
				primaryStage.setScene(betScene);
			}
		});

		//Adding functionality to rules button
		rules.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				Scene rulesScene = createRulesScene();
				primaryStage.setScene(rulesScene);
			}
		});

		//Adding functionality to exit button
		exit.setOnAction(e -> System.exit(0));

		//Setting menu options
		VBox menu = new VBox(50);
		menu.getChildren().addAll(play, rules, exit);
		menu.setAlignment(Pos.CENTER);
		canvas.setCenter(menu);

		//setting Background color
		BackgroundFill backgroundFill = new BackgroundFill(Color.valueOf("#FF0000"), new CornerRadii(10), new Insets(10)); 
		Background background = new Background(backgroundFill);
		canvas.setBackground(background);

		//Create and return main menu scene
		return new Scene(canvas, 1000,700);

	}

	//Creating Betting Scene
	Scene createBetScene(){

		BorderPane canvas = new BorderPane(); //Initializing the canvas
		BorderPane textPane = new BorderPane(); //Border Pane to allign text

		//Setting Title
        Text title = new Text("Place Your Bets!!");
		title.setFont(Font.font ("Bodoni MT Black", 70));
		textPane.setCenter(title);
		canvas.setTop(textPane);

		//setting Background color
		BackgroundFill backgroundFill = new BackgroundFill(Color.valueOf("#FF0000"), new CornerRadii(10), new Insets(10)); 
		Background background = new Background(backgroundFill);
		canvas.setBackground(background);

		//Setting back button
		Button back = new Button("Back");
		back.setMinWidth(50);
		back.setMinHeight(20);
		back.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color:#FFFFFF; -fx-font-size: 2em; -fx-text-fill: #000000; -fx-font-family: 'Bodoni MT Black';");
		
		//Placing back button in HBox
		HBox back_button = new HBox(50);
		back_button.getChildren().addAll(back);
		back_button.setAlignment(Pos.BOTTOM_RIGHT);
		HBox.setMargin(back, new Insets(0,0,20, 20));
		canvas.setLeft(back_button);

		//Adding functionality to back button
		back.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				Scene mainScene = createMainScene();
				primaryStage.setScene(mainScene);
			}
		});

		//Setting player button
		Button player = new Button("Player");
		player.setMinWidth(100);
		player.setMinHeight(30);
		player.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color:#FF0000; -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';");

		//Setting banker button
		Button banker = new Button("Banker");
		banker.setMinWidth(100);
		banker.setMinHeight(30);
		banker.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color:#FF0000; -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';");

		//Setting tie button
		Button tie = new Button("Tie");
		tie.setMinWidth(100);
		tie.setMinHeight(30);
		tie.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color:#FF0000; -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';");

		HBox bet_buttons = new HBox(50);
		bet_buttons.getChildren().addAll(player, tie, banker);
		bet_buttons.setAlignment(Pos.CENTER);
		HBox.setMargin(player, new Insets(200,0,200, 0));
		HBox.setMargin(tie, new Insets(200,0,200, 0));
		HBox.setMargin(banker, new Insets(200,100,200, 0));
		canvas.setCenter(bet_buttons);

		//Adding functionality to player button
		player.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				betChoice.set(1); //Recording choice of bet

				player.setStyle("-fx-border-color: #7FFF00; -fx-border-width: 5px; -fx-background-color:#7FFF00; -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';");
				banker.setDisable(true);
				tie.setDisable(true);

				//Initializing play button
				Button play = new Button("Play");
				play.setMinWidth(50);
				play.setMinHeight(20);
				play.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color:#000000; -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';");
				
				//Adding Hover attribute to play button
				play.setOnMouseEntered(h -> play.setStyle("-fx-border-color: #7FFF00; -fx-border-width: 5px; -fx-background-color:#7FFF00; -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';"));
				play.setOnMouseExited(h -> play.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color:#000000; -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';")); 

        		// Create a TextField & Label for user input
				Label textLabel = new Label("Enter Your Bet");
        		TextField dollarAmountField = new TextField();
				dollarAmountField.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color:#FFFFFF; -fx-font-size: 2em; -fx-text-fill: #000000; -fx-font-family: 'Bodoni MT Black';");				
				dollarAmountField.setPrefHeight(20);
				dollarAmountField.setPrefWidth(250);
				dollarAmountField.setEditable(true);
				textLabel.setStyle("-fx-font-size: 2em; -fx-text-fill: #000000; -fx-font-family: 'Bodoni MT Black';");	

        		// Add an event filter to validate input
        		dollarAmountField.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            		if (!isValidDollar(dollarAmountField.getText(), event.getCharacter())) {
                		event.consume(); // Ignore the input
            		}
        		});

				//Adding functionality to play button - Starting the game
				play.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e){
						betAmount.set(Double.parseDouble(dollarAmountField.getText())); //Storing amount that's bet
						Scene gameScene = createGameScene();
						primaryStage.setScene(gameScene);
					}
				});

				//Arrange TextField, Label, and Buttons in scene
		        HBox text = new HBox(20);
        		text.getChildren().addAll(dollarAmountField, play);
				text.setAlignment(Pos.CENTER);
				HBox.setMargin(dollarAmountField, new Insets(0, 10, 0, 0));
				HBox.setMargin(play, new Insets(0, 10, 0, 0));

				HBox label = new HBox();
				label.getChildren().addAll(textLabel);
				label.setAlignment(Pos.CENTER);
				HBox.setMargin(textLabel, new Insets(0, 120, 0, 0));

				VBox betting = new VBox(20);
				betting.getChildren().addAll(bet_buttons, label, text);
				betting.setAlignment(Pos.CENTER);
				canvas.setCenter(betting);
			}
		});

		//Adding functionality to banker button
		banker.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				betChoice.set(2); //Recording choice of bet

				banker.setStyle("-fx-border-color: #7FFF00; -fx-border-width: 5px; -fx-background-color:#7FFF00; -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';");
				player.setDisable(true);
				tie.setDisable(true);	

				//Initializing play button
				Button play = new Button("Play");
				play.setMinWidth(50);
				play.setMinHeight(20);
				play.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color:#000000; -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';");
				
				//Adding Hover attribute to play button
				play.setOnMouseEntered(h -> play.setStyle("-fx-border-color: #7FFF00; -fx-border-width: 5px; -fx-background-color:#7FFF00; -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';"));
				play.setOnMouseExited(h -> play.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color:#000000; -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';")); 

        		// Create a TextField & Label for user input
				Label textLabel = new Label("Enter Your Bet");
        		TextField dollarAmountField = new TextField();
				dollarAmountField.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color:#FFFFFF; -fx-font-size: 2em; -fx-text-fill: #000000; -fx-font-family: 'Bodoni MT Black';");				
				dollarAmountField.setPrefHeight(20);
				dollarAmountField.setPrefWidth(250);
				dollarAmountField.setEditable(true);
				textLabel.setStyle("-fx-font-size: 2em; -fx-text-fill: #000000; -fx-font-family: 'Bodoni MT Black';");	

				// Add an event filter to validate input
        		dollarAmountField.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            		if (!isValidDollar(dollarAmountField.getText(), event.getCharacter())) {
                		event.consume(); // Ignore the input
            		}
        		});

				//Adding functionality to play button - Starting the game
				play.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e){
						betAmount.set(Double.parseDouble(dollarAmountField.getText())); //Storing amount that's bet
						Scene gameScene = createGameScene();
						primaryStage.setScene(gameScene);
					}
				});


				//Arrange TextField, Label, and Buttons in scene
		        HBox text = new HBox(20);
        		text.getChildren().addAll(dollarAmountField, play);
				text.setAlignment(Pos.CENTER);
				HBox.setMargin(dollarAmountField, new Insets(0, 10, 0, 0));
				HBox.setMargin(play, new Insets(0, 10, 0, 0));

				HBox label = new HBox();
				label.getChildren().addAll(textLabel);
				label.setAlignment(Pos.CENTER);
				HBox.setMargin(textLabel, new Insets(0, 120, 0, 0));

				VBox betting = new VBox(20);
				betting.getChildren().addAll(bet_buttons, label, text);
				betting.setAlignment(Pos.CENTER);
				canvas.setCenter(betting);
			}
		});

		//Adding functionality to tie button
		tie.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				betChoice.set(3); //Recording choice of bet

				tie.setStyle("-fx-border-color: #7FFF00; -fx-border-width: 5px; -fx-background-color:#7FFF00; -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';");
				banker.setDisable(true);
				player.setDisable(true);

				//Initializing play button
				Button play = new Button("Play");
				play.setMinWidth(50);
				play.setMinHeight(20);
				play.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color:#000000; -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';");
				
				//Adding Hover attribute to play button
				play.setOnMouseEntered(h -> play.setStyle("-fx-border-color: #7FFF00; -fx-border-width: 5px; -fx-background-color:#7FFF00; -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';"));
				play.setOnMouseExited(h -> play.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color:#000000; -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';")); 
				
        		// Create a TextField & Label for user input
				Label textLabel = new Label("Enter Your Bet");
        		TextField dollarAmountField = new TextField();
				dollarAmountField.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color:#FFFFFF; -fx-font-size: 2em; -fx-text-fill: #000000; -fx-font-family: 'Bodoni MT Black';");				
				dollarAmountField.setPrefHeight(20);
				dollarAmountField.setPrefWidth(250);
				dollarAmountField.setEditable(true);
				textLabel.setStyle("-fx-font-size: 2em; -fx-text-fill: #000000; -fx-font-family: 'Bodoni MT Black';");	

        		// Add an event filter to validate input
        		dollarAmountField.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            		if (!isValidDollar(dollarAmountField.getText(), event.getCharacter())) {
                		event.consume(); // Ignore the input
            		}
        		});

				//Adding functionality to play button - Starting the game
				play.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e){
						betAmount.set(Double.parseDouble(dollarAmountField.getText())); //Storing amount that's bet
						Scene gameScene = createGameScene();
						primaryStage.setScene(gameScene);
					}
				});

				//Arrange TextField, Label, and Buttons in scene
		        HBox text = new HBox(20);
        		text.getChildren().addAll(dollarAmountField, play);
				text.setAlignment(Pos.CENTER);
				HBox.setMargin(dollarAmountField, new Insets(0, 10, 0, 0));
				HBox.setMargin(play, new Insets(0, 10, 0, 0));

				HBox label = new HBox();
				label.getChildren().addAll(textLabel);
				label.setAlignment(Pos.CENTER);
				HBox.setMargin(textLabel, new Insets(0, 120, 0, 0));

				VBox betting = new VBox(20);
				betting.getChildren().addAll(bet_buttons, label, text);
				betting.setAlignment(Pos.CENTER);
				canvas.setCenter(betting);
			}
		});

		//Create and return Betting Scene
		return new Scene(canvas, 1000,700);
	}


	//Creating Game Scene 
	Scene createGameScene(){
		BaccaratGameLogic Game = new BaccaratGameLogic(); //Initializing game
		Game.dealer.generateDeck(); //Initializing deck

		ArrayList<Card> Player = new ArrayList<>(Game.dealer.dealHand()); //Dealing Hand to Player
		ArrayList<Card> Banker = new ArrayList<>(Game.dealer.dealHand()); //Dealing Hand to Banker

		BorderPane canvas = new BorderPane();

        // Create a Border with brown color
        Border border = new Border(
            new BorderStroke(
                Color.BROWN,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(15)
            )
        );

		canvas.setBorder(border);

		//setting Background color
		BackgroundFill backgroundFill = new BackgroundFill(Color.GREEN, new CornerRadii(0), new Insets(10)); 
		Background background = new Background(backgroundFill);
		canvas.setBackground(background);
		
		Button PlayAgain = new Button("Play Again");
		Button Exit = new Button("Exit");

		PlayAgain.setMinWidth(50);
		PlayAgain.setMinHeight(20);
		PlayAgain.setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 5px; -fx-background-color:#FFFFFF; -fx-font-size: 2em; -fx-text-fill: #000000; -fx-font-family: 'Bodoni MT Black';");
		
		Exit.setMinWidth(50);
		Exit.setMinHeight(20);
		Exit.setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 5px; -fx-background-color:#FFFFFF; -fx-font-size: 2em; -fx-text-fill: #000000; -fx-font-family: 'Bodoni MT Black';");
			
		PlayAgain.setOnMouseEntered(h -> PlayAgain.setStyle("-fx-border-color: #7FFF00; -fx-border-width: 5px; -fx-background-color:#7FFF00; -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';"));
		PlayAgain.setOnMouseExited(h -> PlayAgain.setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 5px; -fx-background-color:#FFFFFF; -fx-font-size: 2em; -fx-text-fill: #000000; -fx-font-family: 'Bodoni MT Black';")); 
		
		Exit.setOnMouseEntered(h -> Exit.setStyle("-fx-border-color: #FF0000; -fx-border-width: 5px; -fx-background-color:#FF0000; -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';"));
		Exit.setOnMouseExited(h -> Exit.setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 5px; -fx-background-color:#FFFFFF; -fx-font-size: 2em; -fx-text-fill: #000000; -fx-font-family: 'Bodoni MT Black';")); 
			
		//Adding functionality to rules button
		PlayAgain.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				Scene betScene = createBetScene();
				primaryStage.setScene(betScene);
			}
		});

		//Adding functionality to exit button
		Exit.setOnAction(e -> System.exit(0));

		Label betLabel = new Label(String.format("Your Bet: %.2f", betAmount.get()));
		betLabel.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color:#FFFFFF; -fx-font-size: 2em; -fx-text-fill: #000000; -fx-font-family: 'Bodoni MT Black';");				
		Label winningLabel = new Label(String.format("Total Winnings: %.2f", totalWinnings.get()));
		winningLabel.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color:#FFFFFF; -fx-font-size: 2em; -fx-text-fill: #000000; -fx-font-family: 'Bodoni MT Black';");				
		
		HBox label1 = new HBox();
		label1.getChildren().addAll(betLabel);
		label1.setAlignment(Pos.CENTER_LEFT);

		HBox label2 = new HBox();
		label2.getChildren().addAll(winningLabel);
		label2.setAlignment(Pos.CENTER_LEFT);

		VBox labels = new VBox(5);
		labels.getChildren().addAll(label1, label2);

		//Label PlayerLabel = new Label(String.format("Your Bet: %.2f", betAmount.get()));
		Label PlayerLabel = new Label(String.format("Player"));
		Label BankerLabel = new Label(String.format("Banker"));

		HBox Lables = new HBox(150);
		Lables.getChildren().addAll(PlayerLabel, BankerLabel);
		Lables.setAlignment(Pos.CENTER_RIGHT);
		Lables.setPadding(new Insets(0,90,0,30));
		Lables.setStyle("-fx-font-size: 2em; -fx-text-fill: #000000; -fx-font-family: 'Bodoni MT Black';");				


        betLabel.setVisible(false);
		winningLabel.setVisible(false);
		PlayerLabel.setVisible(false);
		BankerLabel.setVisible(false);

		canvas.setTop(labels);
		canvas.setRight(Lables);

		SequentialTransition cardAnimation = new SequentialTransition(); //Card Animation
		
		HBox PlayerCards = new HBox(2);
		HBox BankerCards = new HBox(2);
		HBox opponents = new HBox();
		double cardWidth = 100; // Set your desired width
		double cardHeight = 150; // Set your desired height
		
		// // Create a common initial position for all cards (top-center)
		double initialX = 0; 
		double initialY = -550;

		for(Card c : Player){

			//Input image
			Image player_card = new Image("cards/"+c.value+c.suite+".png");
			ImageView playerCard = new ImageView(player_card);
			playerCard.setFitWidth(cardWidth);
			playerCard.setFitHeight(cardHeight);

		    // Set the initial position
			playerCard.setTranslateX(initialX);
			playerCard.setTranslateY(initialY);
			PlayerCards.getChildren().add(playerCard);		

		    // Create a slide-in animation for each card
			TranslateTransition slideIn = new TranslateTransition();
			slideIn.setNode(playerCard);
			slideIn.setDuration(Duration.millis(1100));
			slideIn.setByX(500);
			slideIn.setByY(500);
			slideIn.play();

			cardAnimation.getChildren().add(slideIn);
		}

		initialX = 30; 
		initialY = -550; 

		for(Card c : Banker){
			
			//Input image
			Image banker_card = new Image("cards/"+c.value+c.suite+".png");
			ImageView bankerCard = new ImageView(banker_card);
			bankerCard.setFitWidth(cardWidth);
			bankerCard.setFitHeight(cardHeight);

		    // Set the initial position at the top-center
			bankerCard.setTranslateX(initialX);
			bankerCard.setTranslateY(initialY);			
			BankerCards.getChildren().add(bankerCard);		

		    // Create a slide-in animation
			TranslateTransition slideIn = new TranslateTransition();
			slideIn.setNode(bankerCard);
			slideIn.setDuration(Duration.millis(1370));
			slideIn.setByX(500);
			slideIn.setByY(500);
			slideIn.play();

			cardAnimation.getChildren().add(slideIn);
			
		}

		opponents.getChildren().addAll(PlayerCards, BankerCards);
		opponents.setAlignment(Pos.CENTER_LEFT);

		canvas.setBottom(opponents);	
	
		PauseTransition pause = new PauseTransition(Duration.seconds(2));

		pause.setOnFinished(event -> {
            // Show the label after the delay
            betLabel.setVisible(true);
			winningLabel.setVisible(true);
			PlayerLabel.setVisible(true);
			BankerLabel.setVisible(true);
			
			//Deal Third Card
			if(Game.handTotal(Player) < 8 && Game.handTotal(Banker) < 8){
				Card add_player_card = null;
				HBox ThirdPlayerCard = new HBox(2);
				int set = 0;
				
				if(Game.evaluatePlayerDraw(Player)==true){
					
					set = 1;
					//Player ThirdCard
					double InitialX = 0; // Adjust as needed
					double InitialY = -550; // Top of the screen
					add_player_card = Game.dealer.drawOne();
					Player.add(add_player_card);

					Image player_card = new Image("cards/"+add_player_card.value+add_player_card.suite+".png");
					ImageView playerCard = new ImageView(player_card);
					playerCard.setFitWidth(cardWidth);
					playerCard.setFitHeight(cardHeight);

					// Set the initial position
					playerCard.setTranslateX(InitialX);
					playerCard.setTranslateY(InitialY);
					ThirdPlayerCard.getChildren().add(playerCard);		

					// Create a slide-in animation
					TranslateTransition slideIn = new TranslateTransition();
					slideIn.setNode(playerCard);
					slideIn.setDuration(Duration.millis(1100));
					slideIn.setByX(500);
					slideIn.setByY(500);
					slideIn.play();

					cardAnimation.getChildren().add(slideIn);

					ThirdPlayerCard.setAlignment(Pos.BOTTOM_LEFT);
					ThirdPlayerCard.setPadding(new Insets(0,0,0,50));
					
					canvas.setCenter(ThirdPlayerCard);

				}

				if(Game.evaluateBankerDraw(Banker, add_player_card)==true){

					//Banker Third Card
					HBox ThirdBankerCard = new HBox(2);
					double InitialX = 30; // Adjust as needed
					double InitialY = -550; // Top of the screen

					Card add_banker_card = Game.dealer.drawOne();
					Banker.add(add_banker_card);

					Image banker_card = new Image("cards/"+add_banker_card.value+add_banker_card.suite+".png");
					ImageView bankerCard = new ImageView(banker_card);
					bankerCard.setFitWidth(cardWidth);
					bankerCard.setFitHeight(cardHeight);
					

					// Set the initial position
					bankerCard.setTranslateX(InitialX);
					bankerCard.setTranslateY(InitialY);
					ThirdBankerCard.getChildren().add(bankerCard);		

					// Create a slide-in animation
					TranslateTransition slideIn2 = new TranslateTransition();
					slideIn2.setNode(bankerCard);
					slideIn2.setDuration(Duration.millis(1370));
					slideIn2.setByX(500);
					slideIn2.setByY(500);
					slideIn2.play();

					cardAnimation.getChildren().add(slideIn2);
					if(set==1){
						ThirdBankerCard.setAlignment(Pos.BOTTOM_RIGHT);
					}else{
						ThirdBankerCard.setAlignment(Pos.BOTTOM_CENTER);
						ThirdBankerCard.setPadding(new Insets(0,0,0,80));
					}
					canvas.setCenter(ThirdBankerCard);
					
					if(set==1){
						HBox ThirdCards = new HBox(100);
						ThirdCards.getChildren().addAll(ThirdPlayerCard, ThirdBankerCard);
						ThirdCards.setAlignment(Pos.BOTTOM_LEFT);
						canvas.setCenter(ThirdCards);
					}

				}
				
			}
			
			
			PauseTransition pause1 = new PauseTransition(Duration.seconds(2));

			pause1.setOnFinished(event1 -> {

				if(Game.whoWon(Player, Banker)=="Bank"){
					
					//Label PlayerLabel = new Label(String.format("Your Bet: %.2f", betAmount.get()));
					Label WinnerLabel = new Label(String.format("Banker Wins!!"));
					WinnerLabel.setStyle("-fx-font-size: 3em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';");				
					Label BetOutcome;
					Label BetAmount;
					
					if(betChoice.get()==2){
						BetOutcome = new Label(String.format("Congratulations"));
						totalWinnings.set(2*betAmount.get() + (totalWinnings.get()));
						BetAmount = new Label(String.format("Your've Won $%.2f", 2*betAmount.get()));
						BetOutcome.setStyle(" -fx-font-size: 2.9em; -fx-text-fill: #FFD700; -fx-font-family: 'Bodoni MT Black';");				
						BetAmount.setStyle("-fx-font-size: 2em; -fx-text-fill: #FFD700; -fx-font-family: 'Bodoni MT Black';");				
			
					}else{
						BetOutcome = new Label(String.format(" You've Lost!"));
						BetAmount = new Label(String.format("Better Luck Next Time"));
						BetOutcome.setStyle("-fx-font-size: 2.9em; -fx-text-fill: #FF0000; -fx-font-family: 'Bodoni MT Black';");				
						BetAmount.setStyle("-fx-font-size: 2em; -fx-text-fill: #FF0000; -fx-font-family: 'Bodoni MT Black';");				
			
					}
					
					VBox FinalLabels = new VBox(50);
					FinalLabels.getChildren().addAll(WinnerLabel, BetOutcome, BetAmount);
					FinalLabels.setAlignment(Pos.CENTER_LEFT);
					FinalLabels.setPadding(new Insets(100,0,0,55));
					labels.getChildren().add(FinalLabels);
					PlayerLabel.setVisible(false);
					BankerLabel.setVisible(false);
					canvas.setTop(labels);

				}else if(Game.whoWon(Player, Banker)=="Player"){

					//Label PlayerLabel = new Label(String.format("Your Bet: %.2f", betAmount.get()));
					Label WinnerLabel = new Label(String.format("Player Wins!!"));
					WinnerLabel.setStyle("-fx-font-size: 3em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';");				
					Label BetOutcome;
					Label BetAmount;
					
					if(betChoice.get()==1){
						BetOutcome = new Label(String.format("Congratulations"));
						totalWinnings.set(2*betAmount.get() + (totalWinnings.get()));
						BetAmount = new Label(String.format("Your've Won $%.2f", 2*betAmount.get()));
						BetOutcome.setStyle(" -fx-font-size: 2.9em; -fx-text-fill: #FFD700; -fx-font-family: 'Bodoni MT Black';");				
						BetAmount.setStyle("-fx-font-size: 2em; -fx-text-fill: #FFD700; -fx-font-family: 'Bodoni MT Black';");				
			
					}else{
						BetOutcome = new Label(String.format("You've Lost!"));
						BetAmount = new Label(String.format("Better Luck Next Time"));
						BetOutcome.setStyle("-fx-font-size: 2.9em; -fx-text-fill: #FF0000; -fx-font-family: 'Bodoni MT Black';");				
						BetAmount.setStyle("-fx-font-size: 2em; -fx-text-fill: #FF0000; -fx-font-family: 'Bodoni MT Black';");				
			
					}
					
					VBox FinalLabels = new VBox(50);
					FinalLabels.getChildren().addAll(WinnerLabel, BetOutcome, BetAmount);
					FinalLabels.setAlignment(Pos.CENTER_LEFT);
					FinalLabels.setPadding(new Insets(100,0,0,55));
					labels.getChildren().add(FinalLabels);
					PlayerLabel.setVisible(false);
					BankerLabel.setVisible(false);
					canvas.setTop(labels);

				}else{
				
					//Label PlayerLabel = new Label(String.format("Your Bet: %.2f", betAmount.get()));
					Label WinnerLabel = new Label(String.format("It's a Draw!"));
					WinnerLabel.setStyle("-fx-font-size: 3em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';");				
					Label BetOutcome;
					Label BetAmount;
					
					if(betChoice.get()==3){
						BetOutcome = new Label(String.format("Congratulations"));
						totalWinnings.set(8*betAmount.get() + (totalWinnings.get()));
						BetAmount = new Label(String.format("Your've Won $%.2f", 8*betAmount.get()));
						BetOutcome.setStyle("-fx-font-size: 2.9em; -fx-text-fill: #FFD700; -fx-font-family: 'Bodoni MT Black';");				
						BetAmount.setStyle("-fx-font-size: 2em; -fx-text-fill: #FFD700; -fx-font-family: 'Bodoni MT Black';");				
			
					}else{
						BetOutcome = new Label(String.format("You've Lost!"));
						BetAmount = new Label(String.format("Better Luck Next Time"));
						BetOutcome.setStyle("-fx-font-size: 2.9em; -fx-text-fill: #FF0000; -fx-font-family: 'Bodoni MT Black';");				
						BetAmount.setStyle("-fx-font-size: 2em; -fx-text-fill: #FF0000; -fx-font-family: 'Bodoni MT Black';");				
			
					}
					
					VBox FinalLabels = new VBox(50);
					FinalLabels.getChildren().addAll(WinnerLabel, BetOutcome, BetAmount);
					FinalLabels.setAlignment(Pos.CENTER_LEFT);
					FinalLabels.setPadding(new Insets(100,0,0,55));
					labels.getChildren().add(FinalLabels);
					PlayerLabel.setVisible(false);
					BankerLabel.setVisible(false);
					canvas.setTop(labels);

				}

				HBox LastButtons = new HBox(40);
				LastButtons.getChildren().addAll(PlayAgain, Exit);
				LastButtons.setAlignment(Pos.TOP_RIGHT);
				LastButtons.setPadding(new Insets(0,0,0,20));

				HBox TopElements = new HBox(300);
				TopElements.getChildren().addAll(labels, LastButtons);
				canvas.setTop(TopElements);
			
			});
			pause1.play();

        });

		pause.play();
			
		return new Scene(canvas, 1000,700);
	}

	//Creating Rules Scene
	Scene createRulesScene(){
		BorderPane canvas = new BorderPane(); //Initializing the canvas
		BorderPane textPane = new BorderPane(); //Border Pane to allign text

		//Setting Title
        Text title = new Text("How To Play??");
		title.setFont(Font.font ("Bodoni MT Black", 70));
		textPane.setCenter(title);
		canvas.setTop(textPane);

		//setting Background color
		BackgroundFill backgroundFill = new BackgroundFill(Color.valueOf("#FF0000"), new CornerRadii(10), new Insets(10)); 
		Background background = new Background(backgroundFill);
		canvas.setBackground(background);

		Text rulesText = new Text("Place your bet on either the section labelled Player,"+
				"the section labelled Banker or the section labelled Tie.\r\n"+
				"The game is started by dealing two cards for the"+
				"player hand and two cards for the bank hand.\r\n"+
				"An initial hand with a value of 8 or 9 is called a natural.\r\n"+
				"If either hand is a natural, its holder must expose it and the game ends.\r\n"+
				"Otherwise play continues, first with the player hand and then with the banker hand.\r\n"+
				"The winning hand is the one with a total of 9 or as close to 9 as possible.\r\n\r\n" +
				"Rules for the player hand:\r\n" + "If the player's first two cards total 6 or more, then the player must stand without\r\n"+
				"drawing a card. If the player's first two cards total 5 or less, the player must draw one additional card.\r\n\r\n"+
				"Rules for the banker hand:\r\n" +"If the banker's first two cards total 7 or more, then the banker must stand without drawing a card.\r\n"+
				"If the banker's first two cards total 0, 1, or 2, then the banker must draw one card.\r\n"+
				"If the banker's first two cards total 3, 4, 5, or 6, then whether the banker draws is determined by the\r\n"+
				"whether the player drew, and if so the value of the player's draw card.");

        // Set alignment of the Text to the center of the BorderPane
        rulesText.setStyle("-fx-alignment: center; -fx-font-size: 1.3em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bodoni MT Black';");	

        // Add the Text to the BorderPane's center
        canvas.setCenter(rulesText);

		//Setting back button
		Button back = new Button("Back");
		back.setMinWidth(50);
		back.setMinHeight(20);
		back.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color:#FFFFFF; -fx-font-size: 2em; -fx-text-fill: #000000; -fx-font-family: 'Bodoni MT Black';");
		
		//Placing back button in HBox
		HBox back_button = new HBox(50);
		back_button.getChildren().addAll(back);
		back_button.setAlignment(Pos.BOTTOM_RIGHT);
		HBox.setMargin(back, new Insets(0,0,20, 20));
		canvas.setLeft(back_button);

		//Adding functionality to back button
		back.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				Scene mainScene = createMainScene();
				primaryStage.setScene(mainScene);
			}
		});

		return new Scene(canvas, 1000, 700);
	}

    // Validate if the input is an integer or a float with 2 decimal places
    private boolean isValidDollar(String currentText, String input) {
		String newText = currentText + input;
        // Regular expression to match an integer or a float with 2 decimal places
        return newText.matches("\\d*|\\d*\\.\\d|\\d*\\.\\d{0,2}");
    }

}