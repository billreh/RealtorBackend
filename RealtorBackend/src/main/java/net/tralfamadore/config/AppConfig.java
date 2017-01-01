package net.tralfamadore.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * AppConfig - application configuration with base package scan.  Called from web.xml.
 * Created by Bill on 12/30/2016.
 */
@Configuration
@ComponentScan(basePackages = "net.tralfamadore")
public class AppConfig {
}
