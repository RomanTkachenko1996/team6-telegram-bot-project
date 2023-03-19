package edu.goit.feature.service.bot_service.bot_dto;


import edu.goit.feature.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettingsStorageDto {
    Currency currency;
    String bank;
    String digitsAfterDots;
    String timeForUpdates;
}
