package com.defterp.cdi;

import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.literal.InjectLiteral;
import jakarta.enterprise.inject.spi.*;
import jakarta.enterprise.inject.spi.configurator.AnnotatedFieldConfigurator;
import jakarta.inject.Named;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SpringAutowiredExtension implements Extension {

    private Map<Class<?>, String> autowiredFields = new ConcurrentHashMap<>();

    private static void injectBeanViaSpringContext(AfterBeanDiscovery event, Class<?> type, String name) {
        event.addBean().addType(type).createWith(ignoreCdiContext -> getBeanFromSpringContext(type, name));
    }

    private static Object getBeanFromSpringContext(Class<?> type, String name) {
        try {
            try {
                return Spring.getContext().getBean(type);
            } catch (NoUniqueBeanDefinitionException ignore) {
                return Spring.getContext().getBean(name);
            }
        } catch (Exception e) {
            throw new IllegalStateException("Cannot get bean from Spring context", e);
        }
    }

    public <T> void processAnnotatedType(@Observes @WithAnnotations(Named.class) ProcessAnnotatedType<T> processAnnotatedType, BeanManager beanManager) {
        Class<T> beanClass = processAnnotatedType.getAnnotatedType().getJavaClass();

        if (!beanClass.getPackage().getName().startsWith(YourSpringConfiguration.BEANS_PACKAGE)) {
            return; // This should filter out any CDI managed beans provided by e.g. Faces and OmniFaces themselves.
        }

        processAnnotatedType.configureAnnotatedType()
                .filterFields(field -> field.isAnnotationPresent(Autowired.class))
                .forEach(this::registerAutowiredField);
    }

    private void registerAutowiredField(AnnotatedFieldConfigurator<?> fieldConfigurator) {
        fieldConfigurator.add(InjectLiteral.INSTANCE);
        Field field = fieldConfigurator.getAnnotated().getJavaMember();
        autowiredFields.put(field.getType(), field.getName());
    }

    public void afterBeanDiscovery(@Observes AfterBeanDiscovery event) {
        autowiredFields.entrySet().forEach(autowiredField -> injectBeanViaSpringContext(event, autowiredField.getKey(), autowiredField.getValue()));
    }
}
