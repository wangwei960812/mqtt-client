package com.ww.help;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @author ：黑洞里的光
 * @date ：Created in 2021/8/29 16:47
 * @description：提供service
 */
public class ServiceProvider {

    /**
     * 通过java spi机制提供服务
     * @param clzz
     * @param <T>
     * @return
     */
    public static <T> List<T> provideByJavaSpi(Class<T> clzz) {
        ServiceLoader<T> loaders = ServiceLoader.load(clzz);
        List<T> services = new ArrayList<>();
        for (T service : loaders) {
            services.add(service);
        }
        return services;
    }
}
