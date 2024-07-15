package Validation;

import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class TextFieldValidation {
    public static EventHandler<KeyEvent> loginValidation(final Integer max_Length) {
        return e -> {
            TextField txt_TextField = (TextField) e.getSource();
            if (txt_TextField.getText().length() >= max_Length) {
                e.consume();
                return;
            }

            String character = e.getCharacter();
            String text = txt_TextField.getText();

            if (text.isEmpty() && !character.matches("[a-zA-Z]")) {
                e.consume();
                return;
            }

            if (!character.matches("[a-zA-Z0-9_]")) {
                e.consume();
            }
        };
    }
    public static EventHandler<KeyEvent> passwordValidation(final Integer max_Length) {
        return e -> {
            PasswordField txt_TextField = (PasswordField) e.getSource();
            if (txt_TextField.getText().length() >= max_Length) {
                e.consume();
                return;
            }

            String character = e.getCharacter();
            if (character.matches("\\s")) {
                e.consume(); // Ignoruj białe znaki
            }
        };
    }
    }


