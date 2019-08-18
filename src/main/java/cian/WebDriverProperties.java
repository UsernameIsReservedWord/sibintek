package cian;

import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

import java.net.URL;

@Resource.Classpath("application.properties")
public class WebDriverProperties {

    @Property("app.server")
    private URL server;

    @Property("app.browser")
    private String browser;

    @Property("target.url")
    private String target;

    public WebDriverProperties() {
        PropertyLoader.populate(this);
    }

    public URL getServer() {
        return server;
    }

    public String getBrowser() {
        return browser;
    }

    public String getTarget() {
        return target;
    }
}
