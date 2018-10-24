package org.apereo.cas.util.http;

import org.apereo.cas.config.CasCoreUtilConfiguration;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;

import static org.junit.Assert.*;

/**
 * This is {@link HttpClientMultiThreadedDownloaderTests}.
 *
 * @author Misagh Moayyed
 * @since 5.1.0
 */
@SpringBootTest(
    classes = {
        AopAutoConfiguration.class,
        CasCoreUtilConfiguration.class})
@EnableScheduling
@ExtendWith(SpringExtension.class)
public class HttpClientMultiThreadedDownloaderTests {
    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void verify() throws Exception {
        val resource = resourceLoader.getResource("https://raw.githubusercontent.com/apereo/cas/master/NOTICE");
        val target = File.createTempFile("notice", ".md");
        val downloader = new HttpClientMultiThreadedDownloader(resource, target);
        downloader.download();
        assertTrue(target.exists());
    }
}
