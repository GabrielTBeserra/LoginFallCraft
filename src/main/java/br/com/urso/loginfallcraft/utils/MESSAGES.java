package br.com.urso.loginfallcraft.utils;

import br.com.urso.loginfallcraft.data.ConfigFile;

public class MESSAGES {
    public static String CONFIG_RELOADED;
    public static String LOGIN_SUCCESS;
    public static String LOGIN_FAILED;
    public static String PLAYER_UNREGISTER;
    public static String CHANGED_PASSWORD;
    public static String NEED_LOGIN;
    public static String NEED_REGISTER;
    public static String PASSWORD_INCORRECT;
    public static String LOGIN_ATTEMPTS;
    public static String REGISTER_SUCCESS;
    public static String PASSWORD_DONT_MATCH;
    public static String PLAYER_NOT_REGISTRED;
    public static String CHANGE_PASSWORD;
    public static String ALREADY_REGISTRED;
    public static String ALREADY_LOGGED;

    public static void loadMessages() {
        CONFIG_RELOADED = ConfigFile.getConfigFile().getString("messages.config_reloaded");
        LOGIN_SUCCESS = ConfigFile.getConfigFile().getString("messages.login_success");
        LOGIN_FAILED = ConfigFile.getConfigFile().getString("messages.login_failed");
        PLAYER_UNREGISTER = ConfigFile.getConfigFile().getString("messages.player_unregistred");
        CHANGED_PASSWORD = ConfigFile.getConfigFile().getString("messages.changed_password");
        NEED_LOGIN = ConfigFile.getConfigFile().getString("messages.need_login");
        NEED_REGISTER = ConfigFile.getConfigFile().getString("messages.need_register");
        PASSWORD_INCORRECT = ConfigFile.getConfigFile().getString("messages.password_incorrect");
        LOGIN_ATTEMPTS = ConfigFile.getConfigFile().getString("messages.login_attempts");
        REGISTER_SUCCESS = ConfigFile.getConfigFile().getString("messages.register_success");
        PASSWORD_DONT_MATCH = ConfigFile.getConfigFile().getString("messages.passwords_dont_match");
        PLAYER_NOT_REGISTRED = ConfigFile.getConfigFile().getString("messages.player_not_registred");
        CHANGE_PASSWORD = ConfigFile.getConfigFile().getString("messages.change_password");
        ALREADY_REGISTRED = ConfigFile.getConfigFile().getString("messages.already_registred");
        ALREADY_LOGGED = ConfigFile.getConfigFile().getString("messages.already_logged");
    }
}
