package com.example.weathertracker.ui.screens.search

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil3.ImageLoader
import coil3.compose.rememberAsyncImagePainter
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import com.example.weathertracker.R

@Composable
fun LoadImageURL(
    modifier: Modifier = Modifier,
    imageURL: String
) {
    val customImageLoader = provideCustomImageLoader(LocalContext.current)

    Image(
        modifier = modifier,
        painter = rememberAsyncImagePainter(
            model = imageURL,
            imageLoader = customImageLoader
        ),
        contentDescription = stringResource(R.string.search_icon_weather)
    )
}

private fun provideCustomImageLoader(ctx: Context): ImageLoader {
    return ImageLoader.Builder(ctx)
        .diskCache {
            DiskCache.Builder()
                .directory(ctx.cacheDir.resolve("image_cache"))
                .maxSizeBytes(50L * 1024 * 1024) // Set max cache size to 50MB
                .build()
        }
        .memoryCache {
            MemoryCache.Builder()
                .maxSizePercent(ctx, 0.25) // Use up to 25% of app's memory
                .build()
        }
        .build()
}