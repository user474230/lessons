package lesson20kt.configs

import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.stereotype.Component

@Component
class MyBeanPostProcessor : BeanPostProcessor  {
    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any? {
        // Бин перед вызовом init метода
        return super.postProcessBeforeInitialization(bean, beanName)
    }

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        // Бин после вызова init метода
        return super.postProcessAfterInitialization(bean, beanName)
    }
}