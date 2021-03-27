package net.javaguides.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DBConfiguration {
    private String url;
    private String username;
    private String password;
    
    @Profile("dev")
    @Bean
    public String devDatabaseConnection() {
    	return "DB conection with localhost";
    }
    
    @Profile("prod")
    @Bean
    public String prodDatabaseConnection() {
		return "DB connection with docker";
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
