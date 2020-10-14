module martian.tones.banking {
	
	requires java.base;
	requires org.junit.jupiter.api;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.media;
	requires javafx.swing;
	requires javafx.web;
	
	exports banking.frontend;
	opens banking.frontend;
	  
}