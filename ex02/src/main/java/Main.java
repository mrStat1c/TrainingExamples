import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.time.Duration;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws InterruptedException {

//        создание кэш-менеджера, а также конфигурации дефолтного кэша
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("preConfiguredCache",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(
                                Integer.class, String.class, ResourcePoolsBuilder.heap(10)))
                .build();

        cacheManager.init();

//        создание дефолтного кэша
        Cache<Integer, String> preConfiguredCache =
                cacheManager.getCache("preConfiguredCache", Integer.class, String.class);

//        создание кэша, отличного от дефолтного (конфигурируется вместимость кэша и время жизни объекта в кэше)
        int cacheSize = 5;
        int lifeTime = 5;
        Cache<Integer, String> myCache = cacheManager.createCache("myCache",
                CacheConfigurationBuilder
                        .newCacheConfigurationBuilder(Integer.class, String.class, ResourcePoolsBuilder.heap(cacheSize))
                        .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(lifeTime))));

        myCache.put(1, "One");
        myCache.put(2, "Two");
        myCache.put(3, "Three");
        myCache.put(4, "Four");
        myCache.put(5, "Five");
        myCache.put(6, "Six");

        Set<Integer> keys = Set.of(1, 2, 3, 4, 5, 6);

        System.out.println("Print elements 1-6:");
        printCacheValues(myCache, keys);
        Thread.sleep(lifeTime * 1000);
        System.out.println("Print elements 1-6 after time expiring:");
        printCacheValues(myCache, keys);

        cacheManager.close();
    }

    private static <K, V> void printCacheValues(Cache<K, V> cache, Set<K> keys) {
        for (K key : keys) {
            V value = cache.get(key);
            if (value != null) {
                System.out.println("element " + key + " = " + value);
            } else {
                System.out.println("element " + key + " is absent");
            }
        }


    }
}