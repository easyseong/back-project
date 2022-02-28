# back-project

https://github.com/SangHyunGil/SpringSecurityJWT-Local-OAuth2-EmailAuth-Redis

https://gilssang97.tistory.com/56?category=1048454



https://gilssang97.tistory.com/57?category=1048454 => REFRESH TOKEN 추가할 예정




2022-02-28 18:17:07.940 ERROR 12632 --- [nio-8080-exec-6] o.a.c.c.C.[.[.[.[dispatcherServlet]      : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception

org.springframework.security.core.userdetails.UsernameNotFoundException: test@naver.com
	at org.springframework.security.provisioning.InMemoryUserDetailsManager.loadUserByUsername(InMemoryUserDetailsManager.java:150) ~[spring-security-core-5.6.1.jar:5.6.1]
	at com.example.backproject.config.security.JwtTokenProvider.getAuthentication(JwtTokenProvider.java:47) ~[classes/:?]
	at com.example.backproject.config.security.JwtAuthenticationFilter.doFilter(JwtAuthenticationFilter.java:22) ~[classes/:?]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336) ~[spring-security-web-5.6.1.jar:5.6.1]
	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:103) ~[spring-security-web-5.6.1.jar:5.6.1]
	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:89) ~[spring-security-web-5.6.1.jar:5.6.1]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336) ~[spring-security-web-5.6.1.jar:5.6.1]
	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90) ~[spring-security-web-5.6.1.jar:5.6.1]
	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75) ~[spring-security-web-5.6.1.jar:5.6.1]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117) ~[spring-web-5.3.15.jar:5.3.15]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336) ~[spring-security-web-5.6.1.jar:5.6.1]
	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:110) ~[spring-security-web-5.6.1.jar:5.6.1]
	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:80) ~[spring-security-web-5.6.1.jar:5.6.1]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336) ~[spring-security-web-5.6.1.jar:5.6.1]
	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:55) ~[spring-security-web-5.6.1.jar:5.6.1]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117) ~[spring-web-5.3.15.jar:5.3.15]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336) ~[spring-security-web-5.6.1.jar:5.6.1]
	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:211) ~[spring-security-web-5.6.1.jar:5.6.1]
	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:183) ~[spring-security-web-5.6.1.jar:5.6.1]
	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:354) ~[spring-web-5.3.15.jar:5.3.15]
	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:267) ~[spring-web-5.3.15.jar:5.3.15]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189) ~[tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162) ~[tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100) ~[spring-web-5.3.15.jar:5.3.15]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117) ~[spring-web-5.3.15.jar:5.3.15]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189) ~[tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162) ~[tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93) ~[spring-web-5.3.15.jar:5.3.15]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117) ~[spring-web-5.3.15.jar:5.3.15]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189) ~[tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162) ~[tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201) ~[spring-web-5.3.15.jar:5.3.15]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117) ~[spring-web-5.3.15.jar:5.3.15]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189) ~[tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162) ~[tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:197) [tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97) [tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:540) [tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:135) [tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92) [tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78) [tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:357) [tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:382) [tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65) [tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:895) [tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1732) [tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) [tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191) [tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659) [tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) [tomcat-embed-core-9.0.56.jar:9.0.56]
	at java.lang.Thread.run(Thread.java:834) [?:?]
