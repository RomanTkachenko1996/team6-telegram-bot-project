package edu.goit.button;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;


public interface CreateButton {
    List<InlineKeyboardButton> createButton(String stringOnButton);
}
