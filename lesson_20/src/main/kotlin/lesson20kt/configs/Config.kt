package lesson20kt.configs

import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan

@ComponentScan(basePackages = ["lesson20kt"])
open class Config {
    @Bean
    open fun sessionFactory(): SessionFactory = Configuration().configure("hibernateKt.cfg.xml").buildSessionFactory()
}