package st.pawel.mobilnyprzewodnik.common.util;


import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import st.pawel.mobilnyprzewodnik.BuildConfig;

public class ImageLoaderProvider {

    private static final int ONE_MB = 1024 * 1024;

    private static final int MEMORY_CACHE_SIZE = 2 * ONE_MB;

    private static final int DISK_CACHE_SIZE = 50 * ONE_MB;

    private static final int DISK_CACHE_FILE_COUNT = 100;

    private static ImageLoader imageLoader;

    public static ImageLoader newInstance(Context c) {
        if (imageLoader == null) {
            imageLoader = provideImageLoader(c);
        }
        return imageLoader;
    }

    static DisplayImageOptions.Builder provideDefaultDisplayImageOptionsBuilder() {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true);
    }

    static MemoryCache provideMemoryCache() {
        return new LruMemoryCache(MEMORY_CACHE_SIZE);
    }

    static DiskCache provideDiskCache(Context context) {
        return new UnlimitedDiskCache(context.getCacheDir());
    }


    static ImageLoaderConfiguration provideDefaultImageLoaderConfigurationWithMemory(Context context) {
        ImageLoaderConfiguration.Builder builder = provideImageLoaderConfigurationBuilder(context);
        builder.defaultDisplayImageOptions(provideDefaultDisplayImageOptionsBuilder().build());
        return builder.build();
    }

    static ImageLoaderConfiguration.Builder provideImageLoaderConfigurationBuilder(Context context) {
        final ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(context)
                .memoryCache(provideMemoryCache())
                .memoryCacheSize(MEMORY_CACHE_SIZE)
                .diskCache(provideDiskCache(context))
                .diskCacheSize(DISK_CACHE_SIZE)
                .diskCacheFileCount(DISK_CACHE_FILE_COUNT);
        if (BuildConfig.DEBUG) {
            builder.writeDebugLogs();
        }
        return builder;
    }

    static ImageLoader provideImageLoader(Context c) {
        final ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(provideDefaultImageLoaderConfigurationWithMemory(c));
        return imageLoader;
    }

    static DisplayImageOptions.Builder provideCircleDisplayImageOptionsBuilder() {
        return new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(1000))
                .cacheInMemory(true)
                .cacheOnDisk(true);
    }

    public static DisplayImageOptions provideCircleDisplayImageOptions(){
        return provideCircleDisplayImageOptionsBuilder().build();
    }
}
