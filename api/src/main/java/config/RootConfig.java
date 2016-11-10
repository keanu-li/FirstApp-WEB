package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "me.firstapp.module", "me.firstapp.repository", "me.firstapp.service" })
public class RootConfig {

}
