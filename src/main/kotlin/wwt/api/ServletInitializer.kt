package wwt.api

import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import wwt.api.utils.logger

class ServletInitializer : SpringBootServletInitializer() {

    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
        val logger = logger()
        logger.info("ServletInitializer.configure")
        return application.sources(WwtapiApplication::class.java)
    }

}
