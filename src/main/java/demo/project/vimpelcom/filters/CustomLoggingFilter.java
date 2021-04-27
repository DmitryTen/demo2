package demo.project.vimpelcom.filters;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Кастомный фильтр для логгинга позволяет конфигурировать лог в широких пределах.
 *
 * Имеет недостаток:
 * Не может писать request payload для content-type: application/x-www-form-urlencoded && method: POST, это связано с
 * особенностями реализации Jetty 9.4.39 (на Jetty 9.4.4 работало исправно, где-то между 4..39 - сломалось)
 * */
public class CustomLoggingFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger( CustomLoggingFilter.class );

    private static final String FORM_URL_ENCODED = "application/x-www-form-urlencoded";
    private static final String MULTIPART_FORM_DATA = "multipart/form-data";

    private final int maxContentLength;

    public CustomLoggingFilter(int maxContentLength) {
        this.maxContentLength = maxContentLength;
    }

    public void init(FilterConfig fc) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;

            String contentType = httpServletRequest.getHeader("content-type");

            ContentCachingResponseWrapper wrappedResp = new ContentCachingResponseWrapper(httpServletResponse);
            /**
             * For "POST" && application/x-www-form-urlencoded the Jetty 9.4.39 consumes input stream during filling
             * the MultiMap therefore there is no way to log request payload BEFORE controller.
             * There is a possibility to log request payload only AFTER controller, then you need to use
             * ContentCachingRequestWrapper.getContentAsByteArray()
             * */
            if ("POST".equals(httpServletRequest.getMethod()) && FORM_URL_ENCODED.equals(contentType)) {
                ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(httpServletRequest);
                logRequest(wrappedRequest, false);
                chain.doFilter(wrappedRequest, wrappedResp);
                logResponse(wrappedResp);
            } else {
                int contentLength = request.getContentLength();
                if (contentLength > maxContentLength || MULTIPART_FORM_DATA.equals(contentType)) {
                    logRequest(httpServletRequest, false);
                    chain.doFilter(httpServletRequest, wrappedResp);
                } else {
                    MyHttpServletRequestWrapper wrappedRequest = new MyHttpServletRequestWrapper(httpServletRequest);
                    logRequest(wrappedRequest, true);
                    chain.doFilter(wrappedRequest, wrappedResp);
                }
                logResponse(wrappedResp);
            }
        }
    }

    private void logRequest(HttpServletRequest request, boolean isAbleToUsePayload) {
        try {
            int contentLength = request.getContentLength();

            if (isAbleToUsePayload) {
                String payload = IOUtils.toString(request.getInputStream());
                log.info("URI {}, query: {}, method {}, content-length: {}, payload '{}'", request.getRequestURI(), request.getQueryString(), request.getMethod(), contentLength, payload);
            } else {
                log.info("URI {}, query: {}, method {}, content-length: {}", request.getRequestURI(), request.getQueryString(), request.getMethod(), contentLength);
            }
        } catch (Exception e) {
            log.info("Exception", e);
        }
    }

    private void logResponse(ContentCachingResponseWrapper response) {
        try {
            log.info("resp content-length: {}, payload: '{}'", response.getContentSize(), new String(response.getContentAsByteArray()));
            response.copyBodyToResponse();
        } catch (Exception e) {
            log.info("Exception", e);
        }
    }
}
