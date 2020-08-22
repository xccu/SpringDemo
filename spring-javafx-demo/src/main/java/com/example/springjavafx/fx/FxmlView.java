package com.example.springjavafx.fx;

import java.util.ResourceBundle;

public enum FxmlView {
    MAIN {
        @Override
		public String title() {
            return getStringFromResourceBundle("app.title");
        }

        @Override
		public String fxml() {
            return "views/main/main.fxml";
        }

    }, 
    MODULE_DASHBOARD {
        @Override
		public String title() {
            return getStringFromResourceBundle("module.dashboard.title");
        }

        @Override
		public String fxml() {
            return "views/module/dashboard.fxml";
        }

    },
    MODULE_PROFILE {
        @Override
		public String title() {
            return getStringFromResourceBundle("module.profile.title");
        }

        @Override
		public String fxml() {
            return "views/module/profile.fxml";
        }

    },
    MODULE_WEBVIEW {
        @Override
		public String title() {
            return getStringFromResourceBundle("module.webview.title");
        }

        @Override
		public String fxml() {
            return "views/module/webview.fxml";
        }

    };
	
    
    public abstract String title();
    public abstract String fxml();
    
    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
