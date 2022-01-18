package com.example.itunesalbumsapp.presentation

import android.app.Application
import androidx.room.Room
import com.example.itunesalbumsapp.BuildConfig
import com.example.itunesalbumsapp.data.AppDatabase
import com.example.itunesalbumsapp.data.ItunesRetrofit
import com.example.itunesalbumsapp.data.impls.AlbumRepositoryImpl
import com.example.itunesalbumsapp.data.impls.SongRepositoryImpl
import com.example.itunesalbumsapp.domain.use_cases.*
import com.example.itunesalbumsapp.presentation.detail_screen.DetailScreenViewModel
import com.example.itunesalbumsapp.presentation.home_screen.HomeScreenViewModel
import com.example.itunesalbumsapp.presentation.utils.NetworkingChecker
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    localModule,
                    daosModule,
                    remoteModule,
                    implsModule,
                    useCasesModule,
                    viewModelModule
                )
            )
        }
    }

    private val localModule = module {
        single {
            Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                AppDatabase.DB_NAME
            ).build()
        }
    }

    private val daosModule = module {
        factory { get<AppDatabase>().getAlbumDao() }
        factory { get<AppDatabase>().getSongDao() }
    }

    private val remoteModule = module {
        single { ItunesRetrofit() }
        factory { NetworkingChecker(applicationContext) }
    }

    private val implsModule = module {
        factory { AlbumRepositoryImpl(get<ItunesRetrofit>().itunesApi, get()) }
        factory { SongRepositoryImpl(get<ItunesRetrofit>().itunesApi, get()) }
    }

    private val useCasesModule = module {
        factory { GetAlbumByIdUseCase(get<AlbumRepositoryImpl>()) }
        factory { GetAlbumListUseCase(get<AlbumRepositoryImpl>()) }
        factory { SearchAlbumListUseCase(get<AlbumRepositoryImpl>()) }
        factory { GetSongListByAlbumUseCase(get<SongRepositoryImpl>()) }
        factory { SearchSongListByAlbumUseCase(get<SongRepositoryImpl>()) }
    }

    private val viewModelModule = module {
        viewModel { HomeScreenViewModel(get(), get(), get()) }
        viewModel { DetailScreenViewModel(get(), get(), get(), get()) }
    }
}