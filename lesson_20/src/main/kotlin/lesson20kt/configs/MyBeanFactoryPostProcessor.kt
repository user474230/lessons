package lesson20kt.configs

import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.stereotype.Component

@Component
class MyBeanFactoryPostProcessor : BeanFactoryPostProcessor {
    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        for (name in beanFactory.beanDefinitionNames) {
            val beanDefinition = beanFactory.getBeanDefinition(name);
            println("$name - class name = ${beanDefinition.beanClassName}")
        }
    }
}