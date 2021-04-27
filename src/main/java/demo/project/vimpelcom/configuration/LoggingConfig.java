package demo.project.vimpelcom.configuration;

import demo.project.vimpelcom.filters.CustomLoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * "Необходимо обеспечить логирование работы API."
 *
 * На мой взгляд для логгирования http запросов не существует какого-то общепринятого, устраивающего всех инструмента.
 * Наиболее известный общепринятый инструмент:
 *      CommonsRequestLoggingFilter - он обеспечивает базовый логгинг, он может писать:
 *      - URI
 *      - Http Method
 *      - source ip
 *      Но, его сложно конфигурировать и он не пишет request/response payload
 *
 *
 * Поэтому, зачастую пишутся кастомные фильтры для логгинга.
 *      CustomLoggingFilter - кастомный фильтр, который можно отконфигурить по потребностям.
 *      Он подключается при помощи FilterChainProxy (обращаю внимание, что этот FilterChainProxy не имеет отношения к
 *      spring-security и подключается отдельно)
 * У кастомных решений есть свои недостатки, например если в дальнейшем обновить сервер (новая версия Jetty или другой сервер)
 * То фильтр может перестать работать и это нужно отслеживать.
 *
 * */
@Configuration
public class LoggingConfig  {

    @Bean
    @Profile("!custom_api_logging")
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(64000);
        return loggingFilter;
    }

    private static final CustomLoggingFilter LOGGING_FILTER = new CustomLoggingFilter(64000);

    @Bean
    @Profile("custom_api_logging")
    public FilterChainProxy loggingFilterChainProxy() {
        List<SecurityFilterChain> listOfFilterChains = new ArrayList<>();
        /**
         * Направляем кастомный логгер на URI, который необходимо логгировать.
         * */
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/**"), LOGGING_FILTER));

        return new FilterChainProxy(listOfFilterChains);
    }
}
